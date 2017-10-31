package model;

import java.sql.Timestamp;
import java.util.ArrayList;
/*
 * class that works as a row of post table
 */
public class Post {
	String Username;//` VARCHAR(20) NOT NULL,
	Timestamp TimeCreated;//` DATETIME NOT NULL,
	int ForumID;//` INT NOT NULL,
	String TextEntry;//` TEXT NULL,
	String PhotoLocation;//` VARCHAR(255) NULL,
	String LinkLocation;//` VARCHAR(255) NULL,
	String VideoLocation;//` VARCHAR(255) NULL,
	
	ArrayList<Comment> com = new ArrayList<Comment>();
	/**
	 * @param username
	 * @param timeCreated
	 * @param forumID
	 * @param textEntry
	 * @param photoLocation
	 * @param linkLocation
	 * @param videoLocation
	 */
	public Post(String username, Timestamp timeCreated, int forumID,
			String textEntry, String photoLocation, String linkLocation,
			String videoLocation) {
		Username = username;
		TimeCreated = timeCreated;
		ForumID = forumID;
		TextEntry = textEntry;
		PhotoLocation = photoLocation;
		LinkLocation = linkLocation;
		VideoLocation = videoLocation;
	}
	/**
	 * @param username
	 * @param forumID
	 * @param textEntry
	 * @param photoLocation
	 * @param linkLocation
	 * @param videoLocation
	 */
	public Post(String username, int forumID, String textEntry,
			String photoLocation, String linkLocation, String videoLocation) {
		Username = username;
		ForumID = forumID;
		TextEntry = textEntry;
		PhotoLocation = photoLocation;
		LinkLocation = linkLocation;
		VideoLocation = videoLocation;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}
	/**
	 * @return the timeCreated
	 */
	public Timestamp getTimeCreated() {
		return TimeCreated;
	}
	
	/**
	 * @param timeCreated the timeCreated to set
	 */
	public void setTimeCreated(Timestamp timeCreated) {
		TimeCreated = timeCreated;
	}
	/**
	 * @return the forumID
	 */
	public int getForumID() {
		return ForumID;
	}
	/**
	 * @param forumID the forumID to set
	 */
	public void setForumID(int forumID) {
		ForumID = forumID;
	}
	/**
	 * @return the textEntry
	 */
	public String getTextEntry() {
		return TextEntry;
	}
	/**
	 * @param textEntry the textEntry to set
	 */
	public void setTextEntry(String textEntry) {
		TextEntry = textEntry;
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
		return "Post [Username=" + Username + ", TimeCreated=" + TimeCreated +" in millisecond : "+TimeCreated.getTime()
				+ ", ForumID=" + ForumID + ", TextEntry=" + TextEntry
				+ ", PhotoLocation=" + PhotoLocation + ", LinkLocation="
				+ LinkLocation + ", VideoLocation=" + VideoLocation + "]";
	}
	
}
