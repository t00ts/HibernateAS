package com.as.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.controllers.CtrlHotel;
import com.as.controllers.CtrlViatge;
import com.as.data.Ciutat;
import com.as.data.Client;

import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.HotelLowCost;
import com.as.data.HotelSuperior;
import com.as.data.Viatge;

public class TestCtrl {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//comprobar ctrlviatge
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
		//new SchemaExport (cfg).create (true, true);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		/*CtrlViatge cv = new CtrlViatge(cfg);
		Date dini=sdf.parse("09/01/2013");
		Date dini2=sdf.parse("05/01/2013");
		Date dini3=sdf.parse("01/01/2013");
		Viatge v;
		List<Viatge> lv;
		v=cv.get("436453K", dini);
		System.out.println ("DniClient: "+v.getDniClient()+" Dataini: "+v.getdataInici());
		System.out.println ("DniClient: "+v.getDniClient()+" Dataini: "+v.getdataInici());*/
		CtrlHotel ch = new CtrlHotel(cfg);
	
		Hotel h;
		List<Hotel> lh;
		h=ch.get("Hotel NH", "Barcelona");
		System.out.println ("NomHot: "+h.getNom()+" Ciutat: "+h.getNomCiutat()+" Preu: "+h.getPreu());
	}

}
