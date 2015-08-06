package com.gt.chapter3;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月6日上午10:10:59
 * 返回整数中二进制1的个数
 */
public class BitOperation {
	public static void main(String[] args) {
//		System.out.println(numOfOne2(3));
		testI();
	}
	
	/**
	 * 整数二进制表示中1的个数
	 * @param n
	 * @return
	 */
	public static int numOfOne(int n){
		int count=0;
		while(n!=0){
			if((n&1)!=0)
				count++;
			n = n>>>1;	//java可以进行无符号右移， -3的1个数为31
		}
		return count;
	}
	
	/**
	 * 另一种方法，整数二进制中1的个数
	 * @param n
	 * @return
	 */
	public static int numOfOne2(int n){
		int count =0;
		int flag = 1;
		while(flag!=0){
			if((n&flag)!=0)
				count++;
			flag=flag<<1;
		}
		return count;
	}
	
	/**
	 * 整数二进制中1的个数方法3
	 * @param n
	 * @return
	 */
	public static int numOfOne3(int n){
		int count =0;
		while(n!=0){
			n = n&(n-1);
			count ++;
		}
		return count;
	}
	
	/**
	 * 反转一个无符号整数的所有比特位
	 * @param n
	 * @return   //未完成
	 */
	public static int reverse(int n){
		return 0;
	}
	
	public static void testI(){
		int i=0;
		while(i++<10){
			System.out.println(i);
		}
	}
}
