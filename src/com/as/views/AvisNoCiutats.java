package com.as.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AvisNoCiutats extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvisNoCiutats frame = new AvisNoCiutats();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AvisNoCiutats() {
		setFont(new Font("Arial", Font.BOLD, 12));
		setTitle("Av\u00EDs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Surt");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setBounds(343, 127, 100, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Juanjo\\workspace\\Prueba\\src\\LogoError.PNG"));
		label.setBounds(10, 24, 145, 94);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("No hi ha ciutats.");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(180, 34, 213, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNoExisteixenCiutats = new JLabel("<html>No existeixen ciutats disponibles<br>per fer viatges.</html>");
		lblNoExisteixenCiutats.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNoExisteixenCiutats.setBounds(180, 58, 213, 47);
		contentPane.add(lblNoExisteixenCiutats);
	}
}
