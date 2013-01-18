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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import com.as.data.tuples.TupleCiutat;

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
	
	public Ciutat (String nom, String descripcio, float d) {
		this.nom = nom;
		this.descripcio = descripcio;
		this.preuVol = d;
		this.hotels=new ArrayList<Hotel>();
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
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity=Hotel.class, mappedBy="ciutat", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	public List<Hotel> getHotels() {
		return hotels;
	}
	public void setHotels (List<Hotel> hotels) {
		this.hotels = hotels;
	}
	public List<TupleCiutat> cercaHotels(Date dIni, Date dFi){//retorna una lista de nomhoteles con su precio
		List<TupleCiutat> hotelsLliures=new ArrayList<TupleCiutat>();
		int i=0;
		boolean esLliure=false; //true = hotel amb habs lliures
		while(i<hotels.size()){
			esLliure = hotels.get(i).habEsLliure(dIni, dFi);/*canvia una mica respecte al diagrama de sequencia
															*habEslliure no la teniem definida pero la necessitem per saber si un hotel te
															*habitacions lliures. 
															*habLliure del diagrama entrega 1 la hem dividit en 2, habEslliure(true si l'hotel 
															*conte habitacions lliures)
															*i numHablliure(retorna el numhab d'una habitacio lliure)->definida a hotel*/
			if(esLliure){//te habitacions lliures l'hotel
				TupleCiutat tup=new TupleCiutat();
				Hotel hotel = hotels.get(i);
				tup.nomCiutat=hotel.getNom();//agafem nomhotel(no fer cas del nom dels atributs de la clase tupleCiutat)
				tup.preuVol=hotel.calcularPreu();//pel patro plantilla el preudel hotel sera el correcte ya que la tenim definida a les 3 clases
				hotelsLliures.add(tup);
			}
			i++;
		}
		
		return hotelsLliures;		
	}
	public void addHotel(Hotel h){//relaciona l'hotel con la ciudad
		
		this.hotels.add(h);
	}

}
