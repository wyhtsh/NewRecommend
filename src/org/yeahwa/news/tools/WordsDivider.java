package org.yeahwa.news.tools;

import java.sql.ResultSet;

import org.jfree.util.Log;
import org.yeahwa.news.util.CLibrary;
import org.yeahwa.news.util.DataGetter;
import org.yeahwa.news.util.DataSetter;
import org.yeahwa.news.util.TruncationMaker;

public class WordsDivider {
	
	/**
	 * Stage-1 word dividing
	 * 
	 */
	public static void execute() {
		String argu = "";
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");

		if (init_flag == 0) {
			String nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			Log.error("initializing failed for reasons : " + nativeBytes);
			return;
		}	

		try {
			ResultSet rs = DataGetter.getUserNew();			
			TruncationMaker.doesTruncate("keywords");
			String inserts = "insert into keywords values";
			int count = 0;
			while (rs.next()) {
				String contentBytes = CLibrary.Instance.NLPIR_GetKeyWords(rs.getString(4) + rs.getString(5), 5, true);// the max keyword in title number is 5
				
				inserts += "('"+ rs.getString(1) + "','" + rs.getString(2) + "','" + rs.getString(3) + "','" + contentBytes + "')";						
				
				if (++count % 1000 == 0) {
					DataSetter.insertion(inserts);	
					inserts = "insert into keywords values";
					Log.info("Stage-1 divided rows:" + count);
					continue;
				}
				if(!rs.isLast()){
					inserts += ",";
				}
			}	
			if(inserts != "insert into keywords values"){
				DataSetter.insertion(inserts);
				Log.info("Stage-1 divided rows:" + count);
			}			
			CLibrary.Instance.NLPIR_Exit();
			Log.info("Stage-1 Finished!");
		} catch (Exception ex) {			
			Log.error(ex.getMessage());
		}
	}
}
