package edu.almabridge.dao;

import java.util.List;

import edu.almabridge.model.Friend;

public interface FriendDAO {
	public List<Friend> myFriendsList(String userId);
	public List<Friend> pendingFriendRequests(String userId);
	public Friend getFriend(String userId,String friendId);
	public Friend getFriendToChangeStatus(String userId, String friendId);
	public boolean saveFriend(Friend friend);
	public boolean updateFriend(Friend friend);
	public void deleteFriend(String userId,String friendId);
	public void removeFriend(String userId, String friendId);
	
	public boolean isRequestAlreadySent(String userId,String friendId);
	public boolean isAlreadyAccepted(String userId,String friendId);
	
	
	public void setOnline(String userId);
	public void setOffline(String userId);
	

}
