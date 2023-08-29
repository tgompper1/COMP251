public class Graph {
    // represents a connected, directed, weighted graph

    int aNumVertices;
    int aNumEdges;

    Edge edges[];

    // creates a graph with pNumVertices vertices and pNumEdges edges
    public Graph(int pNumVertices, int pNumEdges, Edge[] pEdges){
        aNumVertices = pNumVertices;
        aNumEdges = pNumEdges;
        edges = pEdges;
    }

}