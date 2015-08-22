package org.yeahwa.news.dao;

import java.util.List;

import org.yeahwa.news.model.UserNew;

public interface UserNewDAO {
	
	public List<UserNew> findAllUserNew(Integer firstindex);

}
