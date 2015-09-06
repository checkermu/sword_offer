package com.gt.chapter5;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月29日上午11:03:20
 * 找到序列中少的那两个数，用异或。
 * 后来发现一个问题的变种，
 */
public class FindTwoNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {6,5,4,3,2,0,7,8,9,10,11,12,20,19,0,17,16,15,14,13};
		FindTwoNum ft = new FindTwoNum();
		System.out.println(Arrays.toString(ft.findTwo(num)));
		
	}
	
	/**
	 * 根据异或的性质，将数组分为两半，根据某个二进制位是否为1分。
	 * @param num
	 * @return
	 */
	public int[] findTwo(int[] num){
		int tmp=0;
		int length=num.length;
		for(int i=0; i<length; i++){
			tmp=tmp^num[i];
			tmp=tmp^(i+1);
		}
		int bitIndex = rightBit(tmp);
		int n1=0,n2=0;
		for(int i=0; i<length; i++){
			if(isBit(i+1, bitIndex)){
				n1=n1^(i+1);
			}else{
				n2=n2^(i+1);
			}
			
			if(isBit(num[i], bitIndex)){
				n1=n1^num[i];
			}else{
				n2=n2^num[i];
			}
		}
		return new int[]{n1, n2};
	}

	/**
	 * 找到整数num二进制表示中最右边第一个1
	 * @param num
	 * @return int
	 */
	public int rightBit(int num){
		int bitIndex=0;
		while((num&1)==0){
			num=num>>1;
			++bitIndex;
		}
		return bitIndex;
	}
	
	/**
	 * 判断数num二进制表示中从右边第index位是否为1
	 * @param num
	 * @param index
	 * @return  boolean
	 */
	public boolean isBit(int num, int index){
		num = num>>index;
		if((num&1) !=0)
			return true;
		else
			return false;
	}
	
}
