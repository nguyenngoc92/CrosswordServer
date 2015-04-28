package org.com.myapp.service;

import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.dao.ItemReposity;
import org.com.myapp.dao.MatchReposity;
import org.com.myapp.dao.SubjectReposity;
import org.com.myapp.entity.Subject;
import org.com.myapp.model.SubjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectReposity subjectReposity;

	@Autowired
	private MatchReposity matchReposity;

	@Autowired
	private ItemReposity itemReposity;

	public Subject getSubjectById(int id) throws ServiceException {

		try {
			Subject subject = subjectReposity.getSubjectById(id);
			return subject;

		} catch (DAOException e) {

			throw new ServiceException("Database error", e);
		}

	}

	public Subject add(Subject subject) throws ServiceException {

		try {
			return subjectReposity.add(subject);
		} catch (DAOException e) {

			throw new ServiceException("Database error", e);
		}
	}

	public void delete(Subject subject) throws ServiceException {

		try {
			subjectReposity.delete(subject);
		} catch (DAOException e) {

			throw new ServiceException("Database error", e);
		}

	}

	public List<Subject> getAllSubject() throws ServiceException {

		try {
			return subjectReposity.getAllSubject();
		} catch (DAOException e) {

			throw new ServiceException("Database error", e);
		}

	}

	public List<SubjectData> getAllSubjectData() throws ServiceException {

		try {
			return subjectReposity.getAllSubjectData();
		} catch (DAOException e) {

			throw new ServiceException("Database error", e);
		}
	}



	public void setSubjectReposity(SubjectReposity subjectReposity) {
		this.subjectReposity = subjectReposity;
	}

	public void setMatchReposity(MatchReposity matchReposity) {
		this.matchReposity = matchReposity;
	}

	public void setItemReposity(ItemReposity itemReposity) {
		this.itemReposity = itemReposity;
	}

}
