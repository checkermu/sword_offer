package com.gt.sword;

import java.util.LinkedList;
import java.util.Queue;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月30日上午10:33:56
 * 
 * 两个队列实现栈
 */
public class TwoQueueStack7 {

	/**
	 * 思想：队列先进先出，栈是先进后出，
	 * 队1, 队2, 
	 * 队1只负责入队；队2中转用来弹出
	 * 1、入队只放到队1；
	 * 2、弹出(1)、队1不为空，中转到队2只剩下一个弹出；(2)队1为空，队2不空，队2中转到队1，只剩下一个弹出;(3)全为空异常
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TwoQueueStack7 stack = new TwoQueueStack7();
		stack.pushStack(10);
		stack.pushStack(8);
		
		System.out.println(stack.popStack());
		
		stack.pushStack(6);
		stack.pushStack(4);
		System.out.println(stack.popStack());
		System.out.println(stack.popStack());
		System.out.println(stack.popStack());
	}

	private Queue<Integer> que1;
	private Queue<Integer> que2;
	
	public TwoQueueStack7(){
		que1 = new LinkedList<Integer>();
		que2 = new LinkedList<Integer>();
	}
	
	//入栈操作
	public void pushStack(int i){
		que1.offer(i);
	}
	
	//弹出栈
	public int popStack() throws Exception{
		if(que1.size()>0){
			while(que1.size()>1){
				que2.offer(que1.poll());
			}
			return que1.poll();
		}else if(que2.size()>0){
			while(que2.size()>1){
				que1.offer(que2.poll());
			}
			return que2.poll();
		}else{
			throw new Exception("the stack is empty");
		}
	}
}
