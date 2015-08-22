package org.yeahwa.news.model;

import java.util.Date;

public class UserNew implements java.io.Serializable{
	/**
	 * 用户新闻浏览记录实体类
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String newid;
	private Date viewdate;
	private String newtitle;
	private String newcontent;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNewid() {
		return newid;
	}
	public void setNewid(String newid) {
		this.newid = newid;
	}
	public Date getViewdate() {
		return viewdate;
	}
	public void setViewdate(Date viewdate) {
		this.viewdate = viewdate;
	}
	public String getNewtitle() {
		return newtitle;
	}
	public void setNewtitle(String newtitle) {
		this.newtitle = newtitle;
	}
	public String getNewcontent() {
		return newcontent;
	}
	public void setNewcontent(String newcontent) {
		this.newcontent = newcontent;
	}
	public UserNew(String userid, String newid, Date viewdate,
			String newtitle, String newcontent) {
		super();
		this.userid = userid;
		this.newid = newid;
		this.viewdate = viewdate;
		this.newtitle = newtitle;
		this.newcontent = newcontent;
	}
	public UserNew() {
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
