import java.util.regex.Pattern;

public class WordCruncher {
	// string variable
	private String word;
	// Vowel array
	private char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

	// A default constructor that sets the instance variable 'word' to the string "default".
	public WordCruncher(String word) {
		super();
		// Matches vowel letters by regular expressions
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(word).matches()) {
			this.word = word;
		} else {
			this.word = "default";
		}
	}

	// A parameterized constructor that accepts one String object as a parameter 
	// and stores it in the instance variable. 
	public WordCruncher() {
		super();
		this.word = "default";
	}

	// A method String toString() that returns the instance variable.
	@Override
	public String toString() {
		return word;
	}

	// A method int numLetters() that returns the number of letters in the instance variable.
	public int numLetters() {
		return word.length();
	}

	// A method int numVowels() that returns the number of vowels in the instance variable.
	public int numVowels() {
		int count = 0;
		// Traverse a word to find vowel letters
		for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < vowels.length; j++) {
				if (word.charAt(i) == vowels[j]) {
					count++;
				}
			}
		}
		return count;
	}
	// method boolean beginsWithVowel() that returns true if the first letter of the instance variable is a vowel, and false otherwise.
	public boolean beginsWithVowel() {
		boolean flag = false;
		// Traverse a word to find consonant letters
		for (int j = 0; j < vowels.length; j++) {
			if (word.charAt(0) == vowels[j]) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	// A method String toPigLatin() that returns a String containing the 'pig latin' version of the instance variable. 
	// The rules for translating a word to pig latin are:
	public String toPigLatin() {
		StringBuffer sb = new StringBuffer();
		if (!beginsWithVowel()) {
			sb.append(word.substring(1, word.length())).append(word.substring(0, 1)).append("ay");
		} else {
			sb.append("way");
		}
		return sb.toString();
	}

	// A method String toGibberish() that returns a String containing the 'gibberish' version of the instance variable. 
	// The rules for translating a word into gibberish are:
	public String toGibberish() {
		// Splice string by StringBuffer
		StringBuffer sb = new StringBuffer();
		if (!beginsWithVowel()) {
			sb.append(word.substring(0, 1)).append("ithag").append(word.substring(1));
		} else {
			sb.append("ithag").append(word);
		}
		return sb.toString();
	}

	// A method String reverse() that returns a String that contains the characters of the instance variable, but in reverse.
	public String reverse() {
		StringBuffer sb = new StringBuffer(word);
		return sb.reverse().toString();
	}

	// A method int numCharOccurrences(char ch) that returns a count of the number of times the parameter char ch occurs in the instance variable.
	public int numCharOccurrences(char ch) {
		// Ignore letter case,Converts string and character to lowercase
		int count = 0;
		word = word.toLowerCase();
		if (Character.isUpperCase(ch)) {
			ch = Character.toLowerCase(ch);
		}
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ch) {
				count++;
			}
		}
		return count;
	}
}
