package com.coding.challenge.service;

import java.util.Collection;
import java.util.List;

import com.coding.challenge.dto.Group;
import com.coding.challenge.dto.PwdUser;

public interface UnixService {
	Collection<PwdUser> getListPwdUser();

	PwdUser getPwdUserBbyId(Integer id);

	Collection<PwdUser> filterPwdUser(String name, Integer uid, Integer gid, String comment, String home, String shell);

	Collection<Group> getListGroup();

	Group getPwdGroupBbyId(Integer id);

	void initCache();

	Collection<Group> filterGroup(String name, Integer gid, List<String> members);

}
