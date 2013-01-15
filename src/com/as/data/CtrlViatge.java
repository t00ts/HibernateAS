package com.as.data;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CtrlViatge {

	public CtrlViatge(){}
	
	public List<Viatge> getAll(){
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Ciutat.class);
		cfg.addAnnotatedClass(Hotel.class);
		cfg.addAnnotatedClass(HotelSuperior.class);
		cfg.addAnnotatedClass(HotelLowCost.class);
		cfg.addAnnotatedClass(Habitacio.class);
		cfg.addAnnotatedClass(Client.class);
		cfg.addAnnotatedClass(Viatge.class);
		cfg.addAnnotatedClass(Data.class);
		cfg.configure ("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction ();
		String hql = "From Viatge v";
		Query query = session.createQuery(hql);
		List results = query.list();
		session.getTransaction().commit();
		return results;
	}
	
	
	@SuppressWarnings("deprecation")
	public Viatge get(String dniClient, Date dataIni){
		int i = 0;
		boolean b = false;
		Viatge v=null;
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Ciutat.class);
		cfg.addAnnotatedClass(Hotel.class);
		cfg.addAnnotatedClass(HotelSuperior.class);
		cfg.addAnnotatedClass(HotelLowCost.class);
		cfg.addAnnotatedClass(Habitacio.class);
		cfg.addAnnotatedClass(Client.class);
		cfg.addAnnotatedClass(Viatge.class);
		cfg.addAnnotatedClass(Data.class);
		cfg.configure ("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction ();
		String hql = "From Viatge v";
		Query query = session.createQuery(hql);
		List results = query.list();
		
		while(i<results.size() && b==false){
			v=(Viatge) results.get(i);
			if(v.getDniClient().matches(dniClient) && v.getdataInici().compareTo(dataIni)==0){
				b=true;
			}else{
				v=null;
			}
			i++;
			
		}
		session.getTransaction().commit();
		return v;
		
	}
	
	public boolean exists(String dniClient, Date dataIni){
		boolean b=true;
		if(get(dniClient, dataIni)==null)
			b=false;
		return b;
	}
	
}
