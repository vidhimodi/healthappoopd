package controller;

import java.util.ArrayList;

import model.*;


/*
 * this is the controller class for managing 
 * Health data. 
 * it stores data into DB
 * and send it to view class for display
 */
public class DatumController {
	public ArrayList<Datum> datum = new ArrayList<Datum>();
	
	public void add(Datum d){
		datum.add(d);
	}
	
	/*public int maxDatumId(){
		int max=0;
		for(Datum d :datum){
			if(d.getDatumID()>max){
				max=d.getDatumID();
			}
		}
		return max;
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatumController [datum=" + datum + "]";
	}

	/**
	 * @return the datum
	 */
	public ArrayList<Datum> getDatum() {
		return datum;
	}

	/**
	 * @param datum the datum to set
	 */
	public void setDatum(ArrayList<Datum> datum) {
		this.datum = datum;
	}
}
