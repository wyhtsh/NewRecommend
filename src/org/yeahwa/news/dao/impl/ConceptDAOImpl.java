package org.yeahwa.news.dao.impl;

import java.util.List;

import org.yeahwa.news.dao.ConceptDAO;
import org.yeahwa.news.dao.util.PageUtil;
import org.yeahwa.news.model.Concept;

public class ConceptDAOImpl implements ConceptDAO{

	private PageUtil pageUtil;
	@Override
	public List<Concept> findConcepts(Integer firstindex) {
		final String hql = "from Concept";
		List<Concept> words = pageUtil.paging(hql, firstindex, 4);
		return words;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}
}
