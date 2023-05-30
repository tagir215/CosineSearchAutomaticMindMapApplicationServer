package com.tiger.CosineSearch.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiger.CosineSearch.entity.WebPageEntity;
import com.tiger.CosineSearch.repository.BatchSaverRepository;
import com.tiger.CosineSearch.repository.WebPagesRepository;

@Service
public class WebPagesServiceImpl implements WebPagesService{

	@Autowired
	WebPagesRepository repository;
	
	@Autowired 
	BatchSaverRepository saverRepo;
	
	public void saveWebPagesToMySql() {
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("webpages.txt");
		List<WebPageEntity>entities = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){
			String url = null;
			while((url = reader.readLine())!=null) {
				entities.add(new WebPageEntity(url));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		saverRepo.saveWebPagesInBatches(entities);
	}
	
	
	@Override
	public List<WebPageEntity> getWebPages() {	
		return repository.findAll();
	}
	
}
