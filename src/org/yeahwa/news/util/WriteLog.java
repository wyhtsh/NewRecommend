package org.yeahwa.news.util;

import java.io.FileWriter;
import java.io.IOException;
/**
 * 日志输出
 * @author yeahwa
 *
 */
public class WriteLog {
	
	public static void WriteText(String fileName, String content) {
	       try {
	           // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	           FileWriter writer = new FileWriter(fileName, true);
	           writer.write(content);
	           writer.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	    }

}
