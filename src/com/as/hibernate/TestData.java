package com.as.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.data.Client;
import com.as.data.Data;

public class TestData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Hibernate model configuration
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass (Data.class);
	
		// Hibernate database configuration
		cfg.configure ("hibernate.cfg.xml");

		// Create tables
		new SchemaExport (cfg).create (true, true);
		
		/* Tests */
		
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		
		/* INSERT Test */
		
		session.beginTransaction();
		
		// Create object
		Data data=new Data();
		@SuppressWarnings("deprecation")
		Date date=new Date(86,12,19);
		data.setDate(date);				
		// The object 'pere' is now persistent
		session.save(data);
		// Commit changes to de DB
		session.getTransaction().commit();


	}

}
