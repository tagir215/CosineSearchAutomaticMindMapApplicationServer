package com.tiger.CosineSearch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.entity.WebPageEntity;
import com.tiger.CosineSearch.service.Calculations;
import com.tiger.CosineSearch.service.Constants;
import com.tiger.CosineSearch.service.EmbeddingsService;
import com.tiger.CosineSearch.service.ResultFormatter;
import com.tiger.CosineSearch.service.Trie;
import com.tiger.CosineSearch.service.WebPage;
import com.tiger.CosineSearch.service.WebPages;
import com.tiger.CosineSearch.service.WebPagesService;


@RestController
public class SearchController {
	
	
	@Autowired
	EmbeddingsService service;
		
	@GetMapping("/search/{inputString}")
    @ResponseBody
    @CrossOrigin(origins = "https://csamma-front.herokuapp.com/")
	public String search(@PathVariable String inputString) {
		System.out.println("searching "+inputString);
		List<WebPage>result = Calculations.getAssociatedURLs(inputString.toLowerCase().split(" "),service);
		return ResultFormatter.fromat(result);
	}
	
	
}
