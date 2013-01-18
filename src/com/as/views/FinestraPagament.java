package com.as.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

public class FinestraPagament extends JFrame {

	private JPanel contentPane;
	private JTextField tfcad;
	private JTextField tfnumtarg;
	private JTextField tfdni;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraPagament frame = new FinestraPagament((float) 732.40, "05/04/1990", "09/05/1991", "Hotel Yuanyo", "47865234Z");
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
	public FinestraPagament(float preuTotal, String dataIni, String dataFi, String nomHotel, String dni) {
		setTitle("Pagament");
		setFont(new Font("Arial", Font.BOLD, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Botó Cancel.lar
		
		btnNewButton = new JButton("Cancel\u00B7lar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(352, 216, 105, 23);
		contentPane.add(btnNewButton);
		
		//Botó confirmar
		
		btnNewButton_1 = new JButton("Confirmar");
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_1.setBounds(238, 216, 105, 23);
		contentPane.add(btnNewButton_1);
		
		//Data de caducitat de la targeta del client

		tfcad = new JTextField();
		tfcad.setFont(new Font("Arial", Font.PLAIN, 12));
		tfcad.setHorizontalAlignment(SwingConstants.CENTER);
		tfcad.setBounds(336, 155, 121, 22);
		contentPane.add(tfcad);
		tfcad.setColumns(10);

		//Numero de la targeta del client
		
		tfnumtarg = new JTextField();
		tfnumtarg.setFont(new Font("Arial", Font.PLAIN, 12));
		tfnumtarg.setHorizontalAlignment(SwingConstants.CENTER);
		tfnumtarg.setBounds(336, 131, 121, 22);
		contentPane.add(tfnumtarg);
		tfnumtarg.setColumns(10);
		
		
		//DNI del client
		
		tfdni = new JTextField();
		tfdni.setHorizontalAlignment(SwingConstants.CENTER);
		tfdni.setBackground(new Color(192, 192, 192));
		tfdni.setFont(new Font("Arial", Font.PLAIN, 12));
		tfdni.setBounds(336, 107, 121, 22);
		tfdni.setText(dni);
		tfdni.setEditable(false);
		contentPane.add(tfdni);
		tfdni.setColumns(10);
		
		//Labels de la ventana
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(236, 106, 46, 22);
		contentPane.add(lblNewLabel);
		
		JLabel label_3 = new JLabel("Preu total a pagar:  ");
		label_3.setFont(new Font("Arial", Font.BOLD, 12));
		label_3.setBounds(31, 27, 121, 14);
		contentPane.add(label_3);
		
		//Label con el precio
		
		String preut = String.valueOf(preuTotal) + " Û";
		JLabel label_4 = new JLabel(preut);
		label_4.setFont(new Font("Arial", Font.BOLD, 12));
		label_4.setBounds(184, 27, 46, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("<html><u>Dades de la Reserva</u></html>");
		label_5.setFont(new Font("Arial", Font.BOLD, 12));
		label_5.setBounds(31, 80, 129, 14);
		contentPane.add(label_5);
		
		//Label con las fechas
		
		JLabel label_6 = new JLabel(dataIni + " - " + dataFi);
		label_6.setFont(new Font("Arial", Font.BOLD, 12));
		label_6.setBounds(31, 110, 150, 14);
		contentPane.add(label_6);
		
		//Label con el hotel
		
		JLabel label_7 = new JLabel(nomHotel);
		label_7.setFont(new Font("Arial", Font.BOLD, 12));
		label_7.setBounds(31, 134, 150, 14);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("<html><u>Dades del Client</u></html>");
		label_8.setFont(new Font("Arial", Font.BOLD, 12));
		label_8.setBounds(238, 80, 129, 14);
		contentPane.add(label_8);
		
		JLabel label = new JLabel("Num. targeta:");
		label.setFont(new Font("Arial", Font.BOLD, 13));
		label.setBounds(237, 130, 89, 22);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("<html>Caducitat:<br/>(mm/yyyy)<html>");
		label_1.setFont(new Font("Arial", Font.BOLD, 13));
		label_1.setBounds(238, 154, 89, 44);
		contentPane.add(label_1);
	}
	
	public void addConfirmar_PListener(ActionListener cal) {
		btnNewButton_1.addActionListener(cal);
    }
	
	public void addCancel_1Listener(ActionListener cal) {
		btnNewButton.addActionListener(cal);
    }
	
	public String get_numTarg(){
		return tfnumtarg.getText();
	}
	
	public String get_dataCad(){
		return tfcad.getText();
	}
}
