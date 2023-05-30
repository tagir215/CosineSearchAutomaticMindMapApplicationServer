package com.tiger.CosineSearch.service;

import java.util.ArrayList;
import java.util.List;


public class Node {
	List<Node>childrenList = new ArrayList<>();
	Node parent;
	Character character;
	double[] embeddings = new double[0];
	
	public Node(Node parent, Character character){
		this.parent = parent;
		this.character = character;
		}
	
	public void insert(Word word) {
		if(word.characterQueue.size()==0) {
			embeddings = word.embeddings;
			return;
		}
		Character c = word.characterQueue.poll();
		for(int i =0; i<childrenList.size(); i++) {
			Node child = childrenList.get(i);
			if(c==child.character) {
				child.insert(word);
				return;
			}
		}
		Node node = new Node(this,c);
		childrenList.add(node);
		node.insert(word);	
	}
	
	public double[] search(String word,int index) {
		word = word.toLowerCase();
		if(index==word.length()) {
			return embeddings;
		}
		for(int i=0; i<childrenList.size(); i++) {
			if(childrenList.get(i).character==word.charAt(index)) {
				return childrenList.get(i).search(word, index+1);
			}
		}
		return null;
	}
	
}
