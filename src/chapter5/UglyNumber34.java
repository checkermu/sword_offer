package com.gt.chapter5;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月12日下午4:46:38
 * 把只包含2,3,5因子的数称为丑数，求从小到大顺序的第1500个丑数，例如，6,8都是丑数；
 * 
 * 思想，最简单的，从1开始，每个数都算一下是不是丑数，这样会很多的时间浪费
 */
public class UglyNumber34 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UglyNumber34 un = new UglyNumber34();
		long startTime = System.currentTimeMillis();
//		un.getUglyNumBase(1500);
		un.getUglyNumberOther(1500);
//		System.out.println(un.min(4, 3, 1));
		
		long endTime = System.currentTimeMillis();
		System.out.println("耗费时间是："+(startTime-endTime)/1000.0+" 秒");
	}
	
	/**
	 * 得到第targetIndex个丑数，最基础的方法，每个数都计算一下是不是丑数，
	 * 是的话就+1，直到丑数个数达到targetIndex
	 * @param targetIndex
	 * @return
	 */
	public int getUglyNumBase(int targetIndex){
		if(targetIndex<=0)
			return 0;
		int number=0;
		int uglyFound = 0;
		while(uglyFound <targetIndex){
			++number;
			
			if(isUgly(number)){
				++uglyFound;
			}
		}
		System.out.println(number);
		return number;
	}
	
	public boolean isUgly(int num){
		while(num%2==0)
			num /= 2;
		while(num%3==0)
			num /= 3;
		while(num%5==0)
			num /= 5;
		
		return (num==1)?true:false;
	}

	/**
	 * 利用一个数组存储丑数数组，只计算丑数，一直往后算，
	 * 重点是如何保证里面的数组里面的丑数是排好序的？
	 * 先假设数组中有若干个数已经排好序了,最大的是M;接下来分析如何生成下一个丑数：该丑数肯定是前面某个丑数乘以2,3,5其中一个的结果，
	 * 每个丑数乘以2的时候，能得到若干个小于等于M的结果(但是这些都在数组中了(因是有序的)),这些不需要再考虑，还会得到若干个大于M的结果，我们只要第一个大于M的丑数，
	 * 同样把已有的每一个丑数乘以3或5都能分别得到第一个大于M的结果，那么M之后的下一个丑数是这三个数之中最小的那个。
	 * 
	 * 前面分析每个丑数都要乘以2,3,5进行比较，事实上不是必须的！
	 * 对乘以2而言，肯定存在某个丑数T2，排在他之前的每一个丑数乘以2得到的结果都会太小，我们只需要记下这个丑数的位置，同时每次生成新的丑数的时候，更新这个T2，
	 * 对于乘以3和5而言，也存在同样的T3,和T5。
	 * @return
	 */
	public int getUglyNumberOther(int targetIndex){
		int[] uglyArr = new int[targetIndex];
		uglyArr[0]=1; //初始化第一个
		int index=0;
		int index_2=0, index_3=0, index_5=0;
		while(index <targetIndex-1){
			int min=min(uglyArr[index_2]*2, uglyArr[index_3]*3, uglyArr[index_5]*5);
			index++;
			uglyArr[index]=min;
			//调整索引
			if(uglyArr[index_2]*2 == min){
				index_2++;//注意更新都是++而并非之前想象的直接变到低。
			}
			if(uglyArr[index_3]*3 == min){
				index_3++;
			}
			if(uglyArr[index_5]*5 == min){
				index_5++;
			}
		}
		System.out.println(Arrays.toString(uglyArr));
		return uglyArr[targetIndex-1];
	}
	
	public int min(int a, int b, int c){
		int min=a>b?b:a;
		return min>c?c:min;
	}
}
