package com.as.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Checkbox;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.*;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXMonthView;


import java.awt.*;

import java.lang.Object;
import java.util.Calendar;
import java.util.Date;


public class FinestraSeleccioViatge extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	final private JTable table;
	private ListSelectionModel cellSelectionModel;
	private JCheckBox chckbxNewCheckBox;
	final JXDatePicker dF;
	final JXDatePicker dI;
	/**
	 * Launch the application.
*/

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					FinestraSeleccioViatge frame = new FinestraSeleccioViatge(null);
					
					frame.setResizable(false);
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

/*
	 * Create the frame.
	 */
	
	public FinestraSeleccioViatge(String[][] ciu) {
		
		setForeground(new Color(0, 0, 0));
		setTitle("Sel\u00B7lecci\u00F3 Viatge");
		setFont(new Font("Arial", Font.BOLD, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//Check de si vol reservar habitació
		
		JLabel lblNewLabel_1 = new JLabel("Reservar Habitaci\u00F3:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(219, 55, 123, 14);
		contentPane.add(lblNewLabel_1);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(433, 55, 21, 14);
		contentPane.add(chckbxNewCheckBox);
		
		//Introduïr DNI
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(219, 88, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBounds(312, 80, 142, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		

<<<<<<< HEAD
		JLabel lblNewLabel_2 = new JLabel("Data Anada:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(219, 119, 83, 14);
		contentPane.add(lblNewLabel_2);

		dI = new JXDatePicker();
		Calendar calendar = dI.getMonthView().getCalendar();
		calendar.setTime(new Date());
		dI.getMonthView().setLowerBound(calendar.getTime());
		dI.setFormats("dd/MM/yyyy");
		dI.setEditable(true);
		dI.setVisible(true);
		dI.setBounds(312, 116, 142, 20);
		contentPane.add(dI);
		
		//Sel.lecció de la data de tornada del viatje		
		
		
		JLabel lblNewLabel_3 = new JLabel("Data Tornada:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(219, 150, 83, 14);
		contentPane.add(lblNewLabel_3);
		dF = new JXDatePicker();
		dF.getMonthView().setLowerBound(calendar.getTime());
		dF.setFormats("dd/MM/yyyy");
		dF.setEditable(true);
		dF.setVisible(true);
		dF.setBounds(312, 147, 142, 20);
		contentPane.add(dF);
=======
		//Sel.lecció de la data d'anada del viatje	  
>>>>>>> da0977bf62894e58acec32c908d14382e2556af2

	 	JLabel lblNewLabel_2 = new JLabel("Data Anada:");
	 	lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
	 	lblNewLabel_2.setBounds(219, 119, 83, 14);
	 	contentPane.add(lblNewLabel_2);

	 	dI = new JXDatePicker();
	 	Calendar calendar = dI.getMonthView().getCalendar();
	 	calendar.setTime(new Date());
	 	dI.getMonthView().setLowerBound(calendar.getTime());
	 	dI.setFormats("dd/MM/yyyy");
	 	dI.setEditable(true);
	 	dI.setVisible(true);
	 	dI.setBounds(312, 116, 142, 20);
	 	contentPane.add(dI);
	 	 
	 	 //Sel.lecció de la data de tornada del viatje	 
	 	 
	 	 
	 	JLabel lblNewLabel_3 = new JLabel("Data Tornada:");
	 	lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
	 	lblNewLabel_3.setBounds(219, 150, 83, 14);
	 	contentPane.add(lblNewLabel_3);
	 	dF = new JXDatePicker();
	 	dF.getMonthView().setLowerBound(calendar.getTime());
	 	dF.setFormats("dd/MM/yyyy");
	 	dF.setEditable(true);
	 	dF.setVisible(true);
	 	dF.setBounds(312, 147, 142, 20);
	 	contentPane.add(dF);


	 	 
	 	//Botó confirma
		
		btnNewButton = new JButton("Confirmar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(257, 222, 105, 23);
		contentPane.add(btnNewButton);
		
		//Botó cancel.lar
		
		btnNewButton_1 = new JButton("Cancel\u00B7lar");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_1.setBounds(372, 222, 105, 23);
		contentPane.add(btnNewButton_1);

		
		//Llista de ciutats amb els seus preus
		
		String col[] = {"Ciutat","Preu"};
		//TODO Descomentar esto!
		table = new JTable(ciu,col);
		JTableHeader header = table.getTableHeader();
		table.setCellSelectionEnabled(true);
		
		cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		header.setBackground(Color.white);
		JScrollPane pane = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pane.setBounds(32, 39, 142, 179);
		contentPane.add(pane);
		  
		
	}
	
	public void addConfirmar_SVListener(ActionListener cal) {
		btnNewButton.addActionListener(cal);
    }
	
	public void addCancel_1Listener(ActionListener cal) {
		btnNewButton_1.addActionListener(cal);
    }
	
	public void addSelectionListener(ListSelectionListener cal) {
		cellSelectionModel.addListSelectionListener(cal);
    }
	
	public String get_DNI(){
		return textField.getText();
	}
	
	public Boolean get_check(){
		return chckbxNewCheckBox.isSelected();
	}
	
	public Date[] get_dates() {
		Date[] dat = new Date [2];
		dat[0] = dI.getDate();
		dat[1] = dF.getDate();
		return dat;
	}
	
	public JTable get_table() {
		return this.table; 
	}
}
