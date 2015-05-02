package org.com.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Match;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MatchReposityImpl implements MatchReposity {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Match> getAllMatchBySubject(int id) throws DAOException {

		List<Match> listMatches = new ArrayList<Match>();

		try {
			Session session = getSession();
			String sql = "select m.* from crossworddb.match as m where m.IdSubject = :id";
			@SuppressWarnings("unchecked")
			List<Match> list = session.createSQLQuery(sql)
					.addEntity(Match.class).setParameter("id", id).list();

			if (list != null)
				return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

		return listMatches;
	}

	public List<Match> getAllMatchByCompetiton(int id) throws DAOException {

		List<Match> listMatches = new ArrayList<Match>();

		try {
			Session session = getSession();
			String sql = "select m.* from crossworddb.match as m where m.IdCompetition =:id";
			@SuppressWarnings("unchecked")
			List<Match> list = session.createSQLQuery(sql)
					.addEntity(Match.class).setParameter("id", id).list();

			if (list != null)
				return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

		return listMatches;
	}

	public Match getMatchById(int id) throws DAOException {

		Match match = null;
		try {

			Session session = getSession();

			String sql = "select * from crossworddb.match as m where m.IdMatch =:id";
			match = (Match) session.createSQLQuery(sql).addEntity(Match.class)
					.setParameter("id", id).uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

		return match;
	}

	public Match save(Match match) throws DAOException {

		Session session = getSession();
		try {

			if (match.getIdMatch() == 0) {
				session.save(match);
			} else {
				session.saveOrUpdate(match);
			}

			session.getTransaction().commit();
			return match;

		} catch (HibernateException e) {
			e.printStackTrace();

			session.getTransaction().rollback();
			throw new DAOException("Data acess error", e);
		}
	}

	public Match delete(Match match) throws DAOException {

		Session session = getSession();
		try {

			session.delete(match);

			session.getTransaction().commit();
			return match;

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new DAOException("Data acess error", e);
		}
	}

	public List<Match> getListMatchPaging(int id, int lenght)
			throws DAOException {

		try {
			Session session = getSession();

			String sql = "select * from crossworddb.match as m where m.IdMatch =:id limit :lenght";

			@SuppressWarnings("unchecked")
			List<Match> pageList = session.createSQLQuery(sql)
					.addEntity(Match.class).setParameter("id", id)
					.setParameter("lenght", lenght).list();

			if (pageList != null)
				return pageList;
			else
				return new ArrayList<Match>();
		} catch (HibernateException e) {
			throw new DAOException("Data acess error", e);
		}

	}

	public Match getMatchBySubjectAndUser(int idSubject, int idUser)
			throws DAOException {

		try {

			Session session = getSession();
			String sql = "select m.* "
					+ "from crossworddb.match as m "
					+ "where m.IdMatch not in (select c.IdMatch from crossworddb.score as c where c.IdUser =:idUser) "
					+ "and m.IdSubject =:idSubject"
					+ " order by m.IdMatch ASC " + "limit 1";
			Match match = (Match) session.createSQLQuery(sql)
					.addEntity(Match.class).setParameter("idUser", idUser)
					.setParameter("idSubject", idSubject).uniqueResult();

			return match;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

	}

	public Match getMatchByCompetitionAndUser(int idCompetition, int idUser)
			throws DAOException {

		try {
			Session session = getSession();
			String sql = " select m.* "
					+ "from crossworddb.match as m "
					+ "where m.IdMatch not in (select c.IdMatch from crossworddb.score as c where c.IdUser =:idUser) "
					+ "and m.IdCompetition =:idCompetition "
					+ "order by m.IdMatch asc limit 1";
			Match match = (Match) session.createSQLQuery(sql)
					.addEntity(Match.class).setParameter("idUser", idUser)
					.setParameter("idCompetition", idCompetition)
					.uniqueResult();

			return match;

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
