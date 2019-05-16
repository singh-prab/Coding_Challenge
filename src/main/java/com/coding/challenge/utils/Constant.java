package com.coding.challenge.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constant {
	public static final Path DEFAULT_PASSWD_FILE = Paths.get("/etc/passwd");
	public static final Path DEFAULT_GROUP_FILE = Paths.get("/etc/group");
	public static final String PASSWD_FILE = "passwd-file";
	public static final String GROUP_FILE = "group-file";
}
