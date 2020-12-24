package com.xuzhihao.design.wechat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 红包发放
 * 
 * @author Administrator
 *
 */
public class RedPackage {

	/**
	 * 二倍均值发红包算法，金额参数以分为单位
	 * 
	 * @param totalAmount    金额(分)
	 * @param totalPeopleNum 数量
	 * @return
	 */
	public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
		List<Integer> amountList = new ArrayList<Integer>();
		Integer restAmount = totalAmount;
		Integer restPeopleNum = totalPeopleNum;
		Random random = new Random();
		for (int i = 0; i < totalPeopleNum - 1; i++) {
			// 随机范围：[1，剩余人均金额的两倍)，左闭右开
			int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
			restAmount -= amount;
			restPeopleNum--;
			amountList.add(amount);
		}
		amountList.add(restAmount);
		return amountList;
	}

	/**
	 * 线段分割算法
	 * 
	 * @param totalAmount    红包金额 分
	 * @param totalPeopleNum 红包个数
	 */
	public static List<Double> divideRedPackage2(Integer totalAmount, Integer totalPeopleNum) {
		List<Integer> list = new ArrayList<Integer>();//线段集合
		List<Double> amountList = new ArrayList<Double>();
		Random random = new Random();
		while (list.size() <= totalPeopleNum - 2) {
			int i = random.nextInt(totalAmount) + 1;// 最低1分钱
			if (list.indexOf(i) < 0) {// 非重复切割添加到集合
				list.add(i);
			}
		}
		Collections.sort(list);
		int flag = 0;
		int fl = 0;
		for (int i = 0; i < list.size(); i++) {
			int temp = list.get(i) - flag;
			flag = list.get(i);
			fl += temp;
			amountList.add(AmountUtil.div(temp, 100));
		}
		// 最后一个红包
		list.add(totalAmount - fl);
		amountList.add(AmountUtil.div(AmountUtil.sub(totalAmount, fl), 100));
		return amountList;
	}

	public static void main(String[] args) {
		List<Integer> amountList = divideRedPackage(100, 10);
		for (Integer amount : amountList) {
			 System.out.println("抢到金额：" + amount);
		}

		List<Double> amountList2 = divideRedPackage2(200, 30);
		for (Double amount : amountList2) {
			System.out.println("抢到金额：" + amount);
		}
	}

}
