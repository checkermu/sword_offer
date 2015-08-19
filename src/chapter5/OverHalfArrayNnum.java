package com.gt.chapter5;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月8日下午7:24:56
 * 在数组中出现次数超过一半的数目
 */
public class OverHalfArrayNnum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OverHalfArrayNnum obj = new OverHalfArrayNnum();
		int[] arr = {1,2,3,2,2,2,5,4,2,4,4,4,4,4,4,4,4,4,4};
		
//		System.out.println(obj.overhalf(arr));
//		System.out.println(obj.hafOver(arr, arr.length));
		
		System.out.println(obj.overhalf3(arr));
	}
	
	public int overhalf3(int[] arr){
		if(arr==null)
			return 0;
		int result = arr[0];
		int count=1;
		for(int i=1; i<arr.length; i++){
			if(count==0){
				count=1;
				result=arr[i];
			}else if(result==arr[i]){
				count++;
			}else
				count--;
		}
		return result;
	}
	
	
	/**
	 * 如果是排序的数组，要找出现次数超过一半的很好找
	 * @param arr
	 * @return
	 */
	public int overhalf(int[] arr){
		if(arr==null)
			return 0;
		int tmp=arr[0];
		int count=1;
		for(int i=1; i<arr.length; i++){
			if(tmp==arr[i]){
				count++;
			}else{
				tmp=arr[i];
				count=1;
			}
			if(count>arr.length/2)
				break;
		}
		return tmp;
	}

	/**
	 * 非排序数组，这个思路是按照快速排序分隔而来
	 * @param arr
	 * @param length
	 * @return
	 */
	public int hafOver(int[] arr, int length){
		if(arr==null||length<=0)
			return 0;
		int mid = length>>1;
		int begin=0;int end = length-1;
		int index = partition(arr, begin, end);
		while(index!=mid){
			if(index>mid){
				end = index-1;
				index=partition(arr, begin, end);
			}else{
				begin = index+1;
				index = partition(arr, begin, end);
			}
		}
		int result = arr[index];
		if(index == mid)
			return result;
		else
			return 0;
	}
	
	/**
	 * 快速排序需要的分隔函数
	 * @param arr
	 * @param begin
	 * @param end
	 * @return
	 */
	public int partition(int[] arr, int begin, int end){
		int tmp=arr[begin];
		while(begin<end){
			while(begin<end&&arr[end]>=tmp)
				end--;
			arr[begin]=arr[end];
			while(begin<end&&arr[begin]<=tmp)
				begin++;
			arr[end]=arr[begin];
		}
		arr[begin]=tmp;
		return begin;
	}
	
}
