package org.yeahwa.news.util.db;

/**
 * 工厂类
 * @author yeahwa
 *
 */
public class DOFactory {
	private static DataOperator dataOperator = null;
	
	/**
	 * return the unique dataOperator
	 * 
	 */
	public static DataOperator getInstance(){
		if(dataOperator == null){
			try {
				dataOperator = new DataOperator();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataOperator;
	}
}
