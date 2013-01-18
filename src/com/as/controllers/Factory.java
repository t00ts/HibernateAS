package com.as.controllers;

import org.hibernate.cfg.Configuration;

public class Factory {

	// Factory singleton instance
	private static Factory Instance = null; 

	// Hibernate configuration
	private static Configuration hibernateCfg = null;

	// Data controllers
	private CtrlViatge ctrlViatge = null; 
	private CtrlClient ctrlClient = null;
	private CtrlHabitacio ctrlHabitacio = null;
	private CtrlCiutat ctrlCiutat = null;
	private CtrlHotel ctrlHotel = null;

	// Factory constructor - Instantiate controllers
	private Factory (Configuration cfg) {
		ctrlViatge = new CtrlViatge (cfg);
		ctrlClient = new CtrlClient (cfg);
	    ctrlHabitacio = new CtrlHabitacio (cfg);
	    ctrlCiutat = new CtrlCiutat (cfg);
	    ctrlHotel = new CtrlHotel (cfg);
	}

	// getInstance () - Returns singleton Factory instance
	public static Factory getInstance () {
		if (Instance == null) {
			Instance = new Factory (hibernateCfg);
		}
		return Instance;
	}

	// setConfiguration (cfg) - Sets hibernate configuration for controllers
	public static void setConfiguration (Configuration cfg) {
		hibernateCfg = cfg;
	}

	// getCtrlViatge () - Returns CtrlViatge instance
	public CtrlViatge getCtrlViatge () {
		return ctrlViatge;
	}

	// getCtrlClient () - Returns CtrlClient instance
	public CtrlClient getCtrlClient () {
		return ctrlClient;
	}

	// getCtrlHabitacio () - Returns CtrlHabitacio instance
	public CtrlHabitacio getCtrlHabitacio () {
		return ctrlHabitacio;
	}
	
	// getCtrlCiutat () - Returns CtrlCiutat instance
	public CtrlCiutat getCtrlCiutat () {
		return ctrlCiutat;
	}
	
	// getCtrlHotel () - Returns CtrlHotel instance
	public CtrlHotel getCtrlHotel () {
		return ctrlHotel;
	}
	
}
