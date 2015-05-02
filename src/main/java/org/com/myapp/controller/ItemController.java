package org.com.myapp.controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.validation.Valid;

import org.com.myapp.entity.Item;
import org.com.myapp.entity.Match;
import org.com.myapp.model.ItemData;
import org.com.myapp.model.MatchItemForm;
import org.com.myapp.service.ItemService;
import org.com.myapp.service.MatchService;
import org.com.myapp.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private MatchService matchService;

	@RequestMapping(value = "/admin/manager/level/match/{id}/item", method = RequestMethod.GET)
	private String getListItemByMatch(@PathVariable("id") int id, Model model)
			throws ServiceException {

		Match match = matchService.getMatchById(id);
		ArrayList<Item> itemList = new ArrayList<Item>();
		if (match != null) {
			itemList.addAll(match.getItems());
			Collections.sort(itemList, new Item());

		}
		model.addAttribute("match", match);
		model.addAttribute("itemList", itemList);

		return "item/index";
	}

	@RequestMapping(value = "/admin/manager/level/match/{id}/item/save", method = RequestMethod.GET)
	private String createOrUpdateItemForm(Model model,
			@PathVariable("id") int id) {

		model.addAttribute("item", new ItemData());
		model.addAttribute("id", id);
		return "item/save";
	}

	@RequestMapping(value = "/admin/manager/level/match/item/save", method = RequestMethod.POST)
	private String createOrUpdateItemHandle(
			@ModelAttribute("item") @Valid ItemData item, BindingResult result)
			throws ServiceException {

		if (result.hasErrors()) {

			return "item/save";
		}
		if (item.getId() != null) {

			itemService.update(item);
		} else {

			Match match = matchService.getMatchById(item.getIdmatch());

			if (match == null) {

				return "redirect:/admin/manager";
			}
			Item newItem = itemService.save(item);
			match.getItems().add(newItem);
			matchService.update(match);
		}

		return "redirect:/admin/manager/level/match/" + item.getIdmatch()
				+ "/item";
	}


	@RequestMapping(value = "/user/match/updateItemInfor", method = RequestMethod.POST)
	public ResponseEntity<String> updateItemInfor(
			@RequestBody MatchItemForm matchItem) throws ServiceException {

		if (matchItem != null) {
			itemService.updateItemInfor(matchItem.getIdList());
			System.out.println("OK");
			return new ResponseEntity<String>(HttpStatus.OK);
		} else
			System.out.println("OK");
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

}
