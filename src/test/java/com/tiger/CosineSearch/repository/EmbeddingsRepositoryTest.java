package com.tiger.CosineSearch.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;

public class EmbeddingsRepositoryTest {

    @Mock
    private EmbeddingsRepository mockRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindEmbeddingsEntityByWord() {
        String word = "apple";
        EmbeddingsEntity expectedEntity = new EmbeddingsEntity();
        // Set the expected entity properties

        when(mockRepository.findEmbeddingsEntityByWord(word)).thenReturn(Optional.of(expectedEntity));

        Optional<EmbeddingsEntity> actualEntity = mockRepository.findEmbeddingsEntityByWord(word);

        assertEquals(expectedEntity, actualEntity.get());
    }

    @Test
    public void testFindByWords() {
        List<String> words = new ArrayList<>();
        // Populate the list with test words

        String expectedQuery = "SELECT e FROM EmbeddingsEntity e WHERE e.word IN (:words)";
        when(mockRepository.findByWords(words)).thenReturn(null); // Change this to the expected result

        List<EmbeddingsEntity> actualResult = mockRepository.findByWords(words);

        verify(mockRepository).findByWords(words);
        assertEquals(null, actualResult); // Change this to the expected result
    }
}
