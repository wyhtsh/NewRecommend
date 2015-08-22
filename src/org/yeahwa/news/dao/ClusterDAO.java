package org.yeahwa.news.dao;

import java.util.List;

import org.yeahwa.news.model.ClusteredNews;

public interface ClusterDAO {
	
	public List<ClusteredNews> findClusterIds(Integer firstindex); 

}
