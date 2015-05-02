package org.com.myapp.controller;

import java.security.Principal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class AuthserverApplication {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
