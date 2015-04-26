package org.com.myapp.dao;

import java.util.List;

import org.com.myapp.entity.Match;

public interface MatchReposity {

	Match getMatchById(int id) throws DAOException;

	List<Match> getAllMatchBySubject(int id) throws DAOException;

	Match save(Match match) throws DAOException;

	Match delete(Match match) throws DAOException;

	List<Match> getListMatchPaging(int id, int lenght)
			throws DAOException;
}
