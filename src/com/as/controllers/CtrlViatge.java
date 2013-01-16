package com.as.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

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
		ArrayList<Viatge> results = (ArrayList<Viatge>) query.list();
	
		return results;
	}
	
	
	public Viatge get (String dniClient, Date dataIni){
		
		Session session = hibernateCfg.buildSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	
		String hql = new StringBuilder("").append("FROM Viatge v WHERE v.viatgePrimaryKey=('").append(dniClient)
				  .append("', ").append(dataIni).append(")").toString();
		Query query = session.createQuery(hql);
		Viatge res = (Viatge) query.uniqueResult();
		
		return res;
		
	}
	
	public boolean exists(String dniClient, Date dataIni) {
		return get(dniClient,dataIni)!=null;
	}
	
}
