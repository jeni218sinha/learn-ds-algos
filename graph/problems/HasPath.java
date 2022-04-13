package graph.problems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import graph.algos.Graph;
import graph.algos.Node;

public class HasPath {

    public boolean hasPath(Graph graph, Character start, Character end, Boolean useStack) {
        if (graph == null || graph.nodes == null || graph.nodes.isEmpty()) {
            return false;
        }

        Map<Character, Node> nodes = graph.nodes;
        Set<Character> visited = new HashSet<>();
        if(!nodes.containsKey(start) || !nodes.containsKey(end)) {
            return false;
        }
        
        if (useStack != null) {
            if (useStack) {
                return dfsStacked(nodes, visited, start, end);
            }        
            return dfs(nodes, visited, start, end);
        }
        return bfs(nodes, visited, start, end);
    }

    private boolean dfs(Map<Character, Node> nodes, Set<Character> visited, Character currNode, Character end) {
        if (currNode == end) {
            return true;
        }

        Map<Character, Integer> edges = nodes.get(currNode).getEdges();
        if (edges != null && !edges.isEmpty()) {
            for (Character ch: edges.keySet()) {
                if(dfs(nodes, visited, ch, end)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfsStacked(Map<Character, Node> nodes, Set<Character> visited, Character start, Character end) {
        Stack<Character> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()) {
            Character node = stack.pop();
            if (node == end) {
                return true;
            }
            Map<Character, Integer> edges = nodes.get(node).getEdges();
            if (edges != null && !edges.isEmpty()) {
                for (Character ch: edges.keySet()) {
                    stack.push(ch);
                }
            }
        }
        return false;
    }

    private boolean bfs(Map<Character, Node> nodes, Set<Character> visited, Character start, Character end) {
        Queue<Character> q = new LinkedList<>();
        q.offer(start);
        
        while(!q.isEmpty()) {
                Character node = q.poll();
                if (node == end) {
                    return true;
                }
                Map<Character, Integer> edges = nodes.get(node).getEdges();
                if (edges != null && !edges.isEmpty()) {
                    for (Character ch: edges.keySet()) {
                        q.offer(ch);
                    }
                }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge('f', 'g', null);
        graph.addEdge('f', 'i', null);
        graph.addEdge('i', 'g', null);
        graph.addEdge('i', 'k', null);
        graph.addEdge('j', 'i', null);
        graph.addNode('h');
        graph.addNode('k');
        Graph.displayGraph(graph);
        HasPath hasPath = new HasPath();
        System.out.println(hasPath.hasPath(graph, 'f', 'k', false));
        System.out.println(hasPath.hasPath(graph, 'f', 'k', true));
        System.out.println(hasPath.hasPath(graph, 'f', 'k', null));
    }
}
