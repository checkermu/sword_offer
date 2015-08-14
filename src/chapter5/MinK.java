package com.gt.chapter5;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月8日下午10:20:44
 */
public class MinK {
	/** 
     *  
     * @param krr 
     * @param k 
     * @return 
     */  
    public static int[] minK(int krr[],int k){  
        int arr[] = new int[k];  
        for(int i = 0;i<k;i++)  
            arr[i] = krr[i];  
//      buildHeap(arr);
        bulidHeapMy(arr);
        for(int j = k;j<krr.length;j++){  
            if(krr[j]<arr[0]){  
                arr[0] = krr[j];  
                heapAdjust(arr,0,k);  
            }  
        }  
        return arr;  
    }  
    /** 
     * 建最大堆 
     * @param arr 
     */  
    public static void buildHeap(int arr[]){  
        int heapsize = arr.length;  
        for(int i=arr.length/2;i>0;i--)  
            maxHeap(arr,i,heapsize);  
    }  
    /** 
     * 调整为最大堆 
     * @param arr 
     * @param i 
     * @param heapsize 
     */  
    public static void maxHeap(int arr[],int i,int heapsize){  
        int largest = i;  
        int left = 2*i;  
        int right = 2*i+1;  
        if(left<=heapsize&&arr[i-1]<arr[left-1])  
            largest = left;  
        if(right<=heapsize&&arr[largest-1]<arr[right-1])  
            largest = right;  
        if(largest!=i){  
            int temp = arr[i-1];  
            arr[i-1] = arr[largest-1];  
            arr[largest-1] = temp;  
            maxHeap(arr,largest,heapsize);  
        }  
    }  
    public static void main(String[] args) {  
        int krr[] = {4,2,7,8,9,10,14,16};  
        int arr[] = minK(krr,4);  
        for(int i =0;i<arr.length;i++)  
            System.out.println(arr[i]);  
  
    }  
    
    public static void bulidHeapMy(int[] arr){
    	int size = arr.length;
    	for(int i=size/2-1;i>=0; i--){
    		heapAdjust(arr, i, size);
    	}
    			
    }
    
    public static void heapAdjust(int[] arr, int i, int n){
    	int tmp = arr[i]; //当前根的值
    	int j = i*2+1;//左孩子
    	
    	for(;j<n; j=i*2+1){
    		if(j<(n-1)&&arr[j]<arr[j+1]){
    			j=j+1;
    		}
    		if(tmp>arr[j])
    			break;
    		arr[i]=arr[j];
    		i=j;
    	}
    	arr[i]=tmp;
    }
}
