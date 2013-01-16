package com.as.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="CIUTATS")
public class Ciutat {

	// Primary Key
	@Id
	private String nom;
	
	// Atributs
	private String descripcio;
	private float preuVol;
	
	// Relationships
	private List<Hotel> hotels;
	
	
	public Ciutat () {}
	
	public Ciutat (String nom, String descripcio, float preuVol) {
		this.nom = nom;
		this.descripcio = descripcio;
		this.preuVol = preuVol;
	}

	@Id
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Basic
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	
	@Column(nullable=false)
	public float getPreuVol() {
		return preuVol;
	}
	public void setPreuVol(float preuVol) {
		this.preuVol = preuVol;
	}
	
	@OneToMany(targetEntity=Hotel.class, mappedBy="ciutat", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	public List<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels (List<Hotel> hotels) {
		this.hotels = hotels;
	}
	public List<Hotel> cercaHotels(Date dIni, Date dFi){
		List<Hotel> hotelsLliures=new ArrayList<Hotel>();
		int i=0;
		int numHab=0; //numero habitacions lliures
		while(i<hotels.size()){
			numHab = hotels.get(i).habLliure(dIni, dFi);
			
			if(numHab!=0){//te habitacions lliures l'hotel
				hotelsLliures.add(hotels.get(i));
			}
			i++;
		}
		
		return hotelsLliures;		
	}

}
