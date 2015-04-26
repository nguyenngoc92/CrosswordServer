package org.com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Role;
import org.com.myapp.model.CurrentUser;
import org.com.myapp.model.MyUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

	// convert from authorization entity to role of user
	public List<GrantedAuthority> getAuthorities(List<Role> auths) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role auth : auths) {
			authorities.add(new SimpleGrantedAuthority(auth.getRoleName()
					.toUpperCase()));
		}
		return authorities;
	}

	public CurrentUser createAuthentedUser(MyUser user,
			List<GrantedAuthority> auths) {

		CurrentUser currentUser = new CurrentUser(user, auths);
		return currentUser;
	}

	public boolean authenticated(MyUser user, List<Role> auths) {

		List<GrantedAuthority> authorities = getAuthorities(auths);
		CurrentUser currentUser = this.createAuthentedUser(user, authorities);

		System.out.println("Register: " + currentUser.getId());

		// set authentication here
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				currentUser, currentUser.getPassword(),
				currentUser.getAuthorities());

		// redirect into secured main page if authentication successful
		if (token.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(token);

			return true;
		}

		return false;
	}
}
