package com.as.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.as.views.*;


public class CtrlInterface {

//Views
	   private  FinestraContractarViatges ContractarViatgeView2;
	   private  FinestraSeleccioViatge SeleccioViatgeView2;
	   private  FinestraReservaHabitacio ReservaHabitacioView2;
	   private  FinestraPagament PagamentView2;
	   private  Avis AvisView2;
	   
//Variables
	   private String CiutatSel, HotelSel;
	   private Integer PreuSel;
	   private String DniClient;
	   private Integer DataIni, DataFi;

	   /** Constructor */
	  public  CtrlInterface(FinestraContractarViatges ContractarViatgeView, FinestraSeleccioViatge SeleccioViatgeView, FinestraReservaHabitacio ReservaHabitacioView, FinestraPagament PagamentView) {
	    	ContractarViatgeView2 = ContractarViatgeView;
	    	SeleccioViatgeView2  = SeleccioViatgeView;
	    	ReservaHabitacioView2  = ReservaHabitacioView;
	    	PagamentView2  = PagamentView;
	        
	        //... Add listeners to the view.
	    	ContractarViatgeView2.addContractar_Listener(new Contractar_Listener());
	    	
	    	
	    	SeleccioViatgeView2.addConfirmar_SVListener(new Confirmar_SVListener());
	    	SeleccioViatgeView2.addCancel_1Listener(new Cancel_1Listener());
	    	
	    	
	    	ReservaHabitacioView2.addSelectionListener(new SelectionListener());
	    	ReservaHabitacioView2.addConfirmar_RHListener(new Confirmar_RHListener());
	    	ReservaHabitacioView2.addCancel_2Listener(new Cancel_2Listener());
	    	
	    	PagamentView2.addConfirmar_PListener(new Confirmar_PListener());
	    	PagamentView2.addCancel_1Listener(new Cancel_1Listener());
	    	
	    }
	    
	    
	    ////////////////////////////////////////////inner class Contractar_Listener
	    /**  Va a la vista de seleccio de viatge */

	    class Contractar_Listener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		ContractarViatgeView2.setVisible(false);
	    		SeleccioViatgeView2.setVisible(true);
	    		SeleccioViatgeView2.setResizable(false);
	    	}
	    }// end inner class Contractar_Listener
	    
	    
	    ////////////////////////////////////////////inner class Confirmar_SVListener
	    /**  Confirmar i enregistra el viatge, si el checkbox está activat aleshores va reservar hotel, si no a pagament */

	    class Confirmar_SVListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    }// end inner class Confirmar_SVListener
	    
	    
	    ////////////////////////////////////////////inner class Confirmar_RHListener
	    /**  Confirmar i enregistra el viatge, si el checkbox está activat aleshores va reservar hotel, si no a pagament */

	    class Confirmar_RHListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    }// end inner class Confirmar_RHListener

	    ////////////////////////////////////////////inner class Confirmar_PListener
	    /**  Confirmar el pagament, si no hi ha cap error */

	    class Confirmar_PListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    		
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
		        	  HotelSel = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
		        	  PreuSel = (Integer) table.getValueAt(selectedRow[i], selectedColumns[j+1]);
		        	}
		        }
	    	}

	    }// end inner class SelectionListener
	    
	    /** Confirma el pagament amb el servei corresponent */
	    
	    class SelectionListener implements ListSelectionListener {
	    	public void valueChanged(ListSelectionEvent e) {
		    	JTable table = ReservaHabitacioView2.get_table();
		        int[] selectedRow = table.getSelectedRows();
		        int[] selectedColumns = table.getSelectedColumns();

		        for (int i = 0; i < selectedRow.length; i++) {
		        	for (int j = 0; j < selectedColumns.length; j++) {
		        	  HotelSel = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
		        	  PreuSel = (Integer) table.getValueAt(selectedRow[i], selectedColumns[j+1]);
		        	}
		        }
	    	}

	    }
	 
}