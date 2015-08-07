package com.gt.chapter5;

import java.util.HashMap;
import java.util.Map;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月13日上午10:17:26
 * 第一次只出现一次的字符
 * 题目：在字符串中找出第一个只出现一次的字符，如输入："abaccdeff"，则输出b；
 * 这样的题目一般用hash来处理，非常节省时间。
 */
public class FirstFirstChar35 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FirstFirstChar35 ffc = new FirstFirstChar35();
		String str = "大家好大家";
		System.out.println(ffc.firstCharAs(str));
	}
	
	/**
	 * 第一次只出现一次的字符，用hash表实现,所有字符都可以，包括中文
	 * @param str
	 * @return
	 */
	public String firstChar(String str){
		//判断边界
		if(str==null||"".equals(str))
			return "Invalid input!";
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();
		char[] tmp = str.toCharArray();
		for(char c:tmp){
			if(countMap.containsKey(c)){
				Integer value = countMap.get(c);
				countMap.put(c, ++value);
			}else
				countMap.put(c, 1);
		}
		
		for(char ch:tmp){
			if(countMap.get(ch)==1)
				return ch+"";
		}
		return "Invalid Input";
	}

	/**
	 * 第一次只出现一次的字符，针对ASCII码
	 * @param str
	 * @return
	 */
	public String firstCharAs(String str){
		if(str==null||"".equals(str))
			return "Invalid input!";
		int[] counts = new int[256];
		int index=0;
		char[] tmp = str.toCharArray();
		for(char ch:tmp){
			index = (int)ch;
			counts[index]++;
		}
		
		for(char ch:tmp){
			index = (int)ch;
			if(counts[index]==1){
				return ch+"";
			}
		}
		return "Invalid Input";
	}
}
