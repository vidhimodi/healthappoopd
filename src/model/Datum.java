package model;

import java.sql.Timestamp;
import java.util.Date;
/*
 * class that works as row of health data
 */
public class Datum {
	
	protected int DatumID;
	protected String Username;
	protected int PropertyID;
	protected String Value;
	protected java.sql.Timestamp WhenSaved;
	public Datum() {
		// TODO Auto-generated constructor stub
	}
	public Datum(int DatumID,String Username,int PropertyID,String Value,java.sql.Timestamp WhenSaved){
		this.DatumID = DatumID;
		this.Username = Username;
		this.PropertyID = PropertyID;
		this.Value = Value;
		this.WhenSaved = WhenSaved;
		
	}
	public Datum(int DatumID,String Username,int PropertyID,String Value){
		this.DatumID = DatumID;
		this.Username = Username;
		this.PropertyID = PropertyID;
		this.Value = Value;
		Date d =new Date();
		this.WhenSaved =new Timestamp(d.getTime());
	}
	/**
	 * @return the datumID
	 */
	public int getDatumID() {
		return DatumID;
	}
	/**
	 * @param datumID the datumID to set
	 */
	public void setDatumID(int datumID) {
		DatumID = datumID;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}
	/**
	 * @return the propertyID
	 */
	public int getPropertyID() {
		return PropertyID;
	}
	/**
	 * @param propertyID the propertyID to set
	 */
	public void setPropertyID(int propertyID) {
		PropertyID = propertyID;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return Value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		Value = value;
	}
	/**
	 * @return the whenSaved
	 */
	public Timestamp getWhenSaved() {
		return WhenSaved;
	}
	/**
	 * @param whenSaved the whenSaved to set
	 */
	public void setWhenSaved(Timestamp whenSaved) {
		WhenSaved = whenSaved;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Datum [DatumID=" + DatumID + ", Username=" + Username
				+ ", PropertyID=" + PropertyID + ", Value=" + Value
				+ ", WhenSaved=" + WhenSaved + "]";
	}
}
