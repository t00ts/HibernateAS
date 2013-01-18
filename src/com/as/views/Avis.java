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

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Avis extends JFrame {
	private String tipus;
	private JPanel contentPane;
	private JButton btnSurt;
	private String avisLogo;
	private String missGran;
	private String missPetit;
	
	/**
	 * Launch the application.
	 */
	//TODO Quitar main y lanzar desde controlador interfaz
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Avis frame = new Avis("nociutats");
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

	public Avis(String tipus) {
		setTitle("Av\u00EDs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setTipus(tipus);
		this.setTextGran(tipus);
		this.setTextPetit();
		this.setLogo();
		this.setButton();
	}
	
    public void addSurtListener(ActionListener cal) {
    	//algunos surts salen, otros vuelven y otros continuan
    	
    	btnSurt.addActionListener(cal);
    }
    
    //t = { nociutats, clientnoex, clientviatge, nohotels, pagamentnoau, pagamentnodisp, pagamentok }
	private void setTipus(String t) {
		if(t.equals("nociutats")) {
			avisLogo = "com/as/resources/LogoError.PNG";
			missGran = "<html>No hi ha ciutats.</html>";
			missPetit = "<html>No existeixen ciutats disponibles per fer viatges.</html>";
		}
		else if(t.equals("clientnoex")) {
			avisLogo = "com/as/resources/LogoError.PNG";
			missGran = "<html>El client no existeix.</html>";
			missPetit = "<html>No existeix un client amb aquest dni.</html>";
		}
		else if(t.equals("clientviatge")) {
			avisLogo = "com/as/resources/LogoError.PNG";
			missGran = "<html>El client ja t\u00E9 un viatge.</html>";
			missPetit = "<html>El client ja t\u00E9 un viatge contractat per <br>a un per\u00EDode solapat amb les dates <br>introdu\u00EFdes.</html>";
		}
		else if(t.equals("nohotels")) {
			avisLogo = "com/as/resources/LogoError.PNG";
			missGran = "<html>No hi ha hotels lliures.</html>";
			missPetit = "<html>No hi ha hotels disponibles per aquest viatge.</html>";
		}
		else if(t.equals("pagamentnoau")) {
			avisLogo = "com/as/resources/LogoError.PNG";
			missGran = "<html>El pagament no est\u00E0 <br>autoritzat.</html>";
			missPetit = "<html>Contacti amb el seu administrador.</html>";
		}
		else if(t.equals("pagamentnodisp")) {
			avisLogo = "com/as/resources/LogoError.PNG";
			missGran = "<html>Servei de pagament <br>no disponible.</html>";
			missPetit = "<html>Contacti amb el seu administrador.</html>";
		}
		else if(t.equals("pagamentok")) {
			avisLogo = "com/as/resources/LogoOk.PNG";
			missGran = "<html>El pagament s'ha realitzat <br>correctament.</html>";
			missPetit = "<html>Que gaudexi del viatge!</html>";
		}
	}
		
	private void setLogo() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(ClassLoader.getSystemResource (avisLogo)));
		lblLogo.setBounds(10, 24, 145, 94);
		contentPane.add(lblLogo);
	}
	
	private void setTextGran(String tipus) {
		JLabel lblmissGran = new JLabel(missGran);
		if (tipus == "pagamentok") lblmissGran.setForeground(new Color(50, 205, 50));
		else lblmissGran.setForeground(new Color(255, 0, 0));
		lblmissGran.setFont(new Font("Arial", Font.BOLD, 20));
		lblmissGran.setBounds(180, 25, 245, 40);
		contentPane.add(lblmissGran);
	}
	
	private void setTextPetit() {
		JLabel lblmissPetit = new JLabel(missPetit);
		lblmissPetit.setFont(new Font("Arial", Font.PLAIN, 14));
		lblmissPetit.setBounds(180, 69, 245, 47);
		contentPane.add(lblmissPetit);
	}
	
	private void setButton() {
		btnSurt = new JButton("Surt");
		btnSurt.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSurt.setBounds(343, 127, 100, 23);
		contentPane.add(btnSurt);
	}
	
}