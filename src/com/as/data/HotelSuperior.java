package com.as.data;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.as.data.primarykeys.HotelPrimaryKey;

@Entity
public class HotelSuperior extends Hotel {

	public HotelSuperior () {
		super();
	}

	public HotelSuperior(HotelPrimaryKey primaryKey, float preu, float recarrec) {
		super(primaryKey, preu);
		this.recarrec = recarrec;
	}

	// Atributs
	private float recarrec;

	@Basic
	public float getRecarrec() {
		return recarrec;
	}
	public void setRecarrec(float recarrec) {
		this.recarrec = recarrec;
	}

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@Override
	public Ciutat getCiutat () {
		return ciutat;
	}
	public void setCiutat (Ciutat c) {
		ciutat = c;
	}

}
