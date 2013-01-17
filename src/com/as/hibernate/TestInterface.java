package com.as.hibernate;

import net.sf.cglib.proxy.Factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.as.controllers.CtrlInterface;
import com.as.data.Ciutat;
import com.as.views.Avis;
import com.as.views.FinestraContractarViatges;
import com.as.views.FinestraPagament;
import com.as.views.FinestraReservaHabitacio;
import com.as.views.FinestraSeleccioViatge;

public class TestInterface {

	public static void main(String[] args) {
	
		FinestraContractarViatges ContractarViatgeView2 = new FinestraContractarViatges();
		FinestraReservaHabitacio ReservaHabitacioView2 = new FinestraReservaHabitacio ();
		FinestraPagament PagamentView2 = new FinestraPagament ();

		CtrlInterface CI = new CtrlInterface(ContractarViatgeView2, ReservaHabitacioView2, PagamentView2);
		
		ContractarViatgeView2.setVisible(true);
		ContractarViatgeView2.setResizable(false);
	}
	
}
