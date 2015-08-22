package org.yeahwa.news.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.yeahwa.news.util.db.DOFactory;
import org.yeahwa.news.util.db.DataOperator;


public class DataSetter {
	/**
	 * Stage-1~7 data insertion
	 * 
	 * @param inserts
	 */
	public static void insertion(String inserts) {
		DataOperator dataOperator = DOFactory.getInstance();
		dataOperator.add(inserts);
	}

	/**
	 * Stage-3 clustering news
	 * 
	 * @param kmeansClusterResult
	 */
	public static void saveClusteredNews(
			Map<String, Integer> kmeansClusterResult) {
		Set<Map.Entry<String, Integer>> kmeansClusterResultSet = kmeansClusterResult
				.entrySet();
		TruncationMaker.doesTruncate("clustered_news");
		String inserts = "insert into clustered_news(newsid,class) values";
		int count = 0;
		for (Iterator<Map.Entry<String, Integer>> it = kmeansClusterResultSet
				.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> me = it.next();
			inserts += "('" + me.getKey() + "','" + me.getValue() + "')";
			if (++count % 1000 == 0) {
				DataSetter.insertion(inserts);
				inserts = "insert into clustered_news(newsid,class) values";
				continue;
			}
			if (it.hasNext()) {
				inserts += ",";
			}
		}
		if (inserts != "insert into clustered_news(newsid,class) values") {
			DataSetter.insertion(inserts);
		}
	}

	/**
	 * Stage-4 user modeling
	 * 
	 * @param user_model
	 */
	public static void savePreference(
			Map<String, Map<String, Double>> user_model) {
		TruncationMaker.doesTruncate("preference");
		String inserts = "insert into preference values";
		int count = 0;
		for (Iterator<Map.Entry<String, Map<String, Double>>> it = user_model
				.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Map<String, Double>> me = it.next();
			String pref = "";
			for (Iterator<Map.Entry<String, Double>> subit = me.getValue()
					.entrySet().iterator(); subit.hasNext();) {
				Map.Entry<String, Double> subme = subit.next();
				pref += subme.getKey() + "/" + subme.getValue() + "#";
			}
			inserts += "('" + me.getKey() + "','" + pref + "')";
			if (++count % 1000 == 0) {
				DataSetter.insertion(inserts);
				inserts = "insert into preference values";
				System.out.println("Stage-4 user modelled:" + count);
				continue;
			}
			if (it.hasNext()) {
				inserts += ",";
			}
		}
		if (inserts != "insert into preference values") {
			DataSetter.insertion(inserts);
			System.out.println("Stage-4 user modelled:" + count);
		}
	}

	/**
	 * Stage-5 clustering users
	 * 
	 * @param kmeansClusterResult
	 */
	public static void saveClusteredPreference(
			Map<String, Integer> kmeansClusterResult) {
		Set<Map.Entry<String, Integer>> kmeansClusterResultSet = kmeansClusterResult
				.entrySet();
		TruncationMaker.doesTruncate("clustered_preference");
		String inserts = "insert into clustered_preference(userid,class) values";
		int count = 0;
		for (Iterator<Map.Entry<String, Integer>> it = kmeansClusterResultSet
				.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> me = it.next();
			inserts += "('" + me.getKey() + "','" + me.getValue() + "')";
			if (++count % 1000 == 0) {
				DataSetter.insertion(inserts);
				inserts = "insert into clustered_preference(userid,class) values";
				continue;
			}
			if (it.hasNext()) {
				inserts += ",";
			}
		}
		if (inserts != "insert into clustered_preference(userid,class) values") {
			DataSetter.insertion(inserts);
		}
	}

	/**
	 * Stage-6 user recommend
	 * 
	 * @param recommedList
	 */
	public static void saveRecommend(Map<String, ArrayList<String>> recommedList) {
		TruncationMaker.doesTruncate("recommend");
		String inserts = "insert into recommend values";
		int count = 0;
		for (Iterator<Map.Entry<String, ArrayList<String>>> it = recommedList
				.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, ArrayList<String>> me = it.next();
			String pref = "";
			for (Iterator<String> subit = me.getValue().iterator(); subit
					.hasNext();) {
				String subme = subit.next();
				pref += subme + "#";
			}
			inserts += "('" + me.getKey() + "','" + pref + "')";
			if (++count % 30 == 0) {
				DataSetter.insertion(inserts);
				inserts = "insert into recommend values";
				System.out.println("Stage-6 recommended:" + count);
				continue;
			}
			if (it.hasNext()) {
				inserts += ",";
			}
		}
		if (inserts != "insert into recommend values") {
			DataSetter.insertion(inserts);
			System.out.println("Stage-6 recommended:" + count);
		}
	}

	/**
	 * Stage-7 recommend estimating
	 * 
	 * @param result
	 */
	public static void saveResult(Map<String, Double> result) {
		Set<Map.Entry<String, Double>> resultSet = result.entrySet();
		TruncationMaker.doesTruncate("result");
		String inserts = "insert into result(userid,accuracy) values";
		int count = 0;
		for (Iterator<Map.Entry<String, Double>> it = resultSet.iterator(); it
				.hasNext();) {
			Map.Entry<String, Double> me = it.next();
			inserts += "('" + me.getKey() + "','" + me.getValue() + "')";
			if (++count % 1000 == 0) {
				DataSetter.insertion(inserts);
				inserts = "insert into result(userid,accuracy) values";
				System.out.println("Stage-7 estimated:" + count);
				continue;
			}
			if (it.hasNext()) {
				inserts += ",";
			}
		}
		if (inserts != "insert into result(userid,accuracy) values") {
			DataSetter.insertion(inserts);
			System.out.println("Stage-7 estimated:" + count);
		}
	}
}
