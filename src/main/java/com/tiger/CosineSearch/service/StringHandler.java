package com.tiger.CosineSearch.service;

import java.util.ArrayList;
import java.util.List;

public class StringHandler {
	public static List<String> splitString(String string) {
		
		List<String>innerWords = new ArrayList<>();

		return innerWords;
	}
	public static String[] filterString(String string) {
		string = string.replaceAll("[^a-zA-Z0-9]", "/");
		String[] strings = string.split("/",-1);
		return strings;
	}
	
	
}
