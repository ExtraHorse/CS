
//*********************************************************************************************************************************
//Name:   
// Period:                                                 
// Date:
// What I learned:
// How I feel about this lab:
// What I wonder:
//***********************************************************************************************************************************

import java.io.*;
import java.util.*;

public class Pd5ZacharyWangDictionary {
	public static void main(String[] args) throws Exception {
		try {
			//System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
		} catch (Exception e) {
		}
		Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();
		Scanner infile = new Scanner(new File("C:\\Users\\Zachary Wang\\git\\CS\\GUI\\src\\advCSQ1\\Semester2Labs\\spanglish.txt"));
		while (infile.hasNext()) {
			add(eng2spn, infile.next(), infile.next());
		}
		infile.close();
		System.out.println("ENGLISH TO SPANISH");
		display(eng2spn);

		Map<String, Set<String>> spn2eng = reverse(eng2spn);
		System.out.println("SPANISH TO ENGLISH");
		display(spn2eng);
	}

	// Note: must explain how your method works
	// Postcondition: display the contents of a dictionary
	public static void display(Map<String, Set<String>> m) {
		for(String s : m.keySet()) {
			System.out.println(s + m.get(s));
		}
	}

	// Note: must explain how your method works
	// postcondition: insert a new pair to the English to Spanish Dictionary
	public static void add(Map<String, Set<String>> engToSpnDictionary, String word, String translation) {
		if(!engToSpnDictionary.containsKey(word))//if the word has not been added, create a set for it
			engToSpnDictionary.put(word, new HashSet<String>());
		engToSpnDictionary.get(word).add(translation);//add the translation to the associated word
	}

	// Note: must explain how your method works
	// postcondition: returns a Spanish to English dictionary
	public static Map<String, Set<String>> reverse(Map<String, Set<String>> engToSpnDictionary) {
		Map<String, Set<String>> spnToEngDictionary = new HashMap<String, Set<String>>();//create a new map to store
		for(String english : engToSpnDictionary.keySet()) 
			for(String spanish : engToSpnDictionary.get(english))//go through each spanish word in the dictionary
				add(spnToEngDictionary, spanish, english);//add it to the new map with the spanish as the key and the original english key as the entry
		return spnToEngDictionary;
	}
}
/********************
 * INPUT: holiday fiesta holiday vacaciones party fiesta celebration fiesta
 * <etc.>
 *********************************** 
 * OUTPUT: ENGLISH TO SPANISH banana [banana] celebration [fiesta] computer
 * [computadora, ordenador] double [doblar, doble, duplicar] father [padre]
 * feast [fiesta] good [bueno] hand [mano] hello [hola] holiday [fiesta,
 * vacaciones] party [fiesta] plaza [plaza] priest [padre] program [programa,
 * programar] sleep [dormir] son [hijo] sun [sol] vacation [vacaciones]
 * 
 * SPANISH TO ENGLISH banana [banana] bueno [good] computadora [computer] doblar
 * [double] doble [double] dormir [sleep] duplicar [double] fiesta [celebration,
 * feast, holiday, party] hijo [son] hola [hello] mano [hand] ordenador
 * [computer] padre [father, priest] plaza [plaza] programa [program] programar
 * [program] sol [sun] vacaciones [holiday, vacation]
 * 
 **********************/
