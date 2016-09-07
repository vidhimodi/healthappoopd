package mayur_ashu;

public class Admin extends Member {
	String em_num;
	public Admin() {
	}

	/**
	 * @param username
	 * @param primary_email
	 * @param secondary_email
	 * @param usertype
	 * @param firstname
	 * @param lastname
	 * @param address
	 * @param aboutme
	 * @param url
	 * @param status
	 */
	public Admin(String username, String password,String primary_email, String secondary_email,
			int usertype, String firstname, String lastname, String aboutme, String[] url, int status,String streetname,
			String streetnumber,String majormunicipality,String governingdistrict,String postalarea,String num) {
		super(username,password ,primary_email, secondary_email, usertype, firstname,
				lastname, aboutme, url, status,streetname,
				streetnumber,majormunicipality,governingdistrict,postalarea);
		this.em_num=num;
		
	}
	/**
	 * @return the em_num
	 */
	public String getEm_num() {
		return em_num;
	}

	/**
	 * @param em_num the em_num to set
	 */
	public void setEm_num(String em_num) {
		this.em_num = em_num;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Admin ["+super.viewProfile()+"em_num=" + em_num + "]";
	}
	@Override
	public String viewProfile(){
		System.out.println(this.toString());
		return this.toString();
	}
	
}
