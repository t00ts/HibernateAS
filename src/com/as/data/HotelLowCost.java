package com.as.data;

import javax.persistence.Basic;
import javax.persistence.Entity;

import com.as.data.primarykeys.HotelPrimaryKey;

@Entity
public class HotelLowCost extends Hotel {

	private float descompte;

	public HotelLowCost () {
		super ();
	}
	
	public HotelLowCost(HotelPrimaryKey primaryKey, float preu, float descompte) {
		super(primaryKey, preu);
		this.descompte = descompte;
	}

	@Basic
	public float getDescompte() {
		return descompte;
	}
	public void setDescompte(float descompte) {
		this.descompte = descompte;
	}


}
