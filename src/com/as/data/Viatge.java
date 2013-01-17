package com.as.data;

import java.util.Date;
import java.util.List;

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
import com.as.data.tuples.Tuple;

@Entity
@Table(name="VIATGES")
public class Viatge {

	
	// Primary Key
	
	protected ViatgePrimaryKey viatgePrimaryKey;
	
	//Relaciones para las foreign keys
	
	private Client client;
	private Ciutat ciutat;
	private Habitacio habitacio;
	
	// Atributs
	protected Date dataFi;
	protected String nomCiutat;//foreign ciutat(nom) pot ser null
	
	// Identificador Habitacio
	protected String nomHotel;
	protected Integer numHabitacio;
	//foreign habitacio(nomCiutat, nomHotel, numHabitacio)
	public Viatge ( ){}
	public Viatge (ViatgePrimaryKey pk, Client cl, Ciutat c, Habitacio hab, Date dfi, String nomHot, Integer numHab ){
		this.viatgePrimaryKey=pk;
		this.client=cl;
		this.ciutat=c;
		this.habitacio=hab;
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
	public boolean viatgeSolapat(Date dataIniNew, Date dataFiNew){
		boolean solapa = false;
		Date dataIni = getdataInici();
		//tenemos que comprobar que dataIni y dataFi del viatge actual no se solape
		//con dataIniNew y dataFiNew.(consideramos que datas iguales solaparia)
		/*---------dIni>-----------<dFi---
		 * ---dIniNew>------<dFiNew------SOLAPA 1
		 * -------------dIniNew>------<dFiNew------SOLAPA 2
		 * ---dIniNew>----------------<dFiNew------SOLAPA 3
		 * ---------dIniNew>---<dFiNew------SOLAPA 4
		 */
		if(dataIni.compareTo(dataIniNew)==0 || dataIni.compareTo(dataFiNew)==0 
			|| dataFi.compareTo(dataIniNew)==0 || dataFi.compareTo(dataFiNew)==0){//alguna data igual entonces SOLAPA
			solapa=true;
		}else if(dataFiNew.before(dataFi) && dataFiNew.after(dataIni)){//caso 1,4
			solapa=true;
		}else if(dataIniNew.after(dataIni) && dataIniNew.before(dataFi)){//caso 2,4
			solapa=true;
		}else if(dataIniNew.before(dataIni) && dataFiNew.after(dataFi)){//caso 3
			solapa=true;
		}
		
		
		return solapa;
	}
	public List<Tuple> mostraHotelsLliures(){//Creo q no la usamos ya que el viatge en este momento aun no existe
		List<Tuple> preuHotels;
		preuHotels = ciutat.cercaHotels(getdataInici(), dataFi);
		
		if(preuHotels.isEmpty()){
			//ACTIVAMOS EXC HOTELSNOLLIURES->PANTALLA AVISNOHOTELS
		}
		return preuHotels;
	}
	
	public float reserva(Habitacio hab, Date dIni, Date dFi){
		float preuHab=0;
		habitacio=hab;//relacionamos el viatge con la habitacion
		nomHotel=hab.getHotel().getNom();
		numHabitacio=hab.getNumero();
		preuHab=hab.reserva(this);
		return preuHab;
	}

}
