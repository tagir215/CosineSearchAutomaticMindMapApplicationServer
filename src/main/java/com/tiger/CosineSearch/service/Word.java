package com.tiger.CosineSearch.service;
import java.util.LinkedList;
import java.util.Queue;

public class Word {
	public double[] embeddings;
	public Queue<Character>characterQueue;
	public Word(String word, double[] embeddings) {
		characterQueue = toCharacterQueue(word);
		this.embeddings = embeddings;
	}
	public Queue<Character> toCharacterQueue(String word) {
		Queue<Character> queue = new LinkedList<>();
		for(int i =0; i< word.length(); i++) {
			queue.add(word.charAt(i));
		}
		return queue;
	}
}
