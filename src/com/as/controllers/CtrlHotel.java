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
	
	public ArrayList<Hotel> getAll(){
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	
		String hql = "From Hotel h";
		Query query = session.createQuery(hql);
		
		ArrayList<Hotel> results = (ArrayList<Hotel>) query.list();
		
		session.getTransaction().commit();
		
		return results;
	}
	
	
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
	
	public boolean exists(String nomHotel, String nomCiutat){
		return get(nomHotel, nomCiutat)!=null;
	}
	
    public void insert (Hotel h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (h);
    	session.getTransaction().commit();
    }
    
    public void update (Hotel h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(h);
    	session.getTransaction().commit();
    }
    
    public void delete (Hotel h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(h);
    	session.getTransaction().commit();
    }
 
}
