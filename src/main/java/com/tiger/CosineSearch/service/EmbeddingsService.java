package com.tiger.CosineSearch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.entity.WebPageEntity;

@Service
public interface EmbeddingsService {
	public void saveFileToMySql();
	public List<EmbeddingsEntity>getEmbeddings();
	public List<EmbeddingsEntity>getEmbeddingsFromEntityList(List<WebPageEntity>entities);
	public List<EmbeddingsEntity>getEmbeddingsFromWords(List<String>words);
}
