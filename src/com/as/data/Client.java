package com.as.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

import com.as.data.primarykeys.ViatgePrimaryKey;

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
	
	private List<Viatge> viatges;
	
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
	@OneToMany(targetEntity=Viatge.class, mappedBy="client", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	public List<Viatge> getViatges() {
		return viatges;
	}
	public void setViatges (List<Viatge> vi) {
		this.viatges = vi;
	}
	public float enregistraViatge(String dniClient, Date dataIni, Date dataFi, String nomCiutat){
		int preuVol=0;
		int i=0;
		boolean solapat = false;
		
		while(i<viatges.size()){
			solapat = viatges.get(i).viatgeSolapat(dataIni, dataFi);
			if(solapat){
				//ACTIVAMOS EXC JATEVIATGE ->PANTALLA AVISCLIENTVIATGE
			}
			i++;
		}
		//Creamos el viatge //decirle al abel la funcion de crear
		Data data=new Data(dataIni);
		//hacer gets de objetos para pasarlos a la creadora
		Viatge v = new Viatge(new ViatgePrimaryKey(dniClient, dataIni),null,null,null, data,dataFi, nomCiutat, null);
		nombreViatges++;
		return v.getCiutat().getPreuVol();
	}
	
}
