package org.yeahwa.news.dao;

import java.util.List;

import org.yeahwa.news.model.KeyWord;

public interface SplitWordDAO {
	
	public List<KeyWord> findKeyWords(Integer firstindex);

}
