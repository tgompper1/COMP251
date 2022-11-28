import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){

		ArrayList<Integer> path = new ArrayList<Integer>();
		int numNodes = graph.getNbNodes();

		String[] nodeColors = new String[numNodes];
		int[] previous = new int[numNodes];
		int[] discoveryTime = new int[numNodes];
		int[] finishingTime = new int[numNodes];

		//dfs set up
		for(int i = 0; i < numNodes; i++){
			nodeColors[i] = "WHITE";
			previous[i] = -1;
		}
		int time = 0;
		for(int i = 0; i < numNodes; i++){
			if(nodeColors[i].equals("WHITE")){
				time = DFSVisit(previous, nodeColors, i, graph, time, discoveryTime, finishingTime);
			}
		}

		int node = destination;
		while(node != -1){
			path.add(0, node);
			node = previous[node];
		}

		if(path.get(0) != source){
			ArrayList<Integer> fail = new ArrayList<>();
			return fail;
		}

		return path;
	}

	public static int DFSVisit(int[] previous, String[] colors, int node, WGraph g, int time, int[] d, int[] f){
		colors[node] = "GRAY";
		time = time +1;
		d[node] = time;
		ArrayList<Edge> edges = g.getEdges();
		for(Edge e : edges){
			if(e.weight == 0){//
				continue;//
			}//
			if(e.nodes[0] == node){
				int v = e.nodes[1];
				if(colors[v].equals("WHITE")){
					previous[v] = node;
					DFSVisit(previous, colors, v, g, time, d, f);
				}
			}
		}
		colors[node] = "BLACK";
		time = time+1;
		f[node] = time;
		return time;
	}


	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		int source = graph.getSource();;
		int destination = graph.getDestination();

		// set residualGraph = graph
		WGraph residualGraph = new WGraph(graph);
		ArrayList<Edge> residualEdgesPrep = residualGraph.getEdges();
		ArrayList<Edge> reverseEdges = new ArrayList<>();
		for(Edge e : residualEdgesPrep) {
			Edge reverse = new Edge(e.nodes[1], e.nodes[0], 0);
			reverseEdges.add(reverse);
		}
		for(Edge r : reverseEdges){
			residualGraph.addEdge(r);
		}

		//while there is an s-t path in the residualgraph
		while(pathDFS(source, destination, residualGraph).size() != 0){
			// let p be the simple s-t path in residualgraph
			ArrayList<Integer> path = pathDFS(source, destination, residualGraph);
			// get edges sorted from residual graph
			ArrayList<Edge> residualEdges = residualGraph.getEdges();
			// find edges on path
			ArrayList<Edge> edgesOnPath = new ArrayList<>();
			for(Edge e : residualEdges){
				for(int i = 0; i<path.size()-1; i++){
					if(e.nodes[0] == path.get(i) && e.nodes[1] == path.get(i+1)){
						edgesOnPath.add(e);
					}
				}
			}
			// find min weight
			int minEdgeWeightOnPath = Integer.MAX_VALUE;
			for(Edge e : edgesOnPath){
				if(e.weight < minEdgeWeightOnPath) {
					minEdgeWeightOnPath = e.weight;
				}
			}
			maxFlow += minEdgeWeightOnPath;
			//change forward edge flows
			for(Edge e : edgesOnPath) {
				int cap = e.weight + residualGraph.getEdge(e.nodes[1], e.nodes[0]).weight;
				residualGraph.setEdge(e.nodes[0], e.nodes[1], e.weight-minEdgeWeightOnPath);
				residualGraph.setEdge(e.nodes[1], e.nodes[0], cap - e.weight);
			}
		}


		ArrayList<Edge> edges = graph.getEdges();

		for(Edge e : edges){
			e.weight = residualGraph.getEdge(e.nodes[1], e.nodes[0]).weight;
		}

		answer += maxFlow + "\n" + graph.toString();
		return answer;
	}

	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
	 }
}

