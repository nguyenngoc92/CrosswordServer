package org.com.myapp.validator;

import org.com.myapp.model.MatchForm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Qualifier("matchFormValidator")
public class MatchFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {

		return clazz.equals(MatchForm.class);
	}

	public void validate(Object target, Errors errors) {

		MatchForm form = (MatchForm) target;

		if (form.getIdSubject() == 0 && form.getIdCompetion() == 0) {
			errors.reject("ID", "You must have subject or competition");
		}

	}

}
