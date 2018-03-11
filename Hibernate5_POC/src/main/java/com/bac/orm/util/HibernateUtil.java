package com.bac.orm.util;

import org.hibernate.HibernateError;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory = null;
	
	private static ServiceRegistry serviceRegistry;

	public static SessionFactory createSessionFactory() {
		if(null == sessionFactory) {
			try {
				StandardServiceRegistry standardRegistry =	new StandardServiceRegistryBuilder().configure().build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();
				System.out.println("MetadatSource :"+metaData.collectTableMappings());
				} catch (Throwable th) {
					th.printStackTrace(System.out);
					System.err.println("Enitial SessionFactory creation failed" + th);
					throw new HibernateError(th.getMessage());
				}
		}
		return sessionFactory;
	}

}
