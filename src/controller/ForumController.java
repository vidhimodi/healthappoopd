package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;

/*
 * this is the controller class for manipulation
 * of Forum
 * It add forum on restricted access
 * and display add comment and post 
 * and populate from db and update in db
 * and send data for viewing to the view class
 */

public class ForumController {
	Forum f;
	Db db =new Db();
	/*
	 * to add forum
	 */
	public Boolean addforum(Member cur,String topic,String url,String summary){
		return db.addforum(new Forum(db.getmaxforumid()+1,topic, url,summary , cur.getUsername()));
	}
	/*
	 * to remove forum
	 */
	public Boolean delforum(Member cur,int forumid){
		return db.delforum(cur, forumid);
	}
	/*
	 * to show active forum
	 */
	public ArrayList<Forum> getAcForums(){
		ResultSet rs = db.getAcForums();
		ArrayList<Forum> forumarr = new ArrayList<Forum>();
		try {
			while(rs.next()){
				forumarr.add(new Forum(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6), rs.getString(7),rs.getString(8)));;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return forumarr;
	}
	/*
	 * to add post
	 */
	public Boolean addpost(Member cur,int forumid,String text,String photolocation,String linklocation,String videolocation){
		return db.addpost(cur,forumid,text,photolocation,linklocation,videolocation);
	}
	/*
	 * to add comment
	 */
	public Boolean addcom(Member cur,String postuname,java.sql.Timestamp posttime,String text,String photolocation,String linklocation,String videolocation){
		return db.addcom(cur,postuname,posttime,text,photolocation,linklocation,videolocation);
	}
	/*
	 * to add rating
	 */
	public Boolean addrate(Rating r){
		return db.addrate(r);
	}
	/* to get forum and its details
	 */
	public Forum getForum(int forumid){
		ResultSet rs =db.getForum(forumid);
		try {
			if(rs.next()){
				return new Forum(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6), rs.getString(7), rs.getString(8));
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * to get post of a forum
	 */
	public ArrayList<Post> getpost(int forumid){
		ArrayList<Post> postarr = new ArrayList<Post>();
		ResultSet rs = db.getpost(forumid);
		try {
			while(rs.next()){
				postarr.add(new Post(rs.getString(1), rs.getTimestamp(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postarr;
	}
	
	/*
	 * to get comment of a post
	 */
	public ArrayList<Comment> getcomment(String postuname,java.sql.Timestamp datetimestamp){
		ArrayList<Comment> comarr = new ArrayList<Comment>();
		ResultSet rs = db.getcomment(postuname, datetimestamp);
		try {
			while(rs.next()){
				comarr.add(new Comment(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comarr;
	}
	/*
	 * to get rating of a post
	 */
	public ArrayList<Rating> getrate(String postuname,java.sql.Timestamp datetimestamp){
		ArrayList<Rating> ratearr = new ArrayList<Rating>();
		ResultSet rs = db.getrate(postuname, datetimestamp);
		try {
			while(rs.next()){
				ratearr.add(new Rating(postuname, datetimestamp, rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ratearr;
	}	
}
