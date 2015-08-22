package org.yeahwa.news.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yeahwa.news.dao.ConceptDAO;
import org.yeahwa.news.model.Concept;
import org.yeahwa.news.model.WordNode;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ConceptAction extends ActionSupport {

	/**
	 * 概念集控制层
	 */
	private static final long serialVersionUID = 1L;

	private ConceptDAO conceptDao;

	private String flag;
	private List<Concept> concepts;

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public String conceptNews() {
		Map session = ActionContext.getContext().getSession();
		int num = 0;
		// 下一页
		if ("next".equals(flag) || "next" == flag) {
			num = (Integer) session.get("num");
			concepts = conceptDao.findConcepts(num += 5);
		}
		// 上一页
		else if ("prev".equals(flag) || "prev" == flag) {
			num = (Integer) session.get("num");
			concepts = conceptDao.findConcepts(num -= 5);
		} else {
			if (session.get("num") != null) {
				num = (Integer) session.get("num");
			}
			concepts = conceptDao.findConcepts(0);

		}
		if (concepts == null) {
			return "fail";
		}
		for (int i = 0; i < concepts.size(); i++) {
			Concept concept = concepts.get(i);
			String[] words = concept.getConceptStr().split("#");
			List<WordNode> listnode = new ArrayList<WordNode>();
			int count = 0;
			for (String word : words) {
				String[] content = word.split("/");
				WordNode node = new WordNode();
				node.setWord("词名：" + content[0]);
				if (content[1].equals("unknown") || "unknown" == content[1]) {
					content[1] = "无";
				}
				node.setSub_clazz("附属类：" + content[1]);
				node.setClazz("词类：" + content[2]);
				node.setWeight("权重：" + content[3]);
				node.setFreq("使用频率：" + content[4]);
				listnode.add(node);
			}
			// while (iter.hasNext()) {
			//
			// String w = iter.next();
			// listnode.add("["+(++count)+"]"+w);
			// }
			concepts.get(i).setConcepts(listnode);
		}
		session.put("num", num);
		return "success";
	}

	public ConceptDAO getConceptDao() {
		return conceptDao;
	}

	public void setConceptDao(ConceptDAO conceptDao) {
		this.conceptDao = conceptDao;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<Concept> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<Concept> concepts) {
		this.concepts = concepts;
	}

}
