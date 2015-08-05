package com.gt.chapter2;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月4日下午9:25:48
 * 位运算的一些例子。
 */
public class ByteOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ByteOperation bp = new ByteOperation();
		int num=15;
//		System.out.println(bp.numOfOneNormal(num));
		System.out.println(bp.quickOne(num));
	}

	/**
	 * java中的移位操作符，>>右移，32位，要与8取模，因此是右移0位，
	 */
	public void testYW(){
		int num=32;
		System.out.println(num >> 32);  //结果不变，因为变成了右移0
		System.out.println(num >> 33);  //结果变为16，因为是右移1位
	}
	
	
	/**
	 * 二进制中1的个数，
	 * 既然刚刚说了位操作，用这个数与1做位操作，可以得到当前最后一位是0还是1.，如果是负数会出问题？负数二进制的补码形式！！！
	 * @param n
	 * @return
	 */
	public  int numOfOneBinary(int n){
		int count=0;
		while(n!=0){
			if((n&1) !=0)
				count++;
			n = n>>1;
		}
		return count;
	}
	
	/**
	 * 二进制中的1的个数
	 * 上面方法中负数的时候会出问题，是一直和1进行与运算， 换个思路，与运算的对象1一直左移
	 * @param n
	 * @return
	 */
	public int numOfOneNormal(int n){
		int count=0;
		int flag=1;
		while(flag!=0){
			if( (n&flag) !=0 )
				count++;
			flag = flag<<1;
		}
		return count;
	}
	
	/**
	 * 快速方法得到二进制中1的个数
	 * n和n-1进行与运算
	 * @param n
	 * @return
	 */
	public int quickOne(int n){
		int count =0;
		while(n!=0){
			count ++;
			n = (n-1)&n;
		}
		return count;
	}
}
