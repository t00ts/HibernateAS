package com.as.data.primarykeys;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class HabitacioPrimaryKey implements Serializable {

	private Integer numero;
	private String nomHotel;
	private String nomCiutat;
	
	public HabitacioPrimaryKey (Integer numero, String nomHotel, String nomCiutat) {
		this.numero = numero;
		this.nomHotel = nomHotel;
		this.nomCiutat = nomCiutat;
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getNomHotel() {
		return nomHotel;
	}
	public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
	}
	
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
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		HabitacioPrimaryKey other = (HabitacioPrimaryKey) obj;
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
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	

}
