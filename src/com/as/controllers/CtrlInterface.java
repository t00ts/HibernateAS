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
	   private float PreuSel;
	   private float PreuVol;
	   private String DniClient;
	   private String DataIni, DataFi;
	   private Date dIni, dFi;
	   private float PreuTotal;
	   private String[][] ciu;

	   /** Constructor */
	  public  CtrlInterface(FinestraContractarViatges ContractarViatgeView, DomainCtrl DC2) {
	    	
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
	    			ciu = DC.conversion(ciutats);
	    			
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
	    /**  Confirmar i enregistra el viatge, si el checkbox est� activat aleshores va reservar hotel, si no a pagament */

	    class Confirmar_SVListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		DniClient = SeleccioViatgeView2.get_DNI();
	    		Date[] dates = SeleccioViatgeView2.get_dates();
	    	
	    		dIni = dates[0];
	    		dFi = dates[1];
	    		
	    		if(!DC.exClientNoEx(DniClient)){ //si no hi ha clients
	    			SeleccioViatgeView2.setVisible(false);
	    			AvisView2 = new Avis("clientnoex");
	    			AvisView2.setVisible(true);
	    			AvisView2.setResizable(false);
	    			
	    			AvisView2.addSurtListener(new Cancel_3Listener());

	    		}else if(DC.excJaTeViatge(DniClient, dIni, dFi,Sel)){ // si ja existeix el viatge

	    			SeleccioViatgeView2.setVisible(false);
	    			AvisView2 = new Avis("clientviatge");
	    			AvisView2.setVisible(true);
	    			AvisView2.setResizable(false);
	    			
	    			AvisView2.addSurtListener(new Cancel_3Listener());
	    		}else{
	    			
	    			if ((dates[0] == null || dates[1] == null) || (!DC.dataOk(dates[0], dates[1]))){ // si la data no s'ha introduit o es invalida
	    				SeleccioViatgeView2.setVisible(false);
		    			SeleccioViatgeView2 = new FinestraSeleccioViatge(ciu);
		    			SeleccioViatgeView2.setVisible(true);
		    			SeleccioViatgeView2.setResizable(false);
		    			
		    			SeleccioViatgeView2.addSelectionListener(new SelectionListener());
		    			SeleccioViatgeView2.addConfirmar_SVListener(new Confirmar_SVListener());
		    	    	SeleccioViatgeView2.addCancel_1Listener(new Cancel_1Listener());
	    			}else{

	    				System.out.println (DniClient);
	    				System.out.println (dIni);
	    				System.out.println (dFi);

	    				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    				DataIni = sdf.format(dIni);
	    				DataFi = sdf.format(dFi);
	    				CiutatSel = Sel;

	    				System.out.println (CiutatSel);

	    				PreuVol = DC.enregistraViatge(DniClient, dIni, dFi, CiutatSel);
	    				
	    		 		if(SeleccioViatgeView2.get_check()) { //si s'ha premut el check del checkbox
	    		 			List<TupleCiutat>  Hotels = DC.mostraHotelsLliures(DniClient, Sel, dIni, dFi);
	    		 			String[][] hot  = DC.conversion(Hotels);
	    		 			
	    		 			if(Hotels.isEmpty()) { // si no hi ha hotels
	    		 				SeleccioViatgeView2.setVisible(false);
	    						AvisView2 = new Avis("nohotels");
	    	    				AvisView2.setVisible(true);
	    	    				AvisView2.setResizable(false);
	    	    			
	    	    				AvisView2.addSurtListener(new Cancel_2Listener_v2());
	    		 			}else{
	    		 				SeleccioViatgeView2.setVisible(false);
	    		 				ReservaHabitacioView2 = new FinestraReservaHabitacio(Sel, DataIni, DataFi, hot);
	    				
	    		 				ReservaHabitacioView2.setVisible(true);
	    		 				ReservaHabitacioView2.setResizable(false);
	    		 				ReservaHabitacioView2.addSelectionListener(new SelectionListenerRH());
	    		 				ReservaHabitacioView2.addConfirmar_RHListener(new Confirmar_RHListener());
	    		 				ReservaHabitacioView2.addCancel_2Listener(new Cancel_2Listener());
	    		 			}
		    			}else{
		    				SeleccioViatgeView2.setVisible(false);
		    				PagamentView2 = new FinestraPagament(PreuSel, DataIni, DataFi, Sel, DniClient);
		    				PagamentView2.setVisible(true);
		    				PagamentView2.setResizable(false);
		    			

		    				PagamentView2.addConfirmar_PListener(new Confirmar_PListener());
		    				PagamentView2.addCancel_1Listener(new Cancel_1Listener());
		    			}
	    			}

	    		}
	    		Sel = null;
	    	  }
	    	}// end inner class Confirmar_SVListener
	    
	    ////////////////////////////////////////////inner class Confirmar_RHListener
	    /**  Confirmar i enregistra el viatge, si el checkbox est� activat aleshores va reservar hotel, si no a pagament */

	    class Confirmar_RHListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		PreuTotal = DC.reservaHabitacio(DniClient, Sel, CiutatSel, dIni, dFi, PreuSel);
	    		ReservaHabitacioView2.setVisible(false);
	    		PagamentView2 = new FinestraPagament(PreuTotal, DataIni, DataFi, Sel, DniClient);
	    		PagamentView2.setResizable(false);
	    		PagamentView2.setVisible(true);
	    		
				PagamentView2.addConfirmar_PListener(new Confirmar_PListener());
				PagamentView2.addCancel_1Listener(new Cancel_1Listener());
	    	}
	    }// end inner class Confirmar_RHListener

	    ////////////////////////////////////////////inner class Confirmar_PListener
	    /**  Confirmar el pagament, si no hi ha cap error */

    class Confirmar_PListener implements ActionListener {
    	
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println ("aaaaaaaaa");
	    		Integer numTarg = Integer.parseInt(PagamentView2.get_numTarg());
	    		String sdataCad = PagamentView2.get_dataCad();
	    		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
	    		Date ddataCad = null;
	    		try { ddataCad= sdf.parse(sdataCad); } catch (Exception exc){} 

    			String aut = DC.pagament(numTarg, ddataCad);
	    		if (aut.equals("Autoritzat")) {
	    			AvisView2 = new Avis("pagamentok");
                    AvisView2.setVisible(true);
                    AvisView2.setResizable(false);
                    AvisView2.addSurtListener(new Cancel_1Listener());
	    		}
	    		else if (aut.equals("NoAutoritzat")){
	    			AvisView2 = new Avis("pagamentnoau");
                    AvisView2.setVisible(true);
                    AvisView2.setResizable(false);
                    AvisView2.addSurtListener(new Cancel_1Listener());
	    		}
	    		else {
	    			AvisView2 = new Avis("pagamentnodisp");
                    AvisView2.setVisible(true);
                    AvisView2.setResizable(false);
                    AvisView2.addSurtListener(new Cancel_1Listener());
	    		}


	    	}
	    }// end inner class Confirmar_PListener
	    
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
	    		PagamentView2 = new FinestraPagament(PreuSel, DataIni, DataFi, CiutatSel, DniClient);
	    		PagamentView2.setVisible(true);
	    		PagamentView2.setResizable(false);
	    		
	    		PagamentView2.addConfirmar_PListener(new Confirmar_PListener());
    	    	PagamentView2.addCancel_1Listener(new Cancel_1Listener());
	    	}
	    }// end inner class Cancel_2Listener
	    
	    ////////////////////////////////////////////inner class Cancel_2Listener_v2
	    /**  Va la finestra de pagament */

	    class Cancel_2Listener_v2 implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		AvisView2.setVisible(false);
	    		PagamentView2 = new FinestraPagament(PreuSel, DataIni, DataFi, CiutatSel, DniClient);
	    		PagamentView2.setVisible(true);
	    		PagamentView2.setResizable(false);
	    		
	    		PagamentView2.addConfirmar_PListener(new Confirmar_PListener());
    	    	PagamentView2.addCancel_1Listener(new Cancel_1Listener());
	    	}
	    }// end inner class Cancel_2Listener_v2
	 
	    
	    ////////////////////////////////////////////inner class Cancel_3Listener
	    /**  Va la finestra de Sel.leccioviatge desde avis */
	    class Cancel_3Listener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		AvisView2.setVisible(false);
	    		SeleccioViatgeView2.setVisible(true);
	    		SeleccioViatgeView2.setResizable(false);
	    	}
	    }// end inner class Cancel_3Listener
	    

	    
	    ////////////////////////////////////////////inner class SelectionListener
	    /**  Agafa els valors seleccionats de la taula */
	    class SelectionListener implements ListSelectionListener {
	    	public void valueChanged(ListSelectionEvent e) {
		    	JTable table = SeleccioViatgeView2.get_table();
		        int[] selectedRow = table.getSelectedRows();

		        for (int i = 0; i < selectedRow.length; i++) {

		        	  Sel = (String) table.getValueAt(selectedRow[i], 0);
		        	  String Preuaux = (String) table.getValueAt(selectedRow[i], 1);
		        	  PreuSel = Float.parseFloat(Preuaux);

		        }
		        JButton baux = SeleccioViatgeView2.get_button();
		        baux.setEnabled(true);
	    	}

	    }// end inner class SelectionListener
	    
	    
	    ////////////////////////////////////////////inner class SelectionListenerRH
	    /**  Agafa els valors seleccionats de la taula */
	    class SelectionListenerRH implements ListSelectionListener {
	    	public void valueChanged(ListSelectionEvent e) {
		    	JTable table = ReservaHabitacioView2.get_table();
		    	  int[] selectedRow = table.getSelectedRows();

			        for (int i = 0; i < selectedRow.length; i++) {

			        	  Sel = (String) table.getValueAt(selectedRow[i], 0);
			        	  String Preuaux = (String) table.getValueAt(selectedRow[i], 1);
			        	  PreuSel = Float.parseFloat(Preuaux);

			        }
			        JButton baux = ReservaHabitacioView2.get_button();
			        baux.setEnabled(true);
		    	}

	    }// end inner class SelectionListenerRH

}