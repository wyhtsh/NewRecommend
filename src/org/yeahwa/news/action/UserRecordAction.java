package org.yeahwa.news.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.yeahwa.news.dao.UserNewDAO;
import org.yeahwa.news.model.UserNew;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserRecordAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserNewDAO userNewDao;

	private List<UserNew> recordResult;

	private String flag;

	/**
	 * 查询用户所有原始数据
	 * 
	 * @return
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findUserRecord() {
		Map session = ActionContext.getContext().getSession();
		int num;
		if ("next".equals(flag) || "next" == flag) {
			num = (Integer) session.get("num");
			recordResult = userNewDao.findAllUserNew(num += 10);
		} else if ("prev".equals(flag) || "prev" == flag) {
			num = (Integer) session.get("num");
			recordResult = userNewDao.findAllUserNew(num -= 10);
		} else {
			num = 0;
			recordResult = userNewDao.findAllUserNew(num);

		}

		if (recordResult == null) {
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
		session.put("num", num);
		return "success";
	}

	public String nextRecord() {

		return "success";
	}

	public UserNewDAO getUserNewDao() {
		return userNewDao;
	}

	public void setUserNewDao(UserNewDAO userNewDao) {
		this.userNewDao = userNewDao;
	}

	public List<UserNew> getRecordResult() {
		return recordResult;
	}

	public void setRecordResult(List<UserNew> recordResult) {
		this.recordResult = recordResult;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
