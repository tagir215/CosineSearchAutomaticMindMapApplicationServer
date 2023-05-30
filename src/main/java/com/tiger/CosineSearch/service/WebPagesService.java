package com.tiger.CosineSearch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tiger.CosineSearch.entity.WebPageEntity;

@Service
public interface WebPagesService {
	public void saveWebPagesToMySql();
	public List<WebPageEntity>getWebPages();
}
