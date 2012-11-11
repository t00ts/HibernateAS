package com.as.data;

import com.as.data.primarykeys.HotelPrimaryKey;

public class HotelSuperior extends Hotel {

	public HotelSuperior(HotelPrimaryKey primaryKey, float preu, float recarrec) {
		super(primaryKey, preu);
		this.recarrec = recarrec;
	}

	// Atributs
	private float recarrec;

	public float getRecarrec() {
		return recarrec;
	}

	public void setRecarrec(float recarrec) {
		this.recarrec = recarrec;
	}

}
