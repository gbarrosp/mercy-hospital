package br.com.mercy.mercybackend.services;

import java.util.List;

import br.com.mercy.mercybackend.entities.DoctorEntity;

public interface DoctorService {
    List<DoctorEntity> getAllDoctors();

    Boolean existsByCpf(String cpf);

    DoctorEntity newDoctor(DoctorEntity doctor);
}
