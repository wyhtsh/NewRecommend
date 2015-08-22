package org.yeahwa.news.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.yeahwa.news.util.DataGetter;
import org.yeahwa.news.util.DataSetter;


public class NewsRecommender {

	/**
	 * Stage-6 user recommend
	 * 
	 * @param threshold,train
	 */
	public static void execute(double threshold, double train,double recom) {
		// Map<userid, Map<NClass, percentage>>
		Map<String, Map<String, Double>> preference = getInfo();
		// Map<NClass, ArrayList<newsid>>
		Map<String, ArrayList<String>> newsPool = DataGetter.getNewsSet(recom);
		// Map<userid, ArrayList<newsid>>
		Map<String, ArrayList<String>> recommedList = new HashMap<String, ArrayList<String>>();
		for (Iterator<Map.Entry<String, Map<String, Double>>> it = preference
				.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Map<String, Double>> me = it.next();
			for (Iterator<Map.Entry<String, Double>> subit = me.getValue()
					.entrySet().iterator(); subit.hasNext();) {
				Map.Entry<String, Double> subme = subit.next();
				if (subme.getValue() > threshold) {
					ArrayList<String> t = newsPool.get(subme.getKey());
					if (t != null) { // newsPool has the news a user like
						if (recommedList.get(me.getKey()) == null) {
							recommedList.put(me.getKey(), t);
						} else {
							recommedList.get(me.getKey()).addAll(t);
						}
					}
				}
			}
		}
		DataSetter.saveRecommend(recommedList);
		System.out.println("Stage-6 Finish!");
	}

	/**
	 * make frequency of NewsClass clicked by user to percentage
	 */
	private static Map<String, Map<String, Double>> getInfo() {
		Map<String, Map<String, Double>> user_model = DataGetter
				.loadPreference();
		// make frequency to percentage
		for (Iterator<Map.Entry<String, Map<String, Double>>> it = user_model
				.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Map<String, Double>> me = it.next();
			int totalFrequency = 0;
			for (Iterator<Map.Entry<String, Double>> subit = me.getValue()
					.entrySet().iterator(); subit.hasNext();) {
				Map.Entry<String, Double> subme = subit.next();
				totalFrequency += subme.getValue();
			}
			for (Iterator<Map.Entry<String, Double>> subit = me.getValue()
					.entrySet().iterator(); subit.hasNext();) {
				Map.Entry<String, Double> subme = subit.next();
				me.getValue().put(subme.getKey(),
						subme.getValue() / totalFrequency);
			}
		}
		return user_model;
	}
}
