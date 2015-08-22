package org.yeahwa.news.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.yeahwa.news.util.db.DOFactory;
import org.yeahwa.news.util.db.DataOperator;

import com.mysql.jdbc.ResultSet;

public class DataGetter {
	/**
	 * Stage-1 dividing words
	 * 
	 */
	public static ResultSet getUserNew() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "select * from user_new";
		return (ResultSet) dataOperator.findResult(sql);
	}

	/**
	 * Stage-2 mapping words
	 * 
	 */
	public static ResultSet getMap() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "select * from map";
		return (ResultSet) dataOperator.findResult(sql);
	}

	public static ResultSet getKeywords() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "select * from keywords";
		return (ResultSet) dataOperator.findResult(sql);
	}

	/**
	 * Stage-3 clustering news
	 * 
	 */
	private static ResultSet getConcepts() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "SELECT newsid, concepts FROM concepts GROUP BY newsid";
		return (ResultSet) dataOperator.findResult(sql);
	}

	public static Map<String, Map<String, Double>> getNewsSample() {
		Map<String, Map<String, Double>> data = new HashMap<String, Map<String, Double>>();
		try {
			ResultSet rs = DataGetter.getConcepts();
			String[] temp, processBytes;
			while (rs.next()) {
				Map<String, Double> m = new TreeMap<String, Double>();
				processBytes = rs.getString(2).split("#");
				for (String s : processBytes) {
					temp = s.split("/");
					if (temp[1].compareTo("unknown") != 0) {
						m.put(temp[1], Double.parseDouble(temp[3]));
					} else {
						m.put(temp[0], Double.parseDouble(temp[3]));
					}
				}
				data.put(rs.getString(1), m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Stage-4 user modeling
	 * 
	 * @param user_model
	 */
	private static ResultSet getConceptsNum() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "SELECT COUNT(*) FROM concepts";
		return (ResultSet) dataOperator.findResult(sql);
	}

	/**
	 * 
	 * 'order by' based on time model, remove to reduce query consumption 
	 */
	private static ResultSet getUserNClass() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "SELECT userid,class FROM concepts,clustered_news WHERE concepts.newsid = clustered_news.newsid ORDER BY viewtime";
		//String sql = "SELECT userid,class FROM concepts,clustered_news WHERE concepts.newsid = clustered_news.newsid";
		return (ResultSet) dataOperator.findResult(sql);
	}

	public static Map<String, Map<String, Double>> loadUserNClass(double train) {
		Map<String, Map<String, Double>> user_model = new HashMap<String, Map<String, Double>>();
		int total_news = 0, count = 0, train_end = 0;
		try {
			ResultSet rs = DataGetter.getConceptsNum();
			while (rs.next()) {
				total_news = Integer.parseInt(rs.getString(1));
			}
			train_end = (int) (total_news * train);

			rs = DataGetter.getUserNClass();
			while (rs.next()) {
				if (count++ < train_end) {
					if (user_model.get(rs.getString(1)) == null) {
						HashMap<String, Double> m = new HashMap<String, Double>();
						m.put(rs.getString(2), 1.0);
						user_model.put(rs.getString(1), m);
					} else {
						HashMap<String, Double> m = (HashMap<String, Double>) user_model
								.get(rs.getString(1));
						if (m.get(rs.getString(2)) == null) {
							m.put(rs.getString(2), 1.0);
						} else {
							m.put(rs.getString(2), m.get(rs.getString(2)) + 1);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_model;
	}

	/**
	 * Stage-5 clustering users
	 * 
	 */
	private static ResultSet getPreference() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "SELECT * FROM preference";
		return (ResultSet) dataOperator.findResult(sql);
	}

	public static Map<String, Map<String, Double>> getUserSample() {
		// Map<userid, Map<NClass, frequency>>
		Map<String, Map<String, Double>> user_model = null;
		try {
			user_model = new HashMap<String, Map<String, Double>>();
			String[] temp, processBytes;
			ResultSet rs = DataGetter.getPreference();
			while (rs.next()) {
				Map<String, Double> m = new TreeMap<String, Double>();
				processBytes = rs.getString(2).split("#");
				for (String s : processBytes) {
					temp = s.split("/");
					m.put(temp[0], Double.parseDouble(temp[1]));
				}
				user_model.put(rs.getString(1), m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_model;
	}

	/**
	 * Stage-6 user recommend
	 * 
	 */
	/**
	 * 
	 * 'order by' based on time model, remove to reduce query consumption 
	 */
	private static ResultSet getClusteredNews() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "SELECT class,concepts.newsid FROM concepts,clustered_news WHERE concepts.newsid = clustered_news.newsid ORDER BY viewtime";
		//String sql = "SELECT class,concepts.newsid FROM concepts,clustered_news WHERE concepts.newsid = clustered_news.newsid";
		return (ResultSet) dataOperator.findResult(sql);
	}

	/**
	 * news is not train data add to news pool to be recommended
	 * 
	 */
	public static Map<String, ArrayList<String>> getNewsSet(double recom) {
		Map<String, ArrayList<String>> newsSet = null;
		int total_news = 0, count = 0, recom_start = 0;
		try {
			ResultSet rs = DataGetter.getConceptsNum();
			while (rs.next()) {
				total_news = Integer.parseInt(rs.getString(1));
			}
			recom_start = (int) (total_news * recom);
			newsSet = new HashMap<String, ArrayList<String>>();
			rs = DataGetter.getClusteredNews();
			while (rs.next()) {
				if (count++ >= recom_start) {
					if (newsSet.get(rs.getString(1)) == null) {
						ArrayList<String> a = new ArrayList<String>();
						a.add(rs.getString(2));
						newsSet.put(rs.getString(1), a);
					} else {
						newsSet.get(rs.getString(1)).add(rs.getString(2));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsSet;
	}

	public static Map<String, Map<String, Double>> loadPreference() {
		Map<String, Map<String, Double>> user_model = null;
		try {
			user_model = new HashMap<String, Map<String, Double>>();
			String[] temp, processBytes;
			ResultSet rs = DataGetter.getPreference();
			while (rs.next()) {
				Map<String, Double> m = new TreeMap<String, Double>();
				processBytes = rs.getString(2).split("#");
				for (String s : processBytes) {
					temp = s.split("/");
					m.put(temp[0], Double.parseDouble(temp[1]));
				}
				user_model.put(rs.getString(1), m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_model;
	}

	/**
	 * Stage-7 recommend estimating
	 * 
	 */
	private static ResultSet getRecommend() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "SELECT * FROM recommend";
		return (ResultSet) dataOperator.findResult(sql);
	}

	private static ResultSet getViewed() {
		DataOperator dataOperator = DOFactory.getInstance();
		String sql = "SELECT userid,newsid FROM concepts";
		return (ResultSet) dataOperator.findResult(sql);
	}

	public static Map<String, ArrayList<String>> loadRecommend() {
		Map<String, ArrayList<String>> recommend = null;
		try {
			recommend = new HashMap<String, ArrayList<String>>();
			ResultSet rs = DataGetter.getRecommend();
			while (rs.next()) {
				if (recommend.get(rs.getString(1)) == null) {
					ArrayList<String> a = new ArrayList<String>();
					String[] temp = rs.getString(2).split("#");
					for(int i=0;i<temp.length;i++){
						a.add(temp[i]);
					}					
					recommend.put(rs.getString(1), a);
				} else {
					recommend.get(rs.getString(1)).add(rs.getString(2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recommend;
	}

	public static Map<String, ArrayList<String>> viewedNews(double train) {
		Map<String, ArrayList<String>> user_model = null;
		try {
			user_model = new HashMap<String, ArrayList<String>>();
			int total_news = 0, count = 0, train_end = 0;

			ResultSet rs = DataGetter.getConceptsNum();
			while (rs.next()) {
				total_news = Integer.parseInt(rs.getString(1));
			}
			train_end = (int) (total_news * train);

			rs = DataGetter.getViewed();
			while (rs.next()) {
				if (count++ >= train_end) {
					if (user_model.get(rs.getString(1)) == null) {
						ArrayList<String> m = new ArrayList<String>();
						m.add(rs.getString(2));
						user_model.put(rs.getString(1), m);
					} else {
						user_model.get(rs.getString(1)).add(rs.getString(2));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user_model;
	}
}
