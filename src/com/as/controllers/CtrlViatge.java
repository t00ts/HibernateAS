package com.as.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.as.data.Viatge;

public class CtrlViatge {

	private Configuration hibernateCfg = null;

    public CtrlViatge (Configuration cfg) {
    	hibernateCfg = cfg;
    }
	
    // getAll () - Returns all trips stored in the system
	public ArrayList<Viatge> getAll(){
	
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();

    	String hql = "From Viatge v";
		Query query = session.createQuery(hql);
		ArrayList<Viatge> results = (ArrayList<Viatge>) query.list();

		session.getTransaction().commit();
		
		return results;
	}
	
    // get () - Returns trip object by primary key
	public Viatge get (String dniClient, Date dataIni) {
		
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    
    	/*Query query = session.createQuery("FROM Viatge v WHERE v.primaryKey = ( ?1 , ?2 )");
    	query.setParameter(1, dniClient);
    	query.setParameter(2, dataIni);*/
    	
    	String hql = new StringBuilder("").append("FROM Viatge v WHERE v.primaryKey = ('").append(new Timestamp(dataIni.getTime())).append("', '").append(dniClient).append("')").toString();
    
    	System.out.println (hql);
	
    	Query query = session.createQuery (hql);
    	Viatge res = (Viatge) query.uniqueResult();
		
		session.getTransaction().commit();
		
		return res;
		
	}
	
    // exists () - Returns true if trip identified by primary key given exists in the ddbb
	public boolean exists(String dniClient, Date dataIni) {
		return get(dniClient,dataIni)!=null;
	}
	
    // insert () - Inserts a trip object into the ddbb
    public void insert (Viatge v) {
    	if (v == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (v);
    	session.getTransaction().commit();
    }
   
    // update () - Updates existing trip object on the ddbb with new parameters
    public void update (Viatge v) {
    	if (v == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(v);
    	session.getTransaction().commit();
    }
    
    // delete () - Deletes existing trip object from the ddbb
    public void delete (Viatge v) {
    	if (v == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(v);
    	session.getTransaction().commit();
    }
}
