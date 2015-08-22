package org.yeahwa.news.model;

/**
 * 词语结构类
 * 
 * @author yeahwa
 * 
 */
public class WordNode {

	private String word;
	private String clazz;
	private String weight;
	private String freq;

	private String sub_clazz;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public String getSub_clazz() {
		return sub_clazz;
	}

	public void setSub_clazz(String sub_clazz) {
		this.sub_clazz = sub_clazz;
	}

	public WordNode(String word, String clazz, String weight, String freq,
			String sub_clazz) {
		super();
		this.word = word;
		this.clazz = clazz;
		this.weight = weight;
		this.freq = freq;
		this.sub_clazz = sub_clazz;
	}

	public WordNode() {
		super();
	}

}
