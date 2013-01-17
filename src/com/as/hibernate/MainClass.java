package com.as.hibernate;


import org.hibernate.cfg.Configuration;


import com.as.controllers.CtrlInterface;
import com.as.controllers.DomainCtrl;
import com.as.controllers.Factory;
import com.as.data.Ciutat;
import com.as.data.Client;
import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.HotelLowCost;
import com.as.data.HotelSuperior;
import com.as.data.Viatge;
import com.as.views.FinestraContractarViatges;

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

		// Hibernate database configuration
		cfg.configure ("hibernate.cfg.xml");

		// Create tables
		// TODO: Ver como gestionamos la creacion o no de las tablas
		//new SchemaExport (cfg).create (true, true);
		
		// Construct factory
		Factory.setConfiguration (cfg);
		Factory factory = Factory.getInstance();
		
		// Init Domain controller
		DomainCtrl domainController = new DomainCtrl (factory);
	
		// Init views
		FinestraContractarViatges contractarViatgeView = new FinestraContractarViatges ();
		CtrlInterface viewController = new CtrlInterface (contractarViatgeView, domainController);
		
		contractarViatgeView.setVisible(true);
	 	contractarViatgeView.setResizable(false);
		
	}

}
