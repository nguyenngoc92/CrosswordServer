package org.com.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectReposityImpl implements SubjectReposity {

	@Autowired
	private SessionFactory sessionFactory;

	public Subject getSubjectById(int id) throws DAOException {

		try {
			Session session = getSession();

			Subject subject = (Subject) session
					.createSQLQuery(
							"select * from subject as s where s.IdSubject =:id")
					.addEntity(Subject.class).setParameter("id", id)
					.uniqueResult();

			return subject;

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

	}

	public Subject add(Subject subject) throws DAOException {

		try {
			Session session = getSession();

			if (subject.getIdSubject() == 0) {
				session.save(subject);
			} else {
				session.saveOrUpdate(subject);
			}

			session.getTransaction().commit();

			return subject;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

	}

	public void delete(Subject subject) throws DAOException {

		try {

			Session session = getSession();
			session.delete(subject);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}
	}

	public List<Subject> getAllSubject() throws DAOException {

		try {

			Session session = getSession();
			@SuppressWarnings("unchecked")
			List<Subject> listSubjects = session.createQuery("from Subject")
					.list();

			if (listSubjects != null)
				return listSubjects;

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

		return new ArrayList<Subject>();
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
