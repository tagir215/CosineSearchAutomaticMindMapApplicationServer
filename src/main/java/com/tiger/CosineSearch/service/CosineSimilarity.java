package com.tiger.CosineSearch.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tiger.CosineSearch.entity.EmbeddingsEntity;
import com.tiger.CosineSearch.mapper.WordVectorMapper;


public class CosineSimilarity {
	public static double cosineSimilarity(double[] vec1, double[] vec2) {
		return dotProduct(vec1,vec2)/ (magnitude(vec1)*magnitude(vec2));
	}
	
	public static double magnitude(double[] vec) {
		double magnitude = 0;
		for(double mag : vec) {
			magnitude += mag*mag;
		}
		return (double) Math.sqrt(magnitude);
	}
	public static double dotProduct(double[] vec1, double[] vec2) {
		double result = 0;
		for(int i=0; i<vec1.length; i++) {
			result += vec1[i] * vec2[i];
		}
		return result;
	}
	
	public static double[] average(String[] words,EmbeddingsService service) {
		List<double[]>embeddingsList = new ArrayList<>();
		searchEmbeddings(embeddingsList,words,service);
		return calculateMeans(embeddingsList);
	}
	private static double[] calculateMeans(List<double[]>embeddingsList) {
		double[] result = new double[Constants.EMBEDDINGS_SIZE];
		for(int i=0; i<result.length; i++) {
			double sum = 0;
			for(double[] e : embeddingsList) {
				if(e!=null) {
					if(e.length>0)
						sum += e[i];					
				}
			}
			result[i] = sum / embeddingsList.size();
		}
		return result;
	}
	private static void searchEmbeddings(List<double[]>list,String[] words,EmbeddingsService service) {
		if(service==null) {
			for(String word : words) {
				double[] embeddings = Trie.root.search(word, 0);
				if(embeddings==null) {
					double[] embs2 = Trie.root.search("fish", 0);
					Trie.root.insert(new Word(word,embs2));
					list.add(embs2);
				}
				else {
					list.add(embeddings);
				}
			}
		}else {
			List<EmbeddingsEntity>entities = service.getEmbeddingsFromWords(Arrays.asList(words));
			List<Word>wordObjects = WordVectorMapper.entitiesToWords(entities);
			if(wordObjects.size()>0) {	
				for(Word w : wordObjects) {
					list.add(w.embeddings);
				}
			}
		}
		
	}
}
 