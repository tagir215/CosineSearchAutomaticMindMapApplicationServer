package com.tiger.CosineSearch.service;

import java.util.List;

public class ResultFormatter {
	public static String fromat(List<WebPage>result) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i=0; i<400; i++) {
			stringBuilder.append(result.get(i).getUrl() +" ");
		}
		return stringBuilder.toString();
	}
}
