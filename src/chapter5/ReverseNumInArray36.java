package com.gt.chapter5;

import java.util.ArrayList;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月10日下午7:53:25
 * 数组中的逆序数。
 * 题目：在数组中两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对总数。
 * 例如{7,5,6,4}，则一共存在5个逆序对分别是（7,6），（7,5），（7,4），（6,4），（5,4）
 * 1.蛮力法
 * 2.归并排序改变而来
 */
public class ReverseNumInArray36 {
	public static int reverseNum =0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ReverseNumInArray36 rna = new ReverseNumInArray36();
		int[] arr={7, 5, 6, 4};
		ArrayList al = new ArrayList();
		al.add(arr);
		
		rna.reverseNumBase(arr);
		rna.mergeSortRe(arr, 0, arr.length-1);
		System.out.println("mergeNum: "+reverseNum);*/
		int a=64;
		int b=140;
		int c=2;
		System.out.println(a^c);

	}

	/**
	 * 蛮力法，强迫用每个i与后面的所有元素比较
	 * @param arr
	 * @return
	 */
	public int reverseNumBase(int[] arr){
		if(arr==null||arr.length<=1)
			return 0;
		int sum=0;
		int n=arr.length;
		for(int i=0; i<n; i++){
			for(int j=i; j<n; j++){
				if(arr[i]>arr[j])
					++sum;
			}
		}
		System.out.println("Base: "+sum);
		return sum;
	}
	
	/**
	 * 利用归并排序求逆序数的对数，最关键的就在合并两个有序子数组的时候计算逆序数目。
	 * @param arr 待排序数组
	 * @param be	第一个有序子数组起始位置，
	 * @param mid	第一个有序子数组结束位置
	 * @param end	第二个有序子数组结束位置
	 */
	public void mergeRe(int[] arr, int be, int mid, int end){
		int[] tmpArr = new int[end-be+1];
		int i=be, j=mid+1, k=0;
		while(i<=mid&&j<=end){
			if(arr[i]<=arr[j])
				tmpArr[k++]=arr[i++];
			else{
				tmpArr[k++]=arr[j++];
				reverseNum += mid-i+1;
				//在后面部分中比arr[i]大的数都比arr[j]大，逆序数要加上mid+1-i。
				//???疑问，算上了比arr[i]大的数，但是后面不计算了吗？不会重复吗？
				//因为在else处，j++了，所以比arr[i]大的数没有机会再和此时的j进行比较了。
			}
		}
		while(i<=mid)
			tmpArr[k++]=arr[i++];
		while(j<=end)
			tmpArr[k++]=arr[j++];
		
		for(int m=0; m<k; m++)
			arr[be+m]=tmpArr[m];
	}
	/**
	 * 归并排序,利用归并排序求解逆序对的数目。
	 * @param arr
	 * @param be
	 * @param end
	 */
	public void mergeSortRe(int[] arr, int be, int end){
		if(be<end){
			int mid=(be+end)/2;
			mergeSortRe(arr, be, mid);
			mergeSortRe(arr, mid+1, end);
			
			mergeRe(arr, be, mid, end);
		}
	}
}
