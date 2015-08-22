package org.yeahwa.news.model;

import java.util.Date;
import java.util.List;

/**
 * 关键词实体类
 * 
 * @author yeahwa
 * 
 */
public class KeyWord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String newsid;
	private Date viewtime;
	private List<WordNode> keywords;
	private String keywordStr;

	public String getKeywordStr() {
		return keywordStr;
	}

	public void setKeywordStr(String keywordStr) {
		this.keywordStr = keywordStr;
	}

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

	public List<WordNode> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<WordNode> keywords) {
		this.keywords = keywords;
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
