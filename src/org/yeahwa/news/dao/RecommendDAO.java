package org.yeahwa.news.dao;

import java.util.List;

import org.yeahwa.news.model.Recommend;


public interface RecommendDAO {
	
	public List<Recommend> findRecommends(Integer firstindex); 

}
