package org.com.myapp.controller;

import java.util.List;

import org.com.myapp.entity.Match;
import org.com.myapp.model.CurrentUser;
import org.com.myapp.model.MatchData;
import org.com.myapp.service.CompetitionService;
import org.com.myapp.service.MatchService;
import org.com.myapp.service.ServiceException;
import org.com.myapp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('ROLE_USER')")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@Autowired
	private SubjectService subjectService;
	@Autowired
	private CompetitionService competitionService;

	@Autowired
	@Qualifier("matchFormValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/admin/subject/{id}/match", method = RequestMethod.GET)
	@ResponseBody
	public List<Match> getAllMatchBySubjectId(@PathVariable int id)
			throws ServiceException {

		return matchService.getAllMatchBySubject(id);
	}

	@RequestMapping(value = "/admin/match/{id}/{limit}", method = RequestMethod.GET)
	@ResponseBody
	public List<Match> getMatchPaging(@PathVariable("id") int id,
			@PathVariable("limit") int limit) {

		return null;
	}

	@RequestMapping(value = "/user/subject/{idsubject}/match", method = RequestMethod.GET)
	public ResponseEntity<MatchData> getMatchBySubjectIdAndUserId(
			@PathVariable("idsubject") int idsubject) throws ServiceException {

		CurrentUser user = getAuthenticatedUser();

		if (subjectService.getSubjectById(idsubject) == null) {
			return new ResponseEntity<MatchData>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<MatchData>(
				matchService.getMatchNextBySubjectAndUser(idsubject,
						user.getId()), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/competition/{idcompetition}/match", method = RequestMethod.GET)
	public ResponseEntity<MatchData> getMatchByCompetitionIdAndUserId(
			@PathVariable("idcompetition") int idcompetition)
			throws ServiceException {

		CurrentUser user = getAuthenticatedUser();

		if (competitionService.findCompetitionById(idcompetition) == null) {
			return new ResponseEntity<MatchData>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<MatchData>(
				matchService.getMatchNextByCompetitionAndUser(idcompetition,
						user.getId()), HttpStatus.OK);
	}

	private CurrentUser getAuthenticatedUser() {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		return user;

	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

}
