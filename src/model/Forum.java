package model;

import java.sql.Timestamp;
import java.util.ArrayList;
/*
 * class represent a row of forum data
 */
public class Forum {
	int ForumID; //INT NOT NULL,
	String Topic;//` VARCHAR(45) NULL,
	String URL;//` VARCHAR(255) NOT NULL,
	String Summary;//` TEXT NULL,
	java.sql.Timestamp WhenCreated;//` DATETIME NOT NULL,
	java.sql.Timestamp WhenClosed;//` DATETIME NULL,
	String CreatedByModerator_Username;//` VARCHAR(20) NOT NULL,
	String DeletedByModerator_Username;//` VARCHAR(20) NULL,	
	
	ArrayList<Post> post = new ArrayList<Post>();
	/**
	 * 
	 */
	public Forum() {
	}
	/**
	 * @param forumID
	 * @param topic
	 * @param uRL
	 * @param summary
	 * @param whenCreated
	 * @param whenClosed
	 * @param createdByModerator_Username
	 * @param deletedByModerator_Username
	 */
	public Forum(int forumID, String topic, String uRL, String summary,
			Timestamp whenCreated, Timestamp whenClosed,
			String createdByModerator_Username,
			String deletedByModerator_Username) {
		ForumID = forumID;
		Topic = topic;
		URL = uRL;
		Summary = summary;
		WhenCreated = whenCreated;
		WhenClosed = whenClosed;
		CreatedByModerator_Username = createdByModerator_Username;
		DeletedByModerator_Username = deletedByModerator_Username;
	}
	/**
	 * @param forumID
	 * @param uRL
	 * @param whenCreated
	 * @param createdByModerator_Username
	 */
	public Forum(int forumID, String uRL, Timestamp whenCreated,
			String createdByModerator_Username) {
		ForumID = forumID;
		Topic = null;
		URL = uRL;
		Summary =null;
		WhenCreated = whenCreated;
		WhenClosed = null;
		CreatedByModerator_Username = createdByModerator_Username;
		DeletedByModerator_Username = null;
	}
	/**
	 * @param forumID
	 * @param topic
	 * @param uRL
	 * @param summary
	 * @param createdByModerator_Username
	 */
	public Forum(int forumID, String topic, String uRL, String summary, String createdByModerator_Username) {
		ForumID = forumID;
		Topic = topic;
		URL = uRL;
		Summary = summary;
		java.util.Date d=new java.util.Date();
		WhenCreated = new Timestamp(d.getTime());
		WhenClosed = null;
		CreatedByModerator_Username = createdByModerator_Username;
		DeletedByModerator_Username = null;
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
	 * @return the topic
	 */
	public String getTopic() {
		return Topic;
	}
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		Topic = topic;
	}
	/**
	 * @return the uRL
	 */
	public String getURL() {
		return URL;
	}
	/**
	 * @param uRL the uRL to set
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return Summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		Summary = summary;
	}
	/**
	 * @return the whenCreated
	 */
	public java.sql.Timestamp getWhenCreated() {
		return WhenCreated;
	}
	/**
	 * @param whenCreated the whenCreated to set
	 */
	public void setWhenCreated(java.sql.Timestamp whenCreated) {
		WhenCreated = whenCreated;
	}
	/**
	 * @return the whenClosed
	 */
	public java.sql.Timestamp getWhenClosed() {
		return WhenClosed;
	}
	/**
	 * @param whenClosed the whenClosed to set
	 */
	public void setWhenClosed(java.sql.Timestamp whenClosed) {
		WhenClosed = whenClosed;
	}
	/**
	 * @return the createdByModerator_Username
	 */
	public String getCreatedByModerator_Username() {
		return CreatedByModerator_Username;
	}
	/**
	 * @param createdByModerator_Username the createdByModerator_Username to set
	 */
	public void setCreatedByModerator_Username(String createdByModerator_Username) {
		CreatedByModerator_Username = createdByModerator_Username;
	}
	/**
	 * @return the deletedByModerator_Username
	 */
	public String getDeletedByModerator_Username() {
		return DeletedByModerator_Username;
	}
	/**
	 * @param deletedByModerator_Username the deletedByModerator_Username to set
	 */
	public void setDeletedByModerator_Username(String deletedByModerator_Username) {
		DeletedByModerator_Username = deletedByModerator_Username;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Forum [ForumID=" + ForumID + ", Topic=" + Topic + ", URL="
				+ URL + ", Summary=" + Summary + ", WhenCreated=" + WhenCreated
				+ ", WhenClosed=" + WhenClosed
				+ ", CreatedByModerator_Username="
				+ CreatedByModerator_Username
				+ ", DeletedByModerator_Username="
				+ DeletedByModerator_Username + "]";
	}

}
