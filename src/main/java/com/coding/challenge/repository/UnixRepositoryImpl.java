package com.coding.challenge.repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.coding.challenge.dto.Group;
import com.coding.challenge.dto.PwdUser;
import com.coding.challenge.utils.Constant;

@Component
public class UnixRepositoryImpl implements UnixRepository {

	@Override
	public List<PwdUser> getListPwdUser() {
		return getUsersFromFile(Constant.DEFAULT_PASSWD_FILE);
	}

	@Override
	public List<PwdUser> getListPwdUser(String filePath) {
		return getUsersFromFile(Paths.get(filePath));
	}

	@Override
	public List<Group> getListGroup() {
		return getGroupsFromFile(Constant.DEFAULT_GROUP_FILE);
	}

	@Override
	public List<Group> getListGroup(String filePath) {
		return getGroupsFromFile(Paths.get(filePath));
	}
	
	private List<PwdUser> getUsersFromFile(Path filePath) {
		try (Stream<String> stream = Files.lines(filePath)) {
			List<PwdUser> users = stream
					.map(PwdUser::new)
					.collect(Collectors.toList());
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private List<Group> getGroupsFromFile(Path filePath) {
		try (Stream<String> stream = Files.lines(filePath)) {
			List<Group> groups = stream
					.map(Group::new)
					.collect(Collectors.toList());
			return groups;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
