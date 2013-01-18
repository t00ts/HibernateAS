package com.as.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Window;

import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class FinestraContractarViatges extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinestraContractarViatges frame = new FinestraContractarViatges();
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
	public FinestraContractarViatges() {
		setBackground(Color.WHITE);
		
		setFont(new Font("Arial", Font.BOLD, 12));
		setTitle("Contractar Viatge");
		
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 295);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		ImageIcon icon = new ImageIcon("/Logo.PNG");
		
		btnNewButton = new JButton("Contractar viatge");
		btnNewButton.setActionCommand("Contractar");


		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(175, 222, 154, 35);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(155, 14, 181, 99);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FinestraContractarViatges.class.getResource("/res/Logo.PNG")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Benvinguts a <font color='green'>Traveling!</font><br>Per realitzar la contractaci\u00F3 d'un viatje,<br> \r\nprem el bot\u00F3 seg\u00FCent.</html>");
		lblNewLabel_1.setBounds(91, 124, 307, 80);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_1);
		contentPane.add(btnNewButton);
		
	}
	
	public void addContractar_Listener(ActionListener cal) {
		btnNewButton.addActionListener(cal);
    }
}
