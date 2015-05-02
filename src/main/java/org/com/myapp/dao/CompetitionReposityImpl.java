package org.com.myapp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.com.myapp.entity.Competition;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompetitionReposityImpl implements CompetitionReposity {

	@Autowired
	private SessionFactory sessionFactory;

	public Competition findCompetitionById(int id) throws DAOException {
		try {

			Session session = getSession();
			String sql = "SELECT * FROM crossworddb.competition as c where c.IdCompetition =:id";
			Competition competition = (Competition) session.createSQLQuery(sql)
					.addEntity(Competition.class).setParameter("id", id)
					.uniqueResult();

			return competition;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}
	}

	public Competition saveOrUpdate(Competition competition)
			throws DAOException {
		Session session = getSession();
		try {

			if (competition.getIdCompetition() == 0) {
				session.save(competition);
			} else {
				session.saveOrUpdate(competition);

			}
			session.getTransaction().commit();

			return competition;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new DAOException("Data acess error", e);
		}
	}

	public void delete(Competition competition) throws DAOException {
		Session session = getSession();
		try {
			session.delete(competition);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new DAOException("Data acess error", e);
		}

	}

	public List<Competition> getAllCompetition() throws DAOException {

		Session session = getSession();
		try {
			@SuppressWarnings("unchecked")
			List<Competition> competitions = session.createQuery(
					"from Competition").list();

			if (competitions != null)
				return competitions;
			else
				return new ArrayList<Competition>();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new DAOException("Data acess error", e);
		}
	}

	public Competition getCurrentCompetition(Date date) throws DAOException {
		try {
			Session session = getSession();

			@SuppressWarnings("unchecked")
			List<Competition> competitions = session
					.createCriteria(Competition.class)
					.add(Restrictions.eq("isActive", true))
					.add(Restrictions.le("begin", date))
					.add(Restrictions.ge("end", date)).list();

			if (competitions != null)
				return competitions.get(0);
			return null;

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

	}

	public Competition getLastCompetition() throws DAOException {
		try {

			Session session = getSession();
			String sql = "select * from crossworddb.competition as c"
					+ " where c.End = (select max(c.End) from crossworddb.competition as c where c.isActive =1)";

			Competition competition = (Competition) session.createSQLQuery(sql)
					.addEntity(Competition.class).uniqueResult();

			return competition;

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
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
