package controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

/*
 * class that takes values from healthapp class and sent it to db model class
 * it gets result set as output 
 * Pre-process it and sent the processed output to healthapp class for
 * user viewing
 */
public class HealthController {
	
	Db db = new Db();
	private FriendshipController friendtable=new FriendshipController();
	private DatumController datumtable=new DatumController();
	private ForumController forumcontroller = new ForumController();
	
	/**
	 * @return the db
	 */
	public Db getDb() {
		return db;
	}
	/**
	 * @param db the db to set
	 */
	public void setDb(Db db) {
		this.db = db;
	}
	/**
	 * @return the friendtable
	 */
	public FriendshipController getFriendtable() {
		return friendtable;
	}
	/**
	 * @param friendtable the friendtable to set
	 */
	public void setFriendtable(FriendshipController friendtable) {
		this.friendtable = friendtable;
	}
	/**
	 * @return the datumtable
	 */
	public DatumController getDatumtable() {
		return datumtable;
	}
	/**
	 * @param datumtable the datumtable to set
	 */
	public void setDatumtable(DatumController datumtable) {
		this.datumtable = datumtable;
	}
	/**
	 * @return the forumcontroller
	 */
	public ForumController getForumcontroller() {
		return forumcontroller;
	}
	/**
	 * @param forumcontroller the forumcontroller to set
	 */
	public void setForumcontroller(ForumController forumcontroller) {
		this.forumcontroller = forumcontroller;
	}
	/*
	 * register a user
	 */
	public void register(Member m){
		db.register(m);
		if(m instanceof User){
			db.registerEndUser(m.getUsername());
		}
		else if(m instanceof Admin){
			Admin a = (Admin)m;
			db.registerAdmin(m.getUsername(),a.getEm_num());
		}
		else{
			Moderator mod = (Moderator)m;
			db.registerMod(mod);
		}
	}
	/*
	 * checks for unqiue username 
	 */
	public boolean exists_uname(String user_id){
		ResultSet rs = db.exists_uname(user_id);
		try{
			while(rs.next()){
				String str= rs.getString(1);
				if(str.equals(user_id)){
					return true;
				}
			}
		}
		catch(SQLException e){
			System.out.println(e);
			return false;
		}
		return false;
	}
	/*
	 * checks for unique email
	 */
	public boolean exists_email(String email1){
	
		ResultSet rs = db.exists_email(email1);
		try{
			while(rs.next()){
				String str= rs.getString(1);
				if(str.equals(email1)){
					return true;
				}
			}
		}
		catch(SQLException e){
			System.out.println(e);
			return false;
		}
		return false;
	}
	/*
	 * check for correct email fromat
	 */
	public Boolean incorrectFormatemail(String email){
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; //Taken from www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
		Pattern r = Pattern.compile(EMAIL_PATTERN);
		Matcher m;
		
		while(true){
			m = r.matcher(email);
			if(m.find()){
				return false;
			}
			else
				return true;
		}
	}
	/*
	 * checks for incorrect phone number
	 */
	public Boolean incorrectFormatphone(String phone){
		Pattern r = Pattern.compile("[1-9][0-9]{9}");
		Matcher m = r.matcher(phone);
		if(m.find()){
			return false;
		}
		else
			return true;
	}
	/*
	 * check for status of user
	 */
	public int authact(String uemail,String upass){
		ResultSet rsauth=null;
		try{
			rsauth=db.auth(uemail, upass);
			if(rsauth.next()){
				if(rsauth.getInt(1)==0){
					return 0; //deactivated
				}
				else{
					return 1; //activated
				}
			}
			else{
				return 2; //wrong credertials
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return 1;
	}
	/*
	 * function to login
	 */
	public Member login(String uemail,String upass){
		ResultSet rs=null,rsbase=null,rsfriend=null;
		int flag=0;
		try{
			rs=db.login(uemail,upass);
			while(rs.next()){
				flag=1;
				int utype=rs.getInt(16);
				ResultSet rsdatum; //= db.getDatum(rs.getString(1));
				//datumtable=getDatum(rsdatum);
				if(utype<=3){
					rsbase=db.getBaseDetails(rs.getString(1), "enduser");
					if(rsbase.next()){
					//int karma,int utype,java.sql.Date date
						rsfriend=db.getfriend(rs.getString(1));
						friendtable=getfriend(rsfriend);
						rsdatum = db.getDatum(rs.getString(1));
						datumtable=getDatum(rsdatum);
						return new User(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),new String[]{rs.getString(8),rs.getString(9),rs.getString(10)}
						,rs.getInt(17), rs.getString(12),rs.getString(11), rs.getString(13), rs.getString(14),rs.getString(15), 
						rsbase.getInt(2),rs.getInt(16),
						rsbase.getTimestamp(3));
					}
				}
				else if(utype==4){
					rsbase=db.getBaseDetails(rs.getString(1), "Administrator");
					while(rsbase.next())
					return new Admin(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(16),
							rs.getString(5), rs.getString(6), rs.getString(7), new String[]{rs.getString(8),rs.getString(9)
						,rs.getString(10)}, rs.getInt(17), rs.getString(12), rs.getString(11), rs.getString(13),
						rs.getString(14), rs.getString(15), rsbase.getString(2));
				}
				else{
					rsbase=db.getBaseDetails(rs.getString(1), "Moderator");
					ResultSet rsqual=db.getModQual(rs.getString(1));
					ArrayList<String> qual =new ArrayList<String>();
					while(rsqual.next()){
						qual.add(rsqual.getString(1));
					}
					while(rsbase.next())
					return new Moderator(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(16), 
							rs.getString(5), rs.getString(6), rs.getString(7),  new String[]{rs.getString(8),rs.getString(9)
						,rs.getString(10)}, rs.getInt(17),rs.getString(12), rs.getString(11), rs.getString(13), rs.getString(14), rs.getString(15), rsbase.getString(2), qual);
				}
			}
			if(flag==0){
				return null;
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return null;
	}	
	/*
	 * get the friend list
	 */
	FriendshipController getfriend(ResultSet rs){
		FriendshipController f=new FriendshipController();
		try{
			while(rs.next()){
				f.add(new Friendship(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4),rs.getDate(5),rs.getDate(6),rs.getDate(7)));
			}
		}
		catch(SQLException e){
			
		}
		return f;
	}
	/*
	 * add row to loginattempt
	 */
	public void loginattempt(String uname,String pass,String agent){
		db.loginattempt(uname,pass,agent);
	}
	/* update the profile 
	 * 
	 */
	public void update(Member cur){
		db.update(cur);
	}
	/*
	 * update the friend table for enduser
	 */
	public void updatefriend(){
		db.updatefriend(friendtable);
	}
	public Member getRandUser(String username){
		ResultSet rs = db.getRandUser(username);
		ResultSet rsbase=db.getBaseDetails(username, "enduser");
		try {
			if(rs.next()&&rsbase.next()){
				Member cur = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),new String[]{rs.getString(8),rs.getString(9),rs.getString(10)}, rs.getInt(17), rs.getString(12), rs.getString(11), rs.getString(13), rs.getString(14), rs.getString(15));
				((User)cur).setKarma(rsbase.getInt(2));
				return cur;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * sents a request
	 */
	public Boolean sentRequest(String requestedusername,String username){
		boolean te = db.checkenduser(requestedusername);
		System.out.print(te);
		if(te){
			int result = friendtable.sentRequest(requestedusername, username);
			if(result==1){
				return  true;
			}
			else if(result==2){
				return false;
			}
			else{
				return false;
			}
		}
		else{
			return te;
		}
	}
	/*
	 * reject requests
	 */
	public Boolean unfriend(String rejectusername,String username){
		if(friendtable.unfriend(rejectusername, username)>0){
			return  true;
		}
		else{
			return false;
		}
	}
	/*
	 * reject requests
	 */
	public Boolean rejectedRequest(String rejectusername,String username){
		if(friendtable.rejectedRequest(rejectusername, username)>0){
			return  true;
		}
		else{
			return false;
		}
	}
	/*
	 * accept a request
	 */
	public Boolean acceptRequest(String acceptusername,String username){
		if(friendtable.acceptRequest(acceptusername, username)>0){
			return  true;
		}
		else{
			return false;
		}
	}
	/*
	 * withdraw a request
	 */
	public Boolean withdrawRequest(String withdrawusername,String username){
		if(friendtable.withdrawRequest(withdrawusername, username)>0){
			return  true;
		}
		else{
			return false;
		}
	}
	/*
	 * show all users 
	 */

	public ArrayList<String> showalluser(String username){
		ResultSet rs=null;
		ArrayList<String> str=new ArrayList<String>();
		try{
			rs=db.showalluser(username);
			while(rs.next()){
				String uname = rs.getString(1);
				String name = rs.getString(2);
				uname.concat(" ");
				uname.concat(name);
				str.add(uname);
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return str;
	}
	/*
	 * user to which have sent a request
	 */
	
	public ArrayList<String> showusertoaccepted(String username){
		return friendtable.showusertoaccepted(username);
	}	
	
	/*
	 * user to which have sent a request
	 */
	public ArrayList<String> showrequesteduserlist(String username){
		return friendtable.showrequesteduserlist(username);
	}	
	/*
	 * user to which have sent a request
	 */
	public ArrayList<String> showfriendlist(String username){
		return friendtable.showfriendlist(username);
	}
	
	public Boolean addDatum(String username,int propertyid,String data){
		datumtable.add(new Datum(maxDatumId()+1, username, propertyid, data));
		return true;
	}
	
	public String showPropertyDescription(int id){
		String des=null;
		ResultSet rs= db.showPropertyDescription(id);
		try {
			if(rs.next()){
				des = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return des;
	}
	public int maxDatumId(){
		return db.maxDatumId();
	}
	
	public void updateDatum(){
		db.updateDatum(datumtable);
	}
	public DatumController getDatum(ResultSet rs){
		DatumController dc = new DatumController();
		try {
			while(rs.next()){
				dc.add(new Datum(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getTimestamp(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dc;
	}
	public String viewDatum(String uname,Boolean t){
		if(t){
			return datumtable.toString();
		}else{
			ResultSet rsdatum = db.getDatum(uname);
			DatumController dc =getDatum(rsdatum);
			return dc.toString();
		}
	}
	public String viewDatum(Member cur,String uname){
		ArrayList<String> friend = showfriendlist(cur.getUsername());
		for(String f :friend){
			if(f.equalsIgnoreCase(uname)){
				return viewDatum(uname, false);
			}
		}
		if(uname.equals(cur.getUsername())){
			return viewDatum(uname, true);
		}
		else{
			return "wrong input";
		}
	}
	
	public Boolean addforum(Member cur,String topic,String url,String summary){
		return forumcontroller.addforum(cur, topic,url,summary);
	}
	
	public Boolean delforum(Member cur,int forumid){
		return forumcontroller.delforum(cur,forumid);
	}
	public ArrayList<Forum> getAcForums(){
		return forumcontroller.getAcForums();
	}
	public Boolean addpost(Member cur,int forumid,String text,String photolocation,String linklocation,String videolocation){
		return forumcontroller.addpost(cur,forumid,text,photolocation,linklocation,videolocation);
	}
	public Boolean addcom(Member cur,String postuname,java.sql.Timestamp posttime,String text,String photolocation,String linklocation,String videolocation){
		return forumcontroller.addcom(cur,postuname,posttime,text,photolocation,linklocation,videolocation);
	}
	public Boolean addrate(Member cur,String postuname,java.sql.Timestamp posttime,int stars){
		return forumcontroller.addrate(new Rating(postuname, posttime, cur.getUsername(), stars));
	}
	
	public String viewforum(int forumid){
		StringBuilder str = new StringBuilder();
		Forum f;
		ArrayList<Post> postarr;// = new ArrayList<Post>();
		ArrayList<Comment> comarr;// = new ArrayList<Comment>();
		ArrayList<Rating> ratearr;
		float avgstar=0;
		int totalstar=0;
		
		f = forumcontroller.getForum(forumid);
		if(f!=null){
			str.append(f.toString()+"\n");
			postarr = forumcontroller.getpost(f.getForumID());
			avgstar=0;
			for(Post p:postarr){
				str.append("\n");
				str.append(p.toString());
				ratearr =forumcontroller.getrate(p.getUsername(),p.getTimeCreated()); 
				for(Rating r:ratearr){
					totalstar = totalstar +r.getStars();
				}
				if(ratearr.size()>0){
					avgstar = totalstar/ratearr.size();
				}
				str.append("avg star ratin is "+avgstar+"\n");
				avgstar=0;
				totalstar=0;
				comarr = forumcontroller.getcomment(p.getUsername(),p.getTimeCreated());
				for(Comment c:comarr){
					str.append(c.toString()+"\n");
				}
			}
		}
		else{
			str.append(" ");
		}
		return str.toString();
	}
	
}