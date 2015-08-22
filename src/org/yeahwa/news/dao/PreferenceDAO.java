package org.yeahwa.news.dao;

import java.util.List;

import org.yeahwa.news.model.Preference;

public interface PreferenceDAO {

	public List<Preference> findUserPreferences(Integer firstindex); 
}
