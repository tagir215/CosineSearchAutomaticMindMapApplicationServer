package com.tiger.CosineSearch.service;

import java.util.ArrayList;
import java.util.List;

public class MySort {
	
	public static List<WebPage> mergeSortWebPages(List<WebPage> list) {
		return mergeSort(list);
	}
	private static List<WebPage> mergeSort(List<WebPage> list) {
	    if (list.size() < 2) {
	        return list;
	    }
	    
	    int mid = list.size() / 2;
	    List<WebPage> left = list.subList(0, mid);
	    List<WebPage> right = list.subList(mid, list.size());
	    
	    ;
	    
	    return merge(mergeSort(left), mergeSort(right));
	    
	    
	}

	private static List<WebPage> merge(List<WebPage> left, List<WebPage> right) {
	    int i = 0, j = 0, k = 0;
	    List<WebPage>merged = new ArrayList<>();
	    while (i < left.size() && j < right.size()) {
	        if (left.get(i).product >= right.get(j).product) {
	            merged.add(k++, left.get(i++));
	        } else {
	            merged.add(k++, right.get(j++));
	        }
	    }
	    
	    while (i < left.size()) {
	        merged.add(k++, left.get(i++));
	    }
	    
	    while (j < right.size()) {
	        merged.add(k++, right.get(j++));
	    }
	    return merged;
	    
	}

	

}
