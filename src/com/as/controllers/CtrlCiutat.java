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
    
    public ArrayList<Ciutat> getAll () {
    	
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		
		String hql = "From Ciutat c";
		Query query = session.createQuery (hql);
		ArrayList<Ciutat> results = (ArrayList<Ciutat>) query.list();
		
		return results;
    	
    }
    
    public Ciutat get (String nom) {
    
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
	
    	String hql = new StringBuilder("").append("FROM Ciutat c WHERE nom='").append(nom).append("'").toString();
		Query query = session.createQuery (hql);
		
		Ciutat res = (Ciutat) query.uniqueResult();
        return res;
        
    }
    
     public boolean exists (String nom) {

    	return (get(nom) != null);
    
    }
    
}
