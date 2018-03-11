package com.bac.orm.driver;

import org.hibernate.SessionFactory;

import com.bac.orm.util.HibernateUtil;

public class TestClient {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
		System.out.println("SessionFactory statistics:"+sessionFactory.getCurrentSession().getStatistics());
	}

}
