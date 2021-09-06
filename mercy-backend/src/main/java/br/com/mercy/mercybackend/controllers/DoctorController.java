package br.com.mercy.mercybackend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercy.mercybackend.dtos.DoctorDto;
import br.com.mercy.mercybackend.entities.DoctorEntity;
import br.com.mercy.mercybackend.response.Response;
import br.com.mercy.mercybackend.services.DoctorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

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
}
