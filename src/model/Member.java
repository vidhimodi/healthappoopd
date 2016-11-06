package model;

import java.util.*;

/*
 * super class corresponds to user table to db
 */

public class Member {
	/*protected String username;
	protected String primary_email;
	protected String secondary_email;
	protected String password;
	protected int usertype;
	protected String firstname;
	protected String lastname;
	protected String aboutme;
	protected String url[]=new String[3];
	protected int status;
	protected String streetname;
	protected String streetnumber;
	protected String majormunicipality;
	protected String governingdistrict;
	protected String postalarea;*/
	private String username;
	private String primary_email;
	private String secondary_email;
	private String password;
	private int usertype;
	private String firstname;
	private String lastname;
	private String aboutme;
	private String url[]=new String[3];
	private int status;
	private String streetname;
	private String streetnumber;
	private String majormunicipality;
	private String governingdistrict;
	private String postalarea;
	
	Member(){
	}
	Member(String username,String password,String primary_email,String secondary_email,
			int usertype,String firstname,String lastname,String aboutme,String url[],int status,String streetname,
			String streetnumber,String majormunicipality,String governingdistrict,String postalarea){
		
		this.aboutme=aboutme;
		this.firstname=firstname;
		this.lastname=lastname;
		this.primary_email=primary_email;
		this.secondary_email=secondary_email;
		this.status=status;
		this.url=url;
		this.usertype=usertype;
		this.username=username;
		this.password=password;
		this.streetname=streetname;
		this.streetnumber=streetnumber;
		this.governingdistrict=governingdistrict;
		this.majormunicipality=majormunicipality;
		this.postalarea=postalarea;
	}
	public String viewProfile(){
		return "Member [username=" + username + ", primary_email="
				+ primary_email + ", secondary_email=" + secondary_email
				+ ", password=" + password + ", usertype=" + usertype
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", aboutme=" + aboutme + ", url=" + Arrays.toString(url)
				+ ", status=" + status + ", streetname=" + streetname
				+ ", streetnumber=" + streetnumber + ", majormunicipality="
				+ majormunicipality + ", governingdistrict="
				+ governingdistrict + ", postalarea=" + postalarea + "]";
	}
	
	
	@Override
	public String toString() {
		return "Member [username=" + username + ", primary_email="
				+ primary_email + ", secondary_email=" + secondary_email
				+ ", password=" + password + ", usertype=" + usertype
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", aboutme=" + aboutme + ", url=" + Arrays.toString(url)
				+ ", status=" + status + ", streetname=" + streetname
				+ ", streetnumber=" + streetnumber + ", majormunicipality="
				+ majormunicipality + ", governingdistrict="
				+ governingdistrict + ", postalarea=" + postalarea + "]";
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
			this.lastname = lastname;
		
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the primary_email
	 */
	public String getPrimary_email() {
		return primary_email;
	}
	/**
	 * @param primary_email the primary_email to set
	 */
	public void setPrimary_email(String primary_email) {
			this.primary_email = primary_email;
	}
	/**
	 * @return the secondary_email
	 */
	public String getSecondary_email() {
		return secondary_email;
	}
	/**
	 * @param secondary_email the secondary_email to set
	 */
	public void setSecondary_email(String secondary_email) {
			this.secondary_email = secondary_email;		
	}
	/**
	 * @return the usertype
	 */
	public int getUsertype() {
		return usertype;
	}
	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the aboutme
	 */
	public String getAboutme() {
		return aboutme;
	}
	/**
	 * @param aboutme the aboutme to set
	 */
	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}
	/**
	 * @return the url
	 */
	public String[] getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String[] url) {
			this.url = url;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
			this.status = status;
	}
	/**
	 * @return the status
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param status the status to set
	 */
	public void setPassword(String password,String oldpass) {
			if(oldpass.equals(this.password))
				this.password = password;
			else
				System.out.println("wrong old password entered");
	}
	
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	public String getStreetnumber() {
		return streetnumber;
	}
	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}
	public String getMajormunicipality() {
		return majormunicipality;
	}
	public void setMajormunicipality(String majormunicipality) {
		this.majormunicipality = majormunicipality;
	}
	public String getGoverningdistrict() {
		return governingdistrict;
	}
	public void setGoverningdistrict(String governingdistrict) {
		this.governingdistrict = governingdistrict;
	}
	public String getPostalarea() {
		return postalarea;
	}
	public void setPostalarea(String postalarea) {
		this.postalarea = postalarea;
	}
}
