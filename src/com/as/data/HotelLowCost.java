package com.as.data;

import com.as.data.primarykeys.HotelPrimaryKey;

public class HotelLowCost extends Hotel {

	private float descompte;

	public HotelLowCost(HotelPrimaryKey primaryKey, float preu, float descompte) {
		super(primaryKey, preu);
		this.descompte = descompte;
	}

	public float getDescompte() {
		return descompte;
	}

	public void setDescompte(float descompte) {
		this.descompte = descompte;
	}


}
