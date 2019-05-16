package com.coding.challenge.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.coding.challenge.repository.UnixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.coding.challenge.cache.UserGroupCache;
import com.coding.challenge.dto.Group;
import com.coding.challenge.dto.PwdUser;
import com.coding.challenge.utils.Constant;

@Component
public class UnixServiceImpl implements UnixService {

	@Autowired
	private UnixRepository unixRepository;

	@Autowired
	private Environment environment;

	@Override
	public Collection<PwdUser> getListPwdUser() {
		return UserGroupCache.getInstance().getUsers();
	}

	@Override
	public Collection<Group> getListGroup() {
		return UserGroupCache.getInstance().getGroups();
	}

	@Override
	public PwdUser getPwdUserBbyId(Integer id) {
		return UserGroupCache.getInstance().getPwdUserById(id);
	}

	@Override
	public Group getPwdGroupBbyId(Integer id) {
		return UserGroupCache.getInstance().getPwdGroupById(id);
	}

	@Override
	@EventListener(ApplicationReadyEvent.class)
	public void initCache() {
		if (environment.getProperty(Constant.PASSWD_FILE) != null) {
			List<PwdUser> listPwdUser = unixRepository.getListPwdUser(environment.getProperty(Constant.PASSWD_FILE));
			UserGroupCache.getInstance().addUser(listPwdUser);
		} else {
			List<PwdUser> listPwdUser = unixRepository.getListPwdUser();
			UserGroupCache.getInstance().addUser(listPwdUser);
		}
		if (environment.getProperty(Constant.GROUP_FILE) != null) {
			List<Group> listGroup = unixRepository.getListGroup(environment.getProperty(Constant.GROUP_FILE));
			UserGroupCache.getInstance().addGroup(listGroup);
		} else {
			List<Group> listGroup = unixRepository.getListGroup();
			UserGroupCache.getInstance().addGroup(listGroup);
		}

	}

	@Override
	public Collection<PwdUser> filterPwdUser(String name, Integer uid, Integer gid, String comment, String home,
			String shell) {
		Collection<PwdUser> listPwdUser = this.getListPwdUser();
		if ((name == null || name.isEmpty()) 
				&& (uid == null)
				&& (gid == null)
				&& (comment == null || comment.isEmpty()) 
				&& (home == null || home.isEmpty())
				&& (shell == null || shell.isEmpty())) {
			return listPwdUser;
		}
		Set<PwdUser> result = new HashSet<>();
		if (name != null) {
			result.addAll(listPwdUser.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toList()));
		}

		if (uid != null) {
			result.addAll(listPwdUser.stream().filter(p -> p.getUid().equals(uid)).collect(Collectors.toList()));
		}

		if (gid != null) {
			result.addAll(listPwdUser.stream().filter(p -> p.getGid().equals(gid)).collect(Collectors.toList()));
		}

		if (comment != null) {
			result.addAll(listPwdUser.stream().filter(p -> p.getComment().equals(comment)).collect(Collectors.toList()));
		}

		if (home != null) {
			result.addAll(listPwdUser.stream().filter(p -> p.getHome().equals(home)).collect(Collectors.toList()));
		}

		if (shell != null) {
			result.addAll(listPwdUser.stream().filter(p -> p.getShell().equals(shell)).collect(Collectors.toList()));
		}
		return result;
	}

	@Override
	public Collection<Group> filterGroup(String name, Integer gid, List<String> members) {
		Collection<Group> listGroup = this.getListGroup();
		if ((name == null || name.isEmpty()) 
				&& (gid == null)
				&& (members == null || members.isEmpty())) {
			return listGroup;
		}
		
		Set<Group> result = new HashSet<>();
		if (name != null) {
			result.addAll(listGroup.stream().filter(g -> g.getName().equals(name)).collect(Collectors.toList()));
		}
		
		if (gid != null) {
			result.addAll(listGroup.stream().filter(g -> g.getGid().equals(gid)).collect(Collectors.toList()));
		}
		
		if (members != null && members.size() > 0) {
			for (String member : members) {
				result.addAll(listGroup.stream().filter(g -> g.getMembers().contains(member)).collect(Collectors.toList()));
			}
		}
		return result;
	}

}
