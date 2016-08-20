package mayur_ashu;


import java.util.*;

public class User extends Member {
	protected int karma;
	public Date date;
	public final long date_created;	
	public static final long MONTHTimestamp=2628000000L;
	public static final long YEARimestamp=315360000000000L;
	//protected 
	
	public User() {
		super();
		karma=1;
		date=new Date();
		date_created=date.getTime();
	}

	public User(String username, String password,String primary_email, String secondary_email,
			String firstname, String lastname, String address,
			String aboutme, String[] url, String status) {
		super(username, password,primary_email, secondary_email, "new", firstname,
				lastname, address, aboutme, url, status);
		karma=1;
		date=new Date();
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
	
	public void changeutype(){
		long diff=date.getTime()-date_created;
		if(diff>YEARimestamp){
			setUsertype("middle");
		}else if(diff>MONTHTimestamp){
			setUsertype("old");
		}
		else{
			setUsertype("new");
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
	
	public String toString() {
		return "User [ "+ super.viewProfile()+" karma=" + karma + "] ";
	}
	public void register(String uname,String utype){
		super.register(uname, "new");	
	}
}