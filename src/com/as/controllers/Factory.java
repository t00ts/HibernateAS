package com.as.controllers;

import org.hibernate.cfg.Configuration;

public class Factory {

	private Configuration hibernateCfg;
	private static Factory Instance = null; 
	private static CtrlViatge ctrlViatge = null; 
	private static CtrlClient ctrlClient = null;
	private static CtrlHabitacio ctrlHabitacio = null;
/*	private CtrlCiutat ctrlCiutat;
	private CtrlData ctrlData;
    private CtrlHotel ctrlHotel;
    private CtrlHotelLowCost ctrlHotelLowCost;
    private CtrlHotelSuperior ctrlHotelSuperior;
    private CtrlViatge ctrlViatge;
*/

	public Factory (Configuration cfg) {
		
		if (this.Instance != null) throw new IllegalStateException ("Already instantiated. Use getInstance() instead.");
		
		// Init factory and controllers
		this.hibernateCfg = cfg;
	
		//ctrlViatge = new CtrlViatge (cfg);
		ctrlClient = new CtrlClient (cfg);
	    ctrlHabitacio = new CtrlHabitacio (cfg);
	    
	}
	
	public Factory getInstance () {
		return this.Instance;
	}
	

/*	public static CtrlViatge getCtrlViatge () {
		return ctrlViatge;
	}*/

	public static CtrlClient getCtrlClient () {
		return ctrlClient;
	}

	public static CtrlHabitacio getCtrlHabitacio () {
		return ctrlHabitacio;
	}
}
