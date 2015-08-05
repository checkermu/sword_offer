package com.gt.chapter2;

import java.util.Stack;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月2日上午10:24:48
 * 剑指offer第二章，面试题7：两个栈，实现队列
 * 实现队列的 appendTail,   deleteHead
 */
public class TwoStackToQueue {
	private Stack<Integer> stack1;
	private Stack<Integer> stack2;
	
	public static void main(String[] args) {
		TwoStackToQueue tw = new TwoStackToQueue();
		tw.push(5);
		tw.push(8);
		tw.push(10);
		tw.deleteHead();
		tw.push(12);
		tw.deleteHead();
	}
	
	public TwoStackToQueue(){
		stack1 = new Stack<Integer>();
		stack2 = new Stack<Integer>();
	}
	
	public void push(int data){
		stack1.push(data);
	}
	
	/**
	 * 两个栈实现队列，删除队头的话，把数据全部弹出并压入到另一个栈中，把最上面的弹去即可。
	 * @return
	 */
	public int deleteHead(){
		if(stack2.empty()){
			while(!stack1.empty()){
				int top = stack1.pop();
				stack2.push(top);
			}
		}
		if(stack2.empty())
			return Integer.MIN_VALUE;
		System.out.println("head:"+stack2.peek());
		return stack2.pop();
	}
	
}
