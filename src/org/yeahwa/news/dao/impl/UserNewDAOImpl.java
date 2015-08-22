package org.yeahwa.news.dao.impl;

import java.util.List;

import org.yeahwa.news.dao.UserNewDAO;
import org.yeahwa.news.dao.util.PageUtil;
import org.yeahwa.news.model.UserNew;

public class UserNewDAOImpl implements UserNewDAO{

	private PageUtil pageUtil;
	
	@Override
	public List<UserNew> findAllUserNew(Integer firstindex) {
		final String hql = "from UserNew";
		List<UserNew> records = pageUtil.paging(hql, firstindex, 3);
		return records;
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

}
