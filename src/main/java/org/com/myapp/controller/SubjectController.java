package org.com.myapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.com.myapp.entity.Subject;
import org.com.myapp.model.CurrentUser;
import org.com.myapp.model.SubjectData;
import org.com.myapp.service.ServiceException;
import org.com.myapp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/admin/subject", method = RequestMethod.GET)
	public String index() {

		return "sub/index";
	}

	@RequestMapping(value = "/admin/subject/list")
	@ResponseBody
	public List<Subject> getAllSubject() throws ServiceException {

		return subjectService.getAllSubject();
	}

	@RequestMapping(value = "/admin/subject/{id}")
	@ResponseBody
	public Subject getSubjectById(@PathVariable int id) throws ServiceException {

		return subjectService.getSubjectById(id);
	}

	@RequestMapping(value = "/admin/subject/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Subject> create(@RequestBody @Valid Subject subject)
			throws ServiceException {

		Subject sub = subjectService.add(subject);
		if (sub.getIdSubject() != 0) {
			return new ResponseEntity<Subject>(sub, HttpStatus.CREATED);
		}
		return new ResponseEntity<Subject>(HttpStatus.NOT_ACCEPTABLE);

	}

	@RequestMapping(value = "/admin/subject/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable int id) throws ServiceException {

		Subject subject = subjectService.getSubjectById(id);
		if (subject != null) {

			subjectService.delete(subject);

		}

	}

	@RequestMapping(value = "/user/subject", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<SubjectData>> getAllSubjectByUser()
			throws ServiceException {

		return new ResponseEntity<List<SubjectData>>(
				subjectService.getAllSubjectData(), HttpStatus.OK);

	}

	public void setSubjectService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	private CurrentUser getAuthenticatedUser() {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		return user;

	}

}
