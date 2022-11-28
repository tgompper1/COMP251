import java.util.*;


public class Q1 {

	public static int find_exit(String[][][] jail) {
		int level = jail.length;
		int rows = jail[0].length;
		int columns = jail[0][0].length;

		int[] source = new int[3];
		int[] exit = new int[3];

		//color array
		String[][][] colors = new String[level][rows][columns];
		//dist array
		int[][][] distances = new int[level][rows][columns];
		//empty queue
		Queue<int[]> Q = new LinkedList<int[]>();

		//color and dist = inf for each vertex, not the source
		//source color = gray, d = 0
		for(int l = 0; l < level; l++){
			for(int r = 0; r < rows; r++){
				for(int c = 0; c < columns; c++){
					if(jail[l][r][c].equals("S")){
						source[0] = l; source[1] = r; source[2] = c;
						colors[l][r][c] = "GRAY";
						distances[l][r][c] = 0;
					} else {
						colors[l][r][c] = "WHITE";
						distances[l][r][c] = Integer.MAX_VALUE;
						if (jail[l][r][c].equals("E")) {
							exit[0] = l; exit[1] = r; exit[2] = c;
						}
					}
				}
			}
		}

		//enqueue source
		Q.add(source);

		//while q not empty
		while(!Q.isEmpty()){
			//dequeue u
			int[] u = Q.remove();

			//for each connected vertex v to u
				// color v gray
				// v.d = u.d+1
				//if v is E
					// return v.d
				//enqueue v

			if(jail[u[0]][u[1]][u[2]].equals(".") || jail[u[0]][u[1]][u[2]].equals("S")){
				if((u[0]+1 < level) && (jail[u[0]+1][u[1]][u[2]].equals(".") || jail[u[0]+1][u[1]][u[2]].equals("E"))
						&& colors[u[0]+1][u[1]][u[2]].equals("WHITE")){ //below
					//System.out.println("case1 below");
					colors[u[0]+1][u[1]][u[2]]=("GRAY");
					distances[u[0]+1][u[1]][u[2]] = distances[u[0]][u[1]][u[2]] +1;
					if(jail[u[0]+1][u[1]][u[2]].equals("E")){
						return distances[u[0]+1][u[1]][u[2]];
					}
					Q.add(new int[] {u[0]+1, u[1], u[2]});
				}if((u[0]-1 >= 0) && (jail[u[0]-1][u[1]][u[2]].equals(".") || jail[u[0]-1][u[1]][u[2]].equals("E"))
						&& colors[u[0]-1][u[1]][u[2]].equals("WHITE")){ //above
					colors[u[0]-1][u[1]][u[2]]=("GRAY");
					distances[u[0]-1][u[1]][u[2]] = distances[u[0]][u[1]][u[2]] +1;
					if(jail[u[0]-1][u[1]][u[2]].equals("E")){
						return distances[u[0]-1][u[1]][u[2]];
					}
					Q.add(new int[] {u[0]-1, u[1], u[2]});
				}
				if((u[1]+1 < rows) && (jail[u[0]][u[1]+1][u[2]].equals(".") || jail[u[0]][u[1]+1][u[2]].equals("E"))
						&& colors[u[0]][u[1]+1][u[2]].equals("WHITE")){ //down
					colors[u[0]][u[1]+1][u[2]]=("GRAY");
					distances[u[0]][u[1]+1][u[2]] = distances[u[0]][u[1]][u[2]] +1;
					if(jail[u[0]][u[1]+1][u[2]].equals("E")){
						return distances[u[0]][u[1]+1][u[2]];
					}
					Q.add(new int[] {u[0], u[1]+1, u[2]});
				}
				if((u[1]-1 >= 0) && (jail[u[0]][u[1]-1][u[2]].equals(".") || jail[u[0]][u[1]-1][u[2]].equals("E"))
						&& colors[u[0]][u[1]-1][u[2]].equals("WHITE")){ //up
					colors[u[0]][u[1]-1][u[2]]=("GRAY");
					distances[u[0]][u[1]-1][u[2]] = distances[u[0]][u[1]][u[2]] +1;
					if(jail[u[0]][u[1]-1][u[2]].equals("E")){
						return distances[u[0]][u[1]-1][u[2]];
					}
					Q.add(new int[] {u[0], u[1]-1, u[2]});
				}if((u[2]+1 < columns) && (jail[u[0]][u[1]][u[2]+1].equals(".") || jail[u[0]][u[1]][u[2]+1].equals("E"))
						&& colors[u[0]][u[1]][u[2]+1].equals("WHITE")){ //right
					colors[u[0]][u[1]][u[2]+1] = ("GRAY");
					distances[u[0]][u[1]][u[2]+1] = distances[u[0]][u[1]][u[2]] +1;
					if(jail[u[0]][u[1]][u[2]+1].equals("E")){
						return distances[u[0]][u[1]][u[2]+1];
					}
					Q.add(new int[] {u[0], u[1], u[2]+1});
				}
				if((u[2]-1 >= 0) && (jail[u[0]][u[1]][u[2]-1].equals(".") || jail[u[0]][u[1]][u[2]-1].equals("E"))
						&& colors[u[0]][u[1]][u[2]-1].equals("WHITE")){ //left
					colors[u[0]][u[1]][u[2]-1]=("GRAY");
					distances[u[0]][u[1]][u[2]-1] = distances[u[0]][u[1]][u[2]] +1;
					if(jail[u[0]][u[1]][u[2]-1].equals("E")){
						return distances[u[0]][u[1]][u[2]-1];
					}
					Q.add(new int[] {u[0], u[1], u[2]-1});
				}
				// color u black
				colors[u[0]][u[1]][u[2]] = "BLACK";
			}
		}
		// no way out
		return -1;
	}

	public static void main(String[] args) {
		String[][][] j = new String[3][4][5];
		j[0][0][0] = "S"; j[0][0][1] = "."; j[0][0][2] = "."; j[0][0][3]="."; j[0][0][4] = ".";
		j[0][1][0] = "."; j[0][1][1] = "#"; j[0][1][2] = "#"; j[0][1][3]="#"; j[0][1][4] = ".";
		j[0][2][0] = "."; j[0][2][1] = "#"; j[0][2][2] = "#"; j[0][2][3]="."; j[0][2][4] = ".";
		j[0][3][0] = "#"; j[0][3][1] = "#"; j[0][3][2] = "#"; j[0][3][3]="."; j[0][3][4] = "#";

		j[1][0][0] = "#"; j[1][0][1] = "#"; j[1][0][2] = "#"; j[1][0][3]="#"; j[1][0][4] = "#";
		j[1][1][0] = "#"; j[1][1][1] = "#"; j[1][1][2] = "#"; j[1][1][3]="#"; j[1][1][4] = "#";
		j[1][2][0] = "#"; j[1][2][1] = "#"; j[1][2][2] = "."; j[1][2][3]="#"; j[1][2][4] = "#";
		j[1][3][0] = "#"; j[1][3][1] = "#"; j[1][3][2] = "."; j[1][3][3]="."; j[1][3][4] = ".";

		j[2][0][0] = "#"; j[2][0][1] = "#"; j[2][0][2] = "#"; j[2][0][3]="#"; j[2][0][4] = "#";
		j[2][1][0] = "#"; j[2][1][1] = "#"; j[2][1][2] = "#"; j[2][1][3]="#"; j[2][1][4] = "#";
		j[2][2][0] = "#"; j[2][2][1] = "."; j[2][2][2] = "#"; j[2][2][3]="#"; j[2][2][4] = "#";
		j[2][3][0] = "#"; j[2][3][1] = "#"; j[2][3][2] = "#"; j[2][3][3]="#"; j[2][3][4] = "E";

		System.out.println(find_exit(j)); //should be 11

		String[][][] j2 = new String[3][3][3];
		j2[0][0][0] = "#"; j2[0][0][1]  = "#"; j2[0][0][2] = "#";
		j2[0][1][0] = "#"; j2[0][1][1]  = "#"; j2[0][1][2] = "#";
		j2[0][2][0] = "#"; j2[0][2][1]  = "#"; j2[0][2][2] = "#";

		j2[1][0][0] = "#"; j2[1][0][1]  = "E"; j2[1][0][2] = "#";
		j2[1][1][0] = "#"; j2[1][1][1]  = "S"; j2[1][1][2] = "#";
		j2[1][2][0] = "#"; j2[1][2][1]  = "#"; j2[1][2][2] = "#";

		j2[2][0][0] = "#"; j2[2][0][1]  = "#"; j2[2][0][2] = "#";
		j2[2][1][0] = "#"; j2[2][1][1]  = "#"; j2[2][1][2] = "#";
		j2[2][2][0] = "#"; j2[2][2][1]  = "#"; j2[2][2][2] = "#";

		System.out.println(find_exit(j2));
	}

}
