import java.lang.reflect.Array;
import java.util.*;

public class A1_Q3 {

	public static void main(String[] args) {
		String[] p = {"David no no no no nobody never", "Jennifer why ever not", "Parham no not never nobody", "Shishir no never know nobody", "Alvin why no nobody", "Alvin nobody never know why nobody", "David never no nobody", "Jennifer never never nobody no"};
		System.out.println(Discussion_Board(p));
		String[] d = {"user1 doubledutch double double dutch", "user2 dutch doubledutch doubledutch double", "user3 not double dutch doubledutch"};
		System.out.println(Discussion_Board(d));
		String[] myTest = {"Tess tesc tesb tesb", "madi tesa tesb tesc", "lily tesa tesb tesc tesd", "Tess tesa"};
		System.out.println(Discussion_Board(myTest));
		String[] alphaTest = {"a e c d b", "a b c s e", "a c d e"};
		System.out.println(Discussion_Board(alphaTest));
		String[] single = {"Tess c d f l t j q"};
		System.out.println(Discussion_Board(single));
	}

	public static ArrayList<String> Discussion_Board(String[] posts){
		ArrayList<String> usedByAllWords = new ArrayList<>(); // all words that are used by all users omitting duplicates
		HashMap<String, ArrayList<String>> myHashMap = new HashMap<String, ArrayList<String>>(30); // hashmap that maps words to the users that use them. key = user, holds an arraylist of the words used by the user
		ArrayList<String> uniqueWords = new ArrayList<>(); // list of all words omitting duplicates
		ArrayList<String> users = new ArrayList<>(); // list of users omitting duplicates
		ArrayList<String> usedByAllWordsSorted = new ArrayList<String>(); // final arraylist of words, sorted, to return
		ArrayList<Integer> frequency = new ArrayList<>(); // number of times each word is used, matching index with word in uniqueWords


		/**
		 * add users with words to hashmap
		 */
		for(int i = 0; i < posts.length; i++){
			String cur[] = posts[i].split(" ");
			if(myHashMap.get(cur[0]) == null){
				myHashMap.put(cur[0], new ArrayList<String>());
				users.add(cur[0]);
			}
			for(int j = 1; j < cur.length; j++){
				myHashMap.get(cur[0]).add(cur[j]);
				if(!uniqueWords.contains(cur[j])){
					uniqueWords.add(cur[j]);
				}
			}
		}


		/**
		 *  for each word (duplicates omitted) count how many users use the word, if it is all the
		 *  users, add to usedByAllWords
		 */
		for(String s : uniqueWords){
			int numUsedBy = 0;
			for(String u : users){
				if(myHashMap.get(u).contains(s)){
					numUsedBy++;
				}
			}
			if(numUsedBy == users.size()){
				usedByAllWords.add(s);
			}
		}

		/**
		 *  If no word is used by all, return an empty arraylist
		 */
		if(usedByAllWords.size() == 0){
			return new ArrayList<>();
		}

		Collections.sort(usedByAllWords); //sort alphabetically

		/**
		 *  For each word used by all users, count how many times the word is used,
		 *  add to matching index in frequency
		 */
		for(String s : usedByAllWords){
			int sCount = 0;
			for(String u : users) {
				ArrayList<String> list = myHashMap.get(u);
				for (int k = 0; k < list.size(); k++) {
					if (list.get(k).equals(s)) {
						sCount++;
					}
				}
			}
			frequency.add(sCount);
		}

		/**
		 * sort the words used by all based on number of times used
		 * if two words have the same frequency, compare and add in increasing alphabetical order
		 */
		usedByAllWordsSorted.add(usedByAllWords.get(0)); // add first word to sorted list
		for(int i = 1; i < usedByAllWords.size(); i++){
			int sCount = frequency.get(i);
			boolean added = false;
			for(int j = 0; j < usedByAllWordsSorted.size(); j++){
				int jCount = frequency.get(usedByAllWords.indexOf(usedByAllWordsSorted.get(j)));
				if(sCount > jCount){ // word used more often, needs to be added in front
					usedByAllWordsSorted.add(j, usedByAllWords.get(i));
					added = true;
					break;
				}
			}
			if(added == false){
				usedByAllWordsSorted.add(usedByAllWords.get(i));
			}
		}
		return usedByAllWordsSorted;
	}
}
