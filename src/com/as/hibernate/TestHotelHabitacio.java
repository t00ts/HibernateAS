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

public class TestHotelHabitacio {

	public static void main(String[] args) {
		
		// Hibernate model configuration
		Configuration cfg = new Configuration();
		
		cfg.addAnnotatedClass(Ciutat.class);
		cfg.addAnnotatedClass(Hotel.class);
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
		hotelsBcn.add(new Hotel(new HotelPrimaryKey("NH Barcelona", "Barcelona"), 200.0f));
		hotelsBcn.add(new Hotel(new HotelPrimaryKey("Melia Barcelona", "Barcelona"), 190.0f));
		hotelsBcn.add(new Hotel(new HotelPrimaryKey("Hotel Barcelona", "Barcelona"), 90.0f));

		List<Hotel> hotelsMad = new ArrayList<Hotel>();
		hotelsMad.add(new Hotel (new HotelPrimaryKey("NH Madrid", "Madrid"), 190.0f));
		hotelsMad.add(new Hotel (new HotelPrimaryKey("Melia Castilla", "Madrid"), 210.0f));
		hotelsMad.add(new Hotel (new HotelPrimaryKey("Hotel Madrid", "Madrid"), 160.0f));
		
		List<Hotel> hotelsSev = new ArrayList<Hotel>();
		hotelsSev.add(new Hotel (new HotelPrimaryKey("Hotel Sevilla", "Sevilla"), 110.0f));
		

		// Crear habitaciones
		
		// NH Barcelona
		List<Habitacio> habsNHBcn = new ArrayList<Habitacio>();
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (1, "NH Barcelona", "Barcelona")));
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (2, "NH Barcelona", "Barcelona")));
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (3, "NH Barcelona", "Barcelona")));
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (4, "NH Barcelona", "Barcelona")));
		habsNHBcn.add(new Habitacio (new HabitacioPrimaryKey (5, "NH Barcelona", "Barcelona")));
		hotelsBcn.get(0).setHabitacions(habsNHBcn);


		// Insertar los hoteles
		for (int i = 0; i < hotelsBcn.size(); ++i) session.save (hotelsBcn.get(i));
		for (int i = 0; i < hotelsMad.size(); ++i) session.save (hotelsMad.get(i));
		for (int i = 0; i < hotelsSev.size(); ++i) session.save (hotelsSev.get(i));

		
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
		
		
		
	}

}
