package com.as.controllers;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.as.data.Hotel;

public class CtrlHotel {
	private Configuration hibernateCfg = null;

    public CtrlHotel (Configuration cfg) {
    	hibernateCfg = cfg;
    }
	
    // getAll () - Returns all hotels stored in the system
	public ArrayList<Hotel> getAll(){
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	
		String hql = "From Hotel h";
		Query query = session.createQuery(hql);
		
		ArrayList<Hotel> results = (ArrayList<Hotel>) query.list();
		
		session.getTransaction().commit();
		
		return results;
	}
	
	
    // get () - Returns hotel object by primary key
	public Hotel get(String nomHotel, String nomCiutat){
		
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		String hql = new StringBuilder("").append("FROM Hotel h WHERE h.primaryKey=('").append(nomCiutat)
				.append("', '").append(nomHotel).append("')").toString();
		
		System.out.println (hql);
		
		Query query = session.createQuery(hql);
		Hotel res = (Hotel) query.uniqueResult();
		
		session.getTransaction().commit();
		return res;
	
	}
	
    // exists () - Returns true if hotel identified by primary key given exists in the ddbb
	public boolean exists(String nomHotel, String nomCiutat){
		return get(nomHotel, nomCiutat)!=null;
	}
	
    // insert () - Inserts a hotel object into the ddbb
    public void insert (Hotel h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (h);
    	session.getTransaction().commit();
    }
    
    // update () - Updates existing hotel object on the ddbb with new parameters
    public void update (Hotel h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(h);
    	session.getTransaction().commit();
    }
    
    // delete () - Deletes existing hotel object from the ddbb
    public void delete (Hotel h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(h);
    	session.getTransaction().commit();
    }
 
}
