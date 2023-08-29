public class BellmanFord {
    // Detects negative weight cycles by finding the shortest distance from source to all the other vertices

    // takes in graph and integer representing source vertex
    public static void bellmanFord(Graph graph, int src){
        int numVertices = graph.aNumVertices;
        int numEdges = graph.aNumEdges;
        int distances[] = new int[numVertices];

        // initialize distance from src vertex to all other vertices to INF
        for(int i = 0; i < numVertices; i++){
            distances[i] = Integer.MAX_VALUE;
        }
        distances[src] = 0; // distance from src to itself is 0

        // relax edges
        for(int i = 1; i < numVertices; i++){
            for(int j = 0; j < numEdges; j++){
                int u = graph.edges[j].src;
                int v = graph.edges[j].dest;
                int weight = graph.edges[j].weight;
                if((distances[u] != Integer.MAX_VALUE) && (distances[u] + weight < distances[v])){
                    distances[v] = distances[u] + weight;
                }
            }
        }

        // check for negative weight cycles
        for(int j = 0; j < numEdges; j++){
            int u = graph.edges[j].src;
            int v = graph .edges[j].dest;
            int weight = graph.edges[j].weight;
            if((distances[u] != Integer.MAX_VALUE) && (distances[u] + weight < distances[v])){
                System.out.println("Negative weight cycle detected!");
                return;
            }
        }

        System.out.println("No negative weight cycles detected");
    }
}
