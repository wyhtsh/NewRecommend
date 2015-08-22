package org.yeahwa.news.util.db;

import java.io.FileWriter;
import java.io.IOException;

public class WriteLog {
	
	public static void WriteText(String fileName, String content) {
	       try {
	           // append true
	           FileWriter writer = new FileWriter(fileName, true);
	           writer.write(content);
	           writer.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	    }

}
