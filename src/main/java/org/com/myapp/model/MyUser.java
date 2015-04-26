package org.com.myapp.model;

import org.com.myapp.entity.User;

@SuppressWarnings("serial")
public class MyUser extends User {

	private String password;

	public MyUser() {
		super();
	}

	public MyUser(String username, String email, String password) {
		super(username, email);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
