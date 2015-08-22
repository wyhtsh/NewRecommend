package org.yeahwa.news.action;

import java.util.List;
import java.util.Map;

import org.yeahwa.news.dao.ClusterDAO;
import org.yeahwa.news.model.ClusteredNews;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClusterAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String flag;
	private List<ClusteredNews> listCluster;

	private ClusterDAO clusterDao;

	/*
	 * 概念集view交互
	 */
	@SuppressWarnings("unchecked")
	public String clusterNews() {
		@SuppressWarnings("rawtypes")
		Map session = ActionContext.getContext().getSession();
		int num = 0;
		// 下一页
		if ("next".equals(flag) || "next" == flag) {
			num = (Integer) session.get("num");
			listCluster = clusterDao.findClusterIds(num += 10);
		}
		// 上一页
		else if ("prev".equals(flag) || "prev" == flag) {
			num = (Integer) session.get("num");
			listCluster = clusterDao.findClusterIds(num -= 10);
		} else {
			if (session.get("num") != null) {
				num = (Integer) session.get("num");
			}
			listCluster = clusterDao.findClusterIds(num);

		}
		if (listCluster == null) {
			return "fail";
		}
		session.put("num", num);
		return "success";
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<ClusteredNews> getListCluster() {
		return listCluster;
	}

	public void setListCluster(List<ClusteredNews> listCluster) {
		this.listCluster = listCluster;
	}

	public ClusterDAO getClusterDao() {
		return clusterDao;
	}

	public void setClusterDao(ClusterDAO clusterDao) {
		this.clusterDao = clusterDao;
	}

}
