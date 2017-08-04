import java.util.Scanner;

public class WordCruncherTest {
	public static void main(String[] args) {
		System.out.println("please input a word,input \"quit\" to quit:");
		Scanner sc = new Scanner(System.in);
		while (true) {
			String word = sc.nextLine();
			if (word.equals("quit")) {
				break;
			} else {
				WordCruncher wc = new WordCruncher(word);
				System.out.println(wc.numLetters());
				System.out.println(wc.numVowels());
				System.out.println(wc.reverse());
				System.out.println(wc.toPigLatin());
				System.out.println(wc.toGibberish());
				System.out.println("please enter one letter:");
				Scanner chIn = new Scanner(System.in);
				String str = chIn.nextLine();
				char ch = str.charAt(0);
				System.out.println(wc.numCharOccurrences(ch));
			}
		}
	}
}

/* test output sample:
please input a word,input "quit" to quit:
WordCruncher
12
3
rehcnurCdroW
ordCruncherWay
WithagordCruncher
please enter one letter:
c
2
*/
