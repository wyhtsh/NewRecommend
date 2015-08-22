package org.yeahwa.news.tools;

import java.sql.ResultSet;
import java.util.HashMap;

import org.yeahwa.news.util.DataGetter;
import org.yeahwa.news.util.DataSetter;
import org.yeahwa.news.util.TruncationMaker;


public class WordsMapper {
	
	/**
	 * Stage-2 word mapping
	 * 
	 */
	public static void execute() {
		try {
			HashMap<String, String> mapper = new HashMap<String, String>();
			ResultSet rs = DataGetter.getMap();
			while (rs.next()) {
				mapper.put(rs.getString(3), rs.getString(2));
			}
			rs = DataGetter.getKeywords();
			TruncationMaker.doesTruncate("concepts");
			String inserts = "insert into concepts(userid,newsid,viewtime,concepts) values";
			int count = 0;
			while (rs.next()) {
				String[] processBytes = rs.getString(4).split("#");
				String[] temp;
				String fin = "";
				for (String s : processBytes) {
					temp = s.split("/");
					//if (temp[0].indexOf("NULLNULL") != -1) {
						//continue;
					//}
					fin += temp[0] + "/";
					if (mapper.get(temp[0]) != null) {
						fin += mapper.get(temp[0]) + "/";
					} else {
						fin += "unknown" + "/";
					}
					for (int i = 1; i < temp.length; i++) {
						fin += temp[i];
						if (i != temp.length - 1) {
							fin += "/";
						}
					}
					fin += "#";
				}

				inserts += "('" + rs.getString(1) + "','" + rs.getString(2)
						+ "','" + rs.getString(3) + "','" + fin + "')";

				if (++count % 1000 == 0) {
					DataSetter.insertion(inserts);
					inserts = "insert into concepts(userid,newsid,viewtime,concepts) values";
					System.out.println("Stage-2 mapped rows:" + count);
					continue;
				}
				if (!rs.isLast()) {
					inserts += ",";
				}
			}
			if (inserts != "insert into concepts(userid,newsid,viewtime,concepts) values") {
				DataSetter.insertion(inserts);
				System.out.println("Stage-2 mapped rows:" + count);
			}
			System.out.println("Stage-2 Finished!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
