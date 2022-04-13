package graph.algos;

import java.util.*;

public class Node  {
	Character value;
	Map<Character, Integer> edges;
	
	public Node(Character value) {
		this.value = value;
		this.edges = new HashMap<>();
	}

	public Map<Character, Integer> getEdges() {
		return this.edges;
	}

	public void setEdges(Map<Character, Integer> nodes) {
		this.edges = nodes;
	}

	public void addEdge(Character to, Integer edgeCost) {
		this.edges.put(to, edgeCost);
	}
}
