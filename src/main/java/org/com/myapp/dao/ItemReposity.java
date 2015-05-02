package org.com.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Item;

public interface ItemReposity {

	Item save(Item item) throws DAOException;

	Item update(Item item) throws DAOException;

	Item delete(Item item) throws DAOException;

	ArrayList<Item> getListItemByMatchId(int id) throws DAOException;

	List<Item> getListItem(int begin, int length) throws DAOException;

	void updateItemInfor(int id) throws DAOException;
}
