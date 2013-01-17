package com.as.data.tuples;

public class Tuple {
	public float preu;
	public String nomHotel;
	public String nomCiutat;
	
	public Tuple(){}
	
	public Tuple(String nomHotel, String nomCiutat, float preu){
		this.nomHotel=nomHotel;
		this.nomCiutat=nomCiutat;
		this.preu=preu;
	}
}
