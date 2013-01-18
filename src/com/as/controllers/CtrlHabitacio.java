package com.as.controllers;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.as.data.Habitacio;
import com.as.data.primarykeys.HabitacioPrimaryKey;

public class CtrlHabitacio {

	private Configuration hibernateCfg = null;

    public CtrlHabitacio (Configuration cfg) {
    	hibernateCfg = cfg;
    }
    
    // getAll () - Returns all rooms stored in the system
    public ArrayList<Habitacio> getAll () {
    	
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		
		String hql = "From Habitacio h";
		Query query = session.createQuery (hql);
		ArrayList<Habitacio> results = (ArrayList<Habitacio>) query.list();
	
		session.getTransaction().commit();
		
		return results;
    	
    }
    
    // get () - Returns room object by primary key
    public Habitacio get (int numero, String nomHotel, String nomCiutat) {
    
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();

    	String hql = new StringBuilder("").append("FROM Habitacio h WHERE h.primaryKey=('").append(nomCiutat)
    									  .append("', '").append(nomHotel)
    									  .append("', ").append(numero).append(")")
    									  .toString();
    
    	System.out.println (hql);
		Query query = session.createQuery (hql);
		
		Habitacio res = (Habitacio) query.uniqueResult();
		
		session.getTransaction().commit();
		
        return res;
        
    }
    
    // exists () - Returns true if room identified by primary key given exists in the ddbb
    public boolean exists (int numero, String nomHotel, String nomCiutat) {
    	return (get(numero, nomHotel, nomCiutat) != null);
    }

    // insert () - Inserts a room object into the ddbb
    public void insert (Habitacio h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (h);
    	session.getTransaction().commit();
    }
    
    // update () - Updates existing room object on the ddbb with new parameters
    public void update (Habitacio h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(h);
    	session.getTransaction().commit();
    }
    
    // delete () - Deletes existing room object from the ddbb
    public void delete (Habitacio h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(h);
    	session.getTransaction().commit();
    }
 
}
