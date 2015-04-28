package org.com.myapp.config;

import org.com.myapp.controller.AccessConfirmationController;
import org.com.myapp.service.CurrentUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static PasswordEncoder encoder;

	@Autowired
	private CurrentUserDetailsService currentUserDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.userDetailsService(currentUserDetailsService).passwordEncoder(
				passwordEncoder());

		auth.inMemoryAuthentication().withUser("user@gmail.com")
				.password("user").roles("USER");
		auth.inMemoryAuthentication().withUser("admin@gmail.com")
				.password("admin").roles("AMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/public/", "/register")
				.permitAll().antMatchers("/user/**").hasAuthority("ROLE_USER")
				.and().formLogin()
				.loginPage("/login").failureUrl("/login?error=1")
				.usernameParameter("email").passwordParameter("password")
				.permitAll().and().logout().logoutUrl("/logout")
				.deleteCookies("remember-me").logoutSuccessUrl("/").permitAll()
				.and().rememberMe();
		http.httpBasic();
		http.csrf().disable();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		if (encoder == null) {
			encoder = new BCryptPasswordEncoder();
		}

		return encoder;
	}

	public void setCurrentUserDetailsService(
			CurrentUserDetailsService currentUserDetailsService) {
		this.currentUserDetailsService = currentUserDetailsService;
	}

	@Bean
	public AccessConfirmationController accessConfirmationController(
			ClientDetailsService clientDetailsService,
			ApprovalStore approvalStore) {
		AccessConfirmationController accessConfirmationController = new AccessConfirmationController();
		accessConfirmationController
				.setClientDetailsService(clientDetailsService);
		accessConfirmationController.setApprovalStore(approvalStore);
		return accessConfirmationController;
	}


}
