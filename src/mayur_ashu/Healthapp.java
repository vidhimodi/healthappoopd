package mayur_ashu;

import java.util.*;

public class Healthapp {
	
	ArrayList<Member> session=new ArrayList<Member>();
	private Scanner in;
	private static Scanner in2;
	HealthController hc = new HealthController();
	
	public void work(Member cur){
		String worktodo;
		while(session.contains(cur)){
			System.out.println("Enter view for viewing the profile / logout for logging out");
			worktodo=in.next();
			switch(worktodo){
			case "deactivate":
				cur.setStatus(0);
				session.remove(cur);
				hc.update(cur);
				System.out.println("You logged out");
				break;
			case "view":cur.viewProfile();
				break;
			case "update":update(cur);
				break;
			case "list of all users":System.out.println("u can send reject to below people");
					System.out.println(hc.showalluser(cur.getUsername()));
				break;
			case "list of request to be accepted":System.out.println("u can send reject to below people");
						System.out.println(hc.showusertoaccepted(cur.getUsername()));
				break;
			case "list of request sent":System.out.println("u can send reject to below people");
						System.out.println(hc.showrequesteduserlist(cur.getUsername()));
				break;
			case "list of friend list":System.out.println("u can send reject to below people");
						System.out.println(hc.showfriendlist(cur.getUsername()));
				break;
			case "withdraw request":System.out.println("enter the username of the person u want withdraw request");
					String withdrawusername=in.nextLine();
					if(hc.withdrawRequest(withdrawusername,cur.getUsername())){
						System.out.println("success");
					}
					else{
						System.out.println("wrong input or any other error");
					}
				break;
			case "sent request":System.out.println("enter the username of the person u want to become friend");
					String requestedusername=in.nextLine();
					if(hc.sentRequest(requestedusername,cur.getUsername())){
						System.out.println("success");
					}
					else{
						System.out.println("wrong input or any other error");
					}
				break;
			case "reject request":System.out.println("enter the username of the person u want to reject");
					String rejectusername=in.nextLine();
					if(hc.rejectedRequest(rejectusername,cur.getUsername())){
						System.out.println("success");
					}
					else{
						System.out.println("wrong input or any other error");
					}
				break;
			case "accept request":System.out.println("enter the username of the person u want to accept request");
					String acceptusername=in.nextLine();
					if(hc.acceptRequest(acceptusername,cur.getUsername())){
						System.out.println("success");
					}
					else{
						System.out.println("wrong input or any other error");
					}
				break;
			case "logout":session.remove(cur);
				hc.update(cur);
				System.out.println("You logged out");
				break;
			}
		}
	}
	public void update(Member cur){
		int flag=0;
		do{
			System.out.println("what u want to change");
			System.out.println("1. password,2. firstname,3.lastname,4.aboutme,5.streetname,6.streetnumber,"
					+ "7.mcd,8.district,9.postalarea");
			if(cur.getUsertype()==4){
				System.out.println("10.emergency contact");
			}
			if(cur.getUsertype()==5){
				System.out.println("10.emergency contact 11.qualification");
			}
			int choose=in.nextInt();
			in.nextLine();
			switch(choose){
				case 1:System.out.println("enter the old password");
						String old = in.nextLine();
						System.out.println("enter the new password");
						String n= in.nextLine();
						cur.setPassword(n, old);
					break;
				case 2:System.out.println("enter the first name");
					cur.setFirstname(in.nextLine());
					break;
				case 3:System.out.print("enter the last name");
					cur.setLastname(in.nextLine());
					break;
				case 4:System.out.println("enter the new about me");
					cur.setAboutme(in.nextLine());
					break;
				case 5:System.out.println("enter the new street name");
					cur.setStreetname(in.nextLine());
					break;
				case 6:System.out.println("enter the new street number");
				cur.setStreetnumber(in.nextLine());
					break;
				case 7:System.out.println("enter the new mcd");
				cur.setMajormunicipality(in.nextLine());
					break;
				case 8:System.out.println("enter the new govt");
				cur.setGoverningdistrict(in.nextLine());
					break;
				case 9:System.out.println("enter the new district");
				cur.setGoverningdistrict(in.nextLine());
					break;
				case 10:System.out.println("enter the new emergency contact mnumber");
						if(cur instanceof Admin){
							Admin a =(Admin)cur;
							a.setEm_num(in.nextLine());
						}
						if(cur instanceof Moderator){
							Moderator m =(Moderator)cur;
							m.setEm_number(in.nextLine());
						}
					break;
				case 11:Moderator m =(Moderator)cur;
					System.out.println("your old qualifications" + m.getQualification());
					System.out.print("enter new qualification in newline untill done");
					ArrayList<String> newqual=new ArrayList<String>();
					String qual=in.nextLine();
					while(qual.equalsIgnoreCase("done")){
						newqual.add(qual);
						qual=in.nextLine();
					}
					m.setQualification(newqual);
					break;
				default:flag=1;
			}
		}while(flag==0);
		hc.update(cur);
	}
	public static Boolean print_error_stat(){
		System.out.println("Already taken id or length greater than 20...try again with different unique name with length less than 20");
		return true;
	}	
	public void login(){
		String upass,uemail;
		Member cur=null;
		in = new Scanner(System.in);
		System.out.println("enter the primary email id");
		uemail=in.next();
		System.out.println("enter the password");
		upass=in.next();
		cur=hc.login(uemail,upass);
		if(cur!=null){
			session.add(cur);
			cur.setStatus(1);
			if(cur instanceof User){
				((User) cur).changeutype();
			}
			work(cur);
			hc.loginattempt(cur.getUsername(),cur.getPassword(),"succes");
		}
		else{
			System.out.println("wrong details entered");
			hc.loginattempt(uemail,upass,"fail");
		}
	}
	public Member master_register(String uname,int utype){
		String username=uname;
		String primary_email;
		String secondary_email;
		String password;
		int usertype=utype;
		String firstname;
		String lastname;
		String streetname;
		String streetnumber;
		String majormunicipality;
		String governingdistrict;
		String postalarea;
		String aboutme;
		String url[]=new String[3];
		int status=1;
		String num;
		ArrayList<String> qualification =new ArrayList<String>();
		in = new Scanner(System.in);
		System.out.println("enter the password");
		password=in.nextLine();
		System.out.println("enter the first name");
		firstname=in.nextLine();
		System.out.println("enter the last name");
		lastname=in.nextLine();
		
		do{
			System.out.println("enter the primary email address");
			primary_email=in.nextLine();
		}while((hc.exists_email(primary_email)||hc.incorrectFormatemail(primary_email))&&Healthapp.print_error_stat());
		
		do{
			System.out.println("enter the secondary email address");
			secondary_email=in.nextLine();
		}while((hc.exists_email(secondary_email)||hc.incorrectFormatemail(secondary_email))&&Healthapp.print_error_stat());
		
		System.out.println("enter the street number");
		streetnumber=in.nextLine();
		System.out.println("enter the street name");
		streetname=in.nextLine();
		System.out.println("enter the major municipality");
		majormunicipality=in.nextLine();
		System.out.println("enter the governing district");
		governingdistrict=in.nextLine();
		System.out.println("enter the postal area");
		postalarea=in.nextLine();
		System.out.println("enter the about me");
		aboutme=in.nextLine();
		System.out.println("enter the 1st image url");
		url[0]=in.nextLine();
		System.out.println("enter the 2nd image url");
		url[1]=in.nextLine();
		System.out.println("enter the 3rd image url");
		url[2]=in.nextLine();
		switch(utype){
		case 1: return new User(username, password, primary_email, secondary_email, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea);
		
		case 2:		do{
						System.out.println("enter the emergency contact number");
						num=in.nextLine();
					}while(hc.incorrectFormatphone(num)&&Healthapp.print_error_stat());
					return new Admin(username, password, primary_email, secondary_email, usertype+2, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea, num);
		case 3:		do{
						System.out.println("enter the emergency contact number");
						num=in.nextLine();
					}while(hc.incorrectFormatphone(num)&&Healthapp.print_error_stat());
					System.out.println("enter the degeee qualification. Separate degree by newline and when done enter done");
					String qual=in.nextLine();
					while(!qual.equals("done")){
						qualification.add(qual);
						qual=in.nextLine();
					}
					return new Moderator(username, password, primary_email, secondary_email, usertype+2, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea, num, qualification);
		default:return null;
	}
}
	public void register(Healthapp healthapp){
		String uname;
		int utype;
		System.out.println("enter the usertype :option:1. User,2.Admin,3.Moderator");
		utype=in2.nextInt();
		in2.nextLine();
		do{
			System.out.println("enter the user name");
			uname=in2.next();
		}while((hc.exists_uname(uname)||uname.length()>20)&&Healthapp.print_error_stat());
		Member m = master_register(uname, utype);
		hc.register(m);
	}	
	public static void main(String[] args) {
		Healthapp healthapp = new Healthapp();
		String func;
		in2 = new Scanner(System.in);
		while(true){
			System.out.println("type Login or Register or exit");
			func=in2.next();
			if(func.equals("Login")){
				healthapp.login();
			}
			else if(func.equals("Register")){
				healthapp.register(healthapp);
			}
			else{
				break;
			}
		}	
	}
}
