package com.gt.chapter4;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月11日下午5:16:38
 * 判断一个后续序列数组（不重复），是否是二叉搜索树
 * 后序根在最后，分为左右， 重复查看左右是否符合要求，否则就是false
 */
public class PostIsBST24 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,7,6,9,11,10, 0,8};
		PostIsBST24 pb = new PostIsBST24();
		System.out.println(pb.verifyBst(array,0, array.length-1));
	}
	
	
	public boolean verify(int[] arr, int be, int end){
		int left=be;
		int right = end-1;
		if(end-be<=1)
			return true;
		while(left<end && arr[end]>arr[left])
			left++; //找到第一个比end大的下标
		while(right>be && arr[end]<arr[right])
			right--;//找到第一个比end小的下标
		
		if(left-1 ==  right || left==right)
			return verify(arr, be, left-1) && verify(arr, left, end-1);
		else
			return false;
	}
	
	/**
	 * 判断一个数组是否是某个二叉搜索树的后序序列，返回
	 * 注意两点：1、使用同一个数组当某个子树  be==end的时候肯定是return true的
	 * 		  2、右子树的时候，起始点i与终点end-1如果i==(end-1)也是直接true的！
	 * @param arr
	 * @param length
	 * @return boolean
	 */
	public boolean verifyBst(int[] arr, int be, int end){
		if(arr==null || (be>end))
			return false;
		if(be==end)
			return true;
		int root = arr[end]; //根的值
		int i=be;
		while(i<=end){
			if(arr[i]>root)
				break;
			i++;
		}//找到不大于根节点的个数。 i是个数，从下标0开始到了第一个大于根的，正好是不大于跟的个数
		//看看后面的是否都大于根
		int j=i;
		for(; j<=end; j++){
			if(arr[j]<root)
				return false;
		}
		//现在判断左子树是否是二叉搜索树
		boolean left = true;
		left = verifyBst(arr,be, i-1);
		
		boolean right = true;
		//copyOfRange([]original, from, to)包括from，不包括to		
//		int[] arrR = Arrays.copyOfRange(arr, i, end-1);
		if(i<end-1){
			right = verifyBst(arr, i, end-1);
		}
		return(left&&right);
	}
	
}
