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
	
		session.getTransaction().commit();
		
		return results;
    	
    }
    
    public Client get (String dni) {
    
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
	
    	String hql = new StringBuilder("").append("FROM Client c WHERE dni='").append(dni).append("'").toString();
		Query query = session.createQuery (hql);
		
		Client res = (Client) query.uniqueResult();
		
		session.getTransaction().commit();
		
        return res;
        
    }
    
    public boolean exists (String dni) {
    	return (get(dni) != null);
    }

    public void insert (Client c) {
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (c);
    	session.getTransaction().commit();
    }
    
    public void update (Client c) {
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(c);
    	session.getTransaction().commit();
    }
    
    public void delete (Client c) {
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(c);
    	session.getTransaction().commit();
    }
 
}
