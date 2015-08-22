package org.yeahwa.news.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jfree.util.Log;
import org.yeahwa.news.util.DataGetter;
import org.yeahwa.news.util.DataSetter;
import org.yeahwa.news.util.db.DOFactory;

public class Estimator {

	public static void execute(double train) {
		Map<String, ArrayList<String>> viewedNews = DataGetter
				.viewedNews(train);
		Map<String, ArrayList<String>> recommend = DataGetter.loadRecommend();
		Map<String, Double> result = new HashMap<String, Double>();
		for (Iterator<Map.Entry<String, ArrayList<String>>> it = viewedNews
				.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, ArrayList<String>> me = it.next();
			ArrayList<String> recom = recommend.get(me.getKey());
			if (recom != null) {
				double accuracy = compare(recom, me.getValue());
				result.put(me.getKey(), accuracy);
			}
		}
		DataSetter.saveResult(result);
		DOFactory.getInstance().close();
		Log.info("Stage-7 Finished!");
	}

	private static double compare(ArrayList<String> recom,
			ArrayList<String> view) {
		int same = 0;
		for (Iterator<String> i = view.iterator(); i.hasNext();) {
			String s = (String) i.next();
			if (recom.contains(s)) {
				same++;
			}
		}
		double accuracy = same;
		return accuracy;
	}
}
