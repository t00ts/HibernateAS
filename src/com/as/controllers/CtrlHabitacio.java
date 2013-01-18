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
    
    public ArrayList<Habitacio> getAll () {
    	
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		
		String hql = "From Habitacio h";
		Query query = session.createQuery (hql);
		ArrayList<Habitacio> results = (ArrayList<Habitacio>) query.list();
	
		session.getTransaction().commit();
		
		return results;
    	
    }
    
    public Habitacio get (int numero, String nomHotel, String nomCiutat) {
    
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();

    	String hql = new StringBuilder("").append("FROM Habitacio h WHERE h.primaryKey=('").append(nomCiutat)
    									  .append("', '").append(nomHotel)
    									  .append("', '").append(numero).append("')")
    									  .toString();
    	
		Query query = session.createQuery (hql);
		
		Habitacio res = (Habitacio) query.uniqueResult();
		
		session.getTransaction().commit();
		
        return res;
        
    }
    
    public boolean exists (int numero, String nomHotel, String nomCiutat) {
    	return (get(numero, nomHotel, nomCiutat) != null);
    }

    public void insert (Habitacio h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction ();
    	session.save (h);
    	session.getTransaction().commit();
    }
    
    public void update (Habitacio h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.update(h);
    	session.getTransaction().commit();
    }
    
    public void delete (Habitacio h) {
    	if (h == null) return;
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	session.delete(h);
    	session.getTransaction().commit();
    }
 
}
