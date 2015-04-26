package org.com.myapp.service;

import java.util.List;

import org.com.myapp.entity.Role;
import org.com.myapp.entity.User;
import org.com.myapp.model.MyUser;
import org.com.myapp.model.RegisterForm;

public interface UserService {

	User findUserByEmail(String email);

	MyUser getUserByEmail(String email);

	List<Role> getRoleByUserId(int idUser);

	MyUser createUser(RegisterForm registerForm);

	MyUser addUser(MyUser user);

	Role getRoleByName(String name);

	
}
