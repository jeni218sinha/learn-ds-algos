package graph.algos;

import java.util.*;

public class Dfs {
	public List<Character> dfs(Graph graph, Character start) {
		List<Character> dfsNodes = new ArrayList<>();
		Set<Character> visited = new HashSet<>();
		if (graph == null || graph.nodes == null || graph.nodes.size() == 0) {
	        return dfsNodes;
        }

        Map<Character, Node> nodes = graph.nodes;
        if (!nodes.containsKey(start)) {
            return dfsNodes;
        }
        dfsHelper(nodes, visited, start, dfsNodes); 
        return dfsNodes;
    }

    public List<Character> dfsStacked(Graph graph, Character start) {
        List<Character> dfsNodes = new ArrayList<>();
		Set<Character> visited = new HashSet<>();
		if (graph == null || graph.nodes == null || graph.nodes.size() == 0) {
	        return dfsNodes;
        }

        Map<Character, Node> nodes = graph.nodes;
        if (!nodes.containsKey(start)) {
            return dfsNodes;
        }

        Stack<Character> stack = new Stack<>();
        stack.add(start);

        while(!stack.isEmpty()) {
            Character ch = stack.pop();
            dfsNodes.add(ch);
            visited.add(ch);

            Map<Character, Integer> child = nodes.get(ch).getEdges();
            for(Map.Entry<Character, Integer> entry: child.entrySet()) {
                if (!visited.contains(entry.getKey())) {
                    stack.add(entry.getKey());
                }
            }
        }

        return dfsNodes;
    }

    private void dfsHelper(Map<Character, Node> nodes, Set<Character> visited, Character node, List<Character> dfsNodes) {
        if (dfsNodes.contains(node)) {
            return;
        }
        dfsNodes.add(node);
        visited.add(node);
        // visit child 
        Map<Character, Integer> childNodes = nodes.get(node).getEdges();
        for (Map.Entry<Character, Integer> entry : childNodes.entrySet()) {
            dfsHelper(nodes, visited, entry.getKey(), dfsNodes);
        }
    }

    public void displayDfsOrder(List<Character> nodes) {
        for (Character ch: nodes) {
            System.out.print(ch + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Graph graph = Graph.getSampleGraph();
        Dfs dfs = new Dfs();
        List<Character> dfsNodes = dfs.dfs(graph, 'a');
        dfs.displayDfsOrder(dfsNodes);
        dfsNodes = dfs.dfsStacked(graph, 'a');
        dfs.displayDfsOrder(dfsNodes);
    }
}

