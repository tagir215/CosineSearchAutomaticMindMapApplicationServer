package com.tiger.CosineSearch.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ScraperTest {

    @Test
    public void testScrapeURLSs() {
        assertDoesNotThrow(() -> Scraper.scrapeURLSs());
    }

    @Test
    public void testScrapePages() {
        assertDoesNotThrow(() -> Scraper.scrapePages());
    }
}
