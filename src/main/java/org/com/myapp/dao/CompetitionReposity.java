package org.com.myapp.dao;

import java.util.Date;
import java.util.List;

import org.com.myapp.entity.Competition;

public interface CompetitionReposity {

	Competition findCompetitionById(int id) throws DAOException;

	Competition saveOrUpdate(Competition competition) throws DAOException;

	void delete(Competition competition) throws DAOException;

	List<Competition> getAllCompetition() throws DAOException;

	Competition getCurrentCompetition(Date date) throws DAOException;

	Competition getLastCompetition() throws DAOException;
}
