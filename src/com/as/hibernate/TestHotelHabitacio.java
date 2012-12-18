package com.as.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.data.primarykeys.*;
import com.as.data.Ciutat;
import com.as.data.Hotel;
import com.as.data.Habitacio;
import com.as.data.HotelLowCost;
import com.as.data.HotelSuperior;

public class TestHotelHabitacio {

	public static void main(String[] args) {
		
		// Hibernate model configuration
		Configuration cfg = new Configuration();
		
		cfg.addAnnotatedClass(Ciutat.class);
		cfg.addAnnotatedClass(Hotel.class);
		cfg.addAnnotatedClass(HotelSuperior.class);
		cfg.addAnnotatedClass(HotelLowCost.class);
		cfg.addAnnotatedClass(Habitacio.class);

		// Hibernate database configuration
		cfg.configure ("hibernate.cfg.xml");

		// Create tables
		new SchemaExport (cfg).create (true, true);

		// Session
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
			
		
		
		/* -------------------- INSERT DATA -------------------- */
		session.beginTransaction ();

		// Crear ciudades de prueba: Barcelona, Madrid y Sevilla
		Ciutat bcn = new Ciutat ("Barcelona", "La millor ciutat del mon", 80.0f);
		Ciutat mad = new Ciutat ("Madrid", "Capital de Espa–a", 60.0f);
		Ciutat sev = new Ciutat ("Sevilla", "Sur", 50.0f);

		// Crear hoteles en las tres ciudades
		List<Hotel> hotelsBcn = new ArrayList<Hotel>();
		hotelsBcn.add(new HotelSuperior(new HotelPrimaryKey("NH Barcelona", "Barcelona"), 200.0f, 0));
		hotelsBcn.add(new HotelSuperior(new HotelPrimaryKey("Melia Barcelona", "Barcelona"), 190.0f, 0));
		hotelsBcn.add(new HotelLowCost(new HotelPrimaryKey("Hotel Barcelona", "Barcelona"), 90.0f, 30.0f));

		List<Hotel> hotelsMad = new ArrayList<Hotel>();
		hotelsMad.add(new HotelSuperior (new HotelPrimaryKey("NH Madrid", "Madrid"), 190.0f, 0));
		hotelsMad.add(new HotelLowCost (new HotelPrimaryKey("Melia Castilla", "Madrid"), 210.0f, 30.0f));
		hotelsMad.add(new HotelLowCost (new HotelPrimaryKey("Hotel Madrid", "Madrid"), 160.0f, 0));
		
		List<Hotel> hotelsSev = new ArrayList<Hotel>();
		hotelsSev.add(new HotelSuperior (new HotelPrimaryKey("Hotel Sevilla", "Sevilla"), 110.0f, 10.0f));
		hotelsSev.add(new HotelSuperior (new HotelPrimaryKey ("Hotel Superior Sevilla", "Sevilla"), 300.0f, 100.0f));
		hotelsSev.add(new HotelLowCost (new HotelPrimaryKey ("Hotel LowCost Sevilla", "Sevilla"), 80.0f, 25.0f));

		// Crear habitaciones
		
		// NH Barcelona
		List<Habitacio> habsNHBcn = new ArrayList<Habitacio>();
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (1, "NH Barcelona", "Barcelona")));
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (2, "NH Barcelona", "Barcelona")));
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (3, "NH Barcelona", "Barcelona")));
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (4, "NH Barcelona", "Barcelona")));
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (5, "NH Barcelona", "Barcelona")));
		hotelsBcn.get(0).setHabitacions(habsNHBcn);

		// Hotel Superior Sevilla
		List<Habitacio> habsSupSev = new ArrayList<Habitacio>();
		habsSupSev.add (new Habitacio (new HabitacioPrimaryKey (1, "Hotel Superior Sevilla", "Sevilla")));
		habsSupSev.add (new Habitacio (new HabitacioPrimaryKey (2, "Hotel Superior Sevilla", "Sevilla")));
		habsSupSev.add (new Habitacio (new HabitacioPrimaryKey (3, "Hotel Superior Sevilla", "Sevilla")));
		habsSupSev.add (new Habitacio (new HabitacioPrimaryKey (4, "Hotel Superior Sevilla", "Sevilla")));
		
		// Hotel LowCost Sevilla
		List<Habitacio> habsLCSev = new ArrayList<Habitacio>();
		habsLCSev.add (new Habitacio (new HabitacioPrimaryKey (1, "Hotel LowCost Sevilla", "Sevilla")));
		habsLCSev.add (new Habitacio (new HabitacioPrimaryKey (2, "Hotel LowCost Sevilla", "Sevilla")));
		habsLCSev.add (new Habitacio (new HabitacioPrimaryKey (3, "Hotel LowCost Sevilla", "Sevilla")));
		habsLCSev.add (new Habitacio (new HabitacioPrimaryKey (4, "Hotel LowCost Sevilla", "Sevilla")));
		
		// Asignar hoteles a las habitaciones
		for (int i = 0; i < habsNHBcn.size(); ++i) {
			habsNHBcn.get(i).setHotel (hotelsBcn.get(0));
		}
		for (int i = 0; i < habsSupSev.size(); ++i) {
			habsSupSev.get(i).setHotel(hotelsSev.get(1));
		}
		for (int i = 0; i < habsLCSev.size(); ++i) {
			habsLCSev.get(i).setHotel(hotelsSev.get(2));
		}
		
		// Asignar ciudad a los hoteles e insertarlos
		for (int i = 0; i < hotelsBcn.size(); ++i) {
			hotelsBcn.get(i).setCiutat (bcn);
			session.save (hotelsBcn.get(i));
		}
		for (int i = 0; i < hotelsMad.size(); ++i) {
			hotelsMad.get(i).setCiutat (mad);
			session.save (hotelsMad.get(i));
		}
		for (int i = 0; i < hotelsSev.size(); ++i) {
			hotelsSev.get(i).setCiutat (sev);
			session.save (hotelsSev.get(i));
		}

		// Asignar los hoteles a las ciudades
		bcn.setHotels(hotelsBcn);
		mad.setHotels(hotelsMad);
		sev.setHotels(hotelsSev);
		
		// Guardar ciudades - Por cascada guardara lo demas
		session.save(bcn);
		session.save(mad);
		session.save(sev);
		
		session.getTransaction().commit();

		/* ------------------- /INSERT DATA -------------------- */
		
		
		
		
		/* --------------------- LOAD DATA --------------------- */
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		// Leer HotelSuperior NH Barcelona
		HotelSuperior nhbcn = (HotelSuperior) session.load(HotelSuperior.class, new HotelPrimaryKey ("NH Barcelona", "Barcelona"));
		
		// Listar habitaciones
		List<Habitacio> habitacions = nhbcn.getHabitacions();
		System.out.println ("Habitacions de " + nhbcn.getNom());
		for (int i = 0; i < habitacions.size(); ++i) {
			System.out.println (habitacions.get(i).getNumero());
		}
		
		System.out.println();
		
		// Informacion de la ciudad
		Ciutat ciutat_nh = nhbcn.getCiutat ();
		System.out.println ("L'hotel esta a " + ciutat_nh.getNom());
		System.out.println (ciutat_nh.getNom() + " Žs " + ciutat_nh.getDescripcio());
		System.out.println ("El preu de vol aproximat es de " + ciutat_nh.getPreuVol() + " euros.");
		
		
		session.getTransaction().commit();
		
		/* --------------------- /LOAD DATA -------------------- */
		
	}

}
