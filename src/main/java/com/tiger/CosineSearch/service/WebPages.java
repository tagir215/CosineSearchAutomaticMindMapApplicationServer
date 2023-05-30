package com.tiger.CosineSearch.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.tiger.CosineSearch.entity.WebPageEntity;

public class WebPages {
	public static List<WebPage>listOfPages;

	public WebPages() {
		System.out.println("reading webpages.txt...");
		InputStream stream = WebPages.class.getClassLoader().getSystemResourceAsStream("webpages.txt");
		listOfPages = FileManager.pagesToList(stream);
		System.out.println("done reading");
	}
	public WebPages(List<WebPageEntity>entities) {
		System.out.println("calculating averages...");
		listOfPages = new ArrayList<>();
		for(WebPageEntity e : entities) {
			String[] words = StringHandler.filterString(e.getUrl());
			WebPage page = new WebPage(e.getUrl());
			page.strings = words;
			page.averageVec = CosineSimilarity.average(words,null);
			listOfPages.add(page);
		}
		System.out.println("list of pages done "+listOfPages.size());
	}
	
	
}
