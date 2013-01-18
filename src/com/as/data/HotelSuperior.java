package com.as.data;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.as.data.primarykeys.HotelPrimaryKey;

@Entity
@Table(name="HOTELSUPERIOR")
public class HotelSuperior extends Hotel {

	public HotelSuperior () {
		super();
	}

	public HotelSuperior(HotelPrimaryKey primaryKey, Ciutat ciutat, float preu, float recarrec) {
		super(primaryKey, preu, ciutat);
		
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
	public float calcularPreu(){//precio de una habitacion en un hotelsuperior
		
		return this.preu + this.recarrec;
	}

}
