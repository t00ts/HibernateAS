package com.as.hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.Session;
import com.as.data.Client;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Hibernate model configuration
				Configuration cfg = new Configuration();
				cfg.addAnnotatedClass (Client.class);
			
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
				Client pep = new Client("436453K", "Pep", "54645456", 0);
								
				// The object 'pere' is now persistent
				session.save(pep);
				// Commit changes to de DB
				session.getTransaction().commit();

	}

}
