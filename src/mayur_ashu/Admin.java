package mayur_ashu;
/**
 * 
 */


import java.util.Scanner;

/**
 * @author ashu mayur
 *
 */
public class Admin extends Member {
	long em_num;
	private Scanner in;
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
			String usertype, String firstname, String lastname, String address,
			String aboutme, String[] url, String status,long num) {
		super(username,password ,primary_email, secondary_email, usertype, firstname,
				lastname, address, aboutme, url, status);
		this.em_num=num;
		
	}
	/**
	 * @return the em_num
	 */
	public long getEm_num() {
		return em_num;
	}

	/**
	 * @param em_num the em_num to set
	 */
	public void setEm_num(long em_num) {
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
	public void register(String uname,String utype){
		super.register(uname, utype);
		in = new Scanner(System.in);
		System.out.println("enter the emergencyu contact number");
		setEm_num(in.nextLong());
	}
}
