package com.coding.challenge.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Group {
	private String name;
	public Integer gid;
	Set<String> members = new HashSet<String>();

	public Group(String pwdline) {
		String[] values = pwdline.split(":");
		this.name = values[0];
		this.gid = Integer.parseInt(values[2]);
		if (values.length > 3) {
			String mbs = values[3];
			if (mbs.length() > 0) {
				String[] mbArr = mbs.split(Pattern.quote(","));
				for (String s : mbArr) {
					members.add(s);
				}
			}
		}
	}
}
