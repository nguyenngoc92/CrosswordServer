package org.com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Item;
import org.com.myapp.model.ItemData;

public interface ItemService {
	Item save(ItemData item)  throws ServiceException;

	Item update(ItemData item)  throws ServiceException;

	Item delete(ItemData item)  throws ServiceException;

	ArrayList<Item> getListItemByMatchId(int id)  throws ServiceException;

	List<Item> getListItem(int begin, int length)  throws ServiceException;

	void updateItemInfor(List<Integer> idList)  throws ServiceException;
}
