package com.as.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.as.data.Ciutat;
import com.as.data.Client;
import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.HotelLowCost;
import com.as.data.HotelSuperior;
import com.as.data.Viatge;
import com.as.data.primarykeys.HotelPrimaryKey;
import com.as.data.primarykeys.ViatgePrimaryKey;

public class CtrlHotel {
	private Configuration hibernateCfg = null;

    public CtrlHotel (Configuration cfg) {
    	hibernateCfg = cfg;
    }
	
	public ArrayList<Hotel> getAll(){
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	
		String hql = "From Hotel h";
		Query query = session.createQuery(hql);
		
		ArrayList<Hotel> results = (ArrayList<Hotel>) query.list();
		
		session.getTransaction().commit();
		
		return results;
	}
	
	
	@SuppressWarnings("deprecation")
	public Hotel get(String nomHotel, String nomCiutat){
		
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		String hql = new StringBuilder("").append("FROM Hotel h WHERE h.HotelPrimaryKey=('").append(nomHotel)
				.append("', '").append(nomCiutat).append("')").toString();
		Query query = session.createQuery(hql);
		Hotel res = (Hotel) query.uniqueResult();
		
		session.getTransaction().commit();
		return res;
	
	}
	
	public boolean exists(String nomHotel, String nomCiutat){
		return get(nomHotel, nomCiutat)!=null;
	}
	
    public void insert (Hotel h) {
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (h);
    	session.getTransaction().commit();
    }
    
    public void update (Hotel h) {
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(h);
    	session.getTransaction().commit();
    }
    
    public void delete (Hotel h) {
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(h);
    	session.getTransaction().commit();
    }
 
}
