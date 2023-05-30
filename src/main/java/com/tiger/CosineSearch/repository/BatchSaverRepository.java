package com.tiger.CosineSearch.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.entity.WebPageEntity;
import com.tiger.CosineSearch.service.Constants;

@Repository
public class BatchSaverRepository {
	
	
	@Autowired
	EntityManager manager;

	@Transactional
	public void saveWebPagesInBatches(List<WebPageEntity> entities) {
	    int batchSize = 1000; 
	    int i = 0;
	    StringBuilder sqlBuilder = new StringBuilder("INSERT INTO web_page (url) VALUES ");
	    for (WebPageEntity entity : entities) {
	    	String url = entity.getUrl().replaceAll(Constants.ANDROID_URL, "");
	        sqlBuilder.append("(\"").append(url).append("\"),");
	        i++;
	        if (i % batchSize == 0) {
	            String sql = sqlBuilder.toString();
	            sql = sql.substring(0, sql.length() - 1); // remove trailing comma
	            Query query = manager.createNativeQuery(sql);
	            query.executeUpdate();
	            sqlBuilder = new StringBuilder("INSERT INTO web_page (url) VALUES ");
	            System.out.println("batch saved");
	        }
	    }
	    if (sqlBuilder.length() > "INSERT INTO web_page (url) VALUES ".length()) {
	        String sql = sqlBuilder.toString();
	        sql = sql.substring(0, sql.length() - 1); // remove trailing comma
	        Query query = manager.createNativeQuery(sql);
	        query.executeUpdate();
	        System.out.println("last batch saved");
	    }   
	}

	@Transactional
	public void saveEmbeddingsInBatches(List<EmbeddingsEntity> entities) {
	    int batchSize = 500;
	    int i = 0;
	    
	    
	    StringBuilder sqlBuilder = new StringBuilder("INSERT INTO embeddings (word, embeddings) VALUES ");
	    for (EmbeddingsEntity entity : entities) {
	    	String word = entity.getWord();
	    	word = word.replace("\\", "\\\\");
	    	word = word.replace("\"", "\\\"");
	    	word = word.replace("{","");
	    	word = word.replace("}", "");
	    	
	    	
	        sqlBuilder.append("(\"").append(word).append("\", \"").append(entity.getEmbeddings()).append("\"),");
	        i++;
	        if (i % batchSize == 0) {
	            String sql = sqlBuilder.toString();
	            sql = sql.substring(0, sql.length() - 1); // remove trailing comma
	            Query query = manager.createNativeQuery(sql);
	            query.executeUpdate();
	            sqlBuilder = new StringBuilder("INSERT INTO embeddings (word, embeddings) VALUES ");
	            System.out.println("batch saved");
	        }
	    }
	    if (sqlBuilder.length() > "INSERT INTO embeddings (word, embeddings) VALUES ".length()) {
	        String sql = sqlBuilder.toString();
	        sql = sql.substring(0, sql.length() - 1); // remove trailing comma
	        Query query = manager.createNativeQuery(sql);
	        query.executeUpdate();
	        System.out.println("last batch saved");
	    }
	}

	
	

}
