package com.gt.chapter4;

import java.util.LinkedList;
import java.util.List;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月8日上午9:30:57
 * 一个String的排列与组合
 */
public class StringPermutationCombination {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "ABBD";
//		System.out.println(permutation(str).toString());
		permutation(str.toCharArray(),0,4);
	}
	
	/**
	 * 一个String的全排列
	 * @param str
	 */
	public static List<String> permutation(String str){
		List<String> linkedList = new LinkedList<String>();
		if(str.length()<=1){
			linkedList.add(str);
			return linkedList;
		}
		
		for(int i=0; i<str.length(); i++){
			char ch=str.charAt(i);
			//consider the case in which the character may be duplicated 复制
			//有重复的字符,这里并不完整，如果ABBC可以处理， 但是ABCB就没法处理了，
			if(i>0&&ch==str.charAt(i-1)){
				continue;
			}
			String newStr = remove(str, i);
			List<String> newLinkedList = permutation(newStr);
			for(int j=0; j<newLinkedList.size(); j++){
				linkedList.add(ch+newLinkedList.get(j));
			}
		}
		return linkedList;
	}
	
	/**
	 * 传入一个String，删除这个String index为i的字符
	 * @param str
	 * @param i
	 * @return
	 */
	public static String remove(String str, int i){
		if(i==0)
			return str.substring(1, str.length());
		if(i==str.length()-1)
			return str.substring(0, i);
		return str.substring(0, i)+str.substring(i+1, str.length());
	}
	
	
	public static List<String> permutation2(String str){
		List<String> strList = new LinkedList<String>();
		if(str.length()<=1){
			strList.add(str);
			return strList;
		}
		//先固定一个，剩下的再排列
		for(int i=0; i<str.length(); i++){
			char ch=str.charAt(i);
			
			String newStr = remove(str,i);
			List<String> strNewList = permutation2(newStr);
			
			for(int j=0; j<strNewList.size(); j++){
				strList.add(ch+strNewList.get(j));
			}
		}
		
		return strList;
	}

	public static void swap(char[] arr, int idx1, int idx2) {  
	    char temp = arr[idx1];  
	    arr[idx1] = arr[idx2];  
	    arr[idx2] = temp;  
	}  
	  
	public static void permutation(char[] arr, int index, int size) {  
	    if (index == size) {  
	        for (int i = 0; i < arr.length; i++) {  
	            System.out.print(arr[i] + " ");  
	        }  
	        System.out.println();  
	    } else {  
	        for (int i = index; i < size; i++) {  
	            if(i != index && arr[i] == arr[index])  
	                continue;  
	            swap(arr, i, index);  
	            permutation(arr, index+1, size);  
	            swap(arr, i, index);  
	        }  
	    }  
	}  
}
