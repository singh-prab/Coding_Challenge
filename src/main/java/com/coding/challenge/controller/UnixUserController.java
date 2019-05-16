package com.coding.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.challenge.dto.Group;
import com.coding.challenge.dto.PwdUser;
import com.coding.challenge.service.UnixService;

@RestController
@RequestMapping(value = "api/v1/users")
public class UnixUserController {

	@Autowired
	private UnixService unixService;

	@GetMapping()
	public ResponseEntity<?> getListPwdUser() {
		return new ResponseEntity<>(unixService.getListPwdUser(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getListPwdUserById(@PathVariable("id") Integer id) {
		PwdUser pwdUserBbyId = unixService.getPwdUserBbyId(id);
		if (pwdUserBbyId != null) {
			return new ResponseEntity<>(pwdUserBbyId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/{id}/groups")
	public ResponseEntity<?> getListGroupOfUser(@PathVariable("id") Integer id) {
		PwdUser pwdUserBbyId = unixService.getPwdUserBbyId(id);
		if (pwdUserBbyId != null) {
			Group pwdGroupBbyId = unixService.getPwdGroupBbyId(pwdUserBbyId.getGid());
			return new ResponseEntity<>(pwdGroupBbyId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/query")
	public ResponseEntity<?> filterUser(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "uid", required = false) Integer uid,
			@RequestParam(name = "gid", required = false) Integer gid,
			@RequestParam(name = "comment", required = false) String comment,
			@RequestParam(name = "home", required = false) String home,
			@RequestParam(name = "shell", required = false) String shell) {
		return new ResponseEntity<>(unixService.filterPwdUser(name, uid, gid, comment, home, shell), HttpStatus.OK);
	}

}
