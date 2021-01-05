package com.xuzhihao.test;

import java.util.Arrays;

public class Sort {

	static int[] data = { 3, 6, 10, 2, 19, 1 };

	public static void main(String[] args) {
		int a = 3, b = 5;
		a = a + b;// a==8,b==5
		b = a - b;// a==8,b==3
		a = a - b;// a==5,b==3
		System.out.println(a + "," + b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a + "," + b);
		

		insert();// 插入排序
//		bubble();//冒泡排序
//		BinaryMerge();//归并排序

		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}

	/**
	 * 插入排序
	 */
	public static void insert() {
		for (int i = 1; i < data.length; i++) {
			for (int j = i; j > 0; j--) {
				if (data[j] < data[j - 1]) {
					int temp = data[j - 1];
					data[j - 1] = data[j];
					data[j] = temp;
				}
			}
		}
	}

	/**
	 * 冒泡排序
	 * 
	 */
	public static void bubble() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length - 1; j++) {
				if (data[j] > data[j + 1]) {
					int temp = data[j];
					data[j] = data[j + 1];
					data[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 归并排序
	 */
	public static void BinaryMerge() {
		int data[] = { 9, 5, 6, 8, 0, 3, 7, 1, 11 };
		megerSort(data, 0, data.length - 1);
		System.out.println(Arrays.toString(data));
	}

	// 分治
	public static void megerSort(int data[], int start, int end) {
		int mid = (start + end) / 2;
		if (start < end) {
			megerSort(data, start, mid);
			megerSort(data, mid + 1, end);
			// 左右归并
			meger(data, start, mid, end);
		}
	}

	// 合并
	public static void meger(int data[], int start, int mid, int end) {
		int temp[] = new int[data.length];
		int point1 = start;// 左边第一个位置
		int point2 = mid + 1;// 右边第一个位置

		int index = start;

		// 把较小的数先移到新数组中
		while (point1 <= mid && point2 <= end) {
			if (data[point1] <= data[point2]) {
				temp[index] = data[point1];
				index++;
				point1++;
			} else {
				temp[index] = data[point2];
				index++;
				point2++;
			}
		}
		// 把左边剩余的数移入数组
		while (point1 <= mid) {
			temp[index++] = data[point1++];
		}
		// 把右边边剩余的数移入数组
		while (point2 <= end) {
			temp[index++] = data[point2++];
		}
		// 把新数组中的数覆盖nums数组
		for (int i = start; i <= end; i++) {
			data[i] = temp[i];
		}
	}
}
