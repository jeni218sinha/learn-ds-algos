package graph.problems;

import java.util.*;
import graph.algos.*;

public class ShortestPath {
    public int getShortestPath(Graph graph, Character start, Character end) {
        if (graph == null || graph.nodes == null || graph.nodes.isEmpty()) {
            return 0;
        }

        Map<Character, Node> nodes = graph.nodes;
        Set<Character> visited = new HashSet<>();
        if(!nodes.containsKey(start) || !nodes.containsKey(end)) {
            return 0;
        }

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(start, 0));
        visited.add(start);

        while(!q.isEmpty()) {
            Pair currNode = q.poll();
            if (currNode.getKey() == end) {
                return currNode.getValue();
            }
            Map<Character, Integer> childNodes = nodes.get(currNode.getKey()).getEdges();
            if (childNodes != null && childNodes.size() > 0) {
                for(Character child: childNodes.keySet()) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        q.offer(new Pair(child, currNode.getValue() + 1));
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge('a', 'b'));
        edges.add(new Edge('b', 'c'));
        edges.add(new Edge('c', 'd'));
        edges.add(new Edge('a', 'e'));
        edges.add(new Edge('e', 'd'));
        edges.add(new Edge('e', 'b'));
        Graph graph = Graph.getGraphFromEdges(edges, false);
        Graph.displayGraph(graph);

        ShortestPath sp = new ShortestPath();
        System.out.println(sp.getShortestPath(graph, 'a', 'd'));
    }
}
