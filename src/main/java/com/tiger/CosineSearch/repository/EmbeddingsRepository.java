package com.tiger.CosineSearch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;

public interface EmbeddingsRepository extends JpaRepository<EmbeddingsEntity,Long>{
	Optional<EmbeddingsEntity>findEmbeddingsEntityByWord(String word);
   @Query("SELECT e FROM EmbeddingsEntity e WHERE e.word IN (:words)")
   List<EmbeddingsEntity> findByWords(@Param("words") List<String> words);
   
}
