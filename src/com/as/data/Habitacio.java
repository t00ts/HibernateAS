package com.as.data;

import java.util.Date;
import java.util.List;

import com.as.data.primarykeys.HabitacioPrimaryKey;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="HABITACIONS")
public class Habitacio {

	// Primary Key
	private HabitacioPrimaryKey habitacioPrimaryKey;
	
	// Relationships
	private Hotel hotel;
	
	private List<Viatge> viatges;

	
	public Habitacio () {}
	
	public Habitacio (HabitacioPrimaryKey primaryKey, Hotel hot) {
		this.habitacioPrimaryKey = primaryKey;
		this.hotel = hot;
	}
		
	@Id
	public HabitacioPrimaryKey getPrimaryKey() {
		return habitacioPrimaryKey;
	}
	public void setPrimaryKey(HabitacioPrimaryKey primaryKey) {
		this.habitacioPrimaryKey = primaryKey;
	}

	@Transient
	public Integer getNumero () {
		return this.habitacioPrimaryKey.getNumero();
	}
	public void setNumero (Integer num) {
		this.habitacioPrimaryKey.setNumero (num);
	}
	@Transient
	public String getNomHotel() {
		return this.habitacioPrimaryKey.getNomHotel();
	}
	public void setNomHotel (String nom) {
		this.habitacioPrimaryKey.setNomHotel (nom);
	}
	@Transient
	public String getNomCiutat() {
		return this.habitacioPrimaryKey.getNomCiutat();
	}
	public void setNomCiutat (String nomCiutat) {
		this.habitacioPrimaryKey.setNomCiutat(nomCiutat);
	}
	//fk (nomHotel, nomCiutat) referencia Hotel(nom, nomCiutat)
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumns({
	    @JoinColumn(name="nomHotel", insertable=false, updatable=false, referencedColumnName="nomHotel"),
	    @JoinColumn(name="nomCiutat", insertable=false, updatable=false,referencedColumnName="nomCiutat")	    
	})
	public Hotel getHotel () {
		return hotel;
	}
	public void setHotel (Hotel hotel) {
		this.hotel = hotel;  
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity=Viatge.class, mappedBy="habitacio", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	public List<Viatge> getViatges() {
		return viatges;
	}
	public void setViatges (List<Viatge> lvi) {
		this.viatges = lvi;
		
	}
	public void addViatge (Viatge v){
		this.viatges.add(v);
	}
	public boolean habEsLliure(Date dIni, Date dFi){//true si la habitacio es lliure
		int i=0;
		boolean solapat=false;
		if(viatges.size()==0){//no te viatges llavors es lliure
			return true;
		}else{
			while(i<viatges.size() && solapat==false){//buscamos de todos los viatges de esta habitacion q ninguno solape entonces estara libre
				Viatge v=viatges.get(i);
				solapat=v.viatgeSolapat(dIni, dFi);
				i++;
			}
			
		}
			
		return solapat==false;//si solapat==false significa que de todos los viatges para esta habitacion ninguno SOLAPA con el nuevo
		
	}
	public float reserva(Viatge v){//retorna el precio de la habitacion y guarda relacion con el viatge
		float preuHab=0;
		addViatge(v);//reservamos habitacion
		Hotel h = this.hotel;
		preuHab=h.calcularPreu();//por patro plantilla segun el tipo del hotel se ejecutara una u otra
		return preuHab;
	}

}
