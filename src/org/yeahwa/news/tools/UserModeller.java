package org.yeahwa.news.tools;

import java.util.Map;

import org.yeahwa.news.util.DataGetter;
import org.yeahwa.news.util.DataSetter;


public class UserModeller {
	
	/**
	 * Stage-4 user modeling
	 * 
	 * @param args
	 */
	public static void execute(double train) {
		Map<String, Map<String, Double>> user_model = DataGetter
				.loadUserNClass(train);
		DataSetter.savePreference(user_model);
		System.out.println("Stage-4 Finished!");
	}
}
