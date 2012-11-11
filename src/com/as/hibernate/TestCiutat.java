package com.as.hibernate;

import net.sf.cglib.proxy.Factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.data.Ciutat;

public class TestCiutat {

	public static void main(String[] args) {
		
		// Hibernate model configuration
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass (Ciutat.class);
	
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
		Ciutat bcn = new Ciutat ();
		bcn.setNom("Barcelona");
		bcn.setDescripcio("La mejor ciudad del mundo");
		bcn.setPreuVol(35.0f);
		
		// The object 'bcn' is now persistent
		session.save(bcn);
		
		// Commit changes to de DB
		session.getTransaction().commit();

	}

}
