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
@Table(name = "address")
public class AddressEntity {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "zip_code")
    private String zipCode;

	@Column(name = "street_name")
    private String streetName;

    private Integer number;

	@Column(name = "additional_info")
    private String additionalInfo;

    private String neighborhood;

    private String city;

    private String state;

}
