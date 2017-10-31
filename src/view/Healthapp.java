package view;

import java.sql.*;
import java.util.*;

import controller.*;
import model.*;
/*
 * class which have main function 
 * entry point to the system
 * show various menus
 * calls friendshiparraylist controller and db model
 */
public class Healthapp {
	
	ArrayList<Member> session=new ArrayList<Member>();
	private Scanner in=new Scanner(System.in);
	HealthController hc = new HealthController();
	/*
	 * worker code when a person is logged in
	 */
	public void work(Member cur){
		String worktodo;
		while(session.contains(cur)){
			System.out.println("Enter your choice :-");
			System.out.println("-------------Edit Account--------------");
            System.out.println("Enter deactivate to deactivate the account");
            System.out.println("Enter view for viewing the profile");
            System.out.println("Enter update for updating the account");
            System.out.println("Enter l to logout ");
            System.out.println("-------------Health--------------");
            System.out.println("Enter addhealth to add health data ");
            System.out.println("enter viewData to view health data of ur or ur friend");
            System.out.println("-------------Forum--------------");
            System.out.println("enter acforums to view all active forum");
            System.out.println("enter viewforum to view forum with all its post, commment and ratings");
            System.out.println("enter addpost to add post to a forum");
            System.out.println("Enter addcom to add comment to a post on a forum");
            System.out.println("Enter addrate to rate a post of a forum");
			if(cur instanceof User){
				System.out.println("-------------Friend--------------");
				System.out.println("Enter lau to list all the users");
	            System.out.println("Enter lra to see friend request accepted/rejected");
	            System.out.println("Enter lrs to see list of friend request sent");
	            System.out.println("Enter lfl to see list of all friends");
	            System.out.println("Enter wr to withdraw friend request");
	            System.out.println("Enter sr to send the friend request");
	            System.out.println("Enter rr to reject friend request");
	            System.out.println("Enter ar accept friend request");
			}else if(cur instanceof Moderator){
				System.out.println("enter addforum to create a new forum");
				System.out.println("enter delforum to delete a particular forum");
			}
			worktodo=in.next();
			switch(worktodo){
			case "deactivate":
				cur.setStatus(0);
				session.remove(cur);
				hc.updatefriend();
				hc.update(cur);
				System.out.println("You logged out");
				break;
			case "view":cur.viewProfile();
				break;
			case "update":update(cur);
				break;
			case "lau":System.out.println("u can send request to below people");
					System.out.println(hc.showalluser(cur.getUsername()));
				break;
			case "lra":System.out.println("u can reject/accept request of below people");
						System.out.println(hc.showusertoaccepted(cur.getUsername()));
				break;
			case "lrs":System.out.println("show request and their status");
						System.out.println(hc.showrequesteduserlist(cur.getUsername()));
				break;
			case "lfl":System.out.println("your friendlist");
						System.out.println(hc.showfriendlist(cur.getUsername()));
				break;
			case "wr":System.out.println("enter the username of the person u want withdraw request");
					String withdrawusername=in.nextLine();
					if(hc.withdrawRequest(withdrawusername,cur.getUsername())){
						System.out.println("success");
					}
					else{
						System.out.println("wrong input or any other error");
					}
					hc.updatefriend();
					hc.update(cur);
				break;
			case "sr":System.out.println("enter the username of the person u want to become friend");
					String requestedusername=in.next();
					if(hc.sentRequest(requestedusername,cur.getUsername())){
						System.out.println("success");
					}
					else{
						System.out.println("wrong input or any other error");
					}
					hc.updatefriend();
					hc.update(cur);
				break;
			case "rr":System.out.println("enter the username of the person u want to reject");
					String rejectusername=in.next();
					if(hc.rejectedRequest(rejectusername,cur.getUsername())){
						System.out.println("success");
					}
					else{
						System.out.println("wrong input or any other error");
					}
					hc.updatefriend();
					hc.update(cur);
				break;
			case "ar":System.out.println("enter the username of the person u want to accept request");
					String acceptusername=in.next();
					if(hc.acceptRequest(acceptusername,cur.getUsername())){
						System.out.println("success");
					}
					else{
						System.out.println("wrong input or any other error");
					}
					hc.updatefriend();
					hc.update(cur);
				break;
			case "addhealth":addhealth(cur);
				break;
			case "viewData" : viewDatum(cur);
				break;
			case "addforum":addforum(cur);
				break;
			case "delforum":delforum(cur);
				break;
			case "acforums":acforums();
				break;
			case "viewforum":viewforum();
				break;
			case "addpost":addpost(cur);
				break;
			case "addcom":addcom(cur);
				break;
			case "addrate":addrate(cur);
				break;
			case "l":session.remove(cur);	
				hc.updatefriend();
				hc.update(cur);
				System.out.println("You logged out");
				break;
			}
		}
	}
	
	public void addrate(Member cur){
		System.out.println("enter the postusername");
		String postuname = in.next();
		System.out.println("enter the posttime in timestamp");
		Long timestamp=  in.nextLong();
		java.sql.Timestamp posttime =new Timestamp(timestamp);
		System.out.println("enter no of star {1,2,3,4,5}");
		int star = in.nextInt();
		if(star>0&&star<6){
			if(hc.addrate(cur,postuname,posttime,star)){
				System.out.println("Success");
				if(cur instanceof User){
					((User)cur).setKarma(((User)cur).getKarma()+1);
				}
				hc.update(cur);
			}else{
				System.out.println("u entered wrong input");
			}
		}else{
			System.out.println("u entered wrong input");
		}
	}
	
	public void addcom(Member cur){
		System.out.println("enter the postusername");
		String postuname = in.next();
		System.out.println("enter the posttime in timestamp");
		Long timestamp=  in.nextLong();
		java.sql.Timestamp posttime =new Timestamp(timestamp);
		System.out.println("enter the text entry");
		String text = in.next();
		System.out.println("enter the photolocation");
		String photolocation = in.next();
		System.out.println("enter the linklocation");
		String linklocation = in.next();
		System.out.println("enter the videolocation");
		String videolocation = in.next();
		if(hc.addcom(cur,postuname,posttime,text,photolocation,linklocation,videolocation)){
			System.out.println("Success");
			if(cur instanceof User){
				((User)cur).setKarma(((User)cur).getKarma()+1);
			}
			hc.update(cur);
		}else{
			System.out.println("u entered wrong input");
		}
	}
	
	public void addpost(Member cur){
		System.out.println("enter the forum id");
		int forumid = in.nextInt();
		System.out.println("enter the text entry");
		String text = in.next();
		System.out.println("enter the photolocation");
		String photolocation = in.next();
		System.out.println("enter the linklocation");
		String linklocation = in.next();
		System.out.println("enter the videolocation");
		String videolocation = in.next();
		if(hc.addpost(cur,forumid,text,photolocation,linklocation,videolocation)){
			if(cur instanceof User){
				((User)cur).setKarma(((User)cur).getKarma()+1);
			}
			hc.update(cur);
			System.out.println("Success");
		}else{
			System.out.println("u entered wrong input");
		}
	}
	
	public void viewforum(){
		System.out.println("enter the forum id to be viewesd");
		int forumid = in.nextInt();
		in.nextLine();
		System.out.println(hc.viewforum(forumid));
	}
	
	public void acforums(){
		ArrayList<Forum> forum = hc.getAcForums();
		for(Forum f:forum){
			System.out.println(f.toString());
		}
	}
	public void addforum(Member cur){
		System.out.println("enter Topic of forum");
		String topic = in.next();
		System.out.println("enter Url of forum");
		String url = in.next();
		System.out.println("enter Summary of forum");
		String summary = in.next();
		if(hc.addforum(cur, topic,url,summary)){
			System.out.println("Success");
		}
	}
	public void delforum(Member cur){
		System.out.println("enter forum id to be deleted");
		int forumid = in.nextInt();
		in.nextLine();
		if(hc.delforum(cur,forumid)){
			System.out.println("Success");
		}
		
	}
	public void viewDatum(Member cur){
		System.out.println("enter ur username or one of below");
		System.out.println(hc.showfriendlist(cur.getUsername()));
		String ch=in.next();
		System.out.println(ch);
		System.out.println(hc.viewDatum(cur,ch));
	}
	
	public void addhealth(Member cur){
		int choice=0;
		String data=null;
		System.out.println("Enter your choice :-");
		for(int i=1;i<4;i++){
			System.out.println("Press "+i+" for "+hc.showPropertyDescription(i));
		}
		choice = in.nextInt();
		in.nextLine();
		System.out.println("Enter your data");
		data = in.nextLine();
		if(choice<4&&valid(data,choice)&&hc.addDatum(cur.getUsername(), choice, data)){
			if(cur instanceof User){
				((User)cur).setKarma(((User)cur).getKarma()+1);
			}
			System.out.println("Success");
		}
		hc.updateDatum();
		hc.update(cur);
	}
	public Boolean valid(String data,int ch){
		int d=Integer.parseInt(data);
		if(ch==1){
			if(d>0&&d<10){
				return true;
			}else{
				System.out.println("wrong data");
				return false;
			}
		}else if(ch==2){
			if(d>10&&d<2500){
				return true;
			}else{
				System.out.println("wrong data");
				return false;
			}
		}else if(ch==3){
			if(d>30&&d<150){
				return true;
			}else{
				System.out.println("wrong data");
				return false;
			}
		}else{
			System.out.println("wrong choice");
			return false;
		}
	}
	/*
	 *function to get the input from users to update its profile 
	 */
	public void update(Member cur){
		int flag=0;
		do{
			System.out.println("what u want to change");
			System.out.println("1. password,2. firstname,3.lastname,4.aboutme,5.streetname,6.streetnumber,"
					+ "7.mcd,8.district,9.postalarea 12 exit");
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
				case 12:flag=1;
					break;
				default:flag=1;
			}
		}while(flag==0);

		hc.updatefriend();
		hc.update(cur);
	}
	public static Boolean print_error_stat(){
		System.out.println("Already taken id or length greater than 20...try again with different unique name with length less than 20");
		return true;
	}	
	/*
	 * function to login the person
	 */
	public void login(){
		String upass,uemail;
		Member cur=null;
		in = new Scanner(System.in);
		System.out.println("enter the primary email id");
		uemail=in.next();
		System.out.println("enter the password");
		upass=in.next();
		int resultcode=hc.authact(uemail, upass);
		if(resultcode==0){
			System.out.println("Your account is deactivated press 1 for reactivation else 0");
			int choose=0;
			choose=in.nextInt();
			if(choose!=1){
				return ;
			}
		}
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
	/*
	 * master method to regsiter any users
	 */
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
	/*
	 * register a user
	 */
	public void register(Healthapp healthapp){
		String uname;
		int utype;
		System.out.println("enter the usertype :option:1. User,2.Admin,3.Moderator");
		utype=in.nextInt();
		in.nextLine();
		if(utype>0&&utype<4){
			do{
				System.out.println("enter the user name");
				uname=in.next();
			}while((hc.exists_uname(uname)||uname.length()>20)&&Healthapp.print_error_stat());
			Member m = master_register(uname, utype);
			hc.register(m);
		}
		else{
			System.out.println("wrong input");
		}
	}	
}
