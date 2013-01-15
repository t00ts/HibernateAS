package com.as.data;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;


import com.as.data.primarykeys.HotelPrimaryKey;
import com.as.data.primarykeys.ViatgePrimaryKey;

@Entity
@Table(name="VIATGES")
public class Viatge {

	
	// Primary Key
	
	protected ViatgePrimaryKey viatgePrimaryKey;
	
	//Relaciones para las foreign keys
	
	private Client client;
	private Ciutat ciutat;
	private Habitacio habitacio;
	private Data data;
	
	// Atributs
	protected Date dataFi;
	protected String nomCiutat;//foreign ciutat(nom) pot ser null
	
	// Identificador Habitacio
	protected String nomHotel;
	protected Integer numHabitacio;
	//foreign habitacio(nomCiutat, nomHotel, numHabitacio)
	public Viatge ( ){}
	public Viatge (ViatgePrimaryKey pk, Client cl, Ciutat c, Habitacio hab, Data d, Date dfi, String nomHot, Integer numHab ){
		this.viatgePrimaryKey=pk;
		this.client=cl;
		this.ciutat=c;
		this.habitacio=hab;
		this.data=d;
		this.nomCiutat=c.getNom();
		this.dataFi=dfi;
		this.nomHotel=nomHot;
		this.numHabitacio=numHab;
	}
	@Id
	public ViatgePrimaryKey getPrimaryKey () {
		return this.viatgePrimaryKey;
	}
	public void setPrimaryKey (ViatgePrimaryKey primaryKey) {
		this.viatgePrimaryKey = primaryKey;
	}
	@Transient
	public String getDniClient () {
		return this.viatgePrimaryKey.getdniClient();
	}
	public void setDniClient (String dniClient) {
		this.viatgePrimaryKey.setdniClient(dniClient);
	}
	@Transient
	public Date getdataInici () {
		return this.viatgePrimaryKey.getdataInici();
	}
	public void setdataInici (Date dataInici) {
		this.viatgePrimaryKey.setdataInici(dataInici);
	}
	//foreign key dniclient->Client
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "dniClient", insertable=false, updatable=false)})
	public Client getClient(){
		return client;
	}
	public void setClient(Client c){
		client=c;
	}
	//foreign key datainici->Data
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "dataInici", insertable=false, updatable=false)})
	public Data getData(){
		return data;
	}
	public void setData(Data d){
		data=d;
	}
	//fk (nomCiutat,nomHotel,numeroHabitacio) referencia Habitacio(nomCiutat, nomHotel, numero)
	@ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumns({
	    @JoinColumn(name="NomCiutat", insertable=false, updatable=false),
	    @JoinColumn(name="NomHotel", insertable=false, updatable=false),
	    @JoinColumn(name="NumHabitacio", insertable=false, updatable=false)
	})
	public Habitacio getHabitacio(){
		return this.habitacio;
	}
	public void setHabitacio(Habitacio habitacio){
		this.habitacio=habitacio;
	}
	
	public String getNomCiutat() {
		return nomCiutat;
	}
	public void setNomCiutat(String nomCiutat) {
		this.nomCiutat = nomCiutat;
	}
	
	public Date getDataFi() {
		return dataFi;
	}
	public void setDataFi(Date dataFi) {
		this.dataFi = dataFi;
	}

	
	public Integer getNumHabitacio () {
		return numHabitacio;
	}
	public void setNumHabitacio (Integer numHabitacio) {
		this.numHabitacio = numHabitacio;
	}
	
	
	public String getNomHotel () {
		return nomHotel;
	}
	public void setNomHotel (String nomHotel) {
		this.nomHotel = nomHotel;
	}
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name = "Nomciutat", insertable=false, updatable=false, nullable=true)})
	public Ciutat getCiutat() {
		return ciutat;
	}
	public void setCiutat(Ciutat ciutat) {
		this.ciutat = ciutat;
	}
	

}
