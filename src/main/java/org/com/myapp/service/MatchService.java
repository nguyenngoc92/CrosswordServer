package org.com.myapp.service;

import java.util.List;

import org.com.myapp.entity.Match;
import org.com.myapp.model.MatchData;

public interface MatchService {

	List<Match> getAllMatchBySubject(int id) throws ServiceException;

	Match getMatchById(int id) throws ServiceException;

	Match save(Match match) throws ServiceException;

	Match delete(Match match) throws ServiceException;

	List<Match> getListMatchPage(int id, int limit) throws ServiceException;

	MatchData getMatchNextBySubjectAndUser(int idSubject, int idUser)
			throws ServiceException;

	Match update(Match match) throws ServiceException;
}
