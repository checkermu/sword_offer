package com.gt.chapter6;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月30日下午7:58:06
 * 经典的约瑟夫环问题：把0~n-1共n个数组成一个环，给一个数m；从0开始每次从这个环里删除第m个数字，求最后剩下的数字
 * 可以用环形链表来处理。
 */
public class CircleLastNum45 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CircleLastNum45 cir = new CircleLastNum45();
//		cir.circleSurvice(5, 3);
		cir.myCirList(5,3);
		
	}

	public int circleSurvice(int n, int m){
		if(n<1||m<1){
			return -1;
		}
		List<Integer> list = new LinkedList<>();
		for(int i=0; i<n; i++){
			list.add(i);
		}
		ListIterator iter = list.listIterator();
		int target=-1;
		while(iter.hasNext()){
			for(int j=1; j<m; j++){
				target=(int) iter.next();
			}
		}
		System.out.println(list.get(0));
		return list.get(0);
	}
	
	
	
	public void myCirList(int n, int m){
		if(n<1||m<1)
			return;
		Node first = new Node(0);
		first.next=first; //first自己收尾相连
		Node p=first;
		
		for(int i=1; i<n; i++){
			Node tmp = new Node(i);
			tmp.next=p;//p一直是尾部
			p.next=tmp;
			p=p.next;
		}
		p.next=first;//尾部接头部形成环
		System.out.println("出圈顺序为：");
		while(p != p.next){
			//for 循环后，p是第m个节点的前一个节点
			for(int j=1; j<m; j++){
				p=p.next;
			}
			System.out.println("删除节点:"+p.next.data);
			p=p.next.next;
		}
		
		System.out.println("最后一个是："+p.data);
		
	}
	
}

class Node{
	int data;
	Node next;
	Node(int data){
		this.data=data;	
	}
}