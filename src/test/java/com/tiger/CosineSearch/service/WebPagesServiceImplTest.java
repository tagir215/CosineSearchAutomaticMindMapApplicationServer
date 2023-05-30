package com.tiger.CosineSearch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.tiger.CosineSearch.entity.WebPageEntity;
import com.tiger.CosineSearch.repository.BatchSaverRepository;
import com.tiger.CosineSearch.repository.WebPagesRepository;

@SpringBootTest
public class WebPagesServiceImplTest {

	@Mock
	private WebPagesRepository repository;

	@Mock
	private BatchSaverRepository saverRepo;

	@InjectMocks
	private WebPagesServiceImpl service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSaveWebPagesToMySql() throws IOException {
		String webPages = "https://www.example.com/page1\nhttps://www.example.com/page2";
		InputStream inputStream = new ByteArrayInputStream(webPages.getBytes());

		when(this.getClass().getClassLoader().getResourceAsStream("webpages.txt")).thenReturn(inputStream);

		service.saveWebPagesToMySql();

		List<WebPageEntity> expectedEntities = Arrays.asList(new WebPageEntity("https://www.example.com/page1"),
				new WebPageEntity("https://www.example.com/page2"));

		verify(saverRepo).saveWebPagesInBatches(expectedEntities);
	}

	@Test
	public void testGetWebPages() {
		List<WebPageEntity> expectedWebPages = Arrays.asList(new WebPageEntity("https://www.example.com/page1"),
				new WebPageEntity("https://www.example.com/page2"));

		when(repository.findAll()).thenReturn(expectedWebPages);

		List<WebPageEntity> actualWebPages = service.getWebPages();

		assertEquals(expectedWebPages, actualWebPages);
	}
}

