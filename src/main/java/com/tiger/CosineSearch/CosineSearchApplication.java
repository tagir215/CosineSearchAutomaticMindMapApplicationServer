package com.tiger.CosineSearch;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.entity.WebPageEntity;
import com.tiger.CosineSearch.service.EmbeddingsService;
import com.tiger.CosineSearch.service.Trie;
import com.tiger.CosineSearch.service.WebPages;
import com.tiger.CosineSearch.service.WebPagesService;

@SpringBootApplication
public class CosineSearchApplication {

	@Autowired
	WebPagesService webPagesService;
	@Autowired
	EmbeddingsService embeddingsService;
	public static boolean initState;

	public static void main(String[] args) {
		SpringApplication.run(CosineSearchApplication.class, args);
	}

	@PostConstruct
    public void init() {
        //webPagesService.saveWebPagesToMySql();
        //System.out.println("webpages done");
		//embeddingsService.saveFileToMySql();
		//System.out.println("embeddings done");
		
		List<WebPageEntity>entities = webPagesService.getWebPages();
        System.out.println("done getting entities");
        List<EmbeddingsEntity>entities2 = embeddingsService.getEmbeddingsFromEntityList(entities);
        System.out.println("done getting entites 2");
        new Trie(entities2);
        new WebPages(entities);
        initState=true;
    }
	
	
}
