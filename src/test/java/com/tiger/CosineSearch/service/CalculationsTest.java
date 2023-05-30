package com.tiger.CosineSearch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CalculationsTest {

	@Mock
	private EmbeddingsService embeddingsService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAssociatedURLs() {
		String[] strings = { "word1", "word2", "word3" };

		WebPage webpage1 = new WebPage(null);
		webpage1.averageVec = new double[] { 0.1, 0.2, 0.3 };
		WebPage webpage2 = new WebPage(null);
		webpage2.averageVec = new double[] { 0.4, 0.5, 0.6 };

		List<WebPage> listOfPages = Arrays.asList(webpage1, webpage2);

		when(embeddingsService.average(strings)).thenReturn(new double[] { 0.2, 0.3, 0.4 });
		when(embeddingsService.cosineSimilarity(any(double[].class), any(double[].class))).thenReturn(0.7);

		List<WebPage> associatedURLs = Calculations.getAssociatedURLs(strings, embeddingsService);

		assertEquals(2, associatedURLs.size());
		assertEquals(1.0, associatedURLs.get(0).product);
		assertEquals(0.7, associatedURLs.get(1).product);
	}

}

