package com.as.controllers;

import java.util.Date;

import org.hibernate.cfg.Configuration;

import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.Viatge;

import src.PagamentClient;

public class DomainCtrl {
	
	private Configuration hibernateCfg;
	
	public DomainCtrl(Configuration cfg){
		this.hibernateCfg = cfg;
		
	}
	
	public float reservaHabitacio(String dniClient, String nomHotel, String nomCiutat, Date dIni, Date dFi, float preuVol){
		float preuTotal=0;
		int numHab;
		float preuHab=0;
		Factory f=new Factory(hibernateCfg);
		
		CtrlViatge cv = f.getCtrlViatge();//falta hacer del abel por eso da error
		CtrlHabitacio ch=f.getCtrlHabitacio();
		CtrlHotel chot=f.getCtrlHotel();
		Hotel h=chot.get(nomHotel, nomCiutat);
		numHab=h.numHabLliure(dIni, dFi);
		Habitacio hab=ch.get(numHab, nomHotel, nomCiutat);
		Viatge v=cv.get(dniClient, dIni);
		preuHab=v.reserva(hab, dIni, dFi);
		preuTotal=preuVol+preuHab;
		return preuTotal;
	}
	
	
	public boolean pagament(Integer numTarg, Date dataCad) {
		try {
			//TODO hacer la funcion de pagament en vez de main
			PagamentClient.pagament(numTarg, dataCad);
		}
		catch {
			
		}
		return false;
	}
	
	//No tengo muy fresco como se hacian los sets y tuplas xD
	public set(Tuple ciut(Integer nom, Float preuVol))  obteCiutats() {
		
	}
	
	public set(Tuple hdisp(String nomC, String nom, Float preuHb))  mostraHotelsLliures() {
		
	}
	
	public void enregistraViatge(String dni, Date dataIni, Date dataFi, String nom) {
		
	}
	
	//Faltan algunas funciones de error, pero ya se pondran.
}

	
