package com.tiger.CosineSearch.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.mapper.WordVectorMapper;

public class FileManager {
	public static void streamToTrie(InputStream inputStream, Node root) {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
			String line;
			while((line = reader.readLine())!=null) {
				String[] strings = line.split(" ");
				String name = strings[0];
				double[] values = new double[strings.length-1];
				for(int i=1; i<strings.length; i++) {
					values[i-1] = Double.parseDouble(strings[i]);
				}
				root.insert(new Word(name,values));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("trie done");
	}
	
	public static void writeDivsToFile(List<String>divs,int page,String url) {
		String fileName = "page"+page;
		URL resourceUrl = FileManager.class.getClassLoader().getResource("");
		File file = null;
		try {
			file = File.createTempFile("page"+page+"--", ".txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(url + "\n");
		for(String div : divs) {
			stringBuilder.append("\n<div>");
			stringBuilder.append("\n   "+div);
			stringBuilder.append("\n</div>");
		}
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
			writer.write(stringBuilder.toString());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<WebPage> pagesToList(InputStream stream) {
		List<WebPage>webPages = new ArrayList<>();
		try(BufferedReader reader =new BufferedReader(new InputStreamReader(stream))) {
			String line;
			while((line=reader.readLine())!=null) {
				WebPage page = new WebPage(line);
				page.strings = StringHandler.filterString(line);
				//page.averageVec = CosineSimilarity.average(page.strings);
				webPages.add(page);			
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return webPages;
	}
	
	public static List<EmbeddingsEntity> streamToEntities(InputStream inputStream) {
		List<EmbeddingsEntity>entites = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
			String line;
			while((line=reader.readLine())!=null) {
				entites.add(WordVectorMapper.lineToEntities(line));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return entites;
	}
	
	
}
