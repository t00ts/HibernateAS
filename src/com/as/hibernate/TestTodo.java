package com.as.hibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.Select;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.controllers.CtrlCiutat;
import com.as.controllers.CtrlClient;
import com.as.controllers.CtrlHotel;
import com.as.controllers.CtrlViatge;
import com.as.controllers.DomainCtrl;
import com.as.controllers.Factory;
import com.as.data.primarykeys.ViatgePrimaryKey;
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
import com.as.data.tuples.TupleCiutat;

public class TestTodo {

	/**
	 * @param args
	 * @throws Throwable 
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
		//new SchemaExport (cfg).create (true, true);

		// Session
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		/* -------------------- INSERT DATA -------------------- */
		session.beginTransaction ();
		//Crear base de datos simple
		//Query query1 = session.createQuery("delete Ciutat");//borra todo por cascada creo
		//int result = query1.executeUpdate();
		/*Ciutat bcn = new Ciutat ("Barcelona", "La millor ciutat del mon", 80.0f);
		Client pep = new Client("436453K", "Pep", "54645456", 0);
		Client pep2 = new Client("696969X", "Juan", "111111", 0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		@SuppressWarnings("deprecation")
		Date dini= sdf.parse("01/01/2013");//dinici
		Date dfi=sdf.parse("05/01/2013");//dfi
		Date dfi2=sdf.parse("09/01/2013");//dfi
		Data data = new Data(dini);
		Data data2 = new Data(dfi);
		HotelSuperior hot = new HotelSuperior(new HotelPrimaryKey("Hotel NH", bcn.getNom()), bcn, 100.0f, 20.0f);
		
		Habitacio hab = new Habitacio(new HabitacioPrimaryKey(1, hot.getNom(), hot.getNomCiutat()), hot);
		Viatge vi = new Viatge(new ViatgePrimaryKey("436453K",data.getDate()), pep, hab.getHotel().getCiutat(), hab, data, dfi, hab.getHotel().getNom(), hab.getNumero());
		Viatge vi2 = new Viatge(new ViatgePrimaryKey(pep2.getDni(),data2.getDate()), pep2, hab.getHotel().getCiutat(), hab, data, dfi2, hab.getHotel().getNom(), hab.getNumero());
		//guardamos datos en BD
		session.save(data);
		session.save(data2);
		session.save(bcn);
		session.save(pep);
		session.save(pep2);
		session.save(hot);
		session.save(hab);
		session.save(vi);
		session.save(vi2);
		// Consulta BD
		/*String hql = "From Hotel h";
		Query query = session.createQuery(hql);
		List results = query.list();
		Hotel h = (Hotel) results.get(0);
		System.out.println ("HOTEL: "+h.getNom()+" CIUTAT: "+h.getNomCiutat());
		/*System.out.println ("Hotel: "+hot.);
		 * 
		String var1 = "436453K";
		Date var2 = dini;
		ViatgePrimaryKey pk = new ViatgePrimaryKey("436453K", dini);
		String hql = "From Viatge v WHERE v.viatgePrimaryKey.dniClient= :code";
		Query query = session.createQuery(hql);
		List results = query.list();
		Viatge v = (Viatge) results.get(0);
		System.out.println ("DniClient: "+v.getDniClient()+" DataIni: "+v.getdataInici()+" Ciutat: "+v.getNomCiutat()+" Hotel: "+v.getNomHotel()+" DataFi: "+v.getDataFi());
		/*
		String hql2 = "From Data d";
		Query query2 = session.createQuery(hql2);
		List results2 = query2.list();
		Data dat = (Data) results2.get(0);
		Data dat2 = (Data) results2.get(1);
		System.out.println ("DataIni: "+dat.getDate());
		System.out.println ("DataFi: "+dat2.getDate());
		*/
		//para borrar el objeto de la DB
		//session.delete(X);
		
		
		//<=========================================TESTEAR JATEVIATGE, CLIENTNOEX
		//DomainCtrl dm = new DomainCtrl(cfg);
		//DATA
		
		Ciutat bcn = new Ciutat ("Barcelona", "La millor ciutat del mon", 80.0f);
		Client pep = new Client("436453K", "Pep", "54645456", 0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		@SuppressWarnings("deprecation")
		Date dIni= sdf.parse("01/01/2013");//dinici
		Date dFi=sdf.parse("05/01/2013");//dfi
		Date dIni2=sdf.parse("07/01/2013");
		Date dFi2=sdf.parse("09/01/2013");//dfi
		//session.save(bcn);
		//session.save(pep);
		Factory.setConfiguration (cfg);
		Factory f = Factory.getInstance();
		DomainCtrl dm = new DomainCtrl(f);
		CtrlClient ccl   = f.getCtrlClient();
		CtrlHotel chot = f.getCtrlHotel();
		CtrlCiutat cciu = f.getCtrlCiutat();
		Client c=ccl.get(pep.getDni());
		Ciutat ci= cciu.get(bcn.getNom());
		//Hotel h = new HotelSuperior(new HotelPrimaryKey("NH Hotel", "Barcelona"), ci, 50f, 15f );//Integer numero, String nomHotel, String nomCiutat
		//Habitacio hab = new Habitacio (new HabitacioPrimaryKey(1, h.getNom(), h.getNomCiutat()),h);
		//session.save(h);
		//session.save(hab);
		//CtrlViatge cv = f.getCtrlViatge();
		//Hotel h=chot.get("NH Hotel", "Barcelona");
		System.out.println("existe?===>"+chot.exists("NH Hotel", "Barcelona"));
		//System.out.println("<========no peto====>NOMHOTEL=="+h.getNom()+"   NOMCIUTAT=="+h.getNomCiutat());
		//System.out.println("ENREGISTRAVIATGE====>"+dm.enregistraViatge(pep.getDni(), dIni, dFi, "Barcelona"));
		//System.out.println("Reserva Habitacio ====> "+dm.reservaHabitacio(pep.getDni(), "NH Hotel", "Barcelona", dIni, dFi, 80));
		//System.out.println("obteciutats====> "+dm.obteCiutats().get(0).nomCiutat+" preu: "+dm.obteCiutats().get(0).preuVol);
		//System.out.println(" mostrahotelslliures==> "+dm.mostraHotelsLliures(pep.getDni(), bcn.getNom(), dIni, dFi).get(0).preuVol);
		//Viatge v = cv.get(pep.getDni(), dIni); //ERROR COMENTAR AL ABEL!!
		//Viatge v = new Viatge(new ViatgePrimaryKey(pep.getDni(), dIni), pep, bcn, null, dFi, null, null  );
		//List<Viatge> list = c.getViatges();
		//list.add(v);
		//c.setViatges(list);
		//int i=0;
		/*while (i<list.size()){
			System.out.println("dniclient "+list.get(0).getDniClient()+" dataini: "+list.get(i).getdataInici());
			i++;
		}*/
		//ccl.update(c);
		//session.save(v);
		//System.out.println("ENREGISTRAVIATGE====>"+dm.enregistraViatge(pep.getDni(), dIni, dFi, "Barcelona"));
		//System.out.println("ENREGISTRAVIATGE====>"+dm.enregistraViatge(pep.getDni(), dIni2, dFi2, "Barcelona"));
		dm.guardarCambios();
		
		//System.out.println("viatges client  ====> "+c.getViatges().size()+"  nombreviatges:  "+c.getNombreViatges());
		
		//System.out.println("EXJATEVIATGE====>"+dm.excJaTeViatge(pep.getDni(), dIni2, dFi2, bcn.getNom()));
		
		//System.out.println("EXISTE? ===>"+dm.exClientNoEx("436453K"));
		
		//<===================================================TESTEAR CONVERSION
		/*
		List<TupleCiutat> tc=new ArrayList<TupleCiutat>();
		
		TupleCiutat t1 = new TupleCiutat("Barcelona", 100f);
		TupleCiutat t2 = new TupleCiutat("Madrid", 150f);
		TupleCiutat t3 = new TupleCiutat("Sevilla", 30f);
		
		tc.add(t1);
		tc.add(t2);
		tc.add(t3);
		DomainCtrl dm = new DomainCtrl(cfg);
		String[][] s = dm.conversion(tc);
		System.out.println ("t1: "+s[0][0]+"  "+s[0][1]+"  "+s[1][0]+"  "+s[0][1]+"  "+s[2][0]+"  "+s[2][1]+"  ");
		*/
		session.getTransaction().commit();
	}

}
