package org.com.myapp.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {

	private String username;
	private String email;
	private String password;
	private String re_password;

	@NotEmpty
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty
	@Min(value = 8, message = "At least 8 letter")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty
	public String getRe_password() {
		return re_password;
	}

	public void setRe_password(String re_password) {
		this.re_password = re_password;
	}

}
