

public class Test1 {
	public static void main(String[] args) {
		subset("abc", "");
	}

	public static void subset(String word, String temp) {
		if (word.length() >= 0) {
			System.out.println(temp);
			for (int i = 0; i < word.length(); i++)
				subset(word.substring(i + 1), temp + word.charAt(i));
		}
	}
}
