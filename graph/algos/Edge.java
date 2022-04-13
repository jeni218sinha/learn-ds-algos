package graph.algos;

public class Edge {
    Character from;
    Character to;
    Integer cost;
    public Edge(Character from, Character to, Integer cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Edge(Character from, Character to) {
        this.from = from;
        this.to = to;
    }
}
