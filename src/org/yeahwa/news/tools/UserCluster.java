package org.yeahwa.news.tools;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import org.yeahwa.news.util.DataGetter;
import org.yeahwa.news.util.DataSetter;



public class UserCluster {

	/**
	 * K-means算法主过程
	 * 
	 * @param Map<String, Map<String, Double>> allTestSampleMap 聚类算法测试样本map
	 * @param int K 聚类的数量
	 * @return Map<String,Integer> 聚类的结果 即<对象名，聚类完成后所属的类别标号>
	 */
	private static Map<String, Integer> kMeansCluster(
			Map<String, Map<String, Double>> allTestSampleMap, int K) {
		// 0、首先获取allTestSampleMap所有文件名顺序组成的数组
		String[] testSampleNames = new String[allTestSampleMap.size()];
		int count = 0, tsLength = allTestSampleMap.size();
		Set<Map.Entry<String, Map<String, Double>>> allTestSampeleMapSet = allTestSampleMap
				.entrySet();
		for (Iterator<Map.Entry<String, Map<String, Double>>> it = allTestSampeleMapSet
				.iterator(); it.hasNext();) {
			Map.Entry<String, Map<String, Double>> me = it.next();
			testSampleNames[count++] = me.getKey();
		}
		// 1、初始点的选择算法是随机选择或者是均匀分开选择，这里采用后者
		Map<Integer, Map<String, Double>> meansMap = getInitPoint(
				allTestSampleMap, K);// 保存K个中心点
		double[][] distance = new double[tsLength][K];// distance[i][j]记录点i到聚类中心j的距离
		// 2、初始化K个聚类
		int[] assignMeans = new int[tsLength];// 记录所有点属于的聚类序号，初始化全部为0
		Map<Integer, Vector<Integer>> clusterMember = new TreeMap<Integer, Vector<Integer>>();// 记录每个聚类的成员点序号
		Vector<Integer> mem = new Vector<Integer>();
		int iterNum = 0;// 迭代次数
		while (true) {
			System.out.print("Stage-5 user clustering iteration No." + (iterNum++) + ":");
			// 3、计算每个点和每个聚类中心的距离
			for (int i = 0; i < tsLength; i++) {
				for (int j = 0; j < K; j++) {
					distance[i][j] = getDistance(
							allTestSampleMap.get(testSampleNames[i]),
							meansMap.get(j));
				}
			}
			// 4、找出每个点最近的聚类中心
			int[] nearestMeans = new int[tsLength];
			for (int i = 0; i < tsLength; i++) {
				nearestMeans[i] = findNearestMeans(distance, i);
			}
			// 5、判断当前所有点属于的聚类序号是否已经全部是其离得最近的聚类，如果是或者达到最大的迭代次数，那么结束算法
			int okCount = 0;
			for (int i = 0; i < tsLength; i++) {
				if (nearestMeans[i] == assignMeans[i])
					okCount++;
			}
			System.out.println("Count = " + okCount);
			if (okCount == tsLength || iterNum >= 10)
				break;
			// 6、如果前面条件不满足，那么需要重新聚类再进行一次迭代，需要修改每个聚类的成员和每个点属于的聚类信息
			clusterMember.clear();
			for (int i = 0; i < tsLength; i++) {
				assignMeans[i] = nearestMeans[i];
				if (clusterMember.containsKey(nearestMeans[i])) {
					clusterMember.get(nearestMeans[i]).add(i);
				} else {
					mem.clear();
					mem.add(i);
					Vector<Integer> tempMem = new Vector<Integer>();
					tempMem.addAll(mem);
					clusterMember.put(nearestMeans[i], tempMem);
				}
			}
			// 7、重新计算每个聚类的中心点!
			for (int i = 0; i < K; i++) {
				if (!clusterMember.containsKey(i)) {// 注意k-means可能产生空聚类
					continue;
				}
				Map<String, Double> newMean = computeNewMean(
						clusterMember.get(i), allTestSampleMap, testSampleNames);
				Map<String, Double> tempMean = new TreeMap<String, Double>();
				tempMean.putAll(newMean);
				meansMap.put(i, tempMean);
			}
		}
		// 8、形成聚类结果并且返回
		Map<String, Integer> resMap = new TreeMap<String, Integer>();
		for (int i = 0; i < tsLength; i++) {
			resMap.put(testSampleNames[i], assignMeans[i]);
		}
		return resMap;
	}

	/**
	 * 计算当前聚类新的中心，采用向量平均
	 * 
	 * @param clusterM
	 *            该点到所有聚类中心的距离
	 * @param allTestSampleMap
	 *            所有测试样例的<对象名，向量>构成的map
	 * @param testSampleNames
	 *            所有测试样例文件名构成的数组
	 * @return Map<String, Double> 新的聚类中心的向量
	 */
	private static Map<String, Double> computeNewMean(Vector<Integer> clusterM,
			Map<String, Map<String, Double>> allTestSampleMap,
			String[] testSampleNames) {
		double memberNum = (double) clusterM.size();
		Map<String, Double> newMeanMap = new TreeMap<String, Double>();
		Map<String, Double> currentMemMap = new TreeMap<String, Double>();
		for (Iterator<Integer> it = clusterM.iterator(); it.hasNext();) {
			int me = it.next();
			currentMemMap = allTestSampleMap.get(testSampleNames[me]);
			Set<Map.Entry<String, Double>> currentMemMapSet = currentMemMap
					.entrySet();
			for (Iterator<Map.Entry<String, Double>> jt = currentMemMapSet
					.iterator(); jt.hasNext();) {
				Map.Entry<String, Double> ne = jt.next();
				if (newMeanMap.containsKey(ne.getKey())) {
					newMeanMap.put(ne.getKey(), newMeanMap.get(ne.getKey())
							+ ne.getValue());
				} else {
					newMeanMap.put(ne.getKey(), ne.getValue());
				}
			}
		}

		Set<Map.Entry<String, Double>> newMeanMapSet = newMeanMap.entrySet();
		for (Iterator<Map.Entry<String, Double>> jt = newMeanMapSet.iterator(); jt
				.hasNext();) {
			Map.Entry<String, Double> ne = jt.next();
			newMeanMap
					.put(ne.getKey(), newMeanMap.get(ne.getKey()) / memberNum);
		}
		return newMeanMap;
	}

	/**
	 * 找出距离当前点最近的聚类中心
	 * 
	 * @param double[][] 点到所有聚类中心的距离
	 * @return i 最近的聚类中心的序 号
	 */
	private static int findNearestMeans(double[][] distance, int m) {
		double minDist = 10;
		int j = 0;
		for (int i = 0; i < distance[m].length; i++) {
			if (distance[m][i] < minDist) {
				minDist = distance[m][i];
				j = i;
			}
		}
		return j;
	}

	/**
	 * 计算两个点的距离
	 * 
	 * @param map1
	 *            点1的向量map
	 * @param map2
	 *            点2的向量map
	 * @return double 两个点的欧式距离
	 */
	private static double getDistance(Map<String, Double> map1,
			Map<String, Double> map2) {
		return 1 - computeSim(map1, map2);
	}

	/**
	 * 计算两个文本的相似度
	 * 
	 * @param testWordTFMap
	 *            文本1的<单词,词频>向量
	 * @param trainWordTFMap
	 *            文本2<单词,词频>向量
	 * @return Double 向量之间的相似度 以向量夹角余弦计算（加上注释部分代码即可）或者向量内积计算（不加注释部分，效果相当而速度更快）
	 */
	private static double computeSim(Map<String, Double> testWordTFMap,
			Map<String, Double> trainWordTFMap) {
		double mul = 0;// , testAbs = 0, trainAbs = 0;
		Set<Map.Entry<String, Double>> testWordTFMapSet = testWordTFMap
				.entrySet();
		for (Iterator<Map.Entry<String, Double>> it = testWordTFMapSet
				.iterator(); it.hasNext();) {
			Map.Entry<String, Double> me = it.next();
			if (trainWordTFMap.containsKey(me.getKey())) {
				mul += me.getValue() * trainWordTFMap.get(me.getKey());
			}
			// testAbs += me.getValue() * me.getValue();
		}
		// testAbs = Math.sqrt(testAbs);

		/*
		 * Set<Map.Entry<String, Double>> trainWordTFMapSet =
		 * trainWordTFMap.entrySet(); for(Iterator<Map.Entry<String, Double>> it
		 * = trainWordTFMapSet.iterator(); it.hasNext();){ Map.Entry<String,
		 * Double> me = it.next(); trainAbs += me.getValue()*me.getValue(); }
		 * trainAbs = Math.sqrt(trainAbs);
		 */
		return mul;// / (testAbs * trainAbs);
	}

	/**
	 * 获取k-means算法迭代的初始点
	 * 
	 * @param k聚类的数量
	 * @param Map<String, Map<String, Double>> allTestSampleMap
	 *            所有测试样例的<对象名，向量>构成的map
	 * @return Map<Integer, Map<String, Double>> 初始中心点的Map
	 */
	private static Map<Integer, Map<String, Double>> getInitPoint(
			Map<String, Map<String, Double>> allTestSampleMap, int K) {
		int count = 0, i = 0;
		Map<Integer, Map<String, Double>> meansMap = new TreeMap<Integer, Map<String, Double>>();// 保存K个聚类中心点向量
		Set<Map.Entry<String, Map<String, Double>>> allTestSampleMapSet = allTestSampleMap
				.entrySet();
		for (Iterator<Map.Entry<String, Map<String, Double>>> it = allTestSampleMapSet
				.iterator(); it.hasNext();) {
			Map.Entry<String, Map<String, Double>> me = it.next();
			if (count == i * allTestSampleMapSet.size() / K) {
				meansMap.put(i, me.getValue());
				i++;
			}
			count++;
		}
		return meansMap;
	}

	/**
	 * Stage-5 user clustering
	 * 
	 * @param args
	 */
	public static void execute() {
		// Map<id,Map<keyword,weight>>
		int K = 500;	//number of clusters
		Map<String, Map<String, Double>> allTestSampleMap = DataGetter.getUserSample();
		Map<String, Integer> KmeansClusterResult = new TreeMap<String, Integer>();
		KmeansClusterResult = kMeansCluster(allTestSampleMap, K);
		DataSetter.saveClusteredPreference(KmeansClusterResult);
		System.out.println("Stage-5 Finished!");
	}
}
