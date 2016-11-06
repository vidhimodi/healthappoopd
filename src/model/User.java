package model;

import java.util.Arrays;
import java.util.Date;
/*
 * user class corresponds to enduser table of db
 */
public final class User extends Member {
	protected int karma;
	protected java.sql.Timestamp date;
	public final long date_created;	
	public static final long MONTHTimestamp=2592000000L; //2592073436
	public static final long YEARimestamp=31536000000L; //34214421043
	
	public User() {
		super();
		karma=1;
		Date date1=new Date();
		date_created=date1.getTime();
		date= new java.sql.Timestamp(date_created);
	}

	public User(String username, String password,String primary_email, String secondary_email,
			String firstname, String lastname,
			String aboutme, String[] url, int status,String streetname,
			String streetnumber,String majormunicipality,String governingdistrict,String postalarea) {
		super(username, password,primary_email, secondary_email, 1, firstname,
				lastname, aboutme, url, status,streetname,
				streetnumber,majormunicipality,governingdistrict,postalarea);
		karma=1;
		Date date1=new Date();
		date_created=date1.getTime();
		date= new java.sql.Timestamp(date_created);;
	}
	
	public User(String username, String password,String primary_email, String secondary_email,
			String firstname, String lastname,
			String aboutme, String[] url, int status,String streetname,
			String streetnumber,String majormunicipality,String governingdistrict,String postalarea,
			int karma,int utype,java.sql.Timestamp date) {
		super(username, password,primary_email, secondary_email, utype, firstname,
				lastname, aboutme, url, status,streetname,
				streetnumber,majormunicipality,governingdistrict,postalarea);
		this.karma=karma;
		this.date=date;
		date_created=date.getTime();
	}
	
	/**
	 * @return the karma
	 */
	public int getKarma() {
		return karma;
	}

	/**
	 * @param karma the karma to set
	 */
	public void setKarma(int karma) {
		this.karma = karma;
	}
	/*
	 * function to change the utype automatically for enduser
	 */
	public void changeutype(){
		Date n = new Date();
		long diff=n.getTime()-date_created;
		if(diff>MONTHTimestamp&&diff<YEARimestamp){
			setUsertype(2);
		}else if(diff>YEARimestamp){
			setUsertype(3);
		}
		else{
			setUsertype(1);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String viewProfile(){
		System.out.println(this.toString());
		return this.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [karma=" + karma + ", date=" + date + ", date_created="
				+ date_created + ", getLastname()=" + getLastname()
				+ ", getUsername()=" + getUsername() + ", getPrimary_email()="
				+ getPrimary_email() + ", getSecondary_email()="
				+ getSecondary_email() + ", getUsertype()=" + getUsertype()
				+ ", getFirstname()=" + getFirstname() + ", getAboutme()="
				+ getAboutme() + ", getUrl()=" + Arrays.toString(getUrl())
				+ ", getStatus()=" + getStatus() + ", getPassword()="
				+ getPassword() + ", getStreetname()=" + getStreetname()
				+ ", getStreetnumber()=" + getStreetnumber()
				+ ", getMajormunicipality()=" + getMajormunicipality()
				+ ", getGoverningdistrict()=" + getGoverningdistrict()
				+ ", getPostalarea()=" + getPostalarea() + "]";
	}

	//@Override
	/*public String toString() {
		return "User [karma=" + karma + ", date=" + date + ", date_created="
				+ date_created + ", username=" + username + ", primary_email="
				+ primary_email + ", secondary_email=" + secondary_email
				+ ", password=" + password + ", usertype=" + usertype
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", aboutme=" + aboutme + ", url=" + Arrays.toString(url)
				+ ", status=" + status + ", streetname=" + streetname
				+ ", streetnumber=" + streetnumber + ", majormunicipality="
				+ majormunicipality + ", governingdistrict="
				+ governingdistrict + ", postalarea=" + postalarea + "]";
	}*/
	
}