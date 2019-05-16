package com.coding.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding.challenge.dto.Group;
import com.coding.challenge.service.UnixService;

@RestController
@RequestMapping(value = "api/v1/groups")
public class UnixGroupController {

	@Autowired
	private UnixService unixService;

	@GetMapping
	public ResponseEntity<?> getListGroup() {
		return new ResponseEntity<>(unixService.getListGroup(), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPwdGroupBbyId(@PathVariable("id") Integer id) {
		Group pwdGroupBbyId = unixService.getPwdGroupBbyId(id);
		if (pwdGroupBbyId != null) {
			return new ResponseEntity<>(pwdGroupBbyId, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/query")
	public ResponseEntity<?> filterGroup(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "gid", required = false) Integer gid,
			@RequestParam(name = "member", required = false) List<String> members) {
		return new ResponseEntity<>(unixService.filterGroup(name, gid, members), HttpStatus.OK);
	}
}
