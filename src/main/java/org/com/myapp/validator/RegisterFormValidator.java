package org.com.myapp.validator;

import org.com.myapp.model.RegisterForm;
import org.com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Qualifier("registerFormValidator")
public class RegisterFormValidator implements Validator {

	@Autowired
	private UserService userService;

	public boolean supports(Class<?> clazz) {
		return clazz.equals(RegisterForm.class);
	}

	public void validate(Object target, Errors errors) {

		RegisterForm form = (RegisterForm) target;
		validatePasswords(errors, form);
		validateEmail(errors, form);
	}

	private void validatePasswords(Errors errors, RegisterForm form) {
		if (!form.getPassword().equals(form.getRe_password())) {
			errors.reject("password.no_match", "Passwords do not match");
		}
	}

	private void validateEmail(Errors errors, RegisterForm form) {
		if (userService.findUserByEmail(form.getEmail()) != null) {
			errors.reject("email.exist", "User with this email already exists");
			
		}
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
