package com.gt.chapter5;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月13日上午10:45:41
 * 35题目  后面的相关题目：差不多都是用hash或者有一个字符数组表示hash
 */
public class Extend35 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Extend35 ex = new Extend35();
		String str = "checkermu";
		ex.getNoRepeat(str);
	}

	/**
	 * 得到一个字符串中不重复的字符串，
	 * 也就是删除字符串中所有重复出现的字符
	 * @param str
	 * @return
	 */
	public String getNoRepeat(String str){
		if(str==null || "".equals(str))
			return "";
		boolean[] bool = new boolean[256];
		for(int i=0; i<256; i++){
			bool[i]=false;
		}
		char[] tmp = str.toCharArray();
		int index=0;
		StringBuilder sb = new StringBuilder();
		for(char ch:tmp){
			index = (int)ch;
			if(bool[index]!=true){
				sb.append(ch);
				bool[index]=true;
			}
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}
}
