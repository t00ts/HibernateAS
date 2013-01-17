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
	@OneToMany(targetEntity=Viatge.class, mappedBy="habitacio", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	public List<Viatge> getViatges() {
		return viatges;
	}
	public void setViatges (List<Viatge> lvi) {
		this.viatges = lvi;
		
	}
	public void addViatge (Viatge v){
		this.viatges.add(v);
	}
	public boolean habEsLliure(Date dIni, Date dFi){//si no te viatges registrats es lliure
		int i=0;
		boolean solapat=false;
		if(viatges.size()==0){//no te viatges
			return true;
		}else{
			while(i<viatges.size() && solapat==false){//buscamos de todos los viatges de esta habitacion si hay alguno q solape
				Viatge v=viatges.get(i);
				solapat=v.viatgeSolapat(dIni, dFi);
				i++;
			}
			
		}
			
	
		return solapat;
		
	}
	public float reserva(Viatge v){
		float preuHab=0;
		addViatge(v);//reservamos habitacion
		//habria que mirar si hotel es sup/low
		/*posible codigo
		 * CtrlHotelSuperior chs=new CtrlHotelSuperior();
		 * CtrlHotelLowCost chl=new CtrlHotelLowCost();
		 * if(chs.exists(hotel.getNom(), hotel.getNomCiutat())){//es hotel superior=> precio=preciohotel+recarrec
		 * 		hotelSup HotelSuperior=chs.get(hotel.getNom(), hotel.getNomCiutat());
		 * 		preuHab=hotelSup.getPreu() + hotelSup.getRecarrec();
		 * }else if(chl.exists(hotel.getNom(), hotel.getNomCiutat()){//es hotel low cost => precio = preciohotel-descompte
		 * 		hotelLow HotelLowCost=chl.get(hotel.getNom(), hotel.getNomCiutat());
		 * 		preuHab=hotelLow.getPreu() - hotelLow.getDescompte();
		 * 
		 * }else{//es SOLO hotel => precio = preuHotel
		 * 		preuHab=hotel.getPreu();
		 * }
		 */
		
		//SAVE HABITACION
		return preuHab;
	}

}
