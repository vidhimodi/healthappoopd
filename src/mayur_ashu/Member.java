package mayur_ashu;


import java.util.*;

public class Member {
	protected String username;
	protected String primary_email;
	protected String secondary_email;
	protected String password;
	protected String usertype;
	protected String firstname;
	protected String lastname;
	protected String address;
	protected String aboutme;
	protected String url[]=new String[3];
	protected String status;
	private Scanner in;
	Member(){
	}
	Member(String username,String password,String primary_email,String secondary_email,
			String usertype,String firstname,String lastname,
			String address,String aboutme,String url[],String status){
		
		this.aboutme=aboutme;
		this.address=address;
		this.firstname=firstname;
		this.lastname=lastname;
		this.primary_email=primary_email;
		this.secondary_email=secondary_email;
		this.status=status;
		this.url=url;
		this.usertype=usertype;
		this.username=username;
		this.password=password;
	}
	public String viewProfile(){
		if(!loggedin())
			return "please log in";
		return " username=" + username + ", primary_email="
		+ primary_email + ", secondary_email=" + secondary_email
		+ ", password=" + password + ", usertype=" + usertype
		+ ", firstname=" + firstname + ", lastname=" + lastname
		+ ", address=" + address + ", aboutme=" + aboutme + ", url="
		+ Arrays.toString(url) + ", status=" + status;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(!loggedin())
			return "please log in";
		return " username=" + username + ", primary_email="
				+ primary_email + ", secondary_email=" + secondary_email
				+ ", password=" + password + ", usertype=" + usertype
				+ ", firstname=" + firstname + ", lastname=" + lastname
				+ ", address=" + address + ", aboutme=" + aboutme + ", url="
				+ Arrays.toString(url) + ", status=" + status;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		if(loggedin())
			this.lastname = lastname;
		else
			System.out.println("please log in");
		
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
		if(loggedin())
			this.primary_email = primary_email;
		else
			System.out.println("please log in");
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
		if(loggedin())
			this.secondary_email = secondary_email;
		else
			System.out.println("please log in");
		
	}
	/**
	 * @return the usertype
	 */
	public String getUsertype() {
		return usertype;
	}
	/**
	 * @param usertype the usertype to set
	 */
	public void setUsertype(String usertype) {
		if(loggedin())
			this.usertype = usertype;
		else
			System.out.println("please log in");
		
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
		
		if(loggedin())
			this.firstname = firstname;
		else
			System.out.println("please log in");
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		if(loggedin())
			this.address = address;
		else
			System.out.println("please log in");
		
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
		if(loggedin())
			this.aboutme = aboutme;
		else
			System.out.println("please log in");
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
		if(loggedin())
			this.url = url;
		else
			System.out.println("please log in");
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
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
	public void setPassword(String password) {
		if(loggedin()){
			String oldpass;
			in = new Scanner(System.in);
			System.out.println("enter the old password");
			oldpass=in.next();
			if(oldpass.equals(this.password))
				this.password = password;
			else
				System.out.println("wrong old password entered");
		}
		else
			System.out.println("please log in");
	}

	public void register(String uname,String utype){
		in = new Scanner(System.in);
		String temp;
		this.username=uname;
		this.status="invisible";
		this.usertype=utype;
		System.out.println("enter the password");
		temp=in.next();
		this.password=temp;
		System.out.println("enter the first name");
		this.firstname=in.next();
		System.out.println("enter the last name");
		this.lastname=in.next();
		System.out.println("enter the primary email address");
		this.primary_email=in.next();
		System.out.println("enter the secondary email address");
		this.secondary_email=in.next();
		System.out.println("enter the Address");
		this.address=in.next();
		System.out.println("enter the about me");
		this.aboutme=in.next();
		System.out.println("enter the 1st image url");
		url[0]=in.next();
		System.out.println("enter the 2nd image url");
		url[1]=in.next();
		System.out.println("enter the 3rd image url");
		url[2]=in.next();
	}
	public void loggedout(){
		setStatus("invisible");
	}
	public Boolean loggedin(){
		if(getStatus().equals("Available"))
			return true;
		return false;
	}
}
