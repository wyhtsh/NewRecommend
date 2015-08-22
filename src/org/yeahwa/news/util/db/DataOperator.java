package org.yeahwa.news.util.db;

import java.sql.ResultSet;

/**
 * 数据操作类
 */
public class DataOperator{
	private ConnectionDB conn = null;
	
	public DataOperator() throws Exception {
		conn = new ConnectionDB();
	}
	
	public ResultSet findResult(String sql){
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		ResultSet res = conn.findAll(sb.toString());
		return res;
	}
	
	public void add(String sql){		
		try {
			conn.add(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void close(){
		conn.close();
	}
	
}
