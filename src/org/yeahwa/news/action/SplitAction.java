package org.yeahwa.news.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yeahwa.news.dao.SplitWordDAO;
import org.yeahwa.news.model.KeyWord;
import org.yeahwa.news.model.WordNode;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SplitAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SplitWordDAO splitWordDao;

	private List<KeyWord> keywords;
	private String flag;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String splitWord() {
		Map session = ActionContext.getContext().getSession();
		int num = 0;
		// 下一页
		if ("next".equals(flag) || "next" == flag) {
			num = (Integer) session.get("num");
			keywords = splitWordDao.findKeyWords(num += 5);
		}
		// 上一页
		else if ("prev".equals(flag) || "prev" == flag) {
			num = (Integer) session.get("num");
			keywords = splitWordDao.findKeyWords(num -= 5);
		} else {
			if (session.get("num") != null) {
				num = (Integer) session.get("num");
			}
			keywords = splitWordDao.findKeyWords(num);

		}
		if (keywords == null) {
			return "fail";
		}
		for (int i = 0; i < keywords.size(); i++) {
			KeyWord wordStr = keywords.get(i);
			String[] words = wordStr.getKeywordStr().split("#");
			List<WordNode> listnode = new ArrayList<WordNode>();
			for (String word : words) {
				String[] content = word.split("/");
				WordNode node = new WordNode();
				node.setWord("词名：" + content[0]);
				node.setClazz("词类：" + content[1]);
				node.setWeight("权重：" + content[2]);
				node.setFreq("使用频率：" + content[3]);
				listnode.add(node);
			}
			keywords.get(i).setKeywords(listnode);
		}
		session.put("num", num);
		return "success";
	}

	public SplitWordDAO getSplitWordDao() {
		return splitWordDao;
	}

	public void setSplitWordDao(SplitWordDAO splitWordDao) {
		this.splitWordDao = splitWordDao;
	}

	public List<KeyWord> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<KeyWord> keywords) {
		this.keywords = keywords;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
