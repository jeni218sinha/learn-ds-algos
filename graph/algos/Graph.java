package graph.algos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	public Map<Character, Node> nodes;

	public Graph() {
		this.nodes = new HashMap<>();
	}

	public void addEdge(Character from, Character to, Integer cost) {
		this.addEdge(from, to, cost, true);
	}

	public void addEdge(Character from, Character to, Integer cost, boolean isDirected) {
		Node fromNode = nodes.getOrDefault(from, new Node(from));
		Node toNode = nodes.getOrDefault(to, new Node(to));
		fromNode.addEdge(to, cost);
		if (!isDirected) {
			toNode.addEdge(from, cost);
		}
		nodes.put(from, fromNode);
		nodes.put(to, toNode);
	}

	public void addNode(Character ch) {
		nodes.put(ch, new Node(ch));
	}
	
	public static Graph getSampleGraph() {
		// a: [b, c]
		// b: [d]
		// c: [e, d]
		// d: [f]
		// e: []
		// f: []
		Graph graph = new Graph();
		graph.addEdge('a', 'b', null);
		graph.addEdge('a', 'c', null);
		graph.addEdge('b', 'd', null);
		graph.addEdge('c', 'e', null);
		graph.addEdge('c', 'd', null);
		graph.addEdge('d', 'f', null);
		return graph;
	}

	public static void displayGraph(Graph graph) {
		for(Map.Entry<Character, Node> graphNode: graph.nodes.entrySet()) {
			System.out.print(graphNode.getKey() + ":");
			Node node = graphNode.getValue();
			Map<Character, Integer> edges = node.getEdges();
			if (edges.isEmpty()) {
				System.out.print(" [] ");
			} else {
				System.out.print(" [ ");
				for (Character ch: edges.keySet()) {
					System.out.print("("+ ch + ", " + edges.get(ch) + ")");
				}
				System.out.print(" ]");
			}
			System.out.println();
		}
	}

	public static Graph getGraphFromEdges(List<Edge> edges, boolean isDirected) {
		if (edges == null || edges.isEmpty()) {
			return null;
		}
		Graph graph = new Graph();
		for(Edge edge: edges) {
			graph.addEdge(edge.from, edge.to, edge.cost, false);
		}
		return graph;
	}

}
