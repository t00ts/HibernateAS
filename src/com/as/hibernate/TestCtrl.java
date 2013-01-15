package com.as.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.as.data.CtrlViatge;
import com.as.data.Viatge;

public class TestCtrl {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//comprobar ctrlviatge
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		CtrlViatge cv = new CtrlViatge();
		Date dini=sdf.parse("09/01/2013");
		Date dini2=sdf.parse("05/01/2013");
		Date dini3=sdf.parse("01/01/2013");
		Viatge v;
		List<Viatge> lv;
		lv=cv.getAll();
		System.out.println ("DniClient: "+lv.get(0).getDniClient()+" Dataini: "+lv.get(0).getdataInici());
		System.out.println ("DniClient: "+lv.get(1).getDniClient()+" Dataini: "+lv.get(1).getdataInici());
	}

}
