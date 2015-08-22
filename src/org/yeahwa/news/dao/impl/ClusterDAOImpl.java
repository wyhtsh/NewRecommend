package org.yeahwa.news.dao.impl;

import java.util.List;

import org.yeahwa.news.dao.ClusterDAO;
import org.yeahwa.news.dao.util.PageUtil;
import org.yeahwa.news.model.ClusteredNews;

/**
 * 新闻分类操作实现类
 * @author yeahwa
 *
 */
public class ClusterDAOImpl implements ClusterDAO{

	
	private PageUtil pageUtil;
	/*
	 * 分页查询概念集映射表
	 * (non-Javadoc)
	 * @see org.yeahwa.news.dao.ClusterDAO#findClusterIds(java.lang.Integer)
	 */
	@Override
	public List<ClusteredNews> findClusterIds(Integer firstindex) {
		final String hql = "from ClusteredNews";
		List<ClusteredNews> records = pageUtil.paging(hql, firstindex, 5);
		return records;
	}
	public PageUtil getPageUtil() {
		return pageUtil;
	}
	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

}
