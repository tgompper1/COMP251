public class Main {
    public static void main(String[] args) {
        // general case: a graph with multiple negative cycle (2)
        Edge e1 = new Edge(0, 1, -1);
        Edge e2 = new Edge(0, 2, 4);
        Edge e3 = new Edge(1, 2, 3);
        Edge e4 = new Edge(1, 3, 2);
        Edge e5 = new Edge(1, 4, 2);
        Edge e6 = new Edge(3, 2, 5);
        Edge e7 = new Edge(3, 1, 1);
        Edge e8 = new Edge(4, 3, -6);
        Edge e9 = new Edge(2, 0, -3);
        Edge[] es = new Edge[9];
        es[0] = e1;
        es[1] = e2;
        es[2] = e3;
        es[3] = e4;
        es[4] = e5;
        es[5] = e6;
        es[6] = e7;
        es[7] = e8;
        es[8] = e9;

        Graph graph1 = new Graph(5, 9, es);
        //BellmanFord.bellmanFord(graph1,0);

        // edges case 1: a graph with a negative self loop
        e1 = new Edge(0, 1, -1);
        e2 = new Edge(0, 2, 4);
        e3 = new Edge(1, 2, 3);
        e4 = new Edge(1, 3, 2);
        e5 = new Edge(1, 4, 2);
        e6 = new Edge(3, 2, 5);
        e7 = new Edge(3, 1, 1);
        e8 = new Edge(4, 3, -1);
        e9 = new Edge(2, 2, -1);
        es[0] = e1;
        es[1] = e2;
        es[2] = e3;
        es[3] = e4;
        es[4] = e5;
        es[5] = e6;
        es[6] = e7;
        es[7] = e8;
        es[8] = e9;
        Graph graph2 = new Graph(5, 9, es);
        //BellmanFord.bellmanFord(graph2, 0);

        // edge case 2: a graph with no negative cycles
        e1 = new Edge(0, 1, -1);
        e2 = new Edge(0, 2, 4);
        e3 = new Edge(1, 2, 3);
        e4 = new Edge(1, 3, 2);
        e5 = new Edge(1, 4, 2);
        e6 = new Edge(3, 2, 5);
        e7 = new Edge(3, 1, 1);
        e8 = new Edge(4, 3, -1);
        es = new Edge[8];
        es[0] = e1; es[1] = e2; es[2] = e3; es[3] = e4;
        es[4] = e5; es[5] = e6; es[6] = e7; es[7] = e8;
        Graph graph3 = new Graph(5, 8, es);
        BellmanFord.bellmanFord(graph3, 0);
    }
}
