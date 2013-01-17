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


	   private  FinestraContractarViatges ContractarViatgeView2;
	   private  FinestraSeleccioViatge SeleccioViatgeView2;
	   private  FinestraReservaHabitacio ReservaHabitacioView2;
	   private  FinestraPagament PagamentView2;
	   private  AvisNoCiutats NoCiutatsView2;
	   private String DniClient;
	   private Integer DataIni, DataFi;

	   /** Constructor */
	    CtrlInterface(FinestraContractarViatges ContractarViatgeView, FinestraSeleccioViatge SeleccioViatgeView, FinestraReservaHabitacio ReservaHabitacioView, FinestraPagament PagamentView, AvisNoCiutats NoCiutatsView) {
	    	ContractarViatgeView2 = ContractarViatgeView;
	    	SeleccioViatgeView2  = SeleccioViatgeView;
	    	ReservaHabitacioView2  = ReservaHabitacioView;
	    	PagamentView2  = PagamentView;
	    	NoCiutatsView2  = NoCiutatsView;
	        
	        //... Add listeners to the view.
	    	ContractarViatgeView2.addContractar_Listener(new Contractar_Listener());
	    	
	    	
	    	SeleccioViatgeView2.addConfirmar_SVListener(new Confirmar_SVListener());
	    	SeleccioViatgeView2.addCancel_1Listener(new Cancel_1Listener());
	    	
	    	
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
	    	}
	    }// end inner class Contractar_Listener
	    
	    
	    ////////////////////////////////////////////inner class Confirmar_SVListener
	    /**  Confirmar i enregistra el viatge, si el checkbox est� activat aleshores va reservar hotel, si no a pagament */

	    class Confirmar_SVListener implements ActionListener {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    }// end inner class Confirmar_SVListener
	    
	    
	    ////////////////////////////////////////////inner class Confirmar_RHListener
	    /**  Confirmar i enregistra el viatge, si el checkbox est� activat aleshores va reservar hotel, si no a pagament */

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
	    	}
	    }// end inner class Cancel_2Listener
	    
	   class SelectionListener implements ListSelectionListener {
		      public void valueChanged(ListSelectionEvent e) {
		    	JTable table = ReservaHabitacioView2.get_table();
		        String selectedData_1 = null;
		        String selectedData_2 = null;
		        int[] selectedRow = table.getSelectedRows();
		        int[] selectedColumns = table.getSelectedColumns();

		        for (int i = 0; i < selectedRow.length; i++) {
		          for (int j = 0; j < selectedColumns.length; j++) {
		            selectedData_1 = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
		            selectedData_2 = (String) table.getValueAt(selectedRow[i], selectedColumns[j+1]);
		          }
		        }
		        System.out.println("Selected: " + selectedData_1);
		      }

		    }
	 
}