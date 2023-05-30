package com.tiger.CosineSearch.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.tiger.CosineSearch.service.MySort;
import com.tiger.CosineSearch.service.WebPage;

public class SortTest {

    @Test
    public void testEmptyList() {
        List<WebPage> pages = new ArrayList<>();
        pages = MySort.mergeSortWebPages(pages);
        Assertions.assertTrue(pages.isEmpty());
    }

    @Test
    public void testSinglePage() {
        List<WebPage> pages = new ArrayList<>();
        WebPage page1 = new WebPage("http://example.com");
        page1.product = 3;
        pages.add(page1);

        pages = MySort.mergeSortWebPages(pages);

        Assertions.assertEquals(1, pages.size());
        Assertions.assertEquals("http://example.com", pages.get(0).getUrl());
        Assertions.assertEquals(3.0, pages.get(0).product);
    }

    @Test
    public void testMultiplePages() {
        List<WebPage> pages = new ArrayList<>();
        WebPage page1 = new WebPage("http://example.com");
        page1.product = 3;
        pages.add(page1);

        WebPage page2 = new WebPage("http://example.org");
        page2.product = 2;
        pages.add(page2);

        WebPage page3 = new WebPage("http://example.net");
        page3.product = 1;
        pages.add(page3);

        pages = MySort.mergeSortWebPages(pages);

        Assertions.assertEquals(3, pages.size());
        Assertions.assertEquals("http://example.com", pages.get(0).getUrl());
        Assertions.assertEquals(3.0, pages.get(0).product);
        Assertions.assertEquals("http://example.org", pages.get(1).getUrl());
        Assertions.assertEquals(2.0, pages.get(1).product);
        Assertions.assertEquals("http://example.net", pages.get(2).getUrl());
        Assertions.assertEquals(1.0, pages.get(2).product);
    }
}
