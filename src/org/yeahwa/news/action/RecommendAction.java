package org.yeahwa.news.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.yeahwa.news.dao.RecommendDAO;
import org.yeahwa.news.model.Recommend;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RecommendAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Recommend> recommends;
	private String flag;
	
	private RecommendDAO recommendDao;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String newsRecommend(){
		Map session = ActionContext.getContext().getSession();
		int num = 0;
		//下一页
		if("next".equals(flag)|| "next"==flag){
			num = (Integer) session.get("num");
			recommends = recommendDao.findRecommends(num+=10);
		}
		//上一页
		else if("prev".equals(flag)|| "prev"==flag){
			num = (Integer) session.get("num");
			recommends = recommendDao.findRecommends(num-=10);
		}
		else{
			if(session.get("num")!= null){
				num = (Integer) session.get("num");
			}
			recommends = recommendDao.findRecommends(num);
			
		}
		if(recommends==null){
			return "fail";
		}
		for (int i = 0; i < recommends.size(); i++) {
			Recommend recommend = recommends.get(i);
			String[] words = recommend.getRecommendlist().split("#");
			List<String> listnode = new ArrayList<String>();
			Set<String> setStr = new HashSet<String>();
			int count = 0;
			for (String word : words) {
				setStr.add(word);
			}
			Iterator<String> iter = setStr.iterator();
			while (iter.hasNext()) {
				
				String w = iter.next();
				listnode.add("["+(++count)+"]"+w);
			}
			recommends.get(i).setRecommends(listnode);
		}
		session.put("num", num);
		return "success";
	}

	public List<Recommend> getRecommends() {
		return recommends;
	}

	public void setRecommends(List<Recommend> recommends) {
		this.recommends = recommends;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public RecommendDAO getRecommendDao() {
		return recommendDao;
	}

	public void setRecommendDao(RecommendDAO recommendDao) {
		this.recommendDao = recommendDao;
	}

}
