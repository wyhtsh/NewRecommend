package org.yeahwa.news.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.yeahwa.news.dao.UserNewDAO;
import org.yeahwa.news.model.UserNew;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserNewDAO userNewDao;

	private List<UserNew> recordResult;

	public String indexView() {
		recordResult = userNewDao.findAllUserNew(0);
		if (recordResult == null) {
			Log.error("Null");
			return "fail";
		}
		UserNew record = new UserNew();
		Iterator<UserNew> iter = recordResult.iterator();
		while (iter.hasNext()) {
			record = iter.next();
			if (200 <= record.getNewcontent().length()) {
				record.setNewcontent(record.getNewcontent().substring(0, 200)
						+ "...");
			}
		}
		Map session = ActionContext.getContext().getSession();
		int num = 0;
		session.put("num", num);
		return "success";
	}

	public List<UserNew> getRecordResult() {
		return recordResult;
	}

	public void setRecordResult(List<UserNew> recordResult) {
		this.recordResult = recordResult;
	}

	public UserNewDAO getUserNewDao() {
		return userNewDao;
	}

	public void setUserNewDao(UserNewDAO userNewDao) {
		this.userNewDao = userNewDao;
	}

}
