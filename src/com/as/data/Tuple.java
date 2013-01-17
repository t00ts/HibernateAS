package com.as.data;

public class Tuple {
	float preu;
	String nomHotel;
	String nomCiutat;
	
	public Tuple(){}
	
	public Tuple(String nomHotel, String nomCiutat, float preu){
		this.nomHotel=nomHotel;
		this.nomCiutat=nomCiutat;
		this.preu=preu;
	}
}
