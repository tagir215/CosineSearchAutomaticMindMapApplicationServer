package com.tiger.CosineSearch.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiger.CosineSearch.CosineSearchApplication;
import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.entity.WebPageEntity;
import com.tiger.CosineSearch.repository.BatchSaverRepository;
import com.tiger.CosineSearch.repository.EmbeddingsRepository;

@Service
public class EmbeddingsServiceImpl implements EmbeddingsService{
	@Autowired
	EmbeddingsRepository repository;
	@Autowired
	BatchSaverRepository saverRepo;
	
	public void saveFileToMySql() {
		System.out.println("saving entites to mysql...");
		InputStream inputStream = CosineSearchApplication.class.getClassLoader().getResourceAsStream("glove.6B.100d.txt");
		List<EmbeddingsEntity>entities = FileManager.streamToEntities(inputStream);
		saverRepo.saveEmbeddingsInBatches(entities);	
		System.out.println("done saving entites");
	}


	@Override
	public List<EmbeddingsEntity> getEmbeddings() {
		return repository.findAll();
		
	}


	@Override
	public List<EmbeddingsEntity> getEmbeddingsFromEntityList(List<WebPageEntity> entities) {
		List<String>strings = new ArrayList<>();
		for(WebPageEntity e : entities) {
			String[] s = StringHandler.filterString(e.getUrl());
			strings.addAll(Arrays.asList(s));
		}
		System.out.println("finding Words");
		return repository.findByWords(strings);
	}

	@Override
	public List<EmbeddingsEntity> getEmbeddingsFromWords(List<String> words) {
		return repository.findByWords(words);
	}
}
