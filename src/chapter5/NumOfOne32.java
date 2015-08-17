package com.gt.chapter5;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月12日下午3:14:32
 * 输入一个整数n，求从1到n这n个整数中，十进制表示中1出现的次数，比如输入12，则从1到12这些整数中，1/10/11/12,1一共出现了5次
 * 08-10回顾
 * 从个位开始，比如个位是1，那么就是取余10等于1；再看十位，那就除以10，再和10取余，是否等于1
 * 
 */
public class NumOfOne32 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumOfOne32 object = new NumOfOne32();
		
		System.out.println(object.numOfOneBase(123));

	}
	
	/**
	 * 最基础直观解法,耗费时间是 n*lgn;
	 * @param n
	 * @return
	 */
	public int numOfOneBase(int n){
		int sum=0;
		for(int i=1; i<=n; ++i){
			int sigleSum=0;
			int tmp=i;
			while(tmp!=0){
				if(tmp%10==1)
					sigleSum++;
				tmp=tmp/10;
			}
			sum+=sigleSum;
		}
		return sum;
	}
	
	/**
	 * 根据规律得到的解法
	 * @param n
	 * @return
	 */
	public int numOfOneOther(int n){
		return 0;
	}

}
