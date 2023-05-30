package com.tiger.CosineSearch.service;

import java.io.InputStream;
import java.util.List;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.mapper.WordVectorMapper;

public class Trie {
	public static Node root;
	public static double[] masterEmbeddings;
	public Trie(){
		root = new Node(null,null);
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("glove.6B.100d.txt");
		FileManager.streamToTrie(stream,root);
		masterEmbeddings = root.search("fist", 0);
	}
	public Trie(List<EmbeddingsEntity>entities){
		List<Word>words = WordVectorMapper.entitiesToWords(entities);
		root = new Node(null,null);
		for(Word w : words) {
			root.insert(w);
		}
		System.out.println("trie done");
	}
	
}
