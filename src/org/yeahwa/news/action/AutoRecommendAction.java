package org.yeahwa.news.action;

import org.jfree.util.Log;
import org.yeahwa.news.tools.Estimator;
import org.yeahwa.news.tools.NewsCluster;
import org.yeahwa.news.tools.NewsRecommender;
import org.yeahwa.news.tools.UserCluster;
import org.yeahwa.news.tools.UserModeller;
import org.yeahwa.news.tools.WordsDivider;
import org.yeahwa.news.tools.WordsMapper;


import com.opensymphony.xwork2.ActionSupport;

public class AutoRecommendAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String res;
	private String arr;
	
	public String autoRecomNews(){
		res = "fail";
		double train = 1;	//前x%的数据用于建立用户模型，后1-x%的数据用于检测
		double recom = 0;	//从x%的数据开始用于推荐与检测
		double threshold = 0.8;	//threshold
		Log.info("---News Recommendation Started!---");
		String[] conductor = arr.split("#");
		//分词
		if(conductor[0].equals("1")){
			WordsDivider.execute();
		}
		//映射
		if(conductor[1].equals("1")){
			WordsMapper.execute();
		}
		//新闻聚类
		if(conductor[2].equals("1")){
			NewsCluster.execute(); 
		}
		//用户建模
		if(conductor[3].equals("1")){
			UserModeller.execute(train);
		}
		//用户聚类
		if(conductor[4].equals("1")){
			UserCluster.execute();			
		}
		//新闻推荐
		if(conductor[5].equals("1")){
			NewsRecommender.execute(threshold,train,recom);	
		}
		//评价
		if(conductor[6].equals("1")){
			Estimator.execute(train);
		}
		res = "操作成功";
		return SUCCESS;
	}

	
	public void setRes(String res) {
		this.res = res;
	}

	public String getRes() {
		return res;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

}
