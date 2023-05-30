package com.tiger.CosineSearch.service;

public class WebPage {
	
	private String url;
	public String[] strings;  
	public double[] averageVec;
	public double product;

	public WebPage(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	public boolean contains(String[] inputStrings) {
		for(String string : strings) {
			for(String s : inputStrings) {
				if(string.toLowerCase().equals(s.toLowerCase()))
					return true;
			}
		}
		return false;
	}
	
}
