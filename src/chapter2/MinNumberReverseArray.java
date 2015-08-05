package com.gt.chapter2;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月4日下午8:17:56
 * 旋转数组的最小数字。
 * 原先是有序的数组，把不确定的几个数放到了后面，寻找这个数组中的最小值！
 * 
 * 	最直观的方法就是遍历一遍数组即可得到最小值， 不是个好的方法
 * 
 */
public class MinNumberReverseArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinNumberReverseArray mra = new MinNumberReverseArray();
		int[] array = {1,1,0,1,1,1};
		System.out.println(mra.min(array, array.length));

	}
	
	/**
	 * first  my
	 * @param array
	 * @return
	 */
	public int minNum(int[] array){
		if(array==null)
			return Integer.MAX_VALUE;
		if(array.length==1)
			return array[0];
		int begin =0; 
		int end = array.length-1;
		if(array[end]>array[begin])
			return array[begin];
		while(end-begin!=1){
			int mid = (begin+end)/2;
			if(array[begin]==array[end]&&
					array[mid]==array[end])
				return minOrder(array, begin, end);
			if(array[mid]>array[begin]){
				begin = mid;
				continue;
			}
			if(array[mid]<array[end]){
				end = mid;
			}
		}
		
		return array[end];
	}

	/**
	 * come from book
	 * @param array
	 * @param length
	 * @return
	 */
	public int min(int[] array, int length){
		if(array==null||length<=0){
			return Integer.MAX_VALUE;
		}
		int index1 =0;
		int index2 = length-1;
		int mid = index1;
		while(array[index1]>=array[index2]){
			if(index2-index1==1){
				mid = index2;
				break;
			}
			mid = (index1+index2)/2;
			if(array[index1]==array[index2]&&
					array[mid]==array[index1])
				return minOrder(array, index1, index2);
			if(array[mid]>=array[index1])
				index1 = mid;
			else if(array[mid]<=array[index2])
				index2=mid;
		}
		
		return array[mid];
	}
	
	/**
	 * 在三个数相等的里面顺序寻找最小值
	 * @param array
	 * @param index1
	 * @param index2
	 * @return
	 */
	public int minOrder(int[] array, int index1, int index2){
		int result = array[index1];
		for(int i=index1+1; i<=index2; i++){
			if(result>array[i])
				result = array[i];
		}
		return result;
	}
}
