package com.as.hibernate;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


import com.as.controllers.CtrlCiutat;
import com.as.controllers.CtrlClient;
import com.as.controllers.CtrlHabitacio;
import com.as.controllers.CtrlHotel;
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
import com.as.data.primarykeys.HabitacioPrimaryKey;
import com.as.data.primarykeys.HotelPrimaryKey;
import com.as.data.primarykeys.ViatgePrimaryKey;
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
		//DESCOMENTAR ESTA LINEA PARA BORRAR TODO!!
		new SchemaExport (cfg).create (true, true);

		// Construct factory
		Factory.setConfiguration (cfg);
		Factory factory = Factory.getInstance();
		
		//CREACION DATOS COMENTAR TODO CUANDO YA ESTE INSERTADO!!!!
		//crear clientes
		Client pep = new Client("436453K", "Pep", "69696969", 0);
		Client ivan = new Client("666666K", "Ivan", "12312132", 0);
		Client abel = new Client("777777K", "Abel", "74576345", 0);
		CtrlClient ccl   = factory.getCtrlClient();
		ccl.insert(pep);
		ccl.insert(ivan);
		ccl.insert(abel);
		//crear ciudades		
		Ciutat bcn = new Ciutat ("Barcelona", "La millor ciutat del mon", 80.0f);
		Ciutat mad = new Ciutat ("Madrid", "Es una mierda", 50.0f);
		Ciutat sev = new Ciutat ("Sevilla", "Es especial", 30.0f);
		CtrlCiutat cciu = factory.getCtrlCiutat();
		cciu.insert(bcn);
		cciu.insert(mad);
		cciu.insert(sev);
		//hotels
		Hotel h = new HotelSuperior(new HotelPrimaryKey("NH Hotel", "Barcelona"), bcn, 50f, 15f );
		Hotel h2 = new HotelLowCost(new HotelPrimaryKey("Juan Carlos I", "Madrid"), mad, 50f, 15f );
		Hotel h3 = new HotelSuperior(new HotelPrimaryKey("Virgen del Rocio", "Sevilla"), sev, 90f, 15f );
		Hotel h4 = new HotelSuperior(new HotelPrimaryKey("Sants", "Barcelona"), bcn, 60f, 15f );
		CtrlHotel chot = factory.getCtrlHotel();
		chot.insert(h);
		chot.insert(h2);
		chot.insert(h3);
		chot.insert(h4);
		//relacionar hoteles con ciudades
		bcn.addHotel(h);
		mad.addHotel(h2);
		sev.addHotel(h3);
		bcn.addHotel(h4);
		//Crear habitaciones
		Habitacio hab = new Habitacio (new HabitacioPrimaryKey(1, "NH Hotel", "Barcelona"),h);
		Habitacio hab2 = new Habitacio (new HabitacioPrimaryKey(1, "Juan Carlos I", "Madrid"),h2);
		Habitacio hab3 = new Habitacio (new HabitacioPrimaryKey(1, "Virgen del Rocio","Sevilla"),h3);
		Habitacio hab4 = new Habitacio (new HabitacioPrimaryKey(1, "Sants", "Barcelona"),h4);
		Habitacio hab5 = new Habitacio (new HabitacioPrimaryKey(2, "NH Hotel", "Barcelona"),h);
		Habitacio hab6 = new Habitacio (new HabitacioPrimaryKey(2, "Juan Carlos I", "Madrid"),h2);
		CtrlHabitacio ch = factory.getCtrlHabitacio();
		ch.insert(hab);
		ch.insert(hab2);
		ch.insert(hab3);
		ch.insert(hab4);
		ch.insert(hab5);
		ch.insert(hab6);
		//relacionar habitaciones con hoteles
		h.addHabitacio(hab);
		h.addHabitacio(hab5);
		h2.addHabitacio(hab2);
		h2.addHabitacio(hab6);
		h3.addHabitacio(hab3);
		h4.addHabitacio(hab4);
		
		
		// Init Domain controller
		DomainCtrl domainController = new DomainCtrl (factory);

		// Init views
		FinestraContractarViatges contractarViatgeView = new FinestraContractarViatges ();
		CtrlInterface viewController = new CtrlInterface (contractarViatgeView, domainController);
		
		contractarViatgeView.setVisible(true);
	 	contractarViatgeView.setResizable(false);
	 	
		
	}

}
