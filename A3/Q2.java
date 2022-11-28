import java.util.*;


public class Q2 {

	public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {
		int numTransportations = 0;
		int[] multipleNumTransportations = {0,0};
		int numNodes = location.length;
		int numSources = 0;
		ArrayList<Integer> Q = new ArrayList<>();
		ArrayList<Integer> sorted = new ArrayList<>();

		// check for multiple sources
		int[] inDegrees = new int[numNodes];
		for(int i = 0; i < numNodes; i++){
			inDegrees[i] = 0;
		}
		for(int i = 0; i < graph.size(); i++){
			ArrayList<Integer> edges = graph.get(i);
			for(int j = 0; j < edges.size(); j++){
				inDegrees[edges.get(j)]++;
			}
		}
		ArrayList<Integer> sourcesAsgard = new ArrayList<>();
		ArrayList<Integer> sourcesEarth = new ArrayList<>();
		for(int i = 0; i < numNodes; i++){
			if(inDegrees[i] == 0){
				numSources++;
				if(location[i] == 1) {
					sourcesEarth.add(i);
				}
				else{
					sourcesAsgard.add(i);
				}
			}
		}

		if(numSources > 1){
			System.out.println("multiple source");
			//try earth first
			int[] inDegreesEarth = inDegrees.clone();
			for(int i = 0; i < sourcesEarth.size(); i++){
				Q.add(sourcesEarth.get(i));
			}
			for(int i = 0; i < sourcesAsgard.size(); i++){
				Q.add(sourcesAsgard.get(i));
			}
			while(Q.size() != 0){
				int vertex = Q.remove(0);
				sorted.add(vertex);
				ArrayList<Integer> neighbors = graph.get(vertex);
				for(int i : neighbors){
					inDegreesEarth[i] --;
					if(inDegreesEarth[i]==0){
						if(location[i] == location[vertex]) {
							Q.add(0, i);
						}
						else{
							Q.add(i);
						}
					}
				}
			}
			int last = location[sorted.get(0)];

			for(int i = 1; i < sorted.size(); i++){
				if(location[sorted.get(i)] != last){
					multipleNumTransportations[0]++;
					last = location[sorted.get(i)];
				}
			}

			// try asgard first
			int[] inDegreesAsgard = inDegrees.clone();
			sorted = new ArrayList<>();
			for(int i = 0; i < sourcesAsgard.size(); i++){
				Q.add(sourcesAsgard.get(i));
			}
			for(int i = 0; i < sourcesEarth.size(); i++){
				Q.add(sourcesEarth.get(i));
			}

			while(Q.size() != 0){
				int vertex = Q.remove(0);
				sorted.add(vertex);
				ArrayList<Integer> neighbors = graph.get(vertex);
				for(int i : neighbors){
					inDegreesAsgard[i] --;
					if(inDegreesAsgard[i]==0){
						if(location[i] == location[vertex]) {
							Q.add(0, i);
						}
						else{
							Q.add(i);
						}
					}
				}
			}

			last = location[sorted.get(0)];
			for(int i = 1; i < sorted.size(); i++){
				if(location[sorted.get(i)] != last){
					multipleNumTransportations[1]++;
					last = location[sorted.get(i)];
				}
			}

			return Math.min(multipleNumTransportations[0], multipleNumTransportations[1]);
		}

		//single source
		for(int i = 0; i < numNodes; i++){
			if(inDegrees[i] == 0){
				Q.add(i);
			}
		}

		while(Q.size() != 0){
			int vertex = Q.remove(0);
			sorted.add(vertex);
			ArrayList<Integer> neighbors = graph.get(vertex);
			for(int i : neighbors){
				inDegrees[i] --;
				if(inDegrees[i]==0){
					if(location[i] == location[vertex]) {
						Q.add(0, i);
					}
					else{
						Q.add(i);
					}
				}
			}
		}

		int last = location[sorted.get(0)];

		for(int i = 1; i < sorted.size(); i++){
			if(location[sorted.get(i)] != last){
				numTransportations++;
				last = location[sorted.get(i)];
			}
		}

		return numTransportations;
	}




	public static void main(String[] args) {
		/*int[] location = {1,2,1,2,1};
		Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<>();
		ArrayList<Integer> zero  = new ArrayList<>();
		zero.add(1);
		zero.add(2);

		ArrayList<Integer> one  = new ArrayList<>();
		one.add(3);
		one.add(4);

		ArrayList<Integer> two  = new ArrayList<>();
		two.add(3);
		two.add(4);

		ArrayList<Integer> three  = new ArrayList<>();
		ArrayList<Integer> four  = new ArrayList<>();

		graph.put(0, zero);
		graph.put(1, one);
		graph.put(2, two);
		graph.put(3, three);
		graph.put(4, four);
		System.out.println(rings(graph, location));*/

		/*int[] location = {1,2,1};
		Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<>();
		ArrayList<Integer> zero1  = new ArrayList<>();
		ArrayList<Integer> one1  = new ArrayList<>();
		one1.add(2);
		ArrayList<Integer> two1 = new ArrayList<>();

		graph.put(0, zero1);
		graph.put(1, one1);
		graph.put(2, two1);
		System.out.println(rings(graph, location));*/

		int[] location2 = {2,1,2,2,1};
		Hashtable<Integer, ArrayList<Integer>> graph1 = new Hashtable<>();
		ArrayList<Integer> zero1  = new ArrayList<>();
		zero1.add(3);
		zero1.add(1);
		ArrayList<Integer> one1  = new ArrayList<>();
		one1.add(3);
		ArrayList<Integer> two1  = new ArrayList<>();
		two1.add(1);
		two1.add(4);
		two1.add(0);
		two1.add(3);
		ArrayList<Integer> three1  = new ArrayList<>();
		ArrayList<Integer> four1  = new ArrayList<>();
		four1.add(3);
		four1.add(1);
		four1.add(0);
		graph1.put(0, zero1);
		graph1.put(1, one1);
		graph1.put(2, two1);
		graph1.put(3, three1);
		graph1.put(4, four1);
		System.out.println(rings(graph1,location2));
	}


}
