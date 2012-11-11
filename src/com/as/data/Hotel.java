package com.as.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

import com.as.data.primarykeys.HotelPrimaryKey;

@Entity
@Table(name="hotels")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Hotel {

	// Primary Key
	private HotelPrimaryKey hotelPrimaryKey;

	// Attributes
	private float preu;

	// Relationships
	private Ciutat ciutat;
	private List<Habitacio> habitacions;

	public Hotel (HotelPrimaryKey primaryKey, float preu) {
		this.hotelPrimaryKey = primaryKey;
	    this.preu = preu;
	}
	
	
	@Id
	public HotelPrimaryKey getPrimaryKey () {
		return this.hotelPrimaryKey;
	}
	public void setPrimaryKey (HotelPrimaryKey primaryKey) {
		this.hotelPrimaryKey = primaryKey;
	}

	@Column(nullable=false)
	public float getPreu() {
		return preu;
	}
	public void setPreu(float preu) {
		this.preu = preu;
	}
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumn(name="nom_ciutat")
	public Ciutat getCiutat () {
		return ciutat;
	}
	public void setCiutat (Ciutat c) {
		ciutat = c;
	}
	
	@OneToMany(targetEntity=Habitacio.class, mappedBy="hotel", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	public List<Habitacio> getHabitacions () {
		return habitacions;
	}
	public void setHabitacions (List<Habitacio> habitacions) {
		this.habitacions = habitacions;
	}

}
