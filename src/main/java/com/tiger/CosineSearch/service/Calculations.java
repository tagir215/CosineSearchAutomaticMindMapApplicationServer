package com.tiger.CosineSearch.service;

import java.util.List;

public class Calculations {
	
	public static List<WebPage> getAssociatedURLs(String[] strings,EmbeddingsService service){
		
		double[] vec1 = CosineSimilarity.average(strings,service);
		for(int i=0; i<WebPages.listOfPages.size(); i++) {
			WebPage w = WebPages.listOfPages.get(i);
			if(w.contains(strings)) {
				w.product = 1;
			}else {
				w.product = CosineSimilarity.cosineSimilarity(vec1, w.averageVec);		
				if(Double.isNaN(w.product))
					w.product = 0;
			}
		}
		
	    return MySort.mergeSortWebPages(WebPages.listOfPages);
	}
	
	
}
