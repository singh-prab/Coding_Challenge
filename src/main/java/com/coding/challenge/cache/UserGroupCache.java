package com.coding.challenge.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coding.challenge.dto.Group;
import com.coding.challenge.dto.PwdUser;

public class UserGroupCache {

	private Map<Integer, PwdUser> userMap;
	private Map<Integer, Group> groupMap;

	private static UserGroupCache sc = new UserGroupCache();

	public static UserGroupCache getInstance() {
		return sc;
	}

	// Constructor
	private UserGroupCache() {
		userMap = new HashMap<>();
		groupMap = new HashMap<>();
	}

	public void addUser(PwdUser pwdUser) {
		userMap.put(pwdUser.getUid(), pwdUser);
	}

	public void addUser(List<PwdUser> pwdUsers) {
		for (PwdUser pwdUser : pwdUsers) {
			userMap.put(pwdUser.getUid(), pwdUser);
		}
	}
	
	public PwdUser getPwdUserById(Integer uid) {
		return userMap.get(uid);
	}

	public void addGroup(Group group) {
		groupMap.put(group.getGid(), group);
	}

	public void addGroup(List<Group> groups) {
		for (Group group : groups) {
			groupMap.put(group.getGid(), group);
		}
	}
	
	public Group getPwdGroupById(Integer gid) {
		return groupMap.get(gid);
	}

	public Collection<PwdUser> getUsers() {
		return userMap.values();
	}

	public Collection<Group> getGroups() {
		return groupMap.values();
	}

}
