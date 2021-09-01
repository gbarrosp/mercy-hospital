package br.com.mercy.mercybackend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercy.mercybackend.entities.PatientEntity;
import br.com.mercy.mercybackend.repositories.AddressRepository;
import br.com.mercy.mercybackend.repositories.PatientRepository;
import br.com.mercy.mercybackend.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
    
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public PatientEntity newPatient(PatientEntity patient) {
        addressRepository.save(patient.getAddress());
        return patientRepository.save(patient);
    }

    @Override
    public PatientEntity editPatient(PatientEntity patient) {
        addressRepository.save(patient.getAddress());
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(PatientEntity patient) {
        addressRepository.delete(patient.getAddress());
        patientRepository.delete(patient);
    }
}
