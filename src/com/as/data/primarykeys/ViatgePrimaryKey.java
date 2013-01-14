package com.as.data.primarykeys;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

import com.as.data.Data;

@Embeddable
public class ViatgePrimaryKey implements Serializable {
	
	private String dniClient;
	private Date dataInici;
	
public ViatgePrimaryKey () {}
	
	public ViatgePrimaryKey (String dniClient, Date dataInici) {
		this.dniClient = dniClient;
		this.dataInici = dataInici;
	}
	@Column(name="dniClient")
	public String getdniClient() {
		return dniClient;
	}
	public void setdniClient(String dniClient) {
		this.dniClient = dniClient;
	}
	@Column(name="dataInici")	
	public Date getdataInici() {
		return dataInici;
	}
	public void setdataInici(Date dataInici) {
		this.dataInici = dataInici;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViatgePrimaryKey other = (ViatgePrimaryKey) obj;
		if (dniClient == null) {
			if (other.dniClient != null)
				return false;
		} else if (!dniClient.equals(other.dniClient))
			return false;
		if (dataInici == null) {
			if (other.dataInici != null)
				return false;
		} else if (!dataInici.equals(other.dataInici))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dniClient == null) ? 0 : dniClient.hashCode());
		result = prime * result
				+ ((dataInici == null) ? 0 : dataInici.hashCode());
		
		return result;
	}
	

}
