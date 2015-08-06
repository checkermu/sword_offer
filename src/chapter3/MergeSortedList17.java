package com.gt.chapter3;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月4日下午7:41:05
 * 合并两个排序的链表
 */
public class MergeSortedList17 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeSortedList17 ms = new MergeSortedList17();
		Node nA = ms.addTail(5, 4);
		Node nB = ms.addTail(12, 18);
		
//		Node nC = ms.mergeTwoSorted(nA, nB);
		Node nC = ms.reverse(nA, nB);
		
	}
	
	/**
	 * 合并两个升序的链表 迭代
	 * @param nA
	 * @param nB
	 * @return
	 */
	public Node mergeTwoSorted(Node nA, Node nB){
		if(nA==null)
			return nB;
		if(nB==null)
			return nA;
		Node nC = null;
		if(nA.data<nB.data){
			nC = nA;
			nA = nA.next;
		}else{
			nC = nB;
			nB = nB.next;
		}
		
		Node p=nC;
		while(nA!=null && nB!=null){
			if(nA.data<nB.data){
				p.next=nA;
				nA=nA.next;
				
			}else{
				p.next=nB;
				nB=nB.next;
			}
			p=p.next;
		}
		
		if(nA!=null)
			p.next=nA;
		if(nB!=null)
			p.next = nB;
		
		return nC;
	}
	
	/**
	 * 递归
	 * 合并两个排序链表
	 * @param nA
	 * @param nB
	 * @return
	 */
	public Node reverse(Node nA, Node nB){
		Node res = null;
		if(nA==null)
			return nB;
		if(nB==null)
			return nA;
		if(nA.data<nB.data){
			res = nA;
			res.next = reverse(nA.next, nB);
		}else{
			res=nB;
			res.next = reverse(nA, nB.next);
		}
		return res;
	}
	
	
	
	/**不带头结点
	 * 尾插法构造一个排序链表，数据域从begin到end
	 * @param begin
	 * @param end
	 * @return
	 */
	public Node addTail(int begin, int end){
		//查看异常
		if(begin>=end)
			return null;
		Node head = new Node(begin);
		Node p=head;
		begin++;
		while(begin<end){
			Node tmp = new Node(begin);
			p.next=tmp;
			p=tmp;
			begin++;
		}
		
		return head;
	}
	
	

}

class Node{
	public Integer data;
	public Node next;
	
	public Node(Integer i){
		this.data = i;
	}
	
	public Node(Integer i, Node ne){
		this.data = i;
		this.next = ne;
	}
	
}