package com.tonyb650.authentication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Username is require")
	@Size(min=3, max=30, message="Username must be between 3 and 30 characters")
	private String userName;
	
	@NotBlank(message="Email is required!")
	@Email(message="Please entere a valid email!")
	private String email;
	
	@NotEmpty(message="Password is required!")
	@Size(min=8, max=128, message="Password length must be between 8 and 128 characters.")
	private String password;
	
	@Transient
	@NotEmpty(message="Confirm Password is Required!")
	@Size(min=8, max=128, message="Password length must be between 8 and 128 characters.")
	private String confirm;
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	
}
