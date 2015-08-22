package org.yeahwa.news.model;

/**
 * 新闻分类实体类
 * @author yeahwa
 *
 */
public class ClusteredNews implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String newsid;
	private String clazz;
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public ClusteredNews(String newsid, String clazz) {
		super();
		this.newsid = newsid;
		this.clazz = clazz;
	}
	public ClusteredNews() {
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
