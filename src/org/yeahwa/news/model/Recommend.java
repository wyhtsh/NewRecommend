package org.yeahwa.news.model;

import java.util.List;

/**
 * 推荐新闻实体类
 * 
 * @author yeahwa
 * 
 */
public class Recommend implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userid;
	private List<String> recommends;
	private String recommendlist;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<String> getRecommends() {
		return recommends;
	}

	public void setRecommends(List<String> recommends) {
		this.recommends = recommends;
	}

	public String getRecommendlist() {
		return recommendlist;
	}

	public void setRecommendlist(String recommendlist) {
		this.recommendlist = recommendlist;
	}

	public Recommend(String userid, String recommendlist,
			List<String> recommends) {
		super();
		this.userid = userid;
		this.recommends = recommends;
		this.recommendlist = recommendlist;
	}

	public Recommend() {
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
