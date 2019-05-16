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
import com.coding.challenge.service.UnixService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UnixGroupControllerTest {

	private static final Group GROUP = new Group("tinhcao:x:1000:");

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UnixService unixService;

	@Test
	public void testGetListGroup() throws Exception {
		when(unixService.getListGroup()).thenReturn(Collections.singletonList(GROUP));
		//
		mockMvc.perform(get("/api/v1/groups")).andExpect(status().isOk())
				.andExpect(content().string(containsString("name")));
	}

	@Test
	public void testGetPwdGroupBbyId() throws Exception {
		when(unixService.getPwdGroupBbyId(1000)).thenReturn(GROUP);
		mockMvc.perform(get("/api/v1/groups/1000")).andExpect(status().isOk())
				.andExpect(content().string(containsString("name")));
	}

	@Test
	public void testFilterGroup() throws Exception {
		// mock call
		when(unixService.filterGroup("tinhcao", null, null)).thenReturn(Collections.singletonList(GROUP));
		//
		mockMvc.perform(get("/api/v1/groups/query?name=tinhcao")).andExpect(status().isOk())
				.andExpect(content().string(containsString("tinhcao")));
	}

}
