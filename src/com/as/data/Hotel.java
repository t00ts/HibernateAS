package com.as.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.as.data.primarykeys.HotelPrimaryKey;

@Entity
@Table(name="HOTELS")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Hotel {

	// Primary Key
	protected HotelPrimaryKey hotelPrimaryKey;

	// Attributes
	protected float preu;

	// Relationships

	protected Ciutat ciutat;
	protected List<Habitacio> habitacions;
	
	public Hotel () {}
	
	public Hotel (HotelPrimaryKey primaryKey, float preu, Ciutat c) {
		this.hotelPrimaryKey = primaryKey;
	    this.preu = preu;
	    this.ciutat=c;
	}
	
	
	@Id
	public HotelPrimaryKey getPrimaryKey () {
		return this.hotelPrimaryKey;
	}
	public void setPrimaryKey (HotelPrimaryKey primaryKey) {
		this.hotelPrimaryKey = primaryKey;
	}

	@Transient
	public String getNom () {
		return this.hotelPrimaryKey.getNomHotel();
	}
	public void setNom (String nom) {
		this.hotelPrimaryKey.setNomHotel(nom);
	}
	@Transient
	public String getNomCiutat () {
		return this.hotelPrimaryKey.getNomCiutat();
	}
	public void setNomCiutat (String nom) {
		this.hotelPrimaryKey.setNomCiutat(nom);
	}
	@ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "nomCiutat", insertable=false, updatable=false, nullable=false, referencedColumnName="nom")})
	public Ciutat getCiutat () {
		return ciutat;
	}
	public void setCiutat (Ciutat c) {
		ciutat = c;
	}
	
	@Column(name="preu", nullable=false)
	public float getPreu() {
		return preu;
	}
	public void setPreu(float preu) {
		this.preu = preu;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity=Habitacio.class, mappedBy="hotel", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	public List<Habitacio> getHabitacions() {
		return habitacions;
	}
	public void setHabitacions (List<Habitacio> ha) {
		this.habitacions = ha;
	}
	public void addHabitacio (Habitacio h){
		this.habitacions.add(h);
	}
	public boolean habEsLliure(Date dIni, Date dFi){
		int i=0;
		boolean algunaLliure = false;
		while(i<habitacions.size() && algunaLliure == false){//cuando encontremos una lliure nos salimos
			algunaLliure = habitacions.get(i).habEsLliure(dIni, dFi);//retorna true si hab es lliure
			i++;
		}
		return algunaLliure;//si numhab>0 llavors hi han habitacions lliures.
	}
	
	public int numHabLliure(Date dIni, Date dFi) {//retorna un numero d'habitacio lliure de l'hotel
		int i=0;
		Habitacio h=null;
		boolean algunaLliure = false;
		while(i<habitacions.size() && algunaLliure == false){//cuando encontremos una lliure nos salimos
			algunaLliure = habitacions.get(i).habEsLliure(dIni, dFi);//retorna true si hab es lliure
			h=habitacions.get(i);
			i++;
		}
		if(algunaLliure){
			return h.getNumero();//agafem el numero de l'habitacio lliure
		}else{
			return 0;
		}
	}
	
	public float calcularPreu(){
		
		return this.preu;
		
	}

	

}
