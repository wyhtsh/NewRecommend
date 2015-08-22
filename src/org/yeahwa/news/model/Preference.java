package org.yeahwa.news.model;

import java.util.List;

/**
 * 用户偏好实体类
 * @author yeahwa
 *
 */
public class Preference implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private String clazz;
	private List<String[]> clazzes;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public List<String[]> getClazzes() {
		return clazzes;
	}
	public void setClazzes(List<String[]> clazzes) {
		this.clazzes = clazzes;
	}
	public Preference(String userid, String clazz) {
		super();
		this.userid = userid;
		this.clazz = clazz;
	}
	public Preference() {
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
