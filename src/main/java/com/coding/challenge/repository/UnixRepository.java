package com.coding.challenge.repository;

import java.util.List;

import com.coding.challenge.dto.Group;
import com.coding.challenge.dto.PwdUser;

public interface UnixRepository {
	List<PwdUser> getListPwdUser();

	List<PwdUser> getListPwdUser(String file);

	List<Group> getListGroup();

	List<Group> getListGroup(String file);
}
