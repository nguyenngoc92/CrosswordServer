package org.com.myapp.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private MyUser user;
	private List<GrantedAuthority> authorities;
	
	
	public CurrentUser(MyUser user, List<GrantedAuthority> authorities) {
		super(user.getUserName(), user.getPassword(), authorities);
		this.authorities = authorities;
		this.user = user;
		

	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}


	public int getId() {
		return user.getIdUser();
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}
	
	

}
