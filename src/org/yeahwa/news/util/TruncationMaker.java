package org.yeahwa.news.util;

import org.yeahwa.news.util.db.DataOperator;


public class TruncationMaker {
	public static void doesTruncate(String tableName){
		DataOperator dataOperator = null;
		try {
			dataOperator = new DataOperator();
			String sql = "TRUNCATE TABLE " + tableName;
			dataOperator.findResult(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			dataOperator.close();
		}	
	}
}
