package mayur_ashu;

public class Friendship {
	protected Member requesteruser;
	protected Member requesteduser;
	protected long whenrequested;
	protected long whenwithdrawn;
	protected long whenrejected;
	protected long whenconfirmed;
	protected long whenunfriended;
	public Friendship(Member requesteruser, Member requesteduser){
		this.requesteruser = requesteruser;
		this.requesteduser = requesteduser;
		this.whenrequested = 0;
		this.whenwithdrawn = 0;
		this.whenrejected = 0;
		this.whenconfirmed = 0;
		this.whenunfriended = 0;
	}
	public Friendship(Member requesteruser, Member requesteduser,
			long whenrequested, long whenwithdrawn, long whenrejected,
			long whenconfirmed, long whenunfriended) {
		super();
		this.requesteruser = requesteruser;
		this.requesteduser = requesteduser;
		this.whenrequested = whenrequested;
		this.whenwithdrawn = whenwithdrawn;
		this.whenrejected = whenrejected;
		this.whenconfirmed = whenconfirmed;
		this.whenunfriended = whenunfriended;
	}
	public Member getRequesteruser() {
		return requesteruser;
	}
	public void setRequesteruser(Member requesteruser) {
		this.requesteruser = requesteruser;
	}
	public Member getRequesteduser() {
		return requesteduser;
	}
	public void setRequesteduser(Member requesteduser) {
		this.requesteduser = requesteduser;
	}
	public long getWhenrequested() {
		return whenrequested;
	}
	public void setWhenrequested(long whenrequested) {
		this.whenrequested = whenrequested;
	}
	public long getWhenwithdrawn() {
		return whenwithdrawn;
	}
	public void setWhenwithdrawn(long whenwithdrawn) {
		this.whenwithdrawn = whenwithdrawn;
	}
	public long getWhenrejected() {
		return whenrejected;
	}
	public void setWhenrejected(long whenrejected) {
		this.whenrejected = whenrejected;
	}
	public long getWhenconfirmed() {
		return whenconfirmed;
	}
	public void setWhenconfirmed(long whenconfirmed) {
		this.whenconfirmed = whenconfirmed;
	}
	public long getWhenunfriended() {
		return whenunfriended;
	}
	public void setWhenunfriended(long whenunfriended) {
		this.whenunfriended = whenunfriended;
	}
}
