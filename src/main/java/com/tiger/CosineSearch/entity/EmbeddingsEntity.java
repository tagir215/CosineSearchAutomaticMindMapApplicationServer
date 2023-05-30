package com.tiger.CosineSearch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "embeddings")
public class EmbeddingsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String word;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(10000)")
	private String embeddings;

	public EmbeddingsEntity() {
	}
	
	public EmbeddingsEntity(String word, String embeddings) {
		super();
		this.word = word;
		this.embeddings = embeddings;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getEmbeddings() {
		return embeddings;
	}

	public void setEmbeddings(String embeddings) {
		this.embeddings = embeddings;
	}
	 
	
}
