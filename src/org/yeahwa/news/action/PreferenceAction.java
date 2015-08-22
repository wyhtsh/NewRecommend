package org.yeahwa.news.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yeahwa.news.dao.PreferenceDAO;
import org.yeahwa.news.model.Preference;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PreferenceAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PreferenceDAO preferenceDao;

	private String flag;
	private List<Preference> preferences;

	@SuppressWarnings("unchecked")
	public String preferenceUsr() {
		@SuppressWarnings("rawtypes")
		Map session = ActionContext.getContext().getSession();
		int num = 0;
		// 下一页
		if ("next".equals(flag) || "next" == flag) {
			num = (Integer) session.get("num");
			preferences = preferenceDao.findUserPreferences(num += 10);
		}
		// 上一页
		else if ("prev".equals(flag) || "prev" == flag) {
			num = (Integer) session.get("num");
			preferences = preferenceDao.findUserPreferences(num -= 10);
		} else {
			if (session.get("num") != null) {
				num = (Integer) session.get("num");
			}
			preferences = preferenceDao.findUserPreferences(num);

		}
		if (preferences == null) {
			return "fail";
		}
		for (int i = 0; i < preferences.size(); i++) {
			String[] words = preferences.get(i).getClazz().split("#");
			List<String[]> listnode = new ArrayList<String[]>();
			for(String word : words){
				String[] content = word.split("/");
				content[0]="类别："+content[0];
				content[1]="频率："+content[1];
				listnode.add(content);
			}
			preferences.get(i).setClazzes(listnode);

		}
		session.put("num", num);
		return "success";
	}

	public PreferenceDAO getPreferenceDao() {
		return preferenceDao;
	}

	public void setPreferenceDao(PreferenceDAO preferenceDao) {
		this.preferenceDao = preferenceDao;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}

}
