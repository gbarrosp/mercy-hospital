package br.com.mercy.mercybackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercy.mercybackend.entities.DoctorEntity;
import br.com.mercy.mercybackend.response.Response;
import br.com.mercy.mercybackend.services.DoctorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping(value = "/all")
	public ResponseEntity<Response<List<DoctorEntity>>> getAllDoctors() {
		Response<List<DoctorEntity>> response = new Response<List<DoctorEntity>>();

		try {
			List<DoctorEntity> doctorsList = doctorService.getAllDoctors();

			response.setData(doctorsList);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

    @PostMapping(value = "/new")
	public ResponseEntity<Response<DoctorEntity>> newDoctor(@Valid @RequestBody DoctorEntity doctor) {
		Response<DoctorEntity> response = new Response<DoctorEntity>();

		try {
			DoctorEntity newDoctor = doctorService.newDoctor(doctor);

			response.setData(newDoctor);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
