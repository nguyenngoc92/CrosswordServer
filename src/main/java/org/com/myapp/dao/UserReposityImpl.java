package org.com.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Membership;
import org.com.myapp.entity.Role;
import org.com.myapp.entity.User;
import org.com.myapp.model.MyUser;
import org.com.myapp.model.SubjectData;
import org.com.myapp.model.UserData;
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

			System.out.println(user.getIdUser());
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	public void saveMembership(Membership membership) {

		Session session = getSession();
		try {

			session.save(membership);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	public List<SubjectData> getAllSubjectDataByUserId(int id)
			throws DAOException {

		try {

		} catch (HibernateException e) {

			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

		return null;
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

	public UserData getUserInfor(int id) {

		String sql = "CALL crossworddb.getUserScoreAndRank(:id)";

		UserData value = (UserData) getSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.aliasToBean(UserData.class))
				.setParameter("id", id).uniqueResult();

		return value;
	}

	public ArrayList<UserData> getUserInforList(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<UserData> getUserRankAndScoreList(int idRow, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
}
