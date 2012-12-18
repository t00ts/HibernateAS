package com.as.data;

import com.as.data.primarykeys.HabitacioPrimaryKey;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Table(name="HABITACIONS")
public class Habitacio {

	// Primary Key
	private HabitacioPrimaryKey habitacioPrimaryKey;
	
	// Relationships
	private Hotel hotel;

	
	public Habitacio () {}
	
	public Habitacio (HabitacioPrimaryKey primaryKey) {
		this.habitacioPrimaryKey = primaryKey;
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

	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	public Hotel getHotel () {
		return hotel;
	}
	public void setHotel (Hotel hotel) {
		this.hotel = hotel;  
	}

}
