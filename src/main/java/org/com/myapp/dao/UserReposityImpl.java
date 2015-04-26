package org.com.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Membership;
import org.com.myapp.entity.Role;
import org.com.myapp.entity.User;
import org.com.myapp.model.MyUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserReposityImpl implements UserReposity {

	@Autowired
	private SessionFactory sessionFactory;

	public User findUserByEmail(String email) {

		Session session = getSession();

		try {

			User user = (User) session
					.createSQLQuery("select * from user where email = :email")
					.addEntity(User.class).setParameter("email", email)
					.uniqueResult();

			return user;

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;

	}

	public MyUser getUserByEmail(String email) {

		Session session = getSession();

		try {

			String sql = "select u.*,m.PasswordSalt as password from user as u,membership as m "
					+ "where u.Email =:email and u.IdUser = m.IdUser";

			MyUser user = (MyUser) session
					.createSQLQuery(sql)
					.setParameter("email", email)
					.setResultTransformer(
							Transformers.aliasToBean(MyUser.class))
					.uniqueResult();

			return user;
		} catch (HibernateException e) {

			e.printStackTrace();
		}

		return null;
	}

	public List<Role> getRoleListByUserID(int idUser) {

		Session session = getSession();
		try {

			String sql = "select * from role as r,usersinroles as ur where ur.IdUser=:idUser and r.IdRole = ur.IdRole";
			@SuppressWarnings("unchecked")
			List<Role> roles = session.createSQLQuery(sql)
					.addEntity(Role.class).setParameter("idUser", idUser)
					.list();

			if (roles != null)

				return roles;

		} catch (HibernateException e) {

			e.printStackTrace();
		}

		return new ArrayList<Role>();
	}

	public Role getRoleByName(String name) {

		Session session = getSession();
		try {

			Role r = (Role) session
					.createSQLQuery(
							"select * from role as r where r.RoleName =:name")
					.addEntity(Role.class).setParameter("name", name)
					.uniqueResult();

			return r;
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User addUser(User user) {

		Session session = getSession();

		try {

			session.save(user);
			session.getTransaction().commit();

			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveMembership(Membership membership) {

		try {

			Session session = getSession();
			session.save(membership);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}

		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		return session;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
