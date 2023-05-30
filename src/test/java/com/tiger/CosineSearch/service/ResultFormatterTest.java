package com.tiger.CosineSearch.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


public class ResultFormatterTest {

    @Test
    public void testFormat() {
        List<WebPage> result = new ArrayList<>();
        for (int i = 1; i <= 400; i++) {
            String url = "http://example.com/page" + i;
            WebPage webPage = new WebPage(url);
            result.add(webPage);
        }

        String expected = "";
        for (int i = 1; i <= 400; i++) {
            String url = "http://example.com/page" + i;
            expected += url + " ";
        }

        String actual = ResultFormatter.fromat(result);

        assertEquals(expected.trim(), actual.trim());
    }
}
