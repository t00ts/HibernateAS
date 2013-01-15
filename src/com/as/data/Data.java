package com.as.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="DATES")
public class Data {

	// Primary Key
	private Date date;
	
	public Data () {}
	
	public Data (Date date) {
		this.date=date;
	}
	@Id
	public Date getDate() {
		return date;
	}

	@Temporal(TemporalType.DATE)
	public void setDate(Date date) {
		this.date = date;
	}

}
