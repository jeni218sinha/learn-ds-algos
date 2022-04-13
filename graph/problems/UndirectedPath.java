package graph.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import graph.algos.Edge;
import graph.algos.Graph;
import graph.algos.Node;

public class UndirectedPath {

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
        visited.add(currNode);

        Map<Character, Integer> edges = nodes.get(currNode).getEdges();
        if (edges != null && !edges.isEmpty()) {
            for (Character ch: edges.keySet()) {
                if(!visited.contains(ch) && dfs(nodes, visited, ch, end)) {
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
            visited.add(node);
            Map<Character, Integer> edges = nodes.get(node).getEdges();
            if (edges != null && !edges.isEmpty()) {
                for (Character ch: edges.keySet()) {
                    if (!visited.contains(ch)) {
                        stack.push(ch);
                    }
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
                visited.add(node);
                Map<Character, Integer> edges = nodes.get(node).getEdges();
                if (edges != null && !edges.isEmpty()) {
                    for (Character ch: edges.keySet()) {
                        if (!visited.contains(ch)) {
                            q.offer(ch);
                        }
                    }
                }
        }
        return false;
    }
    public static void main(String args[]) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge('i', 'j'));
        edges.add(new Edge('k', 'i'));
        edges.add(new Edge('m', 'k'));
        edges.add(new Edge('k', 'l'));
        edges.add(new Edge('o', 'n'));
        Graph graph = Graph.getGraphFromEdges(edges, false);
        Graph.displayGraph(graph);

        UndirectedPath undirectedPath = new UndirectedPath();
        System.out.println(undirectedPath.hasPath(graph, 'j', 'm', false));
    }
}
