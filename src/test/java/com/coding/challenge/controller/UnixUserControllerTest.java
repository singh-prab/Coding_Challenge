package com.coding.challenge.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.coding.challenge.dto.Group;
import com.coding.challenge.dto.PwdUser;
import com.coding.challenge.service.UnixService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UnixUserControllerTest {
	private static final PwdUser PWD_USER = new PwdUser("tinhcao:x:1000:1000:tinhcao,,,:/home/tinhcao:/bin/bash");
	private static final Group GROUP = new Group("tinhcao:x:1000:");

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UnixService unixService;

	@Test
	public void testGetListPwdUser() throws Exception {
		// mock call
		when(unixService.getListPwdUser()).thenReturn(Collections.singletonList(PWD_USER));
		//
		mockMvc.perform(get("/api/v1/users")).andExpect(status().isOk())
				.andExpect(content().string(containsString("name")));
	}

	@Test
	public void testGetListPwdUserById() throws Exception {
		// mock call
		when(unixService.getPwdUserBbyId(1)).thenReturn(PWD_USER);
		//
		mockMvc.perform(get("/api/v1/users/1")).andExpect(status().isOk())
				.andExpect(content().string(containsString("tinhcao")));
	}

	@Test
	public void testGetListGroupOfUser() throws Exception {
		// mock call
		when(unixService.getPwdUserBbyId(1)).thenReturn(PWD_USER);
		when(unixService.getPwdGroupBbyId(1000)).thenReturn(GROUP);
		//
		mockMvc.perform(get("/api/v1/users/1/groups")).andExpect(status().isOk())
				.andExpect(content().string(containsString("tinhcao")));
	}

	@Test
	public void testFilterUser() throws Exception {
		// mock call
		when(unixService.filterPwdUser("tinhcao", null, null, null, null, null))
				.thenReturn(Collections.singletonList(PWD_USER));
		//
		mockMvc.perform(get("/api/v1/users/query?name=tinhcao")).andExpect(status().isOk())
				.andExpect(content().string(containsString("tinhcao")));
	}

}
