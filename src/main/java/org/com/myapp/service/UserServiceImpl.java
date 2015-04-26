package org.com.myapp.service;

import java.util.Date;
import java.util.List;

import org.com.myapp.dao.UserReposity;
import org.com.myapp.entity.Membership;
import org.com.myapp.entity.Role;
import org.com.myapp.entity.User;
import org.com.myapp.model.MyUser;
import org.com.myapp.model.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserReposity userReposity;

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

		if (saveUser.getIdUser() >= 1) {

			Membership membership = new Membership();
			membership.setIdUser(saveUser.getIdUser());
			membership.setPassword(myUser.getPassword());
			membership.setPasswordSalt(new BCryptPasswordEncoder()
					.encode(myUser.getPassword()));
			membership.setCreateDate(new Date());

			userReposity.saveMembership(membership);

			myUser.setIdUser(saveUser.getIdUser());
			myUser.setPassword(membership.getPasswordSalt());

			return myUser;
		}

		return null;
	}

	public Role getRoleByName(String name) {

		return userReposity.getRoleByName(name);
	}

	public void setUserReposity(UserReposity userReposity) {
		this.userReposity = userReposity;
	}

}
