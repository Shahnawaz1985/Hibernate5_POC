package com.bac.orm.dao.impl;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.bac.orm.beans.SimpleUser;
import com.bac.orm.dao.UserDAO;
import com.bac.orm.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	private static SessionFactory sessionFactory = null;
	

	public UserDAOImpl() {
		sessionFactory = HibernateUtil.createSessionFactory();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleUser> retrieveAllUsers() {
		Session session = null;
		Transaction tx = null;
		Criteria userCriteria = null;
		List<SimpleUser> usersList = null;
		if (null != sessionFactory) {
			session = sessionFactory.getCurrentSession();
			if (null != session) {
				tx = session.beginTransaction();
				userCriteria = session.createCriteria(SimpleUser.class ,"u")
						//.setFetchMode("u.itemsForSale.bids", FetchMode.JOIN)
								.add(Restrictions.eq("active", "Y"));							
				usersList = (List<SimpleUser>)userCriteria.list();
				tx.commit();
			}
		}
		/*
		 * if (null != session && session.isOpen()) { session.close(); }
		 */
		return usersList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SimpleUser> retrieveAdminUsers() {
		Session session = null;
		Transaction tx = null;
		Criteria userCriteria = null;
		List<SimpleUser> adminUsers = null;
		if (null != sessionFactory) {
			session = sessionFactory.getCurrentSession();
			if (null != session) {
				tx = session.beginTransaction();
				userCriteria = session.createCriteria(SimpleUser.class)
								.add(Restrictions.eq("active", "Y"))
								.add(Restrictions.eq("admin", "Y"));
				adminUsers = (List<SimpleUser>)userCriteria.list();
				tx.commit();
			}
		}
		/*
		 * if (null != session && session.isOpen()) { session.close(); }
		 */
		return adminUsers;
	}

	@Override
	public void saveUser(SimpleUser user) {
		Session session = null;
		Transaction tx = null;
		if (null != sessionFactory) {
			session = sessionFactory.getCurrentSession();
			if (null != session) {
				tx = session.beginTransaction();
				session.saveOrUpdate(user);
			}
			tx.commit();
		}
	}

	@Override
	public SimpleUser getUserById(String id) {
		Session session = null;
		Transaction tx = null;
		SimpleUser simpleUser = null;
		if (null != sessionFactory) {
			session = sessionFactory.getCurrentSession();
			if(null != session) {				
				tx = session.beginTransaction();
				session.setCacheMode(CacheMode.NORMAL);
				simpleUser = (SimpleUser) session.createCriteria(SimpleUser.class)
							.add(Restrictions.idEq(id)).uniqueResult();//(SimpleUser) session.get(SimpleUser.class, id);
			}
		}
		tx.commit();
		return simpleUser;
	}

	@Override
	public SimpleUser mergeObjects(String id, SimpleUser user) {
		//SimpleUser user1 = getUserById(id);
		Session sess = sessionFactory.getCurrentSession();
		Transaction tx = null;
		SimpleUser user2 = null;
		if(null != sess) {
			tx = sess.beginTransaction();
			user2 = (SimpleUser)sess.merge(user);
		}
		tx.commit();
		return user2;
	}

	@Override
	public List<SimpleUser> findUserByFirstname(SimpleUser user) {
		Transaction tx = null;
		Criteria userCriteria = null;
		Example exampleUser = Example.create(user)
							  .ignoreCase()
							  .enableLike(MatchMode.ANYWHERE);							  
		Session session = sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
		userCriteria = session.createCriteria(SimpleUser.class)
								.add(exampleUser);		
		List<SimpleUser> userList = (List<SimpleUser>)userCriteria.list();
		tx.commit();
		return userList;	
	}

	@Override
	public List<SimpleUser> findMultipleUsers(String[] ids) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		MultiIdentifierLoadAccess<SimpleUser> multiLoadAccess = session.byMultipleIds(SimpleUser.class);
		//Dont enable entities present in 1st level cache.
		List<SimpleUser> simpleUsers = multiLoadAccess.enableSessionCheck(true).multiLoad(ids);
		tx.commit();
		return simpleUsers;
	}	
}
