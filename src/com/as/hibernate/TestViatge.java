package com.as.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.data.Ciutat;
import com.as.data.Client;
import com.as.data.Data;
import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.HotelLowCost;
import com.as.data.HotelSuperior;
import com.as.data.Viatge;

public class TestViatge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Hibernate model configuration
				Configuration cfg = new Configuration();
				
				cfg.addAnnotatedClass(Ciutat.class);
				cfg.addAnnotatedClass(Hotel.class);
				cfg.addAnnotatedClass(HotelSuperior.class);
				cfg.addAnnotatedClass(HotelLowCost.class);
				cfg.addAnnotatedClass(Habitacio.class);
				cfg.addAnnotatedClass(Client.class);
				cfg.addAnnotatedClass(Viatge.class);
				cfg.addAnnotatedClass(Data.class);

				// Hibernate database configuration
				cfg.configure ("hibernate.cfg.xml");

				// Create tables
				new SchemaExport (cfg).create (true, true);

				// Session
				SessionFactory factory = cfg.buildSessionFactory();
				Session session = factory.getCurrentSession();
					

	}

}
