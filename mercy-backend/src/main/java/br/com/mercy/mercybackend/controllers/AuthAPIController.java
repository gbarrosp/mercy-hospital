package br.com.mercy.mercybackend.controllers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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
import br.com.mercy.mercybackend.entities.UserEntity;
import br.com.mercy.mercybackend.repositories.UserRepository;
import br.com.mercy.mercybackend.response.Response;
import br.com.mercy.mercybackend.security.JwtProvider;
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
    UserRepository userRepository;
  
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwtProvider;

    @Value("${mercy.app.jwtExpiration}")
    private int jwtExpiration;
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginFormDTO loginRequest) {
    	Response<AuthenticatedUser> response = new Response<AuthenticatedUser>();
    	AuthenticatedUser authUser = new AuthenticatedUser();
        
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateJwtToken(authentication);
            
            UserEntity user = userRepository.findByUsername(loginRequest.getUsername()).get();
            authUser = modelMapper.map(user, AuthenticatedUser.class);
            
            authUser.setToken(jwt);
            Instant instant = Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS);
            authUser.setExpirationDate(instant);
            
            response.setData(authUser);
            log.debug("User has logged in successfully!");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ExceptionTreatment.setExceptionMessage("Error while signing in. ", e, response, log);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
 
    // @PostMapping("/signup")
    // public ResponseEntity<Response<UserEntity>> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
    //     Response<UserEntity> response = new Response<UserEntity>();

    //     if(userRepository.existsByUsername(signUpRequest.getUsername())) {
    //         log.debug("An error occured while signing up");
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    //     }
 
    //     if(userRepository.existsByEmail(signUpRequest.getEmail())) {
    //         log.debug("An error occured while signing up");
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    //     }
    //     try {
    //         UserEntity newUser = modelMapper.map(signUpRequest, UserEntity.class);
    //         newUser.setPassword(encoder.encode(signUpRequest.getPassword()));

    //         userRepository.save(newUser);
    //         response.setData(newUser);
    //         log.debug("UserEntity registered successfully!");
    //         return ResponseEntity.ok(response);
    //     } catch (Exception e) {
    //         ExceptionTreatment.setExceptionMessage("Error while signing up. ", e, response, log);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    //     }
    // }

}



