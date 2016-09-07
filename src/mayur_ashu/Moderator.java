package mayur_ashu;

import java.util.*;

public class Moderator extends Member {
	protected String em_number;
	protected ArrayList<String> qualification =new ArrayList<String>();
	
	public Moderator() {
	}

	public Moderator(String username,String password ,String primary_email,
			String secondary_email, int usertype, String firstname,
			String lastname, String aboutme, String[] url,
			int status,String streetname,
			String streetnumber,String majormunicipality,String governingdistrict,String postalarea,String num,ArrayList<String> s) {
		super(username,password, primary_email, secondary_email, usertype, firstname,
				lastname, aboutme, url, status,streetname,
				streetnumber,majormunicipality,governingdistrict,postalarea);
		this.em_number=num;
		this.qualification=s;
	}

	/**
	 * @return the em_number
	 */
	public String getEm_number() {
		return em_number;
	}

	/**
	 * @param em_number the em_number to set
	 */
	public void setEm_number(String em_number) {
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
	
}
