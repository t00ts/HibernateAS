package com.as.data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="CLIENTS")
public class Client {

	// Primary Key
	@Id
	private String dni;
	
	// Atributs
	private String nom;
	private String tlfn;
	private Integer nombreViatges;
	
	public Client () {}
	public Client (String dni, String nom, String telf, Integer nombreViatges) {
		this.dni=dni;
		this.nom = nom;
		this.tlfn =telf;
		this.nombreViatges = nombreViatges;
	}
	@Id
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Column(nullable=false)
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Column(nullable=false)
	public String getTlfn() {
		return tlfn;
	}
	public void setTlfn(String tlfn) {
		this.tlfn = tlfn;
	}
	
	
	public Integer getNombreViatges() {
		return nombreViatges;
	}
	public void setNombreViatges(Integer nombreViatges) {
		this.nombreViatges = nombreViatges;
	}

	
}
