package org.com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Role;
import org.com.myapp.entity.User;
import org.com.myapp.model.MyUser;
import org.com.myapp.model.RegisterForm;
import org.com.myapp.model.UserData;

public interface UserService {

	User findUserByEmail(String email);

	MyUser getUserByEmail(String email);

	List<Role> getRoleByUserId(int idUser);

	MyUser createUser(RegisterForm registerForm);

	MyUser addUser(MyUser user);

	Role getRoleByName(String name);

	UserData getUserData(int id);

	ArrayList<UserData> getUserDataList(int n);

	ArrayList<UserData> getUserDataList(int idRow, int limit);

	ArrayList<UserData> getUserDataListByMatch(int id, int num);

	UserData updateUserScore(int idUser, int matchId, float score, float time)
			throws ServiceException;

	UserData getUserRankByMatch(int idUser, int idMatch)
			throws ServiceException;

	List<UserData> getTopRankUserByMatch(int id, int lenght)
			throws ServiceException;

	UserData getUserRankByCompetition(int idUser, int idCompetition)
			throws ServiceException;

	List<UserData> getTopRankUserByCompetition(int id, int lenght)
			throws ServiceException;
}
