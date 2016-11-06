package model;

import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import controller.DatumController;
import controller.FriendshipController;

/*
 * class responsible for all db connections
 */

public class Db {
   // JDBC driver name and database URL
   public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   public static final String DB_URL = "jdbc:mysql://localhost:3306/smarthealthdb?autoReconnect=true&useSSL=false";

   //  Database credentials
   public static final String USER = "root";
   public static final String PASS = "root";
   public static Connection conn=null;
   static DataSource dataSource;
   
   public Db(){
	   String jndiname = "jdbc/db";
		
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);
	    } catch (NamingException e) {
	        // Handle error that it's not configured in JNDI.
	        throw new IllegalStateException(jndiname + " is missing in JNDI!", e);
	    }
		try {
			conn = (Connection) dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Connection conn ;
		
	   //conn=getConnection();
   }
   /*
    * function to create connection with database
    */
	public static Connection getConnection(){
		/*try{
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
		}*/
		
		return conn;
		
	}
	/*
	 * function to register a member in the db 
	 * add enduser or moderator or administrator by making a call to db model call
	 * of corresponding class
	 */
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
	/*
	 * register enduser
	 */
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
	/*
	 * register admin
	 */
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
	/*
	 * register moderator in sysrtem
	 */
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
	/*
	 * add qualification of moderator in moderator qual table
	 */
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
	/*
	 * get qualification id of corresponding qual
	 */
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
	/*
	 * add qualification in the qualification table 
	 * with appropiate id and description
	 */
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
	/*
	 * checks whether a username exits or not
	 */
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
	/*
	 * checks whether a email exits or not
	 */
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
	/*
	 * function to login a user 
	 * and gets it all details in objects
	 */
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
	/*
	 * to check the status of the user
	 */
	public ResultSet auth(String uemail,String upass){
		ResultSet rs=null;
		try{
			String sql="Select Status from user where Email1 = ? and Password = ?";
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
	/*
	 * gets the qualification to a moderator
	 */
	
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
	/*
	 * add a row in login attempt table
	 */
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
	public boolean checkenduser(String uname){
		String sql="select * from user where username=? and UserTypeID<4;";
		ResultSet rs=null;
		try{
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, uname);
			rs = prepstmt.executeQuery();
			while(rs.next()){
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	/*
	 * funtion to update profile of user
	 */
	public void update(Member cur){
		try{
			String query = "{CALL spUpdateData(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)}";
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

			if(cur instanceof Moderator){
				Moderator m=(Moderator)cur; 
				cs.setString(15,m.getEm_number());
				cs.setString(16, "Moderator");
				cs.setInt(18,0);
			}
			else if(cur instanceof Admin){
				cs.setString(15,((Admin)cur).getEm_num());
				cs.setString(16, "Administrator");
				cs.setInt(18,0);
			}
			else{
				cs.setString(15,null);
				cs.setString(16, "EndUser");
				cs.setInt(18,((User)cur).getKarma());
			}
			cs.setInt(17, cur.getStatus());
			cs.executeQuery();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * function of show all users
	 */
	public ResultSet showalluser(String username){
		ResultSet rs=null;
		try{
			String sql="select user.username, concat(FirstName, ' ', LastName) from User join usertype on "
					+ "user.usertypeid = usertype.usertypeid and usertype.description='EndUser' join "
					+ "EndUser on user.username = enduser.username and user.username not in "
					+ "(select Requested_Username from friendship where Requester_Username=? "
					+ "union distinct "
					+ "select username from user where username=?);";
			PreparedStatement prepstmt = conn.prepareStatement(sql);
			prepstmt.setString(1, username);
			prepstmt.setString(2, username);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}

	
	public ResultSet getfriend(String uname){
		ResultSet rs=null;
		try{
			String sql="select * from friendship where Requester_Username=? or Requested_Username=?";
			PreparedStatement prepstmt=conn.prepareStatement(sql);
			prepstmt.setString(1, uname);
			prepstmt.setString(2, uname);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	/*
	 * function to update the friendship table
	 */
	public void updatefriend(FriendshipController f){
		ResultSet rs=null;
		for(Friendship fri :f.getFriendship()){
			try{
				String sql="Select count(*) from friendship where Requested_Username=? and Requester_Username=?";
				PreparedStatement prepstmt = conn.prepareStatement(sql);
				prepstmt.setString(1,fri.getRequesteduser());
				prepstmt.setString(2, fri.getRequesteruser());
				rs=prepstmt.executeQuery();
				if(rs.next()){
					if(rs.getInt(1)>0){
						String sql1="update Friendship set WhenRequested=?, WhenWithdrawn=?, WhenRejected=?, WhenConfirmed=?, WhenUnfriended=?  where Requester_Username = ? and Requested_Username = ?";
						PreparedStatement p = conn.prepareStatement(sql1);
						p.setString(6, fri.getRequesteruser());
						p.setString(7, fri.getRequesteduser());
						p.setDate(1, fri.getWhenrequested());
						p.setDate(2, fri.getWhenwithdrawn());
						p.setDate(3, fri.getWhenrejected());
						p.setDate(4, fri.getWhenconfirmed());
						p.setDate(5, fri.getWhenunfriended());
						p.executeUpdate();
					}
					else{
						String sql1="insert into friendship values(?,?,?,?,?,?,?)";
						PreparedStatement p = conn.prepareStatement(sql1);
						p.setString(1, fri.getRequesteruser());
						p.setString(2, fri.getRequesteduser());
						p.setDate(3, fri.getWhenrequested());
						p.setDate(4, fri.getWhenwithdrawn());
						p.setDate(5, fri.getWhenrejected());
						p.setDate(6, fri.getWhenconfirmed());
						p.setDate(7, fri.getWhenunfriended());
						p.executeUpdate();
					}
				}
			}
			catch(SQLException e){
				System.out.print(e);
			}
		}
	}
	
	public ResultSet showPropertyDescription(int id){
		ResultSet rs=null;
		Statement s;
		try{
			s = conn.createStatement();
			rs= s.executeQuery("Select Description from Property where PropertyID = "+id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int maxDatumId(){
		int max=0;
		ResultSet rs=null;
		Statement s;
		try {
			s = conn.createStatement();
			rs= s.executeQuery("Select max(DatumID) from Datum");
			if(rs.next()){
				max=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}
	
	public void updateDatum(DatumController datumtable){
		ResultSet rs=null;
		Statement s=null;
		for(Datum d: datumtable.datum){
			String sql = "select * from Datum where DatumID = "+d.getDatumID();
			try {
				s=conn.createStatement();
				rs=s.executeQuery(sql);
				if(!rs.next()){
					String sql1="insert into Datum values(?,?,?,?,NOW())";
					PreparedStatement p = conn.prepareStatement(sql1);
					p.setInt(1, d.getDatumID());
					p.setString(2, d.getUsername());
					p.setInt(3, d.getPropertyID());
					p.setString(4, d.getValue());
					p.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet getDatum(String uname){
		ResultSet rs=null;
		
		try{
			String sql="select * from Datum where Username=?";
			PreparedStatement prepstmt=conn.prepareStatement(sql);
			prepstmt.setString(1, uname);
			rs=prepstmt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public int getmaxforumid(){
		int max=0;
		ResultSet rs=null;
		Statement s;
		try {
			s = conn.createStatement();
			rs= s.executeQuery("Select max(ForumID) from Forum");
			if(rs.next()){
				max=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return max;
	}
	
	public Boolean addforum(Forum f){
		try {
			//insert into forum values(1,"topic","url","summary",Now(),null,"ashu",null);
			String sql1="insert into Forum values(?,?,?,?,NOW(),null,?,null)";
			PreparedStatement p = conn.prepareStatement(sql1);
			p.setInt(1, f.getForumID());
			p.setString(2, f.getTopic());
			p.setString(3, f.getURL());
			p.setString(4, f.getSummary());
			p.setString(5,f.getCreatedByModerator_Username());
			p.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Boolean delforum(Member m , int forumid){
		try {
			//update forum set DeletedByModerator_Username="ashu8" ,WhenClosed=NOW() where ForumID=1;
			String sql1="update forum set DeletedByModerator_Username=? ,WhenClosed=NOW() where ForumID=?;";
			PreparedStatement p = conn.prepareStatement(sql1);
			p.setString(1,m.getUsername());
			p.setInt(2, forumid);
			p.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ResultSet getAcForums(){
		ResultSet rs=null;
		Statement s;
		try{
			s = conn.createStatement();
			rs= s.executeQuery("Select * from Forum where DeletedByModerator_Username is null");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Boolean addpost(Member cur,int forumid,String text,String photolocation,String linklocation,String videolocation){
		try {
			//insert into post values("test1",NOW(),1,"text","photo","link","video");
			String sql1="insert into post values(?,NOW(),?,?,?,?,?);";
			PreparedStatement p = conn.prepareStatement(sql1);
			p.setString(1, cur.getUsername());
			p.setInt(2, forumid);
			p.setString(3, text);
			p.setString(4, photolocation);
			p.setString(5,linklocation);
			p.setString(6, videolocation);
			p.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Boolean addcom(Member cur,String postuname,java.sql.Timestamp posttime,String text,String photolocation,String linklocation,String videolocation){
		try {
			//insert into comment values(?,NOW(),?,?,?,?,?);
			String sql1="insert into comment values(?,?,?,NOW(),?,?,?,?)";
			PreparedStatement p = conn.prepareStatement(sql1);
			p.setString(1, postuname);
			p.setTimestamp(2, posttime);
			p.setString(3, cur.getUsername());
			p.setString(4, text);
			p.setString(5, photolocation);
			p.setString(6,linklocation);
			p.setString(7, videolocation);
			p.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Boolean addrate(Rating r){
		try {
			//insert into rating values(post_username,post_timecreated,rater_username,stars)
			String sql1="insert into rating values(?,?,?,?)";
			PreparedStatement p = conn.prepareStatement(sql1);
			p.setString(1, r.getPost_Username());
			p.setTimestamp(2, r.getPost_TimeCreated());
			p.setString(3, r.getRater_Username());
			p.setInt(4,r.getStars() );
			p.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ResultSet getForum(int forumid){
		String sql = "Select * from Forum where ForumID = ? and DeletedByModerator_Username is null";
		ResultSet rs=null;
		try{
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, forumid);
			rs = p.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public ResultSet getpost(int forumid){
		String sql = "select * from post where ForumID = ?";
		ResultSet rs=null;
		try{
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, forumid);
			rs = p.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getcomment(String postuname,java.sql.Timestamp datetimestamp){
		String sql = "select * from Comment where Post_Username =? and Post_TimeCreated = ?";
		ResultSet rs=null;
		try{
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, postuname);
			p.setTimestamp(2, datetimestamp);//;(2,datetimestamp.getTime());
			rs = p.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getrate(String postuname,java.sql.Timestamp datetimestamp){
		String sql = "select * from rating where Post_Username =? and Post_TimeCreated = ?";
		ResultSet rs=null;
		try{
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, postuname);
			p.setTimestamp(2,datetimestamp);
			rs = p.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet getRandUser(String username){
		ResultSet rs=null;
		try{
			String sql="Select * from user where username = ?";
			PreparedStatement prepstmt =conn.prepareStatement(sql);
			prepstmt.setString(1,username);
			rs=prepstmt.executeQuery();
		}
		catch(SQLException e){
			System.out.println(e);
		}
		return rs;
	}

}