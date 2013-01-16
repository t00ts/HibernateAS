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
		
		return results;
    	
    }
    
    public Habitacio get (HabitacioPrimaryKey hpk) {
    
    	Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();

    	String hql = new StringBuilder("").append("FROM Habitacio h WHERE h.habitacioPrimaryKey=('").append(hpk.getNumero())
    									  .append("', '").append(hpk.getNomHotel())
    									  .append("', '").append(hpk.getNomCiutat()).append("')")
    									  .toString();
    	
		Query query = session.createQuery (hql);
		
		Habitacio res = (Habitacio) query.uniqueResult();
        return res;
        
    }
    
    public boolean exists (HabitacioPrimaryKey hpk) {

    	return (get(hpk) != null);
    
    }
    
}
