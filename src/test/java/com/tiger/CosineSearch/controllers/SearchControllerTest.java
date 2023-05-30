package com.tiger.CosineSearch.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.service.EmbeddingsService;

@SpringJUnitConfig
@WebMvcTest(SearchController.class)
@ActiveProfiles("test")
public class SearchControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private EmbeddingsService embeddingsService;
  
  @Test
  public void testSearch() throws Exception {
      
    String inputString = "example search terms";
    String expectedOutput = "https://www.example.com/1 https://www.example.com/2 https://www.example.com/3 ";
    List<EmbeddingsEntity>embeddings = new ArrayList<>();
    embeddings.add(new EmbeddingsEntity("example","12"));
    embeddings.add(new EmbeddingsEntity("search","34"));
    embeddings.add(new EmbeddingsEntity("terms","23"));
    Mockito.when(embeddingsService.getEmbeddingsFromWords(Mockito.anyList())).thenReturn(embeddings);

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/search/" + inputString))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();
    
    String actualOutput = mvcResult.getResponse().getContentAsString();
    assertEquals(expectedOutput, actualOutput);
  }
  
}
