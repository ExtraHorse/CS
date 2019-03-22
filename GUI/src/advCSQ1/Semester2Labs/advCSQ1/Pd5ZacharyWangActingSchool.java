package advCSQ1;

//Name:  Zachary Wang
//Date: 3/22/19
//What I learned: the keySet method of the maps are very powerful, they provide the method by which a map can be "traversed", since normal
//					traversal doesn't make since without an index or an obvious order
//How I feel about this lab: I can't help but feel that I missed an easier solution that did not require the creation of a secondary map,
// 					but I was not able to come up with it. 
//I am wondering: Is there a way to change the type of the values of a hash map that has already been created?
import java.util.*;

public class Pd5ZacharyWangActingSchool {
	public static void main(String[] args) {
		Map<String, String> sGrades = new HashMap<String, String>(); // HashMap

		sGrades.put("Jack Nicholson", "A-");
		sGrades.put("Humphrey Bogart", "A+");
		sGrades.put("Audrey Hepburn", "A");
		sGrades.put("Meryl Streep", "A-");
		sGrades.put("Jimmy Stewart", "A");

		// display initial data
		for(String key : sGrades.keySet()) 
			System.out.println(key +  " (" + sGrades.get(key) + ")");
		
		// reverse the map
		HashMap<String, ArrayList<String>> reverseGrades = new HashMap<String, ArrayList<String>>();//create the reversed list with arraylists
		for(String name : sGrades.keySet()) {
			String grade = sGrades.get(name);
			if(!reverseGrades.containsKey(grade))
				reverseGrades.put(grade, new ArrayList<String>());//add the arraylist if that grade was not added before
			reverseGrades.get(grade).add(name);//add the name
		}

		// display the reversed map
		for(String key : reverseGrades.keySet()) 
			System.out.println(key + ":" + reverseGrades.get(key));
	}
}

/**********************
 * Audrey Hepburn (A) Humphrey Bogart (A+) Jack Nicholson (A-) Jimmy Stewart (A)
 * Meryl Streep (A-)
 * 
 * A: [Audrey Hepburn, Jimmy Stewart] A+: [Humphrey Bogart] A-: [Jack Nicholson,
 * Meryl Streep]
 * 
 * 
 * Example Output:
Audrey Hepburn (A)
Meryl Streep (A-)
Jimmy Stewart (A)
Humphrey Bogart (A+)
Jack Nicholson (A-)
A:[Audrey Hepburn, Jimmy Stewart]
A+:[Humphrey Bogart]
A-:[Meryl Streep, Jack Nicholson]

 *********************/
