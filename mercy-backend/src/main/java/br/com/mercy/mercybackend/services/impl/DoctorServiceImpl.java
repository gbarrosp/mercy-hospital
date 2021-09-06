package br.com.mercy.mercybackend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercy.mercybackend.entities.DoctorEntity;
import br.com.mercy.mercybackend.repositories.DoctorRepository;
import br.com.mercy.mercybackend.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public DoctorEntity newDoctor(DoctorEntity doctor) {
        return doctorRepository.save(doctor);
    }

}
