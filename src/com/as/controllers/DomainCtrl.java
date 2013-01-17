package com.as.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.as.controllers.CtrlCiutat;
import com.as.controllers.CtrlClient;
import com.as.controllers.CtrlHotel;
import com.as.controllers.Factory;
import com.as.data.Ciutat;
import com.as.data.Client;
import com.as.data.Habitacio;
import com.as.data.Hotel;
import com.as.data.Viatge;
import com.as.data.primarykeys.ViatgePrimaryKey;
import com.as.data.tuples.Tuple;
import com.as.data.tuples.TupleCiutat;

//import src.PagamentClient;

public class DomainCtrl {

	private Factory factory = null;
	private Client client = null;
	private Habitacio habitacio=null;
	private Viatge viatge=null;
	
	public DomainCtrl (Factory f){
		factory = f;
	}
	
	public void guardarCambios(){//guarda cambios en la db
		CtrlClient ccl   = factory.getCtrlClient();
		CtrlHabitacio chab   = factory.getCtrlHabitacio();
		CtrlViatge cv   = factory.getCtrlViatge();
		ccl.update(this.client);//update client
		chab.update(this.habitacio);//update habitacio
		cv.insert(this.viatge);//insert viatge
		
	}
	public float reservaHabitacio(String dniClient, String nomHotel, String nomCiutat, Date dIni, Date dFi, float preuVol){
		//buscamos el viatge previamente creado que tenia campos con null y se los configuramos con los parametros recibidos
		//le calculamos una habitacion lliure y lo reservamos
		
		float preuTotal, preuHab;
		preuTotal = preuHab =0;
		int numHab;
		

		CtrlHabitacio ch = factory.getCtrlHabitacio();
		CtrlHotel chot   = factory.getCtrlHotel();
		
		Viatge v = this.viatge;
		Hotel h = chot.get (nomHotel, nomCiutat);
		numHab = h.numHabLliure(dIni, dFi);
		Habitacio hab = ch.get (numHab, nomHotel, nomCiutat);
		
		preuHab = v.reserva (hab, dIni, dFi);
		preuTotal=preuVol+preuHab;
		//Guardamos el objeto habitacion ya q lohemos asociado con el objeto viatge
		this.habitacio=hab;
		return preuTotal;
		
	}
	
	/**PagamentClient es una clase del cliente que tiene el stub (adaptador) para conectar al Sv **/
/*	public boolean pagament(Integer numTarg, Date dataCad) {
		//TODO ver que los campos sean correctos creo
		return PagamentClient.pagament(numTarg, dataCad);
	}*/
	
	public List<TupleCiutat>  obteCiutats() {//devuelve una lista de nomciutat, preuvol
		
		CtrlCiutat cc = factory.getCtrlCiutat(); 
		
		int i = 0;
		List<TupleCiutat> listup = new ArrayList<TupleCiutat>();
		List<Ciutat> listCiu = cc.getAll();
		Ciutat c;
		
		while (i < listCiu.size()){
			c = listCiu.get(i);
			TupleCiutat t = new TupleCiutat();
			t.nomCiutat = c.getNom();
			t.preuVol = c.getPreuVol();
			listup.add(t);
			i++;		
		}
		
		return listup;//Si es empty activar exc no hi ha ciutats!!!!
		
	}
	
	public List<Tuple>  mostraHotelsLliures(String dniClient, String nomHotel, String nomCiutat, Date dIni, Date dFi) {
		
		List<Tuple> preuHotels;
		CtrlCiutat cc = factory.getCtrlCiutat(); 
		Ciutat c = cc.get (nomCiutat);//obtenemos la ciudad
		preuHotels = c.cercaHotels (dIni, dFi);//retorna una lista de (nomhotel,precio) libres de esa ciudad.
											
		return preuHotels;//Teneis que comprobar si es empty, si lo es activais la pantalla no hi ha hotels!!
		
	}
	
	public boolean exClientNoEx (String dni){//comprueba si el cliente existe, si retorna false activais la exc.
		CtrlClient cc = factory.getCtrlClient(); 
		return cc.exists(dni);
	}

	public boolean excJaTeViatge(String dniClient, Date dataIni, Date dataFi, String nomCiutat){//detecta si tiene viatges solapados
		//llamar a esta funcion antes que enregistraViatge
		CtrlClient cc = factory.getCtrlClient();
		Client c = cc.get(dniClient);
		return c.excJaTeViatge(dniClient, dataIni, dataFi, nomCiutat);//true si solapa, falso si todo OK. Si solapa activa exc.
	}
	
	public float enregistraViatge(String dni, Date dataIni, Date dataFi, String nomCiutat) {//creamos el viatge y se lo pasamos a otra funcion
		float preuVol = 0;
		//sabemos que el cliente existe no hay solapados                                      //para que lo asocie con el cliente
		CtrlClient ccl = factory.getCtrlClient();
		CtrlCiutat cc = factory.getCtrlCiutat();
		
		
		Client cl = ccl.get(dni);//suponemos que el cliente existe seguro ya que antes hemos ejecutado clientNoEx
		Ciutat c = cc.get(nomCiutat);//cogemos la ciudad
		
		Viatge v = new Viatge(new ViatgePrimaryKey(dni, dataIni), cl, c, null, dataFi, null, null  );
		//los 3 valores null son habitacio, nomHotel y numHab ya que aun no sabemos nada de eso hasta que no reservemos.
		preuVol=cl.enregistraViatge(v);//asocia la clase cliente con el viatge y devuelve el precio
		
		//guardamos los objetos para luego insertarlos/actualizarlos
		this.client=cl;
		this.viatge=v;
		return preuVol;
	}
	
	public String[][] conversion( List<TupleCiutat> listuple){
		
		String[][] datos = new String[listuple.size()][2];
		int i=0;
		
		while(i < listuple.size()){
			datos[i][0] = new String(listuple.get(i).nomCiutat);
			datos[i][1] = new String(""+listuple.get(i).preuVol);
			
			i++;
		}
		return datos;
	}
	
	//Faltan algunas funciones de error, pero ya se pondran.
}


	
