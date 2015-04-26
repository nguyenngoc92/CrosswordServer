package org.com.myapp.service;

import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.entity.Match;

public interface MatchService {

	List<Match> getAllMatchBySubject(int id) throws DAOException;

	Match getMatchById(int id) throws DAOException;

	Match save(Match match) throws DAOException;

	Match delete(Match match) throws DAOException;

	List<Match> getListMatchPage(int id, int limit) throws DAOException;
}
