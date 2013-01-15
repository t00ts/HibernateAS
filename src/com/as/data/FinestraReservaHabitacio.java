package com.as.data;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class FinestraReservaHabitacio extends JFrame {

	private JPanel contentPane;

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
		
		JList list = new JList();
		list.setBounds(354, 45, 139, 137);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("Cancel\u00B7lar");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(430, 204, 105, 23);
		contentPane.add(btnNewButton);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConfirmar.setBounds(315, 204, 105, 23);
		contentPane.add(btnConfirmar);
		
		JLabel label = new JLabel("Ciutat: ");
		label.setFont(new Font("Arial", Font.BOLD, 12));
		label.setBounds(36, 91, 72, 23);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("XXXXXX");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Arial", Font.BOLD, 12));
		label_1.setBounds(159, 91, 89, 23);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Data d'inici: ");
		label_2.setFont(new Font("Arial", Font.BOLD, 12));
		label_2.setBounds(36, 125, 72, 23);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Data de fi: ");
		label_3.setFont(new Font("Arial", Font.BOLD, 12));
		label_3.setBounds(36, 147, 72, 23);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("XX/XX/XXXX");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Arial", Font.BOLD, 12));
		label_4.setBounds(159, 125, 89, 23);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("XX/XX/XXXX");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Arial", Font.BOLD, 12));
		label_5.setBounds(159, 147, 89, 23);
		contentPane.add(label_5);
	}

}
