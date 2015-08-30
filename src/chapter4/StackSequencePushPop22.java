package com.gt.sword;

import java.util.Stack;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月30日下午3:47:56
 * 
 * 栈的压入弹出序列 22题
 * 
 * 假定数字都不相同，给一个压入栈的顺序，另一个是弹出序列， 查看是否是符合条件的弹出序列
 * 
 * 比如给定压入序列[1, 2, 3, 4, 5]，
 * 那么弹出序列[4, 5, 3, 2, 1]是符合要求的;而弹出序列[4, 3, 5, 1, 2]就不是符合要求的
 * 
 */
public class StackSequencePushPop22 {

	/**
	 * 思路：用一个辅助栈，按照弹出序列构造，每个否符合则这个序列是符合的，否则就是不符合的！
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackSequencePushPop22 ssp = new StackSequencePushPop22();
		int[] sta = {1,2,3,4,5};
		int[] seq = {4,5,3,1,2};
		System.out.println(ssp.checkSequence(sta, seq));
	}

	/**
	 * 思路：sta是正常压入顺序，seq是待检测的弹出顺序；
	 * 从seq待检测的第一个开始(下标0)--(for循环的i从0开始)辅助栈，栈顶如果相同则弹出，不相同则压入，
	 * 发现tmpStack是空或者栈顶元素和 当前i值不同，则需要压栈，直到相同，怎么压栈呢？从哪里开始压栈？
	 * 最开始不同，肯定是从压入序列的第一个也就是index=0开始，但是如果中间过程呢？
	 * 	中间过程需要注意的是比如压入1,2,3,4,5，而弹出3,4,5,2,1；当第一次弹出3的时候，需要把1,2,都压入tmpStack，
	 * 	同时压入压入3，发现3相同则弹出3；下一个是4，不相同，继续入栈，
	 * 		从哪里开始入栈呢？从上次弹出的下一个开始入栈！！！！！
	 * 		………………所以，内部for循环 j要在index基础上+1；
	 * @param sta
	 * @param seq
	 * @return
	 */
	
	public boolean checkSequence(int[] sta, int[] seq){
		Stack<Integer> stack = new Stack<Integer>();
		if(sta.length!=seq.length||sta.length<=0||seq.length<=0)
			return false;
		int index=-1;
		for(int i=0; i<seq.length; i++){
			if(stack.isEmpty()||stack.peek()!=seq[i]){
				for(int j=index+1; j<seq.length; j++){
					stack.push(sta[j]);
					if(sta[j]==seq[i]){
						stack.pop();
						index=j;
						break;
					}
				}
			}else
				stack.pop();
		}
		
		return stack.isEmpty();
	}
	
	/**
	 * 这个函数明显是有问题的！！！！！！
	 * 问题在哪里呢？ 
	 * 内部for循环的if判断，如果!=则压入，else直接弹出，问题是相等的那个还没有压入呢！！！，造成错误
	 * @param sta
	 * @param seq
	 * @return
	 */
	public boolean checkSequenceWrong(int[] sta, int[] seq){
		Stack<Integer> stack = new Stack<Integer>();
		if(sta.length!=seq.length||sta.length<=0||seq.length<=0)
			return false;
		int index=-1;
		for(int i=0; i<seq.length; i++){
			if(stack.isEmpty()||stack.peek()!=seq[i]){
				for(int j=index+1; j<seq.length; j++){
					if(seq[i]!=sta[j]){
						stack.push(sta[j]);
					}else{
						stack.pop();
						index=j;
					}
				}
			}else
				stack.pop();
		}
		
		return stack.isEmpty();
	}
}
