package com.gt.chapter5;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月10日上午9:59:20
 */
public class MaxSumOfArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaxSumOfArray msa = new MaxSumOfArray();
		int[] array = {1, -2, 3, 5, -1, 2};
		int[] array2={-9, -2, -3, -5, -3};
		int[] array3={9, -8, -3, 15, -7, 8, 2, -1};
//		System.out.println(msa.maxSum4(array));
		System.out.println(Arrays.toString(msa.maxSumStartEnd(array3)));
	}
	
	/**
	 * 连续子数组的最大值,求解方法，
	 * @param array
	 * @return
	 */
	public int maxSum1(int[] array){
		int maxNum = Integer.MIN_VALUE;
		int n = array.length;
		for(int i=0; i<n; i++){
			int sum=0;
			//要求是连续子数组的最大值，
			//连续的开头必定是从0到n-1每个都来一遍
			for(int j=i; j<n; j++){
				sum+=array[j];
				if(sum>maxNum){
					maxNum =sum;
				}
			}
		}
		return maxNum;
	}
	
	/**
	 * 第二种解法，来自剑指offer书
	 * @param array
	 * @return
	 */
	public int maxSum3(int[] array){
		int nCurSum=0;
		int nMax=Integer.MIN_VALUE;
		for(int i=0; i<array.length; i++){
			if(nCurSum<=0){
				nCurSum=array[i];
			}else{
				nCurSum += array[i];
			}
			
			if(nCurSum>nMax){
				nMax = nCurSum;
			}
		}
		
		return nMax;
	}

	/**
	 * 连续子数组最大值，自己写的有问题？当全是负数的时候，会跳过第二个负数直接去第三个了
	 * @param array
	 * @return
	 */
	public int maxSum2(int[] array){
		int n = array.length;
		int tmpSum = 0;
		int MaxSum = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++){
			tmpSum+=array[i];
			if(tmpSum>MaxSum){
				MaxSum = tmpSum;
			}else if((i+1)<n){
				if(tmpSum+array[i+1]<array[i+1]){
					tmpSum=array[i+1];
					MaxSum=array[i+1];
					i++;
				}
			}
		}
		return MaxSum;
	}
	
	/**
	 * 动态规划求解
	 * @param array
	 * @return
	 */
	public int maxSum4(int[] array){
		int n= array.length;
		int[] end = new int[n];
		int[] all = new int[n];
		
		end[0]=all[0]=array[0];
		for(int i=1; i<n; i++){
			end[i]=max(end[i-1]+array[i], array[i]);
			all[i]=max(end[i], all[i-1]);
		}
		return all[n-1];
		
	}
	
	/**
	 * 返回最大子数组的和以及始末位置，
	 * @param array
	 * @return []array, [0]是最大子数组和，[1]是起始位置,[2]是结束位置
	 */
	public int[] maxSumStartEnd(int[] array){
		int[] result=new int[3];
		int maxSum = Integer.MIN_VALUE;
		int sum=0;
		int curStart=0,start=0,end=0;
		int n = array.length;
		for(int i=0; i<n; i++){
			if(sum<0){
				sum=array[i];
				curStart=i;
			}else{
				sum+=array[i];
			}
			
			if(sum>maxSum){
				maxSum = sum;
				start=curStart;
				end=i;
			}
		}
		result[0]=maxSum;
		result[1]=start;
		result[2]=end;
		return result;
	}
	
	/**
	 * 辅助方法，返回两个数中的最大值
	 * @param a
	 * @param b
	 * @return
	 */
	public int max(int a, int b){
		return a>b?a:b;
	}
}
