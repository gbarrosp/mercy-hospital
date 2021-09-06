package br.com.mercy.mercybackend.dtos;

import java.time.Instant;

import lombok.Data;

@Data
public class AuthenticatedUser {
	
	private String token;
	
	private String username;

	private Instant expirationDate;
	
}
