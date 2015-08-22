package org.yeahwa.news.dao;

import java.util.List;

import org.yeahwa.news.model.Concept;

public interface ConceptDAO {

	public List<Concept> findConcepts(Integer firstindex);
}
