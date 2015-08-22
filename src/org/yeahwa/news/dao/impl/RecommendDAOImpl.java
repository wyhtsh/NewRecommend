package org.yeahwa.news.dao.impl;

import java.util.List;

import org.yeahwa.news.dao.RecommendDAO;
import org.yeahwa.news.dao.util.PageUtil;
import org.yeahwa.news.model.Recommend;

public class RecommendDAOImpl implements RecommendDAO{

	private PageUtil pageUtil;
	@Override
	public List<Recommend> findRecommends(Integer firstindex) {
		final String hql = "from Recommend";
		List<Recommend> words = pageUtil.paging(hql, firstindex, 2);
		return words;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

}
