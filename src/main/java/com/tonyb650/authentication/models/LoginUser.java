package com.tonyb650.authentication.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class LoginUser {

	@NotEmpty(message="Email is required!")
	private String email;
	
	@NotBlank(message="Password is required!")
	private String password;
	
	public LoginUser() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
