package com.as.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.sql.Select;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.data.Ciutat;
import com.as.data.Client;
import com.as.data.Data;
import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.HotelLowCost;
import com.as.data.HotelSuperior;
import com.as.data.Viatge;
import com.as.data.primarykeys.HabitacioPrimaryKey;
import com.as.data.primarykeys.HotelPrimaryKey;
import com.as.data.primarykeys.ViatgePrimaryKey;

public class TestTodo {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
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
		/* -------------------- INSERT DATA -------------------- */
		session.beginTransaction ();
		//Crear base de datos simple
		//Query query1 = session.createQuery("delete Ciutat");//borra todo por cascada creo
		//int result = query1.executeUpdate();
		Ciutat bcn = new Ciutat ("Barcelona", "La millor ciutat del mon", 80.0f);
		Client pep = new Client("436453K", "Pep", "54645456", 0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		@SuppressWarnings("deprecation")
		Date dini= sdf.parse("01/01/2013");//dinici
		Date dfi=sdf.parse("05/01/2013");//dfi
		Data data = new Data(dini);
		Data data2 = new Data(dfi);
		HotelSuperior hot = new HotelSuperior(new HotelPrimaryKey("Hotel NH", bcn.getNom()), bcn, 100.0f, 20.0f);
		
		Habitacio hab = new Habitacio(new HabitacioPrimaryKey(1, hot.getNom(), hot.getNomCiutat()), hot);
		//Viatge vi = new Viatge(new ViatgePrimaryKey("436453K",data.getDate()), pep, hab.getHotel().getCiutat(), hab, data, dfi, hab.getHotel().getNom(), hab.getNumero());
		
		//guardamos datos en BD
		session.save(data);
		session.save(data2);
		session.save(bcn);
		session.save(pep);
		session.save(hot);
		session.save(hab);
		//session.save(vi);
		// Consulta BD
		String hql = "From Hotel h";
		Query query = session.createQuery(hql);
		List results = query.list();
		Hotel h = (Hotel) results.get(0);
		System.out.println ("HOTEL: "+h.getNom()+" CIUTAT: "+h.getNomCiutat());
		/*System.out.println ("Hotel: "+hot.);
		String hql = "From Viatge v";
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
		
		
		session.getTransaction().commit();
	}

}
