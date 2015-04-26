package org.com.myapp.model;

import org.hibernate.validator.constraints.NotEmpty;

public class MatchForm {

	private int idSubject;
	private int idCompetion;
	private String title;

	public MatchForm() {

	}

	public int getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}

	public int getIdCompetion() {
		return idCompetion;
	}

	public void setIdCompetion(int idCompetion) {
		this.idCompetion = idCompetion;
	}

	@NotEmpty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
