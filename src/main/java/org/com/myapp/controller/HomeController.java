package org.com.myapp.controller;

import org.com.myapp.entity.Match;
import org.com.myapp.service.MatchService;
import org.com.myapp.service.ServiceException;
import org.com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MatchService matchService;

	public String home() {
/*
		Role r = userService.getRoleByName("ROLE_USER");

		RegisterForm form = new RegisterForm();
		form.setUsername("Mr Ngoc");
		form.setEmail("nguyenngoc.hust@gmail.com");
		form.setPassword("from1to9");
		form.setRe_password("from1to9");

		MyUser user = userService.createUser(form);
		user.getRoles().add(r);
		userService.addUser(user);

		Role r2 = userService.getRoleByName("ROLE_ADMIN");

		RegisterForm form2 = new RegisterForm();
		form2.setUsername("Mr Admin");
		form2.setEmail("admin@gmail.com");
		form2.setPassword("from1to9");
		form2.setRe_password("from1to9");

		MyUser user2 = userService.createUser(form2);
		user2.getRoles().add(r2);
		userService.addUser(user2);*/

		return "SUCCESS";
	}
	
	@RequestMapping(value ="/match/")
	public Match test() throws ServiceException{
		
		return matchService.getMatchById(1);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}
	
	

}
