package br.com.mercy.mercybackend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercy.mercybackend.entities.PatientEntity;
import br.com.mercy.mercybackend.repositories.PatientRepository;
import br.com.mercy.mercybackend.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
    
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }
}
