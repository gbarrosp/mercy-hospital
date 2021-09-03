package br.com.mercy.mercybackend.dtos;

import lombok.Data;

@Data
public class DoctorDto {
	private Long id;
	
	private String name;

    private String phoneNumber;

    private String cpf;

    private String gender;
    
}
