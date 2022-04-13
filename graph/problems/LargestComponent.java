package graph.problems;

import graph.algos.*;
import java.util.*;

public class LargestComponent {

    public List<Character> getLargestComponent(Graph graph) {
        List<Character> largestComponent = new ArrayList<>();
        if (graph == null || graph.nodes == null || graph.nodes.isEmpty()) {
            return largestComponent;
        }
        Map<Character, Node> nodes = graph.nodes;
        Set<Character> visited = new HashSet<>();
        
        for (Character node: nodes.keySet()) {
            if(!visited.contains(node)) {
                List<Character> currentComps = new ArrayList<>();
                dfs(nodes, visited, currentComps, node);
                if (currentComps.size() > largestComponent.size()) {
                    largestComponent = currentComps;
                }
            }
        }
        return largestComponent;
    }

    public void dfs(Map<Character, Node> nodes, Set<Character> visited, List<Character> currentComponent, Character start) {
        if (visited.contains(start)) {
            return;
        }
        currentComponent.add(start);
        visited.add(start);
        Map<Character, Integer> childNodes = nodes.get(start).getEdges();
        if (childNodes != null&& childNodes.size() > 0) {
            for (Character ch: childNodes.keySet()) {
                dfs(nodes, visited, currentComponent, ch);
            }
        }
    }
    
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        LargestComponent lc = new LargestComponent();
        Graph graph = lc.populateEdgesAndGetGraph(edges);
        List<Character> largestGroup = lc.getLargestComponent(graph);
        System.out.println(largestGroup);
    }

    public Graph populateEdgesAndGetGraph(List<Edge> edges) {
        if (edges == null) {
            return null;
        }
        edges.add(new Edge('1', '0'));
        edges.add(new Edge('0', '5'));
        edges.add(new Edge('5', '8'));
        edges.add(new Edge('0', '8'));
        edges.add(new Edge('4', '2'));
        edges.add(new Edge('4', '3'));
        edges.add(new Edge('3', '2'));
        Graph graph = Graph.getGraphFromEdges(edges, false);
        return graph;
    }
}
