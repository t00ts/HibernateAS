package com.as.controllers;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.as.data.Ciutat;
import com.as.data.Client;
import com.as.data.Data;
import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.HotelLowCost;
import com.as.data.HotelSuperior;
import com.as.data.Viatge;
import com.as.data.primarykeys.ViatgePrimaryKey;

public class CtrlViatge {
	private Configuration hibernateCfg = null;

    public CtrlViatge (Configuration cfg) {
    	hibernateCfg = cfg;
    }
	
	public List<Viatge> getAll(){
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
		String hql = "From Viatge v";
		Query query = session.createQuery(hql);
		List results = query.list();
		session.getTransaction().commit();
		return results;
	}
	
	
	@SuppressWarnings("deprecation")
	public Viatge get(String dniClient, Date dataIni){
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	ViatgePrimaryKey vpk= new ViatgePrimaryKey(dniClient, dataIni);
		String hql = new StringBuilder("").append("FROM Viatge v WHERE v.viatgePrimaryKey=('").append(vpk.getdniClient())
				  .append("', ").append(vpk.getdataInici()).append(")").toString();
		Query query = session.createQuery(hql);
		Viatge res = (Viatge) query.uniqueResult();
		
		session.getTransaction().commit();
		return res;
		
	}
	
	public boolean exists(String dniClient, Date dataIni){
		
		return get(dniClient,dataIni)!=null;
	}
	
}
