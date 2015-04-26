package org.com.myapp.service;

import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.dao.SubjectReposity;
import org.com.myapp.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectReposity subjectReposity;

	public Subject getSubjectById(int id) throws DAOException {

		return subjectReposity.getSubjectById(id);
	}

	public Subject add(Subject subject) throws DAOException {

		return subjectReposity.add(subject);
	}

	public void delete(Subject subject) throws DAOException {

		subjectReposity.delete(subject);
	}

	public List<Subject> getAllSubject() throws DAOException {

		return subjectReposity.getAllSubject();
	}

	public void setSubjectReposity(SubjectReposity subjectReposity) {
		this.subjectReposity = subjectReposity;
	}

}
