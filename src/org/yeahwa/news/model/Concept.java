package org.yeahwa.news.model;

import java.util.Date;
import java.util.List;

/**
 * 新闻概念映射实体类
 * 
 * @author yeahwa
 * 
 */
public class Concept implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String newsid;
	private Date viewtime;
	private List<WordNode> concepts;
	private String conceptStr;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	public Date getViewtime() {
		return viewtime;
	}

	public void setViewtime(Date viewtime) {
		this.viewtime = viewtime;
	}

	public List<WordNode> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<WordNode> concepts) {
		this.concepts = concepts;
	}

	public String getConceptStr() {
		return conceptStr;
	}

	public void setConceptStr(String conceptStr) {
		this.conceptStr = conceptStr;
	}

	public Concept(String userid, String newsid, Date viewtime,
			List<WordNode> concepts) {
		super();
		this.userid = userid;
		this.newsid = newsid;
		this.viewtime = viewtime;
		this.concepts = concepts;
	}

	public Concept() {
		super();
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		return true;
	}
}
