package com.as.data;

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


public class AvisPagamentOk extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AvisPagamentOk frame = new AvisPagamentOk();
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
	public AvisPagamentOk() {
		setFont(new Font("Arial", Font.BOLD, 12));
		setTitle("Av\u00EDs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 195);
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
		btnNewButton.setBounds(343, 135, 100, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Juanjo\\workspace\\Prueba\\src\\LogoOk.PNG"));
		label.setBounds(10, 30, 145, 94);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("<html>El pagament s'ha realitzat <br>correctament. </html>");
		lblNewLabel.setForeground(new Color(154, 205, 50));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(165, 30, 263, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNoExisteixenCiutats = new JLabel("Que gaudexi del viatge!");
		lblNoExisteixenCiutats.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNoExisteixenCiutats.setBounds(165, 88, 245, 36);
		contentPane.add(lblNoExisteixenCiutats);
	}
}
