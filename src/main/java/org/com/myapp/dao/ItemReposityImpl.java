package org.com.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.com.myapp.entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemReposityImpl implements ItemReposity {

	@Autowired
	private SessionFactory sessionFactory;

	public Item save(Item item) throws DAOException {
		Session session = getSession();
		try {

			session.save(item);
			session.getTransaction().commit();
			return item;
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

	}

	public Item update(Item item) throws DAOException {
		Session session = getSession();

		try {

			session.saveOrUpdate(item);
			session.getTransaction().commit();
			return item;
		} catch (HibernateException e) {

			session.getTransaction().rollback();
			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

	}

	public Item delete(Item item) {
		return null;
	}

	public ArrayList<Item> getListItemByMatchId(int id) throws DAOException {

		Session session = getSession();

		try {

			String sql = "select i.* from crossworddb.itemsinmatches as mt, crossworddb.item as i where mt.iditem = i.iditem and mt.idmatch =:id order by i.iditem ASC";

			@SuppressWarnings("unchecked")
			ArrayList<Item> items = (ArrayList<Item>) session
					.createSQLQuery(sql).addEntity(Item.class)
					.setParameter("id", id).list();

			if (items == null)
				return new ArrayList<Item>();

			return items;
		} catch (HibernateException e) {

			e.printStackTrace();
			throw new DAOException("Data acess error", e);
		}

	}

	public List<Item> getListItem(int begin, int length) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateItemInfor(int id) throws DAOException {
		Session session = getSession();
		try {

			String sql = "UPDATE crossworddb.item SET count = count+1 WHERE IdItem =:id";

			session.createSQLQuery(sql).setParameter("id", id).executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {

			session.getTransaction().rollback();
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
