package com.as.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="VIATGES")
public class Viatge {

	// Primary Keys
	private String dniClient;
	private Date dataIni;
	private String nomCiutat;
	
	// Atributs
	private Date dataFi;
	
	// Identificador Habitacio
	private String nomHotel;
	private Integer numHabitacio;
	
	
	@Id
	public String getDniClient() {
		return dniClient;
	}
	public void setDniClient(String dniClient) {
		this.dniClient = dniClient;
	}
	
	@Id
	public Date getDataIni() {
		return dataIni;
	}
	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}
	
	@Id
	public String getNomCiutat() {
		return nomCiutat;
	}
	public void setNomCiutat(String nomCiutat) {
		this.nomCiutat = nomCiutat;
	}
	
	@Column(nullable=false)
	public Date getDataFi() {
		return dataFi;
	}
	public void setDataFi(Date dataFi) {
		this.dataFi = dataFi;
	}

	@Column(nullable=false)
	public Integer getNumHabitacio () {
		return numHabitacio;
	}
	public void setNumHabitacio (Integer numHabitacio) {
		this.numHabitacio = numHabitacio;
	}
	
	@Column(nullable=false)
	public String getNomHotel () {
		return nomHotel;
	}
	public void setNomHotel (String nomHotel) {
		this.nomHotel = nomHotel;
	}

}
