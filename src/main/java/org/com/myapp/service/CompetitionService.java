package org.com.myapp.service;

import java.util.List;

import org.com.myapp.entity.Competition;
import org.com.myapp.model.CompetitionData;

public interface CompetitionService {

	Competition findCompetitionById(int id) throws ServiceException;

	Competition saveOrUpdate(Competition competition) throws ServiceException;

	void delete(Competition competition) throws ServiceException;

	List<Competition> getAllCompetition() throws ServiceException;

	CompetitionData getCurrentCompetition() throws ServiceException;
}
