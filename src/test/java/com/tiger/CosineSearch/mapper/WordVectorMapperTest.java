package com.tiger.CosineSearch.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.service.Word;

public class WordVectorMapperTest {
	
	@Test
	public void testLineToEntities() {
		String line = "apple 0.5 0.3 0.1";
		EmbeddingsEntity expectedEntity = new EmbeddingsEntity("apple", "0.5 0.3 0.1");
		EmbeddingsEntity actualEntity = WordVectorMapper.lineToEntities(line);
		assertEquals(expectedEntity, actualEntity);
	}
	
	@Test
	public void testEntitiesToWords() {
		List<EmbeddingsEntity> entities = new ArrayList<>();
		entities.add(new EmbeddingsEntity("apple", "0.5 0.3 0.1"));
		entities.add(new EmbeddingsEntity("banana", "0.2 0.4 0.6"));
		
		Word expectedWord1 = new Word("apple", new double[] { 0.5, 0.3, 0.1 });
		Word expectedWord2 = new Word("banana", new double[] { 0.2, 0.4, 0.6 });
		List<Word> expectedWords = Arrays.asList(expectedWord1, expectedWord2);
		
		List<Word> actualWords = WordVectorMapper.entitiesToWords(entities);
		assertEquals(expectedWords, actualWords);
	}
}
