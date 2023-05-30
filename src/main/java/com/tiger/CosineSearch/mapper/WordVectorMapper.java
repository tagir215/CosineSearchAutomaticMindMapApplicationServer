package com.tiger.CosineSearch.mapper;

import java.util.ArrayList;
import java.util.List;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.service.Word;

public class WordVectorMapper {
	
	public static EmbeddingsEntity lineToEntities(String line) {
		String firstWord = line.split(" ")[0];
		String vectors = line.substring(firstWord.length()+1,line.length());
		return new EmbeddingsEntity(firstWord,vectors);
	}
	
	public static List<Word> entitiesToWords(List<EmbeddingsEntity>entities){
		List<Word>words = new ArrayList<>();
		for(EmbeddingsEntity e : entities) {
			String[] strings = e.getEmbeddings().split(" ");
			double[] embeddings = new double[strings.length];
			for(int i=0; i<strings.length; i++) {
				embeddings[i] = Double.parseDouble(strings[i]);
			}
			
			words.add(new Word(e.getWord(),embeddings));
		}
		return words;
	}
}
