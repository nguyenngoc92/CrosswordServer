package org.com.myapp.service;

import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.entity.Subject;

public interface SubjectService {

	Subject getSubjectById(int id) throws DAOException;

	Subject add(Subject subject) throws DAOException;

	void delete(Subject subject) throws DAOException;

	List<Subject> getAllSubject() throws DAOException;
}
