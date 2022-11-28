import java.util.*;
import java.io.File; //testing
import java.io.FileNotFoundException; //testing

public class A2_Q2 {

	public static int weight(int[] plates) {
		int n = plates.length; // number of weights
		int W = findMaxPossible(plates); // max weight possible
		int[][] M = new int[n+1][W+1];

		for(int w = 0; w <= W; w++){
			M[0][W] = 0;
		}

		for(int i=1; i <= n; i++){
			for(int w = 1; w <= W; w++){
				if(plates[i-1] > w){
					M[i][w] = M[i-1][w];
				}
				else{
					M[i][w] = closestTo1000(M[i][w], plates[i-1]+M[i-1][w-plates[i-1]]);
				}
				if(M[i][w] == 1000){
					return 1000;
				}
			}
		}

		return M[n][W];
	}


	/**
	 * Returns integer closest to 1000, or equal to 1000
	 * 	if the two are equals, returns the max
	 */
	public static int closestTo1000(int a, int b){
		if(a == 1000) {
			return a;
		}
		else if(b == 1000){
		return b;
		}
		else if(Math.abs(1000-a) < Math.abs(1000-b)){
		return a;
		}
		else if(Math.abs(1000-a) > Math.abs(1000-b)){
		return b;
		}
		else{
		return Math.max(a,b);
		}
	}

	/**
	 *  returns the largest possible integer that could be an answer
	 *  takes the closest number to 1000 out of the weights to find the distance between that and 1000
	 *  returns 1000+distance
	 */
	public static int findMaxPossible(int[] pWeights){
		int max = pWeights[0];
		for(int i = 1; i < pWeights.length-1;i++){
			if(pWeights[i] > max){
				max = pWeights[i];
			}
		}
		int maxDist = 1000 - max;
		return 1000+maxDist;
	}


	/**
	 * testing
	 */

	final static String TEST_FOLDER = "src/Open_Tests/Q2";

	public static void main(String[] args) {
		File f = new File(TEST_FOLDER);
		System.out.println(f);
		System.out.println(f.list());
		for (String name : f.list()) {
			if (name.endsWith(".in")) {
				try {
					File in = new File(TEST_FOLDER + "/" + name);
					File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length() - 3) + ".ans");
					Scanner in_scan = new Scanner(in);
					Scanner out_scan = new Scanner(out);
					int n = in_scan.nextInt();
					int[] weights = new int[n];
					for (int i = 0; i < n; i++) {
						weights[i] = in_scan.nextInt();
					}
					int got = weight(weights);
					int expected = out_scan.nextInt();
					if (got != expected) {
						System.out.printf("Expected %d but got %d\n", expected, got);
					} else {
						System.out.printf("Passed test %s\n", name);
					}
					in_scan.close();
					out_scan.close();
				} catch (FileNotFoundException e) {
					System.out.println(e);
				}
			}
		}
	}

}