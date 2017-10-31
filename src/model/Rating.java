package model;

import java.sql.Timestamp;
/*
 * work as row of Rating table
 */
public class Rating {
	String Post_Username;//` VARCHAR(20) NOT NULL,
	Timestamp Post_TimeCreated;//` DATETIME NOT NULL,
	String Rater_Username;//` VARCHAR(20) NOT NULL,
	int Stars;//` INT NOT NULL,
	/**
	 * @param post_Username
	 * @param post_TimeCreated
	 * @param rater_Username
	 * @param stars
	 */
	public Rating(String post_Username, Timestamp post_TimeCreated,
			String rater_Username, int stars) {
		Post_Username = post_Username;
		Post_TimeCreated = post_TimeCreated;
		Rater_Username = rater_Username;
		Stars = stars;
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
	 * @return the rater_Username
	 */
	public String getRater_Username() {
		return Rater_Username;
	}
	/**
	 * @param rater_Username the rater_Username to set
	 */
	public void setRater_Username(String rater_Username) {
		Rater_Username = rater_Username;
	}
	/**
	 * @return the stars
	 */
	public int getStars() {
		return Stars;
	}
	/**
	 * @param stars the stars to set
	 */
	public void setStars(int stars) {
		Stars = stars;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Rating [Post_Username=" + Post_Username + ", Post_TimeCreated="
				+ Post_TimeCreated + ", Rater_Username=" + Rater_Username
				+ ", Stars=" + Stars + "]";
	}
}
