package br.com.mercy.mercybackend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class PatientEntity {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private DoctorEntity doctor;

    @NotEmpty
	@NotBlank
	@Size(min = 6, max = 50)
	private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String cpf;

}