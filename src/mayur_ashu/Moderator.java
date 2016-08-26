package mayur_ashu;

import java.util.*;

public class Moderator extends Member {
	protected long em_number;
	protected ArrayList<String> qualification =new ArrayList<String>();
	private Scanner in;
	
	public Moderator() {
	}

	public Moderator(String username,String password ,String primary_email,
			String secondary_email, String usertype, String firstname,
			String lastname, String address, String aboutme, String[] url,
			String status,long num,ArrayList<String> s) {
		super(username,password, primary_email, secondary_email, usertype, firstname,
				lastname, address, aboutme, url, status);
		this.em_number=num;
		this.qualification=s;
	}

	/**
	 * @return the em_number
	 */
	public long getEm_number() {
		return em_number;
	}

	/**
	 * @param em_number the em_number to set
	 */
	public void setEm_number(long em_number) {
		this.em_number = em_number;
	}

	/**
	 * @return the qualification
	 */
	public ArrayList<String> getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(ArrayList<String> qualification) {
		this.qualification = qualification;
	}
	
	public void addqua(String newqual){
		this.qualification.add(newqual);
	}
	public void removequa(String rmqual){
		if(this.qualification.contains(rmqual)){
			this.qualification.remove(rmqual);
		}
		else{
			System.out.println("this qualification is not present");
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Moderator ["+super.viewProfile()+"em_number=" + em_number + ", qualification="
				+ qualification + "]";
	}
	@Override
	public String viewProfile(){
		System.out.println(this.toString());
		return this.toString();
	}
	
	public void register(String uname,String utype){
		super.register(uname, utype);
		in = new Scanner(System.in);
		System.out.println("enter the phone number");
		setEm_number(in.nextLong());
		System.out.println("enter the degeee qualification..."
				+ "separate degre by newline and when done enter done");
		String qual=in.next();
		while(!qual.equals("done")){
			qualification.add(qual);
			qual=in.next();
		}
	}
}
