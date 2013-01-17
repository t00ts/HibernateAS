package com.as.controllers;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import org.hibernate.cfg.Configuration;


import com.as.data.tuples.TupleCiutat;

import src.PagamentClient;


import com.as.views.*;


public class CtrlInterface {
	
//Domain Controller
		private DomainCtrl DC;
		private Configuration hibernateCfg;
	  

//Views
	   private  FinestraContractarViatges ContractarViatgeView2;
	   private  FinestraSeleccioViatge SeleccioViatgeView2;
	   private  FinestraReservaHabitacio ReservaHabitacioView2;
	   private  FinestraPagament PagamentView2;
	   private  Avis AvisView2;
	   
//Variables
	   private String Sel, CiutatSel;
	   private Integer PreuSel, PreuVol;
	   private String DniClient;
	   private String DataIni, DataFi;

	   /** Constructor */
	  public  CtrlInterface(FinestraContractarViatges ContractarViatgeView, FinestraPagament PagamentView, DomainCtrl DC2) {
	    	
		  	DC = DC2;
		    ContractarViatgeView2 = ContractarViatgeView;
	        
	        //... Add listeners to the view.
	    	ContractarViatgeView2.addContractar_Listener(new Contractar_Listener());
	    }
	    
	    
	    ////////////////////////////////////////////inner class Contractar_Listener
	    /**  Va a la vista de seleccio de viatge, sempre que existeixi alguna ciutat */

	    class Contractar_Listener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		List<TupleCiutat> ciutats = DC.obteCiutats();
	    		if(ciutats.isEmpty()) { //si esta buit
	    			ContractarViatgeView2.setVisible(false);
	    			AvisView2 = new Avis("nociutats");
	    			AvisView2.setVisible(true);
	    			AvisView2.setResizable(false);
	    			
	    			AvisView2.addSurtListener(new Cancel_1Listener());
	    		}else{
	    			ContractarViatgeView2.setVisible(false);
	    			String[][] ciu = DC.conversion(ciutats);
	    			SeleccioViatgeView2 = new FinestraSeleccioViatge(ciu);
	    			SeleccioViatgeView2.setVisible(true);
	    			SeleccioViatgeView2.setResizable(false);
	    			
	    			SeleccioViatgeView2.addSelectionListener(new SelectionListener());
	    			SeleccioViatgeView2.addConfirmar_SVListener(new Confirmar_SVListener());
	    	    	SeleccioViatgeView2.addCancel_1Listener(new Cancel_1Listener());
	    		}
	    	}
	    }// end inner class Contractar_Listener
	    
	    
	    ////////////////////////////////////////////inner class Confirmar_SVListener
	    /**  Confirmar i enregistra el viatge, si el checkbox está activat aleshores va reservar hotel, si no a pagament */

	    class Confirmar_SVListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		String DNI = SeleccioViatgeView2.get_DNI();
	    		List<TupleCiutat>  Hotels = DC.mostraHotelsLliures(DNI, Sel, Date dIni, Date dFi);
	    		String[][] hot  = DC.conversion(Hotels);
	    		if(DC.exClientNoEx(DNI)){
	    			SeleccioViatgeView2.setVisible(false);
	    			AvisView2 = new Avis("clientnoex");
	    			AvisView2.setVisible(true);
	    			AvisView2.setResizable(false);
	    			
	    			AvisView2.addSurtListener(new Cancel_3Listener());

	    		}else if(DC.excJaTeViatge(DNI, dataIni, dataFi,Sel)){

	    			SeleccioViatgeView2.setVisible(false);
	    			AvisView2 = new Avis("clientviatge");
	    			AvisView2.setVisible(true);
	    			AvisView2.setResizable(false);
	    			
	    			AvisView2.addSurtListener(new Cancel_3Listener());
	    		}else{
	    			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    		 	String DataIni = sdf.format(dataIni);
	    		 	String DataFi = sdf.format(dataFi);
	    		 	PreuVol = enregistraViatge(DNI, dataIni, dataFi, CiutatSel);
	    			if(true/*checklistener*/) {
	    				SeleccioViatgeView2.setVisible(false);
	    				ReservaHabitacioView2 = new FinestraReservaHabitacio(Sel, DataIni, DataFi, hot);
	    				
	    				ReservaHabitacioView2.addSelectionListener(new SelectionListener());
	    		    	ReservaHabitacioView2.addConfirmar_RHListener(new Confirmar_RHListener());
	    		    	ReservaHabitacioView2.addCancel_2Listener(new Cancel_2Listener());
		    		}else{
		    			SeleccioViatgeView2.setVisible(false);
		    			PagamentView2 = new PagamentView2(float PreuVol, dataIni, dataFi, Sel, DNI)
			    		PagamentView2.setVisible(true);
		    			PagamentView2.setResizable(false);
		    			
		    			PagamentView2.addConfirmar_PListener(new Confirmar_PListener());
		    	    	PagamentView2.addCancel_1Listener(new Cancel_1Listener());
		    		}
	    			CiutatSel = Sel;
	    			Sel = null;
	    		}

	    	  }
	    	}// end inner class Confirmar_SVListener
	    
	    
	    ////////////////////////////////////////////inner class Confirmar_RHListener
	    /**  Confirmar i enregistra el viatge, si el checkbox está activat aleshores va reservar hotel, si no a pagament */

	    class Confirmar_RHListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		ReservaHabitacioView2.setVisible(false);
	    		PagamentView2 = new PagamentView2(float preuTotal, Date dataIni, Date dataFi, Integer numTarg, Date dataCad, String nomHotel, String dni)
	    		PagamentView2.setVisible(true);
	    	}
	    }// end inner class Confirmar_RHListener

	    ////////////////////////////////////////////inner class Confirmar_PListener
	    /**  Confirmar el pagament, si no hi ha cap error */

	    class Confirmar_PListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
	    			//TODO numTarg y dataCad vienen de la interfaz anterior, ponerlas como privates
		    		if (DC.pagament(numTarg, dataCad)) {
		    			AvisView2 = new Avis("pagamentok");
	                    AvisView2.setVisible(true);
	                    AvisView2.setResizable(false);
	                    AvisView2.addSurtListener(new Cancel_1Listener());
		    		}
		    		else {
		    			AvisView2 = new Avis("pagamentnoau");
	                    AvisView2.setVisible(true);
	                    AvisView2.setResizable(false);
	                    AvisView2.addSurtListener(new Cancel_1Listener());
		    		}
	    		}
	    		catch (RemoteException ex) {
	    			//TODO lanzar ventana error de servicio no disponible
	    			AvisView2 = new Avis("pagamentnodisp");
                    AvisView2.setVisible(true);
                    AvisView2.setResizable(false);
                    AvisView2.addSurtListener(new Cancel_1Listener());
	    			ex.printStackTrace();
	    		}

	    	}
	    }// end inner class Confirmar_PListener*/
	    
	    ////////////////////////////////////////////inner class Cancel_1Listener
	    	/**  Surt de l'aplicacio */
  
	    class Cancel_1Listener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		System.exit(0);
	    	}
	    }// end inner class Cancel_1Listener
	    
	    ////////////////////////////////////////////inner class Cancel_2Listener
	    /**  Va la finestra de pagament */

	    class Cancel_2Listener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		ReservaHabitacioView2.setVisible(false);
	    		PagamentView2.setVisible(true);
	    		PagamentView2.setResizable(false);
	    	}
	    }// end inner class Cancel_2Listener
	 
	    
	    ////////////////////////////////////////////inner class Cancel_3Listener
	    /**  Va la finestra de Sel.leccioviatge desde avis */
	    class Cancel_3Listener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		SeleccioViatgeView2.setVisible(true);
	    		SeleccioViatgeView2.setResizable(false);
	    	}
	    }// end inner class Cancel_3Listener
	    
	    
	    ////////////////////////////////////////////inner class Cancel_4Listener
	    /**  Va la finestra de pagament desde avis */

	    class Cancel_4Listener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		PagamentView2.setVisible(true);
	    		PagamentView2.setResizable(false);
	    	}
	    }// end inner class Cancel_4Listener
	    
	    ////////////////////////////////////////////inner class SelectionListener
	    /**  Agafa els valors seleccionats de la taula */
	    class SelectionListener implements ListSelectionListener {
	    	public void valueChanged(ListSelectionEvent e) {
		    	JTable table = ReservaHabitacioView2.get_table();
		        int[] selectedRow = table.getSelectedRows();
		        int[] selectedColumns = table.getSelectedColumns();

		        for (int i = 0; i < selectedRow.length; i++) {
		        	for (int j = 0; j < selectedColumns.length; j++) {
		        	  Sel = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
		        	  PreuSel = (Integer) table.getValueAt(selectedRow[i], selectedColumns[j+1]);
		        	}
		        }
	    	}

	    }// end inner class SelectionListener

}