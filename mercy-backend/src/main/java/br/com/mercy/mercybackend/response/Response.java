package br.com.mercy.mercybackend.response;

import lombok.Data;

@Data
public class Response<T> {

	private T data;

	private String error;

}
