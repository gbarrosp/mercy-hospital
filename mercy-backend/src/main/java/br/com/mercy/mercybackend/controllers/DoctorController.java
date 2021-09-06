package br.com.mercy.mercybackend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercy.mercybackend.dtos.DoctorDto;
import br.com.mercy.mercybackend.entities.DoctorEntity;
import br.com.mercy.mercybackend.entities.UserEntity;
import br.com.mercy.mercybackend.response.Response;
import br.com.mercy.mercybackend.services.DoctorService;
import br.com.mercy.mercybackend.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;
	
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/all")
	public ResponseEntity<Response<List<DoctorDto>>> getAllDoctors() {
		Response<List<DoctorDto>> response = new Response<List<DoctorDto>>();

		try {
			List<DoctorEntity> doctorsList = doctorService.getAllDoctors();
			List<DoctorDto> doctorsDtos = doctorsList.stream().map(doctor -> modelMapper.map(doctor, DoctorDto.class)).collect(Collectors.toList());

			response.setData(doctorsDtos);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

    @PostMapping(value = "/new")
	public ResponseEntity<Response<DoctorEntity>> newDoctor(@Valid @RequestBody DoctorEntity doctor) {
		Response<DoctorEntity> response = new Response<DoctorEntity>();

		try {
			if(userService.existsByUsername(doctor.getUser().getUsername())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
			}
	 
			UserEntity newUser = modelMapper.map(doctor.getUser(), UserEntity.class);
			newUser.setPassword(encoder.encode(doctor.getUser().getPassword()));
			userService.saveUser(newUser);

			DoctorEntity newDoctor = doctorService.newDoctor(doctor);

			response.setData(newDoctor);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
