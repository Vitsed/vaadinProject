package com.vitsed.utils;

public enum StringUtils {

	MENU_STUDENT("STUDENTS"), 
	MENU_UNIVERSITY("UNIVERSITY"), 
	MENU_ADD_STUDENT("Add student"), 
	MENU_REMOVE_STUDENT("Remove student"),
	MENU_OPERATIONS("Operations"),
	;

	private final String string;

	private StringUtils(String string) {
		this.string = string;
	}

	public String getString() {
		return this.string;
	}

}
