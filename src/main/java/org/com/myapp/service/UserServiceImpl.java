package org.com.myapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.com.myapp.dao.DAOException;
import org.com.myapp.dao.MatchReposity;
import org.com.myapp.dao.ScoreReposity;
import org.com.myapp.dao.UserReposity;
import org.com.myapp.entity.Match;
import org.com.myapp.entity.Membership;
import org.com.myapp.entity.Role;
import org.com.myapp.entity.Score;
import org.com.myapp.entity.ScoreId;
import org.com.myapp.entity.User;
import org.com.myapp.model.MyUser;
import org.com.myapp.model.RegisterForm;
import org.com.myapp.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserReposity userReposity;

	@Autowired
	private MatchReposity matchReposity;

	@Autowired
	private ScoreReposity scoreReposity;

	public User findUserByEmail(String email) {
		User user = userReposity.findUserByEmail(email);

		return user;
	}

	public MyUser getUserByEmail(String email) {

		MyUser user = userReposity.getUserByEmail(email);
		return user;
	}

	public List<Role> getRoleByUserId(int idUser) {

		return userReposity.getRoleListByUserID(idUser);
	}

	public MyUser createUser(RegisterForm form) {

		MyUser user = new MyUser();
		user.setUserName(form.getUsername());
		user.setPassword(form.getPassword());
		user.setEmail(form.getEmail());

		return user;
	}

	public MyUser addUser(MyUser myUser) {

		User user = new User(myUser.getUserName(), myUser.getEmail());
		user.setRoles(myUser.getRoles());

		User saveUser = userReposity.addUser(user);

		Membership membership = new Membership();
		membership.setIdUser(saveUser.getIdUser());
		membership.setPassword(myUser.getPassword());
		membership.setPasswordSalt(new BCryptPasswordEncoder().encode(myUser
				.getPassword()));
		membership.setCreateDate(new Date());

		userReposity.saveMembership(membership);

		myUser.setIdUser(saveUser.getIdUser());
		myUser.setPassword(membership.getPasswordSalt());

		return myUser;

	}

	public Role getRoleByName(String name) {

		return userReposity.getRoleByName(name);
	}

	public UserData getUserData(int id) {

		UserData data = userReposity.getUserInfor(id);
		if (data == null) {

			UserData user = new UserData();
			return user;
		}

		return data;
	}

	public ArrayList<UserData> getUserDataList(int n) {
		ArrayList<UserData> userDatas = (ArrayList<UserData>) userReposity
				.getUserInforList(n);

		if (userDatas != null)
			return userDatas;
		else
			return userDatas = new ArrayList<UserData>();
	}

	public ArrayList<UserData> getUserDataList(int idRow, int limit) {
		ArrayList<UserData> userDatas = (ArrayList<UserData>) userReposity
				.getUserRankAndScoreList(idRow, limit);

		if (userDatas != null) {
			return userDatas;
		} else {
			return userDatas = new ArrayList<UserData>();
		}
	}

	public ArrayList<UserData> getUserDataListByMatch(int id, int num) {
		ArrayList<UserData> userDatas = (ArrayList<UserData>) userReposity
				.getUserRankAndScoreList(id, num);

		if (userDatas == null)
			return new ArrayList<UserData>();

		return userDatas;
	}

	public UserData updateUserScore(int idUser, int matchId, float score,
			float time) throws ServiceException

	{
		try {
			Match match = matchReposity.getMatchById(matchId);

			if (match != null)

			{

				Score scoreObject = new Score();

				scoreObject.setPoint(score);
				scoreObject.setTime((float) time);
				scoreObject.setUnit("minute");
				scoreObject.setId(new ScoreId(idUser, matchId));
				scoreObject.setCreateDate(new Date());
				scoreReposity.save(scoreObject);

				UserData userData = userReposity.getUserRankByMatch(idUser,
						matchId);

				return userData;

			}

			return null;
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Database error", e);
		}

	}

	public UserData getUserRankByMatch(int idUser, int idMatch)
			throws ServiceException {

		try {

			return userReposity.getUserRankByMatch(idUser, idMatch);

		} catch (DAOException e) {

			throw new ServiceException("Database error", e);
		}

	}

	public List<UserData> getTopRankUserByMatch(int id, int lenght)
			throws ServiceException {
		try {

			return userReposity.getTopListRankUserByMatchId(id, lenght);

		} catch (DAOException e) {
			throw new ServiceException("Database error", e);
		}
	}

	public UserData getUserRankByCompetition(int idUser, int idCompetition)
			throws ServiceException {
		try {

			UserData userData = userReposity.getUserRankByCompetition(idUser,
					idCompetition);

			if (userData == null) {
				userData = new UserData();
				userData.setRank(0);
				userData.setScore(0);
				userData.setUsername("NOT_HAVE_SCORE");
			}
			return userData;

		} catch (DAOException e) {

			throw new ServiceException("Database error", e);
		}
	}

	public List<UserData> getTopRankUserByCompetition(int id, int lenght)
			throws ServiceException {
		try {

			return userReposity.getTopListRankUserByCompetitionId(id, lenght);

		} catch (DAOException e) {

			throw new ServiceException("Database error", e);
		}
	}

	public void setUserReposity(UserReposity userReposity) {
		this.userReposity = userReposity;
	}

	public void setMatchReposity(MatchReposity matchReposity) {
		this.matchReposity = matchReposity;
	}

	public void setScoreReposity(ScoreReposity scoreReposity) {
		this.scoreReposity = scoreReposity;
	}

}
