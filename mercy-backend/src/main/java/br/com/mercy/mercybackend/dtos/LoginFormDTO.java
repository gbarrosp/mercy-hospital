package br.com.mercy.mercybackend.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginFormDTO {

	@NotBlank
    private String username;
 
    @NotBlank
    @Size(min = 6, max = 40, message="Password must be a minimum of 6 and a maximum of 40 characters")
    private String password;
 
}
