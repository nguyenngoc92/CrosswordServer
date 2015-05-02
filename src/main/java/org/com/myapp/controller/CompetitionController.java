package org.com.myapp.controller;

import org.com.myapp.entity.Competition;
import org.com.myapp.model.CompetitionData;
import org.com.myapp.model.CurrentUser;
import org.com.myapp.service.CompetitionService;
import org.com.myapp.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompetitionController {

	@Autowired
	private CompetitionService competitionService;

	@RequestMapping(value = "/user/competition/current", method = RequestMethod.GET)
	public ResponseEntity<CompetitionData> getCurrentCompetition()
			throws ServiceException {

		CompetitionData competition = competitionService
				.getCurrentCompetition();

		return new ResponseEntity<CompetitionData>(competition, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/competition/last", method = RequestMethod.GET)
	public ResponseEntity<Competition> getLastCompetition() {

		return null;
	}

	private CurrentUser getAuthenticatedUser() {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		return user;

	}

	public void setCompetitionService(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}

}
