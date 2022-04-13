package graph.problems;

import graph.algos.Graph;

import java.util.*;
import graph.algos.*;

public class ConnectedComponents {

    public int getGraphComponents(Graph graph, boolean stacked) {
        if (graph == null || graph.nodes == null || graph.nodes.isEmpty()) {
            return 0;
        }
        Map<Character, Node> nodes = graph.nodes;
        Set<Character> visited = new HashSet<>();
        
        if (!stacked) {
            int numComponents = 0;
            for(Character node: nodes.keySet()) {
                if (!visited.contains(node)) {
                    dfs(nodes, visited, node);
                    numComponents++;
                }
            }
            return numComponents;
        }
        return dfsStacked(nodes, visited);
    }

    private void dfs(Map<Character, Node> nodes, Set<Character> visited, Character start) {
        if(visited.contains(start)) {
            return;
        }
        visited.add(start);
        Map<Character, Integer> childNodes = nodes.get(start).getEdges();
        if (childNodes == null || childNodes.isEmpty()) {
            return;
        } else {
            for(Character ch: childNodes.keySet()) {
                dfs(nodes, visited, ch);
            }
        }
        
    }

    private int dfsStacked(Map<Character, Node> nodes, Set<Character> visited) {
        int numComponents = 0;
        Stack<Character> stack = new Stack<>();
        for (Character node: nodes.keySet()) {
            if(!visited.contains(node)) {
                stack.push(node);
                while(!stack.isEmpty()) {
                    Character currNode = stack.pop();
                    visited.add(currNode);
                    Map<Character, Integer> childNodes = nodes.get(currNode).getEdges();
                    if (childNodes == null || childNodes.isEmpty()) {
                        continue;
                    } else {
                        for(Character ch: childNodes.keySet()) {
                            if(!visited.contains(ch)) {
                                visited.add(ch);
                                stack.push(ch);
                            }
                        }
                    }                    
                }
                numComponents++;
            }
        }
        return numComponents;
    }

    public static void main(String args[]) {
        List<Edge> edges = new ArrayList<>();
        ConnectedComponents cc = new ConnectedComponents();
        Graph graph = cc.populateEdgesAndGetGraph(edges);
        int numComponents = cc.getGraphComponents(graph, false);
        System.out.println("Number of components in graph = " + numComponents);
        numComponents = cc.getGraphComponents(graph, true);
        System.out.println("Number of components in graph via stacking = " + numComponents);
    }

    public Graph populateEdgesAndGetGraph(List<Edge> edges) {
        if (edges == null) {
            return null;
        }
        edges.add(new Edge('1', '2'));
        edges.add(new Edge('5', '6'));
        edges.add(new Edge('6', '4'));
        edges.add(new Edge('6', '8'));
        edges.add(new Edge('6', '7'));
        Graph graph = Graph.getGraphFromEdges(edges, false);
        graph.addNode('3');
        return graph;
    }
}
