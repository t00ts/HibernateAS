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
	@OneToMany(targetEntity=Habitacio.class, mappedBy="hotel", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	public List<Habitacio> getHabitacions() {
		return habitacions;
	}
	public void setHabitacions (List<Habitacio> ha) {
		this.habitacions = ha;
	}
	public void addHabitacio (Habitacio h){
		this.habitacions.add(h);
	}
	public int habLliure(Date dIni, Date dFi){
		int i=0;
		int numHab=0;//habitacions Lliures
		while(i<habitacions.size()){
			numHab = numHab + habitacions.get(i).habLliure(dIni, dFi);//si habitacio es lliure retorna 1 sino 0.
			i++;
		}
		return numHab;//si numhab>0 llavors hi han habitacions lliures.
	}

	

}
