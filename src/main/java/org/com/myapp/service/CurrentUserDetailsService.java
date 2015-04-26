package org.com.myapp.service;

import java.util.List;

import org.com.myapp.entity.Role;
import org.com.myapp.model.CurrentUser;
import org.com.myapp.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticatedUserService authenticatedUserService;

	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		MyUser user = userService.getUserByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(" User not found !");

		} else {

			List<Role> roles = userService.getRoleByUserId(user.getIdUser());

			// create user principal
			CurrentUser currentUser = new CurrentUser(user,
					authenticatedUserService.getAuthorities(roles));

			return currentUser;
		}

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
