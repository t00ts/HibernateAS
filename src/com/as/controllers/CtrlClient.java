package com.as.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.as.data.Client;

public class CtrlClient {

	private Configuration hibernateCfg = null;

    public CtrlClient (Configuration cfg) {
    	hibernateCfg = cfg;
    }
    
    public ArrayList<Client> getAll () {
    	
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		
		String hql = "From Client c";
		Query query = session.createQuery (hql);
		ArrayList<Client> results = (ArrayList<Client>) query.list();
		
		return results;
    	
    }
    
    public Client get (String dni) {
    
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
	
    	String hql = new StringBuilder("").append("FROM Client c WHERE numero='").append(dni).append("'").toString();
		Query query = session.createQuery (hql);
		
		Client res = (Client) query.uniqueResult();
        return res;
        
    }
    
     public boolean exists (String dni) {

    	return (get(dni) != null);
    
    }
    
}
