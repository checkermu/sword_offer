package com.gt.chapter5;

import java.util.Stack;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月13日下午2:31:54
 * 题目：确定两个链表的第一个公共节点
 * 引申题目：1、一个链表是否有环，确定环入口
 * 		  2、两个链表如果可能有环，如何判断是否相交，并找到第一个公共节点。
 */
public class PublicNodeLink37 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PublicNodeLink37 pnl = new PublicNodeLink37();
		Nodes n11 = new Nodes(1);
		Nodes n12 = new Nodes(2);
		Nodes n13 = new Nodes(3);
		Nodes n131 = new Nodes(7);
		Nodes n14 = new Nodes(8);
		Nodes n15 = new Nodes(9);
		n11.next = n12;
		n12.next=n13;
		n13.next=n131;
		n131.next=n14;
		n14.next=n15;
		n15.next=n13;
		
		Nodes n21 = new Nodes(4);
		Nodes n211 = new Nodes(5);
		Nodes n212 = new Nodes(7);
		Nodes n22 = new Nodes(8);
		Nodes n23 = new Nodes(9);
		n21.next=n211;
		n211.next=n212;
		n212.next=n22;
		n22.next=n23;
		
//		pnl.getSameNode(n11, n21);
//		pnl.getEqual(n11, n21);
		
		pnl.findRingEntrance(n11);
	}

	/**
	 * 利用栈来得到两个链表第一个相等的地方
	 * @param n1
	 * @param n2
	 * @return
	 */
	public Nodes getSameNode(Nodes n1, Nodes n2){
		Stack<Nodes> s1 = new Stack<Nodes>();
		Stack<Nodes> s2 = new Stack<Nodes>();
		Nodes t1=n1, t2=n2;
		while(t1!=null){
			s1.add(t1);
			t1=t1.next;
		}
		while(t2!=null){
			s2.add(t2);
			t2=t2.next;
		}
		Nodes tmp1 = s1.pop();
		Nodes tmp2 = s2.pop();
		Nodes equal = new Nodes();
		while(tmp1.value!=tmp2.value){
			equal = tmp1;
			tmp1 = s1.pop();
			tmp2 = s2.pop();
		}
		System.out.println(equal.value);
		return equal;
	}
	
	/**
	 * 先让两个链表长度相等
	 * @param n1
	 * @param n2
	 * @return
	 */
	public Nodes getEqual(Nodes n1, Nodes n2){
		int l1 = 0, l2=0;
		Nodes t1 = n1, t2=n2;
		while(t1!=null){
			++l1;
			t1=t1.next;
		}
		while(t2!=null){
			++l2;
			t2=t2.next;
		}
		
		int diff = 0;
		if(l1>=l2){
			diff = l1-l2;
			t1=n1;
			t2=n2;
		}
		else{ 
			diff= l2-l1;
			t1=n2;
			t2=n1;
		}
		for(int i=0; i<diff; i++){
			t1 = t1.next;
		} //现在 t1和t2代表的长度都相等了
		while(t1!=null && t2!=null &&(t1.value != t2.value)){
			t1 = t1.next;
			t2=t2.next;
		}
		Nodes equal = t1;
		
		return equal;
	}

	
	/**
	 * 判断一个链表是否有环，有环就返回环入口
	 * 
	 */
	public Nodes findRingEntrance(Nodes head){
		if(head==null)
			return null;
		Nodes fast = head;
		Nodes low = head;
		boolean flag=false;
		while(fast!=null && fast.next!=null){
			low = low.next;
			fast = fast.next.next;
			if(low.value==fast.value){
				flag=true;
				break;
			}
		}
		
		if(!flag)
			return null;
		low = head;
		while(fast!=null && fast.value != low.value){
			low = low.next;
			fast = fast.next;
		}
		
		return fast;
	}
	
	
	
	/**
	 * 判断两个可能有环的链表是否相交，并返回第一个相交节点
	 * 先看A,B是否有环
	 * 都无环，则变为不循环链表第一个相交节点(采用后边节点对齐法)；
	 * 一个有环，一个没有，肯定不想交
	 * 都有环，找到一个环相遇的节点，判断是否在另一个链表中，
	 * 还有情况在分支就相同,
	 * 入口点不同的两个环
	 */
	
	public Nodes findTwoCirSame(Nodes nA, Nodes nB){
		Nodes aC = findRingEntrance(nA);
		Nodes bC = findRingEntrance(nB);
		
		Nodes res = null;
		if(aC==null && bC==null){//都无环
			res = findSameNoCir(nA, nB);
		}else if(aC !=null && bC!=null && aC.value==bC.value){
			Nodes t1 = aC.next; Nodes t2=bC.next; //先缓存一下
			aC.next=null; bC.next=null;
			res = findSameNoCir(nA, nB);
		}else if(aC !=null && bC!=null && aC.value!=bC.value){//同样的环但是入口不同
			res = aC.next;
			while(res.value!=bC.value && res.value!=aC.value){
				res = res.next;
			}
			if(res.value!=bC.value)
				res=null;
		}else{
			res = null;
		}
		return res;
	}
	
	
	public Nodes findSameNoCir(Nodes nA, Nodes nB){
		Nodes a = nA;  Nodes b = nB;
		int alength =0, blength=0;
		while(a!=null){
			a=a.next;
			alength++;
		}
		while(b!=null){
			b=b.next;
			blength++;
		}
		int diff=0;
		Nodes t=null;
		if(alength<blength){
			diff = blength-alength;
			a=nB;
			b=nA;
		}else{
			diff = alength-blength;
			a=nA;
			b=nB;
		}
		
		for(int i=0; i<diff; i++){
			a = a.next;
		}
		while(a!=null && b!=null && a.value!=b.value){
			a=a.next; b=b.next;
		}
		return a; //返回相交的第一个节点
	}
}

/*public class Nodes {
	public int value;
	public Nodes next;
	
	public Nodes(){}
	public Nodes(int value){
		this.value = value;
	}
}*/
