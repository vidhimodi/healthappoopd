package controller;

import java.util.*;

import model.Friendship;

/*
 * class which have the set of row of friendship table as friendship class arraylist
 * it have all functions for 
 * accept 
 * reject 
 * delete 
 * sent 
 * withdraw
 * show all user
 * show friend
 * show sent request
 * show recieve request
 */

public class FriendshipController {
	private ArrayList<Friendship> friendship = new ArrayList<Friendship>();
	
	/*
	 * add the friendship row to arraylist
	 */
	public void add(Friendship f){
		friendship.add(f);
	}
	/*
	 * add a friendship object to arraylist
	 */
	public int sentRequest(String requestedusername,String username){
		
		int ans=1;
		for(Friendship f : this.friendship){
			if(f.getRequesteruser().equalsIgnoreCase(username)&&f.getRequesteduser().equals(requestedusername)){
				return 2;
			}
			if(f.getRequesteruser().equalsIgnoreCase(requestedusername)&&f.getRequesteduser().equals(username)){
				return 3;
			}
		}
		friendship.add(new Friendship(username, requestedusername, new java.sql.Date((new Date()).getTime()), null,null,null,null));
		return ans;
	}
	/*
	 * set the reject time so as get to reject the request
	 */
	public int rejectedRequest(String rejectusername,String username){
		
		for(Friendship f : this.friendship){
			if(f.getRequesteruser().equals(rejectusername)&&f.getWhenwithdrawn()==null){
				f.setWhenrejected(new java.sql.Date((new Date()).getTime()));
				return 1;
			}
		}
		return 0;
	}
	/*
	 * function to accept a friend request
	 * it add the time when was request accepted
	 */
	
	public int acceptRequest(String acceptusername,String username){
		for(Friendship f : this.friendship){
			if(f.getRequesteruser().equals(acceptusername)&&f.getRequesteduser().equals(username)&&f.getWhenwithdrawn()==null){
				f.setWhenconfirmed(new java.sql.Date((new Date()).getTime()));
				return 1;
			}
		}
		return 0;
	}
	/*
	 *  function to withdraw a friend request
	 * it add the time when was request withdrawn
	 */
	public int withdrawRequest(String withdrawusername,String username){
		for(Friendship f : this.friendship){
			if(f.getRequesteruser().equals(username)&&f.getRequesteduser().equals(withdrawusername)&&f.getWhenconfirmed()==null&&f.getWhenrejected()==null){
				f.setWhenwithdrawn(new java.sql.Date((new Date()).getTime()));
				return 1;
			}
		}
		return 0;
	}
	
	public int unfriend(String unfriendusername,String username){
		for(Friendship f : this.friendship){
			if((f.getRequesteruser().equals(username)&&f.getRequesteduser().equals(unfriendusername)||
					f.getRequesteruser().equals(unfriendusername)&&f.getRequesteduser().equals(username))
					&&f.getWhenconfirmed()!=null&&f.getWhenrejected()==null){
				f.setWhenunfriended(new java.sql.Date((new Date()).getTime()));
				return 1;
			}
		}
		return 0;
	}
	
	
	
	public ArrayList<String> showusertoaccepted(String username){
		ArrayList<String> friend=new ArrayList<String>();
		for(Friendship f : this.friendship){
			if(f.getRequesteduser().equals(username)&&(f.getWhenconfirmed()==null)&&(f.getWhenrejected()==null)&&(f.getWhenwithdrawn()==null)){
				friend.add(f.getRequesteruser());
			}
		}
		return friend;
	}	
	
	/*
	 *  function to show user to which request was sent
	 * it gets a list of usernames
	 */
	public ArrayList<String> showrequesteduserlist(String username){
		ArrayList<String> friend=new ArrayList<String>();
		for(Friendship f : this.friendship){
			if(f.getRequesteruser().equals(username)&&(f.getWhenconfirmed()==null)&&(f.getWhenrejected()==null)
					&&(f.getWhenwithdrawn()==null)){
				friend.add(f.getRequesteduser());
			}
		}
		return friend;
	}	
	/*
	 * function to show friedn user
	 * it gets a list of usernames
	 */
	public ArrayList<String> showfriendlist(String username){
		
		ArrayList<String> friend=new ArrayList<String>();
		for(Friendship f : this.friendship){
			if(f.getRequesteruser().equals(username)&&(f.getWhenconfirmed()!=null)&&(f.getWhenunfriended()==null)){
				friend.add(f.getRequesteduser());
			}
			else if(f.getRequesteduser().equals(username)&&(f.getWhenconfirmed()!=null)&&(f.getWhenunfriended()==null)){
				friend.add(f.getRequesteruser());
			}
		}
		return friend;
	}
	/**
	 * @return the friendship
	 */
	public ArrayList<Friendship> getFriendship() {
		return friendship;
	}
	/**
	 * @param friendship the friendship to set
	 */
	public void setFriendship(ArrayList<Friendship> friendship) {
		this.friendship = friendship;
	}
}
