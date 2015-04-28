package org.com.myapp.controller;

import java.util.List;

import org.com.myapp.model.CurrentUser;
import org.com.myapp.model.ScoreForm;
import org.com.myapp.model.UserData;
import org.com.myapp.service.ServiceException;
import org.com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserDataController {

	private final int length = 15;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/userinfor", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<UserData> getHomePage() {
		CurrentUser user = getAuthenticatedUser();

		UserData userData = userService.getUserData(user.getId());
		userData.setUsername(user.getUsername());

		ResponseEntity<UserData> userEntity = new ResponseEntity<UserData>(
				userData, HttpStatus.OK);
		return userEntity;

	}

	// get list contain top 15 user infor
	@RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = { "application/json" })
	public List<UserData> getUserListAndScore(
			@RequestParam(required = false) Integer n) {

		return (n != null ? userService.getUserDataList(n) : userService
				.getUserDataList(length));
	}

	@RequestMapping(value = "/user/list/{id}/{limit}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<List<UserData>> getUserListAndScore(
			@PathVariable("id") Integer id, @PathVariable("limit") Integer limit) {

		List<UserData> userDatas = userService.getUserDataList(id, limit);

		if (userDatas != null)
			return new ResponseEntity<List<UserData>>(userDatas, HttpStatus.OK);

		return new ResponseEntity<List<UserData>>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/user/updatescore", method = RequestMethod.POST)
	public ResponseEntity<UserData> updateUserScore(
			@RequestBody ScoreForm scoreForm) throws ServiceException {

		CurrentUser currentUser = getAuthenticatedUser();

		System.out.println("User: " + currentUser.getId() + " Match: "
				+ scoreForm.getMatchId() + " Score: " + scoreForm.getPoint()
				+ " time: " + scoreForm.getTime());

		UserData userData = userService.updateUserScore(currentUser.getId(),
				scoreForm.getMatchId(), scoreForm.getPoint(),
				scoreForm.getTime());

		if (userData != null) {

			return new ResponseEntity<UserData>(userData, HttpStatus.OK);
		}

		return new ResponseEntity<UserData>(HttpStatus.BAD_REQUEST);

	}

	private CurrentUser getAuthenticatedUser() {
		CurrentUser user = (CurrentUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		return user;

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
