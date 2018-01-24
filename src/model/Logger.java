package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Logger {

	private static PrintWriter writer;



	private Logger(){}

	public static PrintWriter getWritter(){
		return writer;
	}

	public static void close() {
		writer.close();
	}
	
	public static void log(String text){
		writer.println(text);
		writer.flush();
	}

	public static void initialize(String logFile) {
		try {
			writer = new PrintWriter(logFile, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


}
