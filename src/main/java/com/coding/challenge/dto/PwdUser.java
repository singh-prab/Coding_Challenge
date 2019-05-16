package com.coding.challenge.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PwdUser {
	public String name;
	public Integer uid;
	public Integer gid;
	public String comment;
	public String home;
	public String shell;

	public PwdUser(String pwdline) {
		String[] values = pwdline.split(":");
		if (values.length != 7) {
			throw new IllegalArgumentException("Line must have 7 fields");
		}
		this.name = values[0];
		this.uid = Integer.parseInt(values[2]);
		this.gid = Integer.parseInt(values[3]);
		this.comment = values[4];
		this.home = values[5];
		this.shell = values[6];
	}

}
