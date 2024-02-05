
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() < 2) {
			return "";
		} else 
			return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if (word2.length() == 0) {
			return word1.length();
		} else if (word1.length() == 0) {
			return word2.length();
		} else if (word1.charAt(0) == word2.charAt(0) || word1.charAt(0) == (word2.charAt(0) + 32) || word1.charAt(0) == (word2.charAt(0) - 32)){
		    return (levenshtein(tail(word1), tail(word2)));
		} 

		int levT1W2 = levenshtein(tail(word1), word2) + 1;
		int levW1T2 = levenshtein(word1, tail(word2)) + 1;
		int levT1T2 = levenshtein(tail(word1), tail(word2)) + 1;

		return (Math.min(levT1W2, Math.min(levW1T2, levT1T2)));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minIndex = -1;
		int minLevenshtein = threshold + 1;
		for (int i = 0; i < dictionary.length; i++) {
			if (levenshtein(word, dictionary[i]) < minLevenshtein) {
				minIndex = i;
				minLevenshtein = levenshtein(word, dictionary[i]);
			}
		}

		if (minIndex == -1) {
			return word;
		} else 
		   return dictionary[minIndex];

	}

}
