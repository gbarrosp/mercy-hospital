package br.com.mercy.mercybackend.controllers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercy.mercybackend.dtos.AuthenticatedUser;
import br.com.mercy.mercybackend.dtos.LoginFormDTO;
import br.com.mercy.mercybackend.entities.DoctorEntity;
import br.com.mercy.mercybackend.entities.UserEntity;
import br.com.mercy.mercybackend.response.Response;
import br.com.mercy.mercybackend.security.JwtProvider;
import br.com.mercy.mercybackend.services.DoctorService;
import br.com.mercy.mercybackend.services.UserService;
import br.com.mercy.mercybackend.util.ExceptionTreatment;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthAPIController {

    private static final Logger log = LoggerFactory.getLogger(AuthAPIController.class);
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
    AuthenticationManager authenticationManager;
 
    @Autowired
    private UserService userService;
 
    @Autowired
    private DoctorService doctorService;
  
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;

    @Value("${mercy.app.jwtExpiration}")
    private int jwtExpiration;
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginFormDTO loginRequest) {
    	Response<AuthenticatedUser> response = new Response<AuthenticatedUser>();
        
        try {
            
            UserEntity user = userService.findByUsername(loginRequest.getUsername()).get();
            AuthenticatedUser authUser = createAuthUser(loginRequest.getUsername(), loginRequest.getPassword());
            authUser.setUsername(user.getUsername());
            response.setData(authUser);
            log.debug("User has logged in successfully!");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ExceptionTreatment.setExceptionMessage("Error while signing in. ", e, response, log);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping(value = "/sign-up")
	public ResponseEntity<Response<AuthenticatedUser>> newDoctor(@Valid @RequestBody DoctorEntity doctor) {
		Response<AuthenticatedUser> response = new Response<AuthenticatedUser>();

		try {
			if(userService.existsByUsername(doctor.getUser().getUsername())) {
                response.setError("Username not available");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			}

			if(doctorService.existsByCpf(doctor.getCpf())) {
                response.setError("Cpf not available");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			}
            
            String decodedPassword = doctor.getUser().getPassword();
			UserEntity newUser = modelMapper.map(doctor.getUser(), UserEntity.class);
			newUser.setPassword(encoder.encode(doctor.getUser().getPassword()));
			userService.saveUser(newUser);
			DoctorEntity newDoctor = doctorService.newDoctor(doctor);

            AuthenticatedUser authUser = createAuthUser(doctor.getUser().getUsername(), decodedPassword);
            authUser.setUsername(newDoctor.getUser().getUsername());
            response.setData(authUser);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
 
    private AuthenticatedUser createAuthUser(String username, String password){
        AuthenticatedUser authUser = new AuthenticatedUser();
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username,password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
                
        authUser.setToken(jwt);
        Instant instant = Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS);
        authUser.setExpirationDate(instant);

        return authUser;
    }
}



