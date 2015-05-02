package org.com.myapp.dao;

import org.com.myapp.entity.Score;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreReposityImpl implements ScoreReposity {

	@Autowired
	private SessionFactory sessionFactory;

	public void save(Score score) throws DAOException {

		Session session = getSession();

		try {

			Transaction tx = session.getTransaction();
			session.save(score);

			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();

			session.getTransaction().rollback();
			throw new DAOException("Data acess error", e);
		}

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
}
