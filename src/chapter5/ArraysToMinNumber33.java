package com.gt.chapter5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月12日下午3:47:33
 * 输入一个正整数数组，把数组里所有的数字拼接起来排成一个数，打印出拼接出来的所有数字中最小的一个。
 * 15-08-08回顾
 * 可以认为是一个全排列，求全排列最小值，
 * 根本问题是数组中的那些数改放在前面，那些该放在后面，是一个排序问题，
 * 排序的关键是什么呢？不是单纯比较两个数大小(不是比较A和B谁大谁小)，而是比较两个数拼接后的大小，两个数拼接AB或BA，这两个比较大小
 * 如果考虑组合起来的数比较大小的话，还要注意会不会这样组合会不会int溢出，这也是一个隐藏的可能大数问题。
 * 因此用字符串比较。用java的挺简单调用自带函数即可。
 */
public class ArraysToMinNumber33 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArraysToMinNumber33 atmn = new ArraysToMinNumber33();
		int[] array={12, 32, 21, 5, 68, 4, 13,11};
		
		atmn.minNumOfArray(array);
		int re=0;
		re = ("f"+"b").compareTo("b"+"a");
		System.out.println(re);

	}

	/**
	 * 数组排成最小的数，可能是个大数问题，用字符串存储数字，
	 * 思路：把所有的数字排序，转换为String排序。
	 * @param array
	 * @return
	 */
	public String minNumOfArray(int[] array){
		String[] strArr = intArrToStringArr(array);
		Arrays.sort(strArr, new Comparator<String>() {
			public int compare(String o1, String o2){
				return (o1+o2).compareTo(o2+o1);
			}
		});
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<strArr.length; i++){
			sb.append(strArr[i]);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * 将int数组转换为字符串数组
	 * @param array
	 * @return
	 */
	public String[] intArrToStringArr(int[] array){
		if(array==null)
			return null;
		String[] strs = new String[array.length];
		for(int i=0; i<array.length; i++){
			strs[i]=""+array[i];
		}
		return strs;
	}
}
