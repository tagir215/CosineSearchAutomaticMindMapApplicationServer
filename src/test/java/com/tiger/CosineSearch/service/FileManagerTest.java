package com.tiger.CosineSearch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FileManagerTest {

    @Mock
    private InputStream mockInputStream;

    @Test
    public void testStreamToTrie() throws IOException {
        Node mockRoot = mock(Node.class);

        List<String> lines = new ArrayList<>();
        lines.add("word1 1.0 2.0 3.0");
        lines.add("word2 4.0 5.0 6.0");
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        lines.forEach(joiner::add);
        InputStream inputStream = new ByteArrayInputStream(joiner.toString().getBytes());

        FileManager.streamToTrie(inputStream, mockRoot);

        verify(mockRoot, times(2)).insert(any(Word.class));
    }

    @Test
    public void testWriteDivsToFile() throws IOException {
        List<String> divs = new ArrayList<>();
        divs.add("div1");
        divs.add("div2");
        divs.add("div3");

        String url = "http://example.com";
        int page = 1;

        FileManager.writeDivsToFile(divs, page, url);

        // Verify the content of the written file
        String expectedContent = url + System.lineSeparator() +
                System.lineSeparator() +
                "<div>" + System.lineSeparator() +
                "   div1" + System.lineSeparator() +
                "</div>" + System.lineSeparator() +
                System.lineSeparator() +
                "<div>" + System.lineSeparator() +
                "   div2" + System.lineSeparator() +
                "</div>" + System.lineSeparator() +
                System.lineSeparator() +
                "<div>" + System.lineSeparator() +
                "   div3" + System.lineSeparator() +
                "</div>";
        String actualContent = getFileContent("page1.txt");

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testPagesToList() throws IOException {
        List<WebPage> expectedWebPages = new ArrayList<>();
        expectedWebPages.add(new WebPage("http://example.com/page1"));
        expectedWebPages.add(new WebPage("http://example.com/page2"));

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        expectedWebPages.stream().map(WebPage::getUrl).forEach(joiner::add);
        InputStream inputStream = new ByteArrayInputStream(joiner.toString().getBytes());

        List<WebPage> actualWebPages = FileManager.pagesToList(inputStream);

        assertEquals(expectedWebPages.size(), actualWebPages.size());
        for (int i = 0; i < expectedWebPages.size(); i++) {
            assertEquals(expectedWebPages.get(i).getUrl(), actualWebPages.get(i).getUrl());
        }
    }

}

