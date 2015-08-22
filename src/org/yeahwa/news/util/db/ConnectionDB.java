package org.yeahwa.news.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接类
 * 
 * @author yeahwa
 * 
 */
public class ConnectionDB {

	private Connection con = null;
	private Statement stat = null;
	private ResultSet res = null;

	public ConnectionDB() throws Exception {
		getConnect();
	}

	private void getConnect() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/BigData", "root", "root");
	}

	public ResultSet findAll(String sql) {
		if (con == null) {
		} else {
			try {
				stat = con.createStatement();
				res = stat.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	public int add(String sql) throws Exception {
		int num = 0;
		if (con == null) {

		} else {

			stat = con.createStatement();
			num = stat.executeUpdate(sql);
			stat.close();
		}
		return num;

	}

	public void close() {
		try {
			if (res != null) {
				res.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception ignored) {
			// do nothing
		}
	}
}
