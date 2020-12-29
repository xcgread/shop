package com.xuzhihao;

import java.util.List;

import com.xuzhihao.wechat.RedPackage;

/**
 * 红包发放
 * 
 * @author Administrator
 *
 */
public class WechatTest {

	public static void main(String[] args) {
		List<Integer> amountList = RedPackage.divideRedPackage(100, 10);
		for (Integer amount : amountList) {
			System.out.println("抢到金额：" + amount);
		}

		List<Double> amountList2 = RedPackage.divideRedPackage2(200, 30);
		for (Double amount : amountList2) {
			System.out.println("抢到金额：" + amount);
		}
	}

}
