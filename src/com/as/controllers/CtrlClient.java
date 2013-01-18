package com.as.controllers;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.as.data.Client;

public class CtrlClient {

	private Configuration hibernateCfg = null;

    public CtrlClient (Configuration cfg) {
    	hibernateCfg = cfg;
    }
    
    // getAll () - Returns all clients stored in the system
    public ArrayList<Client> getAll () {
    	
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		
		String hql = "From Client c";
		Query query = session.createQuery (hql);
		ArrayList<Client> results = (ArrayList<Client>) query.list();
	
		session.getTransaction().commit();
		
		return results;
    	
    }
    
    // get () - Returns client object by primary key
    public Client get (String dni) {
    
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
	
    	String hql = new StringBuilder("").append("FROM Client c WHERE dni='").append(dni).append("'").toString();
		Query query = session.createQuery (hql);
		
		Client res = (Client) query.uniqueResult();
		
		session.getTransaction().commit();
		
        return res;
        
    }
    
    // exists () - Returns true if client identified by primary key given exists in the ddbb
    public boolean exists (String dni) {
    	return (get(dni) != null);
    }

    // insert () - Inserts a client object into the ddbb
    public void insert (Client c) {
    	if (c == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (c);
    	session.getTransaction().commit();
    }
    
    // update () - Updates existing client object on the ddbb with new parameters
    public void update (Client c) {
    	if (c == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(c);
    	session.getTransaction().commit();
    }
   
    // delete () - Deletes existing client object from the ddbb
    public void delete (Client c) {
    	if (c == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(c);
    	session.getTransaction().commit();
    }
 
}
