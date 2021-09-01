package br.com.mercy.mercybackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercy.mercybackend.entities.PatientEntity;
import br.com.mercy.mercybackend.response.Response;
import br.com.mercy.mercybackend.services.PatientService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/all")
	public ResponseEntity<Response<List<PatientEntity>>> getAllPatients() {
		Response<List<PatientEntity>> response = new Response<List<PatientEntity>>();

		try {
			List<PatientEntity> patientsList = patientService.getAllPatients();

			response.setData(patientsList);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

    @PostMapping(value = "/new")
	public ResponseEntity<Response<PatientEntity>> newPatient(@RequestBody @Valid PatientEntity patient) {
		Response<PatientEntity> response = new Response<PatientEntity>();

		try {
			PatientEntity newPatient = patientService.newPatient(patient);

			response.setData(newPatient);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

    @PatchMapping(value = "/edit")
	public ResponseEntity<Response<PatientEntity>> editPatient(@RequestBody @Valid PatientEntity patient) {
		Response<PatientEntity> response = new Response<PatientEntity>();

		try {
			PatientEntity editedPatient = patientService.editPatient(patient);

			response.setData(editedPatient);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

    @DeleteMapping(value = "/delete")
	public ResponseEntity<Response<PatientEntity>> deletePatiente(@RequestBody @Valid PatientEntity patient) {
		Response<PatientEntity> response = new Response<PatientEntity>();

		try {
			patientService.deletePatient(patient);

			response.setData(patient);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
