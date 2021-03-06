package org.com.myapp.dao;

import java.util.List;

import org.com.myapp.entity.Subject;
import org.com.myapp.model.SubjectData;

public interface SubjectReposity {

	Subject getSubjectById(int id) throws DAOException;

	Subject add(Subject subject) throws DAOException;

	void delete(Subject subject) throws DAOException;

	List<Subject> getAllSubject() throws DAOException;

	List<SubjectData> getAllSubjectData() throws DAOException;


}
