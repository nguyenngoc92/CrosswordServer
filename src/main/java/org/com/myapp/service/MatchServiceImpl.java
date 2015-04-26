package org.com.myapp.service;

import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.dao.MatchReposity;
import org.com.myapp.entity.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchReposity matchReposity;

	public List<Match> getAllMatchBySubject(int id) throws DAOException {

		return matchReposity.getAllMatchBySubject(id);
	}

	public Match getMatchById(int id) throws DAOException {

		return matchReposity.getMatchById(id);
	}

	public Match save(Match match) throws DAOException {

		return matchReposity.save(match);
	}

	public Match delete(Match match) throws DAOException {

		return matchReposity.delete(match);
	}
	
	

	public List<Match> getListMatchPage(int id, int limit) {

		
		
		return null;
	}

	public void setMatchReposity(MatchReposity matchReposity) {
		this.matchReposity = matchReposity;
	}

}
