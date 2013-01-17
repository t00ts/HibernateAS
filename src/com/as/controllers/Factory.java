package com.as.controllers;

import org.hibernate.cfg.Configuration;

public class Factory {

	private static Factory Instance = null; 

	private static Configuration hibernateCfg = null;
	
	private CtrlViatge ctrlViatge = null; 
	private CtrlClient ctrlClient = null;
	private CtrlHabitacio ctrlHabitacio = null;
	private CtrlCiutat ctrlCiutat;
	/*private CtrlData ctrlData;
    private CtrlHotel ctrlHotel;
    private CtrlHotelLowCost ctrlHotelLowCost;
    private CtrlHotelSuperior ctrlHotelSuperior;
    private CtrlViatge ctrlViatge;
*/

	private Factory (Configuration cfg) {
		ctrlViatge = new CtrlViatge (cfg);
		ctrlClient = new CtrlClient (cfg);
	    ctrlHabitacio = new CtrlHabitacio (cfg);
	    ctrlCiutat = new CtrlCiutat (cfg);
	}

	
	public static Factory getInstance () {
		if (Instance == null) {
			Instance = new Factory (hibernateCfg);
		}
		return Instance;
	}
	
	public static void setConfiguration (Configuration cfg) {
		hibernateCfg = cfg;
	}
	
	public CtrlViatge getCtrlViatge () {
		return ctrlViatge;
	}

	public CtrlClient getCtrlClient () {
		return ctrlClient;
	}

	public CtrlHabitacio getCtrlHabitacio () {
		return ctrlHabitacio;
	}
	
	public CtrlCiutat getCtrlCiutat () {
		return ctrlCiutat;
	}
	
}
