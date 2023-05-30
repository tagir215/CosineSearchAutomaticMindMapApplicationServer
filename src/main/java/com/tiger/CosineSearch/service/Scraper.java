package com.tiger.CosineSearch.service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Scraper {

	public static void scrapeURLSs() throws IOException{
		String baseURL = "https://developer.android.com/";
		List<String>urls = new ArrayList<>();
		urls.add(baseURL);
		Set<String>visitedURLs = new HashSet<>();
		int page = 0;
		
		File file = File.createTempFile("scraperfile", ".txt");
		try( BufferedWriter writer = new BufferedWriter(new FileWriter(file)) ){

		
			while(!urls.isEmpty()) {
				String url = urls.remove(0);
				Document doc = Jsoup.connect(url).get();
				Elements links = doc.select("a[href]");
		        for (Element link : links) {
		            String linkURL = link.attr("abs:href");
		            if (linkURL.startsWith(baseURL) && !visitedURLs.contains(linkURL)) {
		                urls.add(linkURL);
		                visitedURLs.add(linkURL);
		                writer.write(linkURL);
		                writer.newLine();
		                System.out.println(page++);
		            }
		        }
		        writer.flush();
			}
		
		}
			
		 
	    
	}
	
    public static void scrapePages() throws IOException {
    	String baseURL = "https://developer.android.com/";

    	List<String> urls = new ArrayList<>();
    	urls.add(baseURL);
    	Set<String> visitedURLs = new HashSet<>();

    	int page = 0;
    	while (!urls.isEmpty()) {
    	    String url = urls.remove(0);
    	    visitedURLs.add(url);
    	    System.out.println("Scraping page: " + url);
    	    try {
    	        Document doc = Jsoup.connect(url).get();
    	        Elements divs = doc.select("div");
    	        String lastText = "";
    	        List<String> divTexts = new ArrayList<>();
    	        for (Element div : divs) {
    	            String text = div.text();
    	            String lastTextSub = lastText;
    	            if (lastText.length() > text.length()) {
    	                lastTextSub = lastText.substring(0, text.length());
    	            } else if (text.length() > lastTextSub.length()) {
    	                lastTextSub = text.substring(0, lastText.length());
    	            }
    	            if (lastTextSub.equals(text) || text.equals("")) {
    	                continue;
    	            }
    	            divTexts.add(div.text());
    	            lastText = div.text();
    	        }
    	        Elements links = doc.select("a[href]");
    	        for (Element link : links) {
    	            String linkURL = link.attr("abs:href");
    	            if (linkURL.startsWith(baseURL) && !visitedURLs.contains(linkURL)) {
    	                urls.add(linkURL);
    	            }
    	        }
    	        FileManager.writeDivsToFile(divTexts, page,url);
    	        page++;
    	    } catch (IOException e) {
    	        System.err.println("Error scraping page: " + url);
    	        e.printStackTrace();
    	    }
    	  
    	}

    }
}
