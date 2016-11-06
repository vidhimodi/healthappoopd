package model;

import java.sql.*;
/*
 * class that works a row of comment table
 */
public class Comment {
	String Post_Username;//` VARCHAR(20) NOT NULL,
	Timestamp Post_TimeCreated;//` DATETIME NOT NULL,
	String Commenter_Username;//` VARCHAR(20) NOT NULL,
	Timestamp CommentTime;//` TIMESTAMP NOT NULL,
	String CommentText;//` TEXT NULL,
	String PhotoLocation;//` VARCHAR(45) NULL,
	String LinkLocation;//` VARCHAR(45) NULL,
	String VideoLocation;//` VARCHAR(45) NULL,
	/**
	 * @param post_Username
	 * @param post_TimeCreated
	 * @param commenter_Username
	 * @param commentTime
	 * @param commentText
	 * @param photoLocation
	 * @param linkLocation
	 * @param videoLocation
	 */
	public Comment(String post_Username, Timestamp post_TimeCreated,
			String commenter_Username, Timestamp commentTime, String commentText,
			String photoLocation, String linkLocation, String videoLocation) {
		Post_Username = post_Username;
		Post_TimeCreated = post_TimeCreated;
		Commenter_Username = commenter_Username;
		CommentTime = commentTime;
		CommentText = commentText;
		PhotoLocation = photoLocation;
		LinkLocation = linkLocation;
		VideoLocation = videoLocation;
	}
	/**
	 * @param post_Username
	 * @param post_TimeCreated
	 * @param commenter_Username
	 * @param commentText
	 * @param photoLocation
	 * @param linkLocation
	 * @param videoLocation
	 */
	public Comment(String post_Username, Timestamp post_TimeCreated,
			String commenter_Username, String commentText,
			String photoLocation, String linkLocation, String videoLocation) {
		Post_Username = post_Username;
		Post_TimeCreated = post_TimeCreated;
		Commenter_Username = commenter_Username;
		CommentTime = new Timestamp((new java.util.Date()).getTime());
		CommentText = commentText;
		PhotoLocation = photoLocation;
		LinkLocation = linkLocation;
		VideoLocation = videoLocation;
	}
	/**
	 * @return the post_Username
	 */
	public String getPost_Username() {
		return Post_Username;
	}
	/**
	 * @param post_Username the post_Username to set
	 */
	public void setPost_Username(String post_Username) {
		Post_Username = post_Username;
	}
	/**
	 * @return the post_TimeCreated
	 */
	public Timestamp getPost_TimeCreated() {
		return Post_TimeCreated;
	}
	/**
	 * @param post_TimeCreated the post_TimeCreated to set
	 */
	public void setPost_TimeCreated(Timestamp post_TimeCreated) {
		Post_TimeCreated = post_TimeCreated;
	}
	/**
	 * @return the commenter_Username
	 */
	public String getCommenter_Username() {
		return Commenter_Username;
	}
	/**
	 * @param commenter_Username the commenter_Username to set
	 */
	public void setCommenter_Username(String commenter_Username) {
		Commenter_Username = commenter_Username;
	}
	/**
	 * @return the commentTime
	 */
	public Timestamp getCommentTime() {
		return CommentTime;
	}
	/**
	 * @param commentTime the commentTime to set
	 */
	public void setCommentTime(Timestamp commentTime) {
		CommentTime = commentTime;
	}
	/**
	 * @return the commentText
	 */
	public String getCommentText() {
		return CommentText;
	}
	/**
	 * @param commentText the commentText to set
	 */
	public void setCommentText(String commentText) {
		CommentText = commentText;
	}
	/**
	 * @return the photoLocation
	 */
	public String getPhotoLocation() {
		return PhotoLocation;
	}
	/**
	 * @param photoLocation the photoLocation to set
	 */
	public void setPhotoLocation(String photoLocation) {
		PhotoLocation = photoLocation;
	}
	/**
	 * @return the linkLocation
	 */
	public String getLinkLocation() {
		return LinkLocation;
	}
	/**
	 * @param linkLocation the linkLocation to set
	 */
	public void setLinkLocation(String linkLocation) {
		LinkLocation = linkLocation;
	}
	/**
	 * @return the videoLocation
	 */
	public String getVideoLocation() {
		return VideoLocation;
	}
	/**
	 * @param videoLocation the videoLocation to set
	 */
	public void setVideoLocation(String videoLocation) {
		VideoLocation = videoLocation;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comment [Post_Username=" + Post_Username
				+ ", Post_TimeCreated=" + Post_TimeCreated
				+ ", Commenter_Username=" + Commenter_Username
				+ ", CommentTime=" + CommentTime + ", CommentText="
				+ CommentText + ", PhotoLocation=" + PhotoLocation
				+ ", LinkLocation=" + LinkLocation + ", VideoLocation="
				+ VideoLocation + "]";
	}
	
}
