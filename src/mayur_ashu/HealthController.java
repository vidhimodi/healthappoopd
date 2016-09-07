package mayur_ashu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HealthController {
	
	Db db = new Db();
	
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
	boolean exists_uname(String user_id){
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
	boolean exists_email(String email1){
	
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
	public Boolean incorrectFormatphone(String phone){
		Pattern r = Pattern.compile("[1-9][0-9]{9}");
		Matcher m = r.matcher(phone);
		if(m.find()){
			return false;
		}
		else
			return true;
	}
	public Member login(String uemail,String upass){
		ResultSet rs=null,rsbase=null;
		int flag=0;
		try{
			rs=db.login(uemail,upass);
			while(rs.next()){
				flag=1;
				int utype=rs.getInt(16);
				if(utype<=3){
					rsbase=db.getBaseDetails(rs.getString(1), "enduser");
					while(rsbase.next()){
					//int karma,int utype,java.sql.Date date
						return new User(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7),new String[]{rs.getString(8),rs.getString(9),rs.getString(10)}
						,rs.getInt(17), rs.getString(12),rs.getString(11), rs.getString(13), rs.getString(14),rs.getString(15), 
						rsbase.getInt(2),rs.getInt(16),
						rsbase.getDate(3));
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
	public void loginattempt(String uname,String pass,String agent){
		db.loginattempt(uname,pass,agent);
	}
	public void update(Member cur){
		db.update(cur);
	}
	public Boolean sentRequest(String requestedusername,String username){
		if(db.sentRequest(requestedusername, username)>0){
			return  true;
		}
		else{
			return false;
		}
	}
	public Boolean rejectedRequest(String rejectusername,String username){
		if(db.rejectedRequest(rejectusername, username)>0){
			return  true;
		}
		else{
			return false;
		}
	}
	public Boolean acceptRequest(String acceptusername,String username){
		if(db.acceptRequest(acceptusername, username)>0){
			return  true;
		}
		else{
			return false;
		}
	}
	public Boolean withdrawRequest(String withdrawusername,String username){
		if(db.withdrawRequest(withdrawusername, username)>0){
			return  true;
		}
		else{
			return false;
		}
	}
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
	public ArrayList<String> showusertoaccepted(String username){
		ResultSet rs=null;
		ArrayList<String> str=new ArrayList<String>();
		try{
			rs=db.showusertoaccepted(username);
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
	public ArrayList<String> showrequesteduserlist(String username){
		ResultSet rs=null;
		ArrayList<String> str=new ArrayList<String>();
		try{
			rs=db.showrequesteduserlist(username);
			while(rs.next()){
				String uname = rs.getString(1);
				String status = rs.getString(2);
				uname.concat(" ");
				uname.concat(status);
				str.add(uname);
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return str;
	}	
	public ArrayList<String> showfriendlist(String username){
		ResultSet rs=null;
		ArrayList<String> str=new ArrayList<String>();
		try{
			rs=db.showfriendlist(username);
			while(rs.next()){
				String uname1= rs.getString(1);
				String uname2 = rs.getString(2);
				if(uname1.equalsIgnoreCase(username)){
					str.add(uname2);
				}
				else{
					str.add(uname1);
				}
			}
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return str;
	}
}