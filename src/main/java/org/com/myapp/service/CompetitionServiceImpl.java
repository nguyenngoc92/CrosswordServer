package org.com.myapp.service;

import java.util.Date;
import java.util.List;

import org.com.myapp.dao.CompetitionReposity;
import org.com.myapp.dao.DAOException;
import org.com.myapp.entity.Competition;
import org.com.myapp.model.CompetitionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionServiceImpl implements CompetitionService {

	@Autowired
	private CompetitionReposity competitionReposity;

	public Competition findCompetitionById(int id) throws ServiceException {
		try {

			return competitionReposity.findCompetitionById(id);
		} catch (DAOException e) {
			throw new ServiceException("Database error", e);
		}
	}

	public Competition saveOrUpdate(Competition competition)
			throws ServiceException {
		try {

			return competitionReposity.saveOrUpdate(competition);
		} catch (DAOException e) {
			throw new ServiceException("Database error", e);
		}
	}

	public void delete(Competition competition) throws ServiceException {
		try {

			competitionReposity.delete(competition);

		} catch (DAOException e) {
			throw new ServiceException("Database error", e);
		}
	}

	public List<Competition> getAllCompetition() throws ServiceException {

		try {

			return competitionReposity.getAllCompetition();
		} catch (DAOException e) {
			throw new ServiceException("Database error", e);
		}
	}

	public CompetitionData getCurrentCompetition() throws ServiceException {

		try {

			Competition competition = competitionReposity
					.getCurrentCompetition(new Date());

			if (competition != null) {
				CompetitionData competitionData = new CompetitionData(
						competition.getName(), competition.getNote(),
						competition.getBegin(), competition.getEnd(),
						competition.getCreateDate());
				competitionData
						.setIdCompetition(competition.getIdCompetition());
				return competitionData;
			}
			return null;
		} catch (DAOException e) {
			throw new ServiceException("Database error", e);
		}
	}

	public void setCompetitionReposity(CompetitionReposity competitionReposity) {
		this.competitionReposity = competitionReposity;
	}

}
