package com.as.data.primarykeys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HotelPrimaryKey implements Serializable {

	private String nomHotel;
	private String nomCiutat;
	
	public HotelPrimaryKey () {}
	
	public HotelPrimaryKey (String nomHotel, String nomCiutat) {
		this.nomHotel = nomHotel;
		this.nomCiutat = nomCiutat;
	}
	
	@Column(name="nomHotel")
	public String getNomHotel() {
		return nomHotel;
	}
	public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
	}
	
	@Column(name="nomCiutat")
	public String getNomCiutat() {
		return nomCiutat;
	}
	public void setNomCiutat(String nomCiutat) {
		this.nomCiutat = nomCiutat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nomCiutat == null) ? 0 : nomCiutat.hashCode());
		result = prime * result
				+ ((nomHotel == null) ? 0 : nomHotel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HotelPrimaryKey other = (HotelPrimaryKey) obj;
		if (nomCiutat == null) {
			if (other.nomCiutat != null)
				return false;
		} else if (!nomCiutat.equals(other.nomCiutat))
			return false;
		if (nomHotel == null) {
			if (other.nomHotel != null)
				return false;
		} else if (!nomHotel.equals(other.nomHotel))
			return false;
		return true;
	}
	

}
