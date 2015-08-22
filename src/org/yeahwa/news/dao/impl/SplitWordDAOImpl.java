package org.yeahwa.news.dao.impl;

import java.util.List;

import org.yeahwa.news.dao.SplitWordDAO;
import org.yeahwa.news.dao.util.PageUtil;
import org.yeahwa.news.model.KeyWord;

public class SplitWordDAOImpl implements SplitWordDAO{

	private PageUtil pageUtil;
	@Override
	public List<KeyWord> findKeyWords(Integer firstindex) {
		final String hql = "from KeyWord";
		List<KeyWord> words = pageUtil.paging(hql, firstindex, 4);
		return words;
	}
	public PageUtil getPageUtil() {
		return pageUtil;
	}
	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

}
