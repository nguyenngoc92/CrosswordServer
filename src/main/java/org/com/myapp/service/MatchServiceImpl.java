package org.com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.dao.ItemReposity;
import org.com.myapp.dao.MatchReposity;
import org.com.myapp.entity.Item;
import org.com.myapp.entity.Match;
import org.com.myapp.model.ItemData;
import org.com.myapp.model.MatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchReposity matchReposity;
	@Autowired
	private ItemReposity itemReposity;

	public List<Match> getAllMatchBySubject(int id) throws ServiceException {

		try {
			return matchReposity.getAllMatchBySubject(id);
		} catch (DAOException e) {

			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	public Match getMatchById(int id) throws ServiceException {

		try {
			return matchReposity.getMatchById(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	public Match save(Match match) throws ServiceException {

		try {
			return matchReposity.save(match);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	public Match delete(Match match) throws ServiceException {

		try {
			return matchReposity.delete(match);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	public List<Match> getListMatchPage(int id, int limit) {

		return null;
	}

	public MatchData getMatchNextBySubjectAndUser(int idSubject, int idUser)
			throws ServiceException {

		Match match;
		try {
			match = matchReposity.getMatchBySubjectAndUser(idSubject, idUser);
			MatchData data = new MatchData();
			if (match == null)
				return data;

			data = convertToMatchData(match);

			List<Item> items = itemReposity.getListItemByMatchId(match
					.getIdMatch());

			data.setItems(convertToItemData(items));

			return data;

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}

	}

	public MatchData getMatchNextByCompetitionAndUser(int idCompetition,
			int idUser) throws ServiceException {

		Match match;
		try {
			match = matchReposity.getMatchByCompetitionAndUser(idCompetition,
					idUser);
			MatchData data = new MatchData();
			if (match == null)
				return data;

			data = convertToMatchData(match);

			List<Item> items = itemReposity.getListItemByMatchId(match
					.getIdMatch());

			data.setItems(convertToItemData(items));

			return data;

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	private ArrayList<ItemData> convertToItemData(List<Item> items) {

		ArrayList<ItemData> itemDatas = new ArrayList<ItemData>();

		for (Item i : items) {
			itemDatas.add(new ItemData(i));
		}
		return itemDatas;
	}

	private MatchData convertToMatchData(Match match) {

		MatchData data = new MatchData();
		data.setId(match.getIdMatch());
		data.setTitle(match.getTitle());

		if (match.getSubject() != null) {
			data.setIdSubject(match.getSubject().getIdSubject());
		}

		if (match.getCompetition() != null) {
			data.setCompetition(match.getCompetition().getIdCompetition());
		}

		return data;
	}

	public Match update(Match match) throws ServiceException {
		try {
			return matchReposity.save(match);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	public void setMatchReposity(MatchReposity matchReposity) {
		this.matchReposity = matchReposity;
	}

	public void setItemReposity(ItemReposity itemReposity) {
		this.itemReposity = itemReposity;
	}

}
