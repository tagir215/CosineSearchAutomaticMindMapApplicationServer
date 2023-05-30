package com.tiger.CosineSearch.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.entity.WebPageEntity;
import com.tiger.CosineSearch.service.Constants;

public class BatchSaverRepositoryTest {

    @Mock
    private EntityManager mockEntityManager;

    @Mock
    private Query mockQuery;

    private BatchSaverRepository batchSaverRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        batchSaverRepository = new BatchSaverRepository();
       // batchSaverRepository.setManager(mockEntityManager);
    }

    @Test
    public void testSaveWebPagesInBatches() {
        List<WebPageEntity> entities = new ArrayList<>();
        // Populate the list with test data

        String expectedSql = "INSERT INTO web_page (url) VALUES ";
        for (WebPageEntity entity : entities) {
            String url = entity.getUrl().replaceAll(Constants.ANDROID_URL, "");
            expectedSql += "(\"" + url + "\"),";
        }
        expectedSql = expectedSql.substring(0, expectedSql.length() - 1); // remove trailing comma

        when(mockEntityManager.createNativeQuery(expectedSql)).thenReturn(mockQuery);

        batchSaverRepository.saveWebPagesInBatches(entities);

        verify(mockQuery).executeUpdate();
    }

    @Test
    public void testSaveEmbeddingsInBatches() {
        List<EmbeddingsEntity> entities = new ArrayList<>();
        // Populate the list with test data

        String expectedSql = "INSERT INTO embeddings (word, embeddings) VALUES ";
        for (EmbeddingsEntity entity : entities) {
            String word = entity.getWord().replace("\\", "\\\\")
                                           .replace("\"", "\\\"")
                                           .replace("{", "")
                                           .replace("}", "");
            expectedSql += "(\"" + word + "\", \"" + entity.getEmbeddings() + "\"),";
        }
        expectedSql = expectedSql.substring(0, expectedSql.length() - 1); // remove trailing comma

        when(mockEntityManager.createNativeQuery(expectedSql)).thenReturn(mockQuery);

        batchSaverRepository.saveEmbeddingsInBatches(entities);

        verify(mockQuery).executeUpdate();
    }
}

