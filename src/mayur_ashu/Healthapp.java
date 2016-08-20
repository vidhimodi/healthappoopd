package mayur_ashu;

import java.util.*;

public class Healthapp {
	
	ArrayList<Member> member=new ArrayList<Member>();
	private Scanner in;
	private static Scanner in2;
	
	public void work(Member cur){
		String worktodo;
		while(cur.loggedin()){
			System.out.println("Enter view for viewing the profile / logout for logging out");
			worktodo=in.next();
			switch(worktodo){
			case "view":
					cur.viewProfile();
				break;
			case "logout":cur.loggedout();
				System.out.println("You logged out");
				break;
			}
		}
	}
	
	public Boolean notunique(String uname,Healthapp healthapp){	
		if(uname.length()>20){
			return false;
		}
		Iterator<Member> itr=healthapp.member.iterator();//getting Iterator from arraylist to traverse elements  
		while(itr.hasNext()){ 
			Member cur=itr.next();
			if(cur.getUsername().equals(uname)){
				return true;
			}
		}
		return false;
	}
	
	public Boolean print_error_stat(){
		System.out.println("Already taken username or length greater than 20...try again with different unique name with length less than 20");
		return true;
	}
	
	public Member login(Healthapp healthapp){
		String upass,uemail;
		Member cur=null;
		in = new Scanner(System.in);
		System.out.println("enter the primary email id");
		uemail=in.next();
		System.out.println("enter the password");
		upass=in.next();
		Iterator<Member> itr=healthapp.member.iterator();//getting Iterator from arraylist to traverse elements  
		while(itr.hasNext()){ 
			cur=itr.next();
			if(cur.primary_email.equals(uemail)){
				if(cur.password.equals(upass)){
					cur.status="Available";
					if(cur instanceof User){
						User t = (User)cur;
						t.changeutype();
					}
					/*if(!cur.usertype.equals("admin")&&!cur.usertype.equals("Mod")){
						User t = (User)cur;
						t.changeutype();
					}*/
					return cur;
					/*
					 * obj instanceof C
					 * if(cur instanceof User){
					 * 		User t = (User)cur;
					 * 		t.changeutype();
					 * }
					 * */
				}
				else{
					System.out.println("wrong email id or password or unregistered user");
				}
			}
		}
		return null;
	}
	
	public void register(Healthapp healthapp){
		String utype,uname;
		System.out.println("enter the usertype :option: User,A Admin, Moderator");
		utype=in2.next();
		do{
		System.out.println("enter the user name");
		uname=in2.next();
		}while(healthapp.notunique(uname,healthapp)&&healthapp.print_error_stat());
		
		switch(utype){
			case "User": User u=new User();
						u.register(uname, utype);
						healthapp.member.add(u);
				break;
			case "Admin":Admin ad=new Admin();
					ad.register(uname, "admin");
					healthapp.member.add(ad);
				break;
			case "Moderator":Moderator mo=new Moderator();
					mo.register(uname, "Mod");
					healthapp.member.add(mo);
				break;
			default:
		}
	}
	
	public static void main(String[] args) {
		Healthapp healthapp = new Healthapp();
		Member insys=null;
		String func;
		in2 = new Scanner(System.in);
		while(true){
			System.out.println("type Login or Register or exit");
			func=in2.next();
			if(func.equals("Login")){
				insys = healthapp.login(healthapp);
				if(insys!=null){
					healthapp.work(insys);
				}
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
