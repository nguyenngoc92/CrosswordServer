package org.com.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.com.myapp.entity.Role;
import org.com.myapp.model.MyUser;
import org.com.myapp.model.RegisterForm;
import org.com.myapp.service.AuthenticatedUserService;
import org.com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticatedUserService authenticatedUserService;

	@Autowired
	@Qualifier("registerFormValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	private final String role_user = "ROLE_USER";

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String doRegister(@RequestBody @Valid RegisterForm registerForm,
			BindingResult result) {

		MyUser myUser = userService.createUser(registerForm);
		Role role = userService.getRoleByName(role_user);
		if (role != null) {

			myUser.getRoles().add(role);
			MyUser user = userService.addUser(myUser);

			List<Role> auths = new ArrayList<Role>();
			auths.addAll(myUser.getRoles());
			if (authenticatedUserService.authenticated(user, auths)) {
				
				return "SUCESS";
			}

		}

		return "FAIL";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}
