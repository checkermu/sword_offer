package com.gt.chapter2;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月4日下午9:04:30
 * 
 * 斐波那契数列，节省效率与空间
 */
public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Fibonacci fib = new Fibonacci();
		long beginTime = System.currentTimeMillis();
		System.out.println(fib.fibArray(100));
		long endTime = System.currentTimeMillis();
		System.out.println("耗费时间："+(endTime-beginTime)+"ms");
		
	}

	/**
	 * 递归实现，数字大了后效率非常低
	 * @param n   n=50  12586269025；耗时：111707ms
	 * @return
	 */
	public long fibRecur(int n){
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		return fibRecur(n-1)+fibRecur(n-2);
	}
	
	/**
	 * 效率高了不是一点半点
	 * @param n  还是n=50; 12586269025  耗时：1ms
	 * @return
	 */
	public long fibArray(int n){
		long[] result = {0,1};
		if(n<2){
			return result[n];
		}
		long fibOne = 1;
		long fibTwo = 0;
		long fibN = 0;
		for(int i=2; i<=n; i++){
			fibN = fibOne+fibTwo;
			fibTwo=fibOne;
			fibOne=fibN;
		}
		
		return fibN;
	}
	
}
