package com.as.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.as.data.Ciutat;

public class CtrlCiutat {

	private Configuration hibernateCfg = null;
	
    public CtrlCiutat (Configuration cfg) {
    	hibernateCfg = cfg;
    }

    // getAll () - Returns all cities stored in the system
    public ArrayList<Ciutat> getAll () {
    	
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		
		String hql = "From Ciutat c";
		Query query = session.createQuery (hql);
		ArrayList<Ciutat> results = (ArrayList<Ciutat>) query.list();
		
		session.getTransaction().commit();
		
		return results;
    	
    }
   
    // get () - Returns city object by primary key
    public Ciutat get (String nom) {
    
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
	
    	String hql = new StringBuilder("").append("FROM Ciutat c WHERE nom='").append(nom).append("'").toString();
		Query query = session.createQuery (hql);
		
		Ciutat res = (Ciutat) query.uniqueResult();
		
		session.getTransaction().commit();
		
        return res;
        
    }
   
    // exists () - Returns true if city identified by primary key given exists in the ddbb
    public boolean exists (String nom) {
    	return (get(nom) != null);
    }
    
    // insert () - Inserts a city object into the ddbb
    public void insert (Ciutat c) {
    	if (c == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (c);
    	session.getTransaction().commit();
    }

    // delete () - Deletes existing city object from the ddbb
    public void update (Ciutat c) {
    	if (c == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(c);
    	session.getTransaction().commit();
    }
    
    public void delete (Ciutat c) {
    	if (c == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(c);
    	session.getTransaction().commit();
    }
 
}
