package model;


/*
 *class correspondes to a friendship table of db
 *have all the getter and setters 
 */

public class Friendship {
	protected String requesteruser;
	protected String requesteduser;
	protected java.sql.Date whenrequested;
	protected java.sql.Date whenwithdrawn;
	protected java.sql.Date whenrejected;
	protected java.sql.Date whenconfirmed;
	protected java.sql.Date whenunfriended;
	public Friendship(String requesteruser, String requesteduser){
		this.requesteruser = requesteruser;
		this.requesteduser = requesteduser;
		this.whenrequested = null;
		this.whenwithdrawn = null;
		this.whenrejected = null;
		this.whenconfirmed = null;
		this.whenunfriended = null;
	}
	public Friendship(String requesteruser, String requesteduser,
			java.sql.Date whenrequested, java.sql.Date whenwithdrawn, java.sql.Date whenrejected,
			java.sql.Date whenconfirmed, java.sql.Date whenunfriended) {
		super();
		this.requesteruser = requesteruser;
		this.requesteduser = requesteduser;
		this.whenrequested = whenrequested;
		this.whenwithdrawn = whenwithdrawn;
		this.whenrejected = whenrejected;
		this.whenconfirmed = whenconfirmed;
		this.whenunfriended = whenunfriended;
	}
	public String getRequesteruser() {
		return requesteruser;
	}
	public void setRequesteruser(String requesteruser) {
		this.requesteruser = requesteruser;
	}
	public String getRequesteduser() {
		return requesteduser;
	}
	public void setRequesteduser(String requesteduser) {
		this.requesteduser = requesteduser;
	}
	public java.sql.Date getWhenrequested() {
		return whenrequested;
	}
	public void setWhenrequested(java.sql.Date whenrequested) {
		this.whenrequested = whenrequested;
	}
	public java.sql.Date getWhenwithdrawn() {
		return whenwithdrawn;
	}
	public void setWhenwithdrawn(java.sql.Date whenwithdrawn) {
		this.whenwithdrawn = whenwithdrawn;
	}
	public java.sql.Date getWhenrejected() {
		return whenrejected;
	}
	public void setWhenrejected(java.sql.Date whenrejected) {
		this.whenrejected = whenrejected;
	}
	public java.sql.Date getWhenconfirmed() {
		return whenconfirmed;
	}
	public void setWhenconfirmed(java.sql.Date whenconfirmed) {
		this.whenconfirmed = whenconfirmed;
	}
	public java.sql.Date getWhenunfriended() {
		return whenunfriended;
	}
	public void setWhenunfriended(java.sql.Date whenunfriended) {
		this.whenunfriended = whenunfriended;
	}	
}
