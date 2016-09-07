package mayur_ashu;

import java.sql.*;

public class Db {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/smarthealthdb?autoReconnect=true&useSSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   public static Connection conn=null;
   
   Db(){
	   conn=getConnection();
   }
	public static Connection getConnection(){
		try{
			Class.forName(JDBC_DRIVER);
			Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
			return conn;
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
			return null;
		}
		catch(SQLException e){
			System.out.println(e);
			return null;
		}
		
	}
	public void register(Member m){
		PreparedStatement prepstmt = null;
		try{
			String sql="Insert into user Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, m.getUsername());
			prepstmt.setString(2, m.getPassword());
			prepstmt.setString(3, m.getPrimary_email());
			prepstmt.setString(4, m.getSecondary_email());
			prepstmt.setString(5, m.getFirstname());
			prepstmt.setString(6, m.getLastname());
			prepstmt.setString(7, m.getAboutme());
			prepstmt.setString(8, m.getUrl()[0]);
			prepstmt.setString(9, m.getUrl()[1]);
			prepstmt.setString(10, m.getUrl()[2]);
			prepstmt.setString(11, m.getStreetnumber());
			prepstmt.setString(12, m.getStreetname());
			prepstmt.setString(13, m.getMajormunicipality());
			prepstmt.setString(14, m.getGoverningdistrict());
			prepstmt.setString(15, m.getPostalarea());
			prepstmt.setInt(16, m.getUsertype());
			prepstmt.setInt(17, m.getStatus());
			prepstmt.executeUpdate();
		}
		catch (SQLException e){
			System.out.print(e);
		}
	}
	public void registerEndUser(String uname){
		PreparedStatement prepstmt = null;
		try{
			String sql="Insert into enduser Values (?,1,CURDATE())";
			prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, uname);
			prepstmt.executeUpdate();
		}
		catch (SQLException e){
			System.out.print(e);
		}
	}
	public void registerAdmin(String uname,String num){
		PreparedStatement prepstmt = null;
		try{
			String sql="Insert into Administrator Values (?,?)";
			prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, uname);
			prepstmt.setString(2,num);
			prepstmt.executeUpdate();
		}
		catch (SQLException e){
			System.out.print(e);
		}
	}
	public void registerMod(Moderator m){
		PreparedStatement prepstmt = null;
		try{
			String sql="Insert into Moderator Values (?,?)";
			prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, m.getUsername());
			prepstmt.setString(2,m.getEm_number());
			prepstmt.executeUpdate();
			for(String qual :m.getQualification()){
				//registerQual(qual);
				int qid=getQualid(qual);
				addqualtomod(qid,m.getUsername());
			}
		}
		catch (SQLException e){
			System.out.print(e);
		}
	}
	public void addqualtomod(int qid,String uname){
		String sql="insert into ModeratorQualification values (?,?,CURDATE())";
		try{
			PreparedStatement prestmt = conn.prepareStatement(sql);
			prestmt.setInt(1, qid);
			prestmt.setString(2,uname);
			prestmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.print(e);			
		}
	}
	public int getQualid(String qual){
		String sql="{CALL uspgetqualid(?)}";
		CallableStatement cs ;
		try{
			cs = conn.prepareCall(sql);
			cs.setString(1, qual);
			ResultSet rs= cs.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
			else{
				Statement s = conn.createStatement();
				ResultSet r= s.executeQuery("Select max(QualificationID) from qualification");
				int max=0;
				while(r.next()){
					max=r.getInt(1);
				}
				addqual(qual,max+1);
				return max+1;
			}
		}
		catch(SQLException e){
			System.out.println(e);
			return 0;
		}
	}	
	public void addqual(String qual,int qualid){
		try{
			PreparedStatement p =conn.prepareStatement("insert into qualification values (?,?)");
			p.setInt(1, qualid);
			p.setString(2,qual);
			p.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
		}
	}
	public ResultSet exists_uname(String uname){
		ResultSet rs=null;
		try{
			String sql="select Username from user where Username=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, uname);
			rs=p.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet exists_email(String email1){
		
		ResultSet rs = null;
		try{
			String sql="select email1 from user where email1=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1,email1);
			rs=p.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet login(String uemail,String upass){
		ResultSet rs=null;
		try{
			String sql="Select * from user where Email1 = ? and Password = ?";
			PreparedStatement prepstmt =conn.prepareStatement(sql);
			prepstmt.setString(1,uemail);
			prepstmt.setString(2, upass);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}
	public ResultSet getBaseDetails(String uname,String table_name){
		ResultSet rs=null;
		try{
			String sql="Select * from $tableName where username = ?";
			String query =sql.replace("$tableName",table_name);
			PreparedStatement prepstmt = conn.prepareStatement(query);
			prepstmt.setString(1, uname);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}
	public ResultSet getModQual(String uname){
		ResultSet rs= null;
		try{
			String sql="Select description from qualification as q join moderatorqualification as mq where "
					+ "q.QualificationID=mq.QualificationID and mq.Username=?";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, uname);
			rs = prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}
	public void loginattempt(String uname,String pass,String agent){	
		try{
			PreparedStatement prepstmt = conn.prepareStatement("insert into loginattempt(username,password,useragentstring) values(?,?,?)");
			prepstmt.setString(1, uname);
			prepstmt.setString(2, pass);
			prepstmt.setString(3, agent);
			prepstmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
		}
	}
	public void update(Member cur){
		try{
			String query = "{CALL spUpdateData(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = conn.prepareCall(query);
			cs.setString(1, cur.getPassword());
			cs.setString(2, cur.getUsername());
			cs.setString(3, cur.getSecondary_email());
			cs.setString(4, cur.getFirstname());
			cs.setString(5, cur.getLastname());
			cs.setString(6, cur.getAboutme());
			cs.setString(7, cur.getUrl()[0]);
			cs.setString(8, cur.getUrl()[1]);
			cs.setString(9, cur.getUrl()[2]);
			cs.setString(10, cur.getStreetnumber());
			cs.setString(11, cur.getStreetname());
			cs.setString(12, cur.getMajormunicipality());
			cs.setString(13, cur.getGoverningdistrict());
			cs.setString(14, cur.getPostalarea());
			Moderator m=(Moderator)cur; 
			cs.setString(15,m.getEm_number() );
			cs.setInt(16, cur.getUsertype());
			cs.setInt(17, cur.getStatus());
			cs.executeQuery();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public int sentRequest(String requestedusername,String username){
		try{
			String sql="insert into friendship(Requester_Username,Requested_Username,WhenRequested) values(?,?,CURDATE())";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			prepstmt.setString(2, requestedusername);
			return prepstmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.print(e);
			return 0;
		}
	}
	public int rejectedRequest(String rejectusername,String username){
		try{
			String sql="update Friendship set WhenRejected=CURTIME() where Requester_Username = ? and Requested_Username = ?;";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(2, username);
			prepstmt.setString(1, rejectusername);
			return prepstmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.print(e);
			return 0;
		}
	}
	public int acceptRequest(String acceptusername,String username){
		try{
			String sql="update Friendship set WhenConfirmed=CURTIME() where Requester_Username = ? and Requested_Username = ?;";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(2, username);
			prepstmt.setString(1, acceptusername);
			return prepstmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.print(e);
			return 0;
		}
	}
	public int withdrawRequest(String withdrawusername,String username){
		try{
			String sql="update Friendship set WhenConfirmed=CURTIME() where Requester_Username = ? and Requested_Username = ?;";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			prepstmt.setString(2, withdrawusername);
			return prepstmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.print(e);
			return 0;
		}
	}
	public ResultSet showalluser(String username){
		ResultSet rs=null;
		try{
			String sql="select Username, concat(FirstName, ' ', LastName) from User join usertype on "
					+ "user.usertypeid = usertype.usertypeid and usertype.description='EndUser' join "
					+ "EndUser on user.username = enduser.username and user.username not in "
					+ "(select Requested_Username from friendship where Requester_Username=? "
					+ "union distinct "
					+ "select username from user where username=from_user);";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}
	public ResultSet showusertoaccepted(String username){
		ResultSet rs=null;
		try{
			String sql="select Requester_Username, concat(u.first_name, ' ', u.last_name) from friendship f join user u on f.Requester_Username = u.username where f.Requested_Username = ?;";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}	
	public ResultSet showrequesteduserlist(String username){
		ResultSet rs=null;
		try{
			String sql="select Requested_Username,case when WhenRejected is not null then 'Rejected' "
					+ "when WhenUnfriended is not null then 'Unfriended' when WhenConfirmed is not null then 'Accepted' "
					+ "when WhenConfirmed is null then 'Not Responded' else NULL end as status from friendship "
					+ "where Requester_Username = ?;";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}	
	public ResultSet showfriendlist(String username){
		ResultSet rs=null;
		try{
			String sql="select `Requester_Username`,`Requested_Username` from friendship where WhenConfirmed is not null and WhenUnfriended is null and (Requester_Username= ? or Requested_Username =?);";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1,username);
			prepstmt.setString(2, username);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}
}