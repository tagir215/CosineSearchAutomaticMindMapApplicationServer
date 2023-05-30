package com.tiger.CosineSearch.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

public class WordTest {

	@Test
	public void testToCharacterQueue() {
		String word = "example";
		Word exampleWord = new Word(word, new double[0]);

		Queue<Character> expectedQueue = new LinkedList<>();
		for (int i = 0; i < word.length(); i++) {
			expectedQueue.add(word.charAt(i));
		}

		Queue<Character> actualQueue = exampleWord.toCharacterQueue(word);

		assertEquals(expectedQueue, actualQueue);
	}

	@Test
	public void testWordConstructor() {
		String word = "example";
		double[] embeddings = { 0.1, 0.2, 0.3 };

		Word exampleWord = new Word(word, embeddings);

		assertEquals(word, getStringFromCharacterQueue(exampleWord.characterQueue));
		assertArrayEquals(embeddings, exampleWord.embeddings);
	}

	private String getStringFromCharacterQueue(Queue<Character> queue) {
		StringBuilder sb = new StringBuilder();
		for (char c : queue) {
			sb.append(c);
		}
		return sb.toString();
	}
}

