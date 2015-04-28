package org.com.myapp.service;

import java.util.List;

import org.com.myapp.entity.Subject;
import org.com.myapp.model.SubjectData;

public interface SubjectService {

	Subject getSubjectById(int id) throws ServiceException;

	Subject add(Subject subject) throws ServiceException;

	void delete(Subject subject) throws ServiceException;

	List<Subject> getAllSubject() throws ServiceException;

	List<SubjectData> getAllSubjectData() throws ServiceException;
}
