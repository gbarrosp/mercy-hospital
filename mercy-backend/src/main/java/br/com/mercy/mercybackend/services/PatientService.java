package br.com.mercy.mercybackend.services;

import java.util.List;

import br.com.mercy.mercybackend.entities.PatientEntity;

public interface PatientService {

    List<PatientEntity> getAllPatients();

    PatientEntity newPatient(PatientEntity patient);

    PatientEntity editPatient(PatientEntity patient);

    void deletePatient(PatientEntity patient);
}
