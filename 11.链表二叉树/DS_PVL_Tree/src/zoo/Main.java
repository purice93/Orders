package zoo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Main {

	
	public static void main(String[] args) {
		
		Zoo zoo = new Zoo("Chemnitz Tierpark");
		new SmokeTest(zoo).RunTest();
		
		
		write(zoo.getStatus());
	}

	
	public static void write(String args) {
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("Textausgabe.txt"), "utf-8"));
		    writer.write(args);
		} catch (IOException ex) {
		  // 
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
}
