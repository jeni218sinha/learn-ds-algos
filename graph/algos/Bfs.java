package graph.algos;

import java.util.*;

public class Bfs {
    public List<Character> bfs(Graph graph, Character start) {
        List<Character> bfsNodes = new ArrayList<>();
        Set<Character> visited = new HashSet<>();
		if (graph == null || graph.nodes == null || graph.nodes.size() == 0) {
	        return bfsNodes;
        }

        Map<Character, Node> nodes = graph.nodes;
        if (!nodes.containsKey(start)) {
            return bfsNodes;
        }

        Queue<Character> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()) {
            int s = q.size();
            while (s > 0) {
                Character node = q.poll();
                bfsNodes.add(node);
                visited.add(node);

                Map<Character, Integer> child = nodes.get(node).getEdges();
                for(Map.Entry<Character, Integer> entry: child.entrySet()) {
                    if (!visited.contains(entry.getKey())) {
                        q.offer(entry.getKey());
                        visited.add(entry.getKey());
                    }
                }
                s--;
            }
        }
        return bfsNodes;
    }

    public void displayNodes(List<Character> nodes) {
        for (Character ch: nodes) {
            System.out.print(ch + " ");
        }
        System.out.println();
    }    
    public static void main(String[] args) {
        Graph graph = Graph.getSampleGraph();
        Graph.displayGraph(graph);
        Bfs bfs = new Bfs();
        List<Character> bfsNodes =  bfs.bfs(graph, 'a');
         bfs.displayNodes(bfsNodes);
    }
}
