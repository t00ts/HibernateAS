package com.as.data;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.as.data.primarykeys.HotelPrimaryKey;

@Entity
@Table(name="HOTELLOWCOST")
public class HotelLowCost extends Hotel {

	private float descompte;
	public HotelLowCost () {
		super ();
	}
	
	public HotelLowCost(HotelPrimaryKey primaryKey, Ciutat ciutat, float preu, float descompte) {
		super(primaryKey, preu, ciutat);
		this.descompte = descompte;
		
	}
	public void setNomCiutat(String NomCiu){
		this.hotelPrimaryKey.setNomCiutat(NomCiu);
	}
	public void setNomHotel(String NomHot){
		this.hotelPrimaryKey.setNomHotel(NomHot);
	}
	@Basic
	public float getDescompte() {
		return descompte;
	}
	public void setDescompte(float descompte) {
		this.descompte = descompte;
	}
	
	public float calcularPreu(){
		
		return this.preu - this.descompte;
	}


}
