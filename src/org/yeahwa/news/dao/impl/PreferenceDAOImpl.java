package org.yeahwa.news.dao.impl;

import java.util.List;

import org.yeahwa.news.dao.PreferenceDAO;
import org.yeahwa.news.dao.util.PageUtil;
import org.yeahwa.news.model.Preference;

public class PreferenceDAOImpl implements PreferenceDAO{

	private PageUtil pageUtil;
	@Override
	public List<Preference> findUserPreferences(Integer firstindex) {
		final String hql = "from Preference";
		List<Preference> words = pageUtil.paging(hql, firstindex, 4);
		return words;
	}
	
	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

}
