package com.as.hibernate;

import java.util.ArrayList;

import org.hibernate.cfg.Configuration;

import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.controllers.CtrlClient;
import com.as.controllers.CtrlHabitacio;
import com.as.controllers.Factory;
import com.as.data.Ciutat;
import com.as.data.Client;
import com.as.data.Data;
import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.HotelLowCost;
import com.as.data.HotelSuperior;
import com.as.data.Viatge;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Throwable {
		
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
		// TODO: Ver como gestionamos la creacion o no de las tablas
		//new SchemaExport (cfg).create (true, true);
		
		// Construct controller factory
		Factory ctrlFactory = new Factory (cfg);
		
		CtrlClient cClient = ctrlFactory.getCtrlClient();
		ArrayList<Client> lc = cClient.getAll();
		for (int i = 0; i < lc.size(); ++i) {
			System.out.println (lc.get(i).getDni());
		}
	
	    CtrlHabitacio cHab = ctrlFactory.getCtrlHabitacio();
	    ArrayList<Habitacio> lh = cHab.getAll();
		for (int i = 0; i < lh.size(); ++i) {
			System.out.println (lh.get(i).getNumero());
		}
	}

}