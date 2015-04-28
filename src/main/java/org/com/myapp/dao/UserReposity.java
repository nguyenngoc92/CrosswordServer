package org.com.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Membership;
import org.com.myapp.entity.Role;
import org.com.myapp.entity.User;
import org.com.myapp.model.MyUser;
import org.com.myapp.model.SubjectData;
import org.com.myapp.model.UserData;

public interface UserReposity {

	User findUserByEmail(String email);

	MyUser getUserByEmail(String email);

	List<Role> getRoleListByUserID(int idUser);

	Role getRoleByName(String name);

	User addUser(User user);

	void saveMembership(Membership membership);

	List<SubjectData> getAllSubjectDataByUserId(int id) throws DAOException;

	UserData getUserInfor(int id);

	ArrayList<UserData> getUserInforList(int n);

	ArrayList<UserData> getUserRankAndScoreList(int idRow, int limit);
}
