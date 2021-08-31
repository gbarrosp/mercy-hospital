package br.com.mercy.mercybackend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "doctor")
public class DoctorEntity {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

    @NotEmpty
	@NotBlank
	@Size(min = 6, max = 50)
	private String name;

	@NotEmpty
	@NotBlank
	@Size(min = 6, max = 50)
	private String username;

	@NotEmpty
	@NotBlank
	@Size(min = 6, max = 100)
	private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String cpf;

    private String gender;
}
