package edu.almabridge.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.almabridge.dao.FriendDAO;
import edu.almabridge.model.Friend;


@RestController
public class FriendController {
	@Autowired
	private Friend friend;
	@Autowired
	private FriendDAO friendDAO;
	//Tested+1
	@RequestMapping(value = "/addFriend/{friendId}/", method = RequestMethod.GET)
	public ResponseEntity<Friend> addFriend(@PathVariable("friendId") String friendId, HttpSession hs) {

		String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		if (friendDAO.isRequestAlreadySent(loggedInUserId, friendId) == true) {
			friend.setId(0);
			friend.setUserId(loggedInUserId);
			friend.setFriendId(friendId);
			friend.setStatus('N');
			friend.setIsOnline('N');
			friend.setErrorCode("200");
			friend.setErrorMsg("friend request sent successfully");
			friendDAO.saveFriend(friend);
		} else {
			friend.setErrorCode("404");
			friend.setErrorMsg("friend request already sent!!");
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	//Tested+1
	@RequestMapping(value = "/approveFriend/{friendId}/", method = RequestMethod.GET)
	public ResponseEntity<Friend> approveFriend(@PathVariable("friendId") String friendId, HttpSession hs) {
		String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		if (friendDAO.isAlreadyAccepted(loggedInUserId, friendId) == true) {
			friend.setErrorCode("200");
			friend.setErrorMsg("Friend request has been accepted!!");
			friend.setUserId(loggedInUserId);
			friend.setFriendId(friendId);
			friend.setStatus('Y');
			friendDAO.saveFriend(friend);
			Friend friend = friendDAO.getFriendToChangeStatus(friendId, loggedInUserId);
			friend.setStatus('Y');
			friendDAO.updateFriend(friend);
		} else {
			friend.setErrorCode("404");
			friend.setErrorMsg("Friend request has been already accepted!!");
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	//Tested+1
	@RequestMapping(value = "/rejectFriend/{friendId}/", method = RequestMethod.GET)
	public boolean rejectFriend(@PathVariable("friendId") String friendId, HttpSession hs) {
		String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		try {
			friendDAO.deleteFriend(loggedInUserId, friendId);
			
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}
	//Tested+1
	@RequestMapping(value = "/unFriend/{friendId}/", method = RequestMethod.GET)
	public boolean deleteFriend(@PathVariable("friendId") String friendId, HttpSession hs) {
		String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		try {
			friendDAO.removeFriend(loggedInUserId, friendId);
			friendDAO.removeFriend(friendId, loggedInUserId);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	//Tested+1
	@RequestMapping(value = "/pendingFriendRequests", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> pendingFriendRequests(HttpSession hs) {
		String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		List<Friend> pendingFriendList = friendDAO.pendingFriendRequests(loggedInUserId);
		if (pendingFriendList.isEmpty()) {
			friend.setErrorCode("404");
			friend.setErrorMsg("No new Friend requests");
			pendingFriendList.add(friend);
		}
		return new ResponseEntity<List<Friend>>(pendingFriendList, HttpStatus.OK);

	}
	//Tested+1
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> friends(HttpSession hs) {
		String loggedInUserId = (String) hs.getAttribute("loggedInUserId");
		List<Friend> friendList = friendDAO.myFriendsList(loggedInUserId);
		if (friendList.isEmpty()) {
			friend.setId(0);
			friend.setErrorCode("404");
			friend.setErrorMsg("No Friends");
			friendList.add(friend);
		}
		return new ResponseEntity<List<Friend>>(friendList, HttpStatus.OK);

	}

}
