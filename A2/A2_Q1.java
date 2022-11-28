import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;



public class A2_Q1 {

	public static int[] game(String[][] board) {
		int numBalls = 0;
		ArrayList<int[]> ballsToTry = new ArrayList<>();
		boolean firstBallFound = false;
		int[] firstBall = new int[2];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].equals("o")) {
					numBalls++;

					int[] cur = {i, j};
					ballsToTry.add(cur);
				}
			}
		}
		int[] mins = {numBalls, 0};


		for(int[] b : ballsToTry){
			boolean first = true;
			ArrayList<int[]> bTTCopy = new ArrayList<>();

			for (int[] s : ballsToTry) {
				if (s[0] != b[0] && s[1] != b[1]) {
					bTTCopy.add(s);
				}
			}
			int[] temp = tryMove(board, b[0], b[1], mins, numBalls, 0, bTTCopy, first);
			if (temp[0] < mins[0]) {
				mins[0] = temp[0];
				mins[1] = temp[1];
			} else if (temp[0] == mins[0]) {
				if (temp[1] < mins[1]) {
					mins[1] = temp[1];
				}
			}
		}

		return mins;
	}

	public static int[] tryMove(String[][] board, int x, int y, int[] mins, int curNumBalls, int curNumMoves, ArrayList<int[]> ballsToTry, boolean first) {

		// base cases: ball can't move or only one ball remaining
		if (curNumBalls == 1) {
			int[] temp = {curNumBalls, curNumMoves};

			return temp;
		}

		boolean noBallCanMove = true;
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if(board[i][j].equals("o")) {

					if (((i + 2) < 5 && board[i + 1][j].equals("o") && board[i + 2][j].equals(".")) || ((i - 2) >= 0 && board[i - 2][j].equals(".") && board[i - 1][j].equals("o")) || ((j - 2) >= 0 && board[i][j - 2].equals(".") && board[i][j - 1].equals("o")) || ((j + 2) < 9 && board[i][j + 2].equals(".") && board[i][j + 1].equals("o"))) {//!((x + 2) < 5 && board[x + 1][y].equals("o") && board[x + 2][y].equals(".")) && !((x - 2) >= 0 && board[x - 2][y].equals(".") && board[x - 1][y].equals("o")) && !((y - 2) >= 0 && x < 5 && board[x][y - 2].equals(".") && board[x][y - 1].equals("o")) && !((y + 2) < 9 && x < 5 && board[x][y + 2].equals(".") && board[x][y + 1].equals("o"))) {
						noBallCanMove = false;
					}
				}
			}
		}
		if (!((x + 2) < 5 && board[x + 1][y].equals("o") && board[x + 2][y].equals(".")) && !((x - 2) >= 0 && board[x - 2][y].equals(".") && board[x - 1][y].equals("o")) && !((y - 2) >= 0 && board[x][y - 2].equals(".") && board[x][y - 1].equals("o")) && !((y + 2) < 9 && board[x][y + 2].equals(".") && board[x][y + 1].equals("o"))) {//!((x + 2) < 5 && board[x + 1][y].equals("o") && board[x + 2][y].equals(".")) && !((x - 2) >= 0 && board[x - 2][y].equals(".") && board[x - 1][y].equals("o")) && !((y - 2) >= 0 && x < 5 && board[x][y - 2].equals(".") && board[x][y - 1].equals("o")) && !((y + 2) < 9 && x < 5 && board[x][y + 2].equals(".") && board[x][y + 1].equals("o"))) {
			if(first){
				int[] temp = {curNumBalls, curNumMoves};
				return temp;
			}
		}
		if(noBallCanMove){
			int[] temp = {curNumBalls, curNumMoves};
			return temp;
		}


		if ((x + 2) < 5 && board[x + 1][y].equals("o") && board[x + 2][y].equals(".")) { // jump ball below

			String[][] newBoard = copyBoard(board);
			newBoard[x][y] = ".";
			newBoard[x + 2][y] = "o";
			newBoard[x + 1][y] = ".";

			int numBalls = curNumBalls;//--;
			numBalls--;
			int numMoves = curNumMoves;
			numMoves++;

			ArrayList<int[]> bTTCopy = new ArrayList<>();

			for(int[] b : ballsToTry){
				if((b[0] != x && b[1] != y) && (newBoard[b[0]][b[1]].equals("o"))){
					bTTCopy.add(b);
				}
			}

			int[] temp = tryMove(newBoard, x + 2, y, mins, numBalls, numMoves, bTTCopy, false);//, balls);
			if (temp[0] < mins[0]) {
				mins[0] = temp[0];
				mins[1] = temp[1];
			} else if (temp[0] == mins[0]) {
				if (temp[1] < mins[1]) {
					mins[1] = temp[1];
				}
			}
		}

		if ((x - 2) >= 0 && board[x - 2][y].equals(".") && board[x - 1][y].equals("o")) {
			String[][] newBoard = copyBoard(board);
			newBoard[x][y] = ".";
			newBoard[x - 2][y] = "o";
			newBoard[x - 1][y] = ".";
			int numBalls = curNumBalls;//--;
			numBalls--;
			int numMoves = curNumMoves;
			numMoves++;

			ArrayList<int[]> bTTCopy = new ArrayList<>();

			for(int[] b : ballsToTry){
				if((b[0] != x && b[1] != y) && (newBoard[b[0]][b[1]].equals("o"))) {
					bTTCopy.add(b);
				}
			}

			int[] temp = tryMove(newBoard, x - 2, y, mins, numBalls, numMoves, bTTCopy, false);//, balls);
			if (temp[0] < mins[0]) {
				mins[0] = temp[0];
				mins[1] = temp[1];
			} else if (temp[0] == mins[0]) {
				if (temp[1] < mins[1]) {
					mins[1] = temp[1];
				}
			}
		}

		if ((y-2)>= 0 && board[x][y - 2].equals(".") && board[x][y - 1].equals("o")) { // jump ball to left

			String[][] newBoard = copyBoard(board);
			newBoard[x][y] = ".";
			newBoard[x][y - 2] = "o";
			newBoard[x][y - 1] = ".";
			int numBalls = curNumBalls;
			numBalls--;
			int numMoves = curNumMoves;
			numMoves++;

			ArrayList<int[]> bTTCopy = new ArrayList<>();

			for(int[] b : ballsToTry){
				if((b[0] != x && b[1] != y) && (newBoard[b[0]][b[1]].equals("o"))){
					bTTCopy.add(b);
				}
			}
			int[] temp = tryMove(newBoard, x, y-2, mins, numBalls, numMoves, bTTCopy, false);//, balls);
			if (temp[0] < mins[0]) {
				mins[0] = temp[0];
				mins[1] = temp[1];
			} else if (temp[0] == mins[0]) {
				if (temp[1] < mins[1]) {
					mins[1] = temp[1];
				}
			}
		}

		if ((y+2)<9 && board[x][y + 2].equals(".") && board[x][y + 1].equals("o")) { // jump ball to right

			String[][] newBoard = copyBoard(board);
			newBoard[x][y] = ".";
			newBoard[x][y + 2] = "o";
			newBoard[x][y + 1] = ".";

			int numBalls = curNumBalls;
			numBalls--;
			int numMoves = curNumMoves;
			numMoves++;

			ArrayList<int[]> bTTCopy = new ArrayList<>();

			for(int[] b : ballsToTry){
				if((b[0] != x && b[1] != y) && (newBoard[b[0]][b[1]].equals("o"))) {
					bTTCopy.add(b);
				}
			}

			int[] temp = tryMove(newBoard, x, y+2, mins, numBalls, numMoves, bTTCopy, false);//, balls);
			if (temp[0] < mins[0]) {
				mins[0] = temp[0];
				mins[1] = temp[1];
			} else if (temp[0] == mins[0]) {
				if (temp[1] < mins[1]) {
					mins[1] = temp[1];
				}
			}
		}








		noBallCanMove = true;
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if(board[i][j].equals("o")) {

					if (((i + 2) < 5 && board[i + 1][j].equals("o") && board[i + 2][j].equals(".")) || ((i - 2) >= 0 && board[i - 2][j].equals(".") && board[i - 1][j].equals("o")) || ((j - 2) >= 0 && board[i][j - 2].equals(".") && board[i][j - 1].equals("o")) || ((j + 2) < 9 && board[i][j + 2].equals(".") && board[i][j + 1].equals("o"))) {//!((x + 2) < 5 && board[x + 1][y].equals("o") && board[x + 2][y].equals(".")) && !((x - 2) >= 0 && board[x - 2][y].equals(".") && board[x - 1][y].equals("o")) && !((y - 2) >= 0 && x < 5 && board[x][y - 2].equals(".") && board[x][y - 1].equals("o")) && !((y + 2) < 9 && x < 5 && board[x][y + 2].equals(".") && board[x][y + 1].equals("o"))) {
						noBallCanMove = false;
					}
				}
			}
		}

		if(noBallCanMove){
			int[] temp = {curNumBalls, curNumMoves};
			return temp;
		}

		if(first == true){
			int[] temp = {curNumBalls, curNumMoves};
			return temp;
		}
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if(board[i][j].equals("o")) {


					if( i!=x || j!=y){
						if ((((i + 2) < 5 && board[i + 1][j].equals("o") && board[i + 2][j].equals(".")) || ((i - 2) >= 0 && board[i - 2][j].equals(".") && board[i - 1][j].equals("o")) || ((j - 2) >= 0 && board[i][j - 2].equals(".") && board[i][j - 1].equals("o")) || ((j + 2) < 9 && board[i][j + 2].equals(".") && board[i][j + 1].equals("o")))) {//&& (i!=x || j!=y)) {//!((x + 2) < 5 && board[x + 1][y].equals("o") && board[x + 2][y].equals(".")) && !((x - 2) >= 0 && board[x - 2][y].equals(".") && board[x - 1][y].equals("o")) && !((y - 2) >= 0 && x < 5 && board[x][y - 2].equals(".") && board[x][y - 1].equals("o")) && !((y + 2) < 9 && x < 5 && board[x][y + 2].equals(".") && board[x][y + 1].equals("o"))) {
							String[][] newBoard = copyBoard(board);
							int[] temp = tryMove(newBoard, i, j, mins, curNumBalls, curNumMoves, ballsToTry, true);

							if (temp[0] < mins[0]) {
								mins[0] = temp[0];
								mins[1] = temp[1];
							} else if (temp[0] == mins[0] ){
								if (temp[1] < mins[1]) {
									mins[1] = temp[1];
								}
							}
						}
					}
				}
			}
		}


		int[] temp = {curNumBalls, curNumMoves};
		return temp;
	}

	public static String[][] copyBoard(String[][] original){
		String[][] copy = new String[5][9];
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 9; j++){
				copy[i][j] = original[i][j];
			}
		}

		return copy;
	}




	final static int HEIGHT = 5;
	final static int WIDTH = 9;

	final static String TEST_FOLDER = "src/Open_Tests/Q1";
	public static void main(String[] args) {
		File f = new File(TEST_FOLDER);
		System.out.println(f);
		System.out.println(f.list());
		for (String name : f.list()) {
			if (name.endsWith(".in")) {
				try {
					File in = new File(TEST_FOLDER + "/" + name);
					File out = new File(TEST_FOLDER + "/" + name.substring(0, name.length()-3)+".ans");
					Scanner in_scan = new Scanner(in);
					Scanner out_scan = new Scanner(out);
					System.out.printf("Attempting file %s\n", name);
					int n = in_scan.nextInt();
					in_scan.nextLine();
					for (int cs = 0; cs < n; cs++) {
						String[][] board = new String[HEIGHT][WIDTH];
						for (int i = 0; i < HEIGHT; i++) {
							String line = in_scan.nextLine();
							for (int j = 0; j < WIDTH; j++) {
								board[i][j] = new String(new char[]{line.charAt(j)});
							}
						}
						int[] got = game(board);
						int expected_0 = out_scan.nextInt();
						int expected_1 = out_scan.nextInt();
						if (got[0] != expected_0 || got[1] != expected_1) {
							System.out.printf("Expected %d %d but got %d %d\n", expected_0, expected_1, got[0], got[1]);
						} else {
							System.out.printf("Passed test %d\n", cs);
						}
						try {
							in_scan.nextLine(); // Skip empty line
						} catch (NoSuchElementException e) {

						}
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