package org.com.myapp.controller;

import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.entity.Match;
import org.com.myapp.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

	@Autowired
	private MatchService matchService;

	@Autowired
	@Qualifier("matchFormValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/admin/subject/{id}/match", method = RequestMethod.GET)
	@ResponseBody
	public List<Match> getAllMatchBySubjectId(@PathVariable int id) throws DAOException {

		return matchService.getAllMatchBySubject(id);
	}

	@RequestMapping(value = "/admin/match/{id}/{limit}", method = RequestMethod.GET)
	@ResponseBody
	public List<Match> getMatchPaging(@PathVariable("id") int id,
			@PathVariable("limit") int limit) {

		
		return null;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

}
