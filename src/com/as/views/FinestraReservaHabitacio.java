package com.as.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


public class FinestraReservaHabitacio extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraReservaHabitacio frame = new FinestraReservaHabitacio();
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
	public FinestraReservaHabitacio() {
		setFont(new Font("Arial", Font.BOLD, 12));
		setTitle("Reserva Habitaci\u00F3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Preu dels hotels amb habitacions lliure per <br>les dates seleccionades:</html>");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(36, 41, 242, 39);
		contentPane.add(lblNewLabel);
		
		//Taula amb la sel.lecció d'hotel i preus

		String data[][] = {{"NHBarcelona","130$"}}; //provisional ha de cridar a la funció del controlador
		String col[] = {"Hotel","Preu"};
		JTable table = new JTable(data,col);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		JScrollPane pane = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		pane.setBounds(354, 45, 139, 137);
		contentPane.add(pane);
		
		//Botó de cancel.lar
		
		btnNewButton = new JButton("Cancel\u00B7lar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(430, 204, 105, 23);
		contentPane.add(btnNewButton);
	
		//Botó de confirmar
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConfirmar.setBounds(315, 204, 105, 23);
		contentPane.add(btnConfirmar);
		
		//Labels amb la ciutat del viatge a reservar
		
		JLabel label = new JLabel("Ciutat: ");
		label.setFont(new Font("Arial", Font.BOLD, 12));
		label.setBounds(36, 91, 72, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("XXXXXX"); //crida a funció del controlador
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.BOLD, 12));
		label_1.setBounds(159, 91, 89, 23);
		contentPane.add(label_1);
		
		//Labels amb la data d'inici del viatge a reservar
		
		JLabel label_2 = new JLabel("Data d'inici: ");
		label_2.setFont(new Font("Arial", Font.BOLD, 12));
		label_2.setBounds(36, 125, 72, 23);
		contentPane.add(label_2);
		
		JLabel label_4 = new JLabel("XX/XX/XXXX"); //crida a funció del controlador
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Arial", Font.BOLD, 12));
		label_4.setBounds(159, 125, 89, 23);
		contentPane.add(label_4);
		
		//Labels amb la data de fi del viatge a reservar
		
		JLabel label_3 = new JLabel("Data de fi: ");
		label_3.setFont(new Font("Arial", Font.BOLD, 12));
		label_3.setBounds(36, 147, 72, 23);
		contentPane.add(label_3);
		
		
		JLabel label_5 = new JLabel("XX/XX/XXXX"); //crida a funció del controlador
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Arial", Font.BOLD, 12));
		label_5.setBounds(159, 147, 89, 23);
		contentPane.add(label_5);
	}

	public void addConfirmar_RHListener(ActionListener cal) {
		btnConfirmar.addActionListener(cal);
    }
	
	public void addCancel_2Listener(ActionListener cal) {
		btnNewButton.addActionListener(cal);
    }
	
}
