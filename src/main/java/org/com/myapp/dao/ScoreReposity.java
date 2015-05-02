package org.com.myapp.dao;

import org.com.myapp.entity.Score;

public interface ScoreReposity {
	void save(Score score) throws DAOException;
}
