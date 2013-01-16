package com.as.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;


public class FinestraSeleccioViatge extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					FinestraSeleccioViatge frame = new FinestraSeleccioViatge();
					
					frame.setResizable(false);
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FinestraSeleccioViatge() {
		
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(377, 55, 21, 14);
		contentPane.add(chckbxNewCheckBox);
		
		//Introduïr DNI
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(219, 88, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBounds(312, 85, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		//Sel.lecció de la data d'anada del viatje			
		
		JLabel lblNewLabel_2 = new JLabel("Data Anada:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(219, 119, 83, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox.setBounds(312, 116, 86, 20);
		contentPane.add(comboBox);
		
		//Sel.lecció de la data de tornada del viatje		
		
		JLabel lblNewLabel_3 = new JLabel("Data Tornada:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(219, 150, 83, 14);
		contentPane.add(lblNewLabel_3);
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox_1.setBounds(312, 147, 86, 20);
		contentPane.add(comboBox_1);
		
		//Botó confirmar
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(257, 222, 105, 23);
		contentPane.add(btnNewButton);
		
		//Botó cancel.lar
		
		JButton btnNewButton_1 = new JButton("Cancel\u00B7lar");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(372, 222, 105, 23);
		contentPane.add(btnNewButton_1);

		
		//Llista de ciutats amb els seus preus
		
		String data[][] = {{"Barcelona","30$"}}; //provisional ha de cridar a la funció del controlador
		String col[] = {"Ciutat","Preu"};
		JTable table = new JTable(data,col);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		JScrollPane pane = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pane.setBounds(32, 39, 142, 179);
		contentPane.add(pane);
		  
		
	}
}
