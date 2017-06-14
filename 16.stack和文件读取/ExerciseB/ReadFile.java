import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("please input the name of a text file:");
		String fileName = sc.nextLine();
		File file = new File(fileName);
		List<String> strArray = new LinkedList<>();
		List<Integer> intArray = new LinkedList<>();
		String regex = "\\s+";
		try {
			if (file.isFile() && file.exists()) {
				System.out.println("The file test.txt was successfully opened.\n"
						+ "The data in it will now be processed\n" + "Press Enter to continue ...");
				sc.nextLine();
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int lineNumber = 0;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					strArray = new LinkedList<>();
					intArray = new LinkedList<>();
					boolean invalid = false;
					lineNumber++;
					if (lineTxt.equals("")) {
						System.out.println("Line " + lineNumber + ":This is a blank line.");
						continue;
					}
					lineTxt = lineTxt.trim();
					strArray = Arrays.asList(lineTxt.split(regex));
					if (strArray.size() > 15) {
						System.out.println("Line " + lineNumber
								+ ":This line contains more than the maximum of 15 allowed datavalues.");
						continue;
					}
					for (int i = 0; i < strArray.size(); i++) {
						try {
							intArray.add(Integer.parseInt(strArray.get(i)));
						} catch (NumberFormatException e) {
							System.out.println("Line " + lineNumber + ":This line contains an invalid integer value.");
							invalid = true;
							break;
						}
					}
					if (invalid) {
						continue;
					}
					if (intArray.size() == 1) {
						System.out.println("Line " + lineNumber
								+ ":Number of values = 1 and value range value range consists ofthis single value.");
						continue;
					}
					Collections.sort(intArray);
					System.out.println("Line " + lineNumber + ":Number of values = " + strArray.size()
							+ " and value range is [" + intArray.get(0) + ", " + intArray.get(intArray.size()-1) + "].");
				}
				read.close();
			} else {
				System.out.println(
						fileName + " (the system cannot find the file specified).\n" + "Program will now terminate.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
