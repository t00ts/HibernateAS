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
	public List<Tuple> cercaHotels(Date dIni, Date dFi){//nota comentar sobre hoteles como hacer la inheritance o ctrl
		List<Tuple> hotelsLliures=new ArrayList<Tuple>();
		int i=0;
		boolean esLliure=false; //hotel amb habs lliures
		while(i<hotels.size()){
			esLliure = hotels.get(i).habEsLliure(dIni, dFi);/*canvia una mica respecte al diagrama de sequencia
															*habEslliure no la teniem definida pero la necessitem
															*habLliure la hem dividit en 2, habEslliure(true si l'hotel conte 
															*habitacions lliures)
															* i numHablliure(retorna el numhab d'una habitacio lliure)->definida a hotel*/
			if(esLliure){//te habitacions lliures l'hotel
				Tuple tup=new Tuple();
				tup.nomCiutat=hotels.get(i).getNomCiutat();
				tup.nomHotel=hotels.get(i).getNom();
				tup.preu=hotels.get(i).getPreu();//AQUI TENGO Q MIRAR SI ES LOW O SUPERIOR
				hotelsLliures.add(tup);
			}
			i++;
		}
		
		return hotelsLliures;		
	}

}
