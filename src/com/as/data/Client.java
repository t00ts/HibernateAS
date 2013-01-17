package com.as.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.as.data.primarykeys.ViatgePrimaryKey;

@Entity
@Table(name="CLIENTS")
public class Client {

	// Primary Key
	@Id
	private String dni;
	
	// Atributs
	private String nom;
	private String tlfn;
	private Integer nombreViatges;
	
	private List<Viatge> viatges;
	
	public Client () {}
	public Client (String dni, String nom, String telf, Integer nombreViatges) {
		this.dni=dni;
		this.nom = nom;
		this.tlfn =telf;
		this.nombreViatges = nombreViatges;
		this.viatges = new ArrayList<Viatge>();
	}
	@Id
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Column(nullable=false)
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Column(nullable=false)
	public String getTlfn() {
		return tlfn;
	}
	public void setTlfn(String tlfn) {
		this.tlfn = tlfn;
	}
	
	
	public Integer getNombreViatges() {
		return nombreViatges;
	}
	public void setNombreViatges(Integer nombreViatges) {
		this.nombreViatges = nombreViatges;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity=Viatge.class, mappedBy="client", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	public List<Viatge> getViatges() {
		return viatges;
	}
	public void setViatges (List<Viatge> vi) {
		this.viatges = vi;
	}
	public boolean excJaTeViatge(String dniClient, Date dataIni, Date dataFi, String nomCiutat){//detecta si tiene viatges solapados
		int i=0;
		boolean solapat = false;
		while(i<viatges.size() && solapat==false){//miramos los viatges del client a ver si solapa alguno con el nuevo
			solapat = viatges.get(i).viatgeSolapat(dataIni, dataFi);
			i++;
		}
				
		return solapat;
	}
	
	public float enregistraViatge(Viatge v){//asocia el cliente con ese viatge y retorna el precio de vuelo
		int preuVol=0;
		
		viatges.add(v);//añadimos el viatge al cliente
		this.nombreViatges++;
		return v.getCiutat().getPreuVol();
	}
	
}
