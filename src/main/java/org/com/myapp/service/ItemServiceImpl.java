package org.com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.dao.ItemReposity;
import org.com.myapp.entity.Item;
import org.com.myapp.model.ItemData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemReposity itemReposity;

	public Item save(ItemData itemData) throws ServiceException {

		Item i = itemData.convertToItem();
		try {
			return itemReposity.save(i);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	public Item update(ItemData itemData) throws ServiceException {
		Item i = itemData.convertToItem();
		try {
			return itemReposity.update(i);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	public Item delete(ItemData item) {

		return null;
	}

	public ArrayList<Item> getListItemByMatchId(int id) throws ServiceException {

		try {
			return itemReposity.getListItemByMatchId(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}
	}

	public List<Item> getListItem(int begin, int length) {

		return null;
	}

	public void updateItemInfor(List<Integer> idList) throws ServiceException {

		try {
			for (int i : idList) {
				itemReposity.updateItemInfor(i);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}

	}

	public void setItemReposity(ItemReposity itemReposity) {
		this.itemReposity = itemReposity;
	}

}
