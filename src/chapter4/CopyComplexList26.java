package com.gt.chapter4;

import java.util.HashMap;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月4日下午8:29:44
 * 剑指offer 26题： 复杂链表复制
 * 复杂链表
 * Node{
 * 	data;   pNext;  pSibling; 
 * }
 * pSibling 指向任意一个节点可能为null；
 * 
 * 三种方案，暴力法，空间换时间法；分治法；
 * 
 */
public class CopyComplexList26 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CopyComplexList26 cc = new CopyComplexList26();
		
		CNode h1 = new CNode(1);
		CNode h2 = new CNode(2);
		CNode h3 = new CNode(3);
		CNode h4 = new CNode(4);
		CNode h5 = new CNode(5);
		
		h1.next=h2; h2.next=h3; h3.next=h4; h4.next=h5;
		h1.psNext=h3; h4.psNext=h2; h5.psNext=h1;
		System.out.println("原先的数据");
		CNode t = h1;
		while(t!=null){
			System.out.println(t.toString());
			t=t.next;
		}
		
		System.out.println("复制后的————————————");
		
//		CNode copy = cc.complexCopy(h1);   //用分治法复制
		
		CNode copy = cc.copyFromMap(h1);	//用空间换取时间
		
		t=copy;
		while(t!=null){
			System.out.println(t.toString());
			t=t.next;
		}
		
	}	
	
	
	/**
	 * 使用map的办法，
	 * 复制为新链表的同时
	 * <old, new>放到map中
	 */
	public CNode copyFromMap(CNode head){
		HashMap<CNode, CNode> map = new HashMap<CNode, CNode>();
		CNode nHead = new CNode(11111111);
		CNode copy = nHead;//注意都要弄个别名
//		nHead.next=copy;
		CNode ori = head;//弄个别名，不然到后面找不到了。
		
		while(ori!=null){
			CNode tmp = new CNode(ori.data);
			map.put(ori, tmp);
			copy.next=tmp;
			
			ori=ori.next;
			copy=copy.next;
		}
		
		ori=head;
		copy = nHead.next;
		while(ori!=null){
			CNode ps = ori.psNext;
			if(ps!=null){
				copy.psNext = map.get(ps);
			}
			copy=copy.next;
			ori = ori.next;
		}
		
		return nHead.next;
	}
	
	
	
	
	

	/**
	 * 分治法方案
	 * 先复制到每个链表节点的后面；再链接psNext；再分隔开两个链表
	 */
	
	public CNode complexCopy(CNode head){
		copyNode(head); //先复制到后面
		connectPS(head);//psNext链接
		return splitNode(head);
	}
	
	
	public void copyNode(CNode head){
		CNode p = head;
		while(p!=null){
			CNode tmp = new CNode();
			tmp.data=p.data;
			tmp.next=p.next;
			tmp.psNext=null;
			p.next = tmp;
			
			p=tmp.next;
		}
		
//		return head;
	}
	//重复的函数
	public void copyNodeBack(CNode head){
		CNode p = head;
		while(p!=null){
			CNode tmp = new CNode();
			tmp.data=p.data;
			tmp.next=p.next;
			tmp.psNext=null;
			//上面完成了新节点的复制并且新的next连接到了原先的next;
			//下面要连接old与新
			p.next=tmp;
			p=tmp.next;//往下推
			
		}
	}
	
	public void connectPS(CNode head){
		CNode p = head;
		while(p!=null){
			CNode tmp = p.psNext;//注意为null的时候
			if(tmp!=null){
				p.next.psNext = tmp.next;
			}
			p = p.next.next;
		}
		
//		return head;
	}
	
	//重复函数
	public void connectionPSBack(CNode head){
		CNode p =head;
		while(p!=null){
			CNode tmp = p.psNext; //找到old节点的 随机指针指向的节点。
			if(tmp!=null){
				p.next.psNext=tmp.next;//明显随机指针不为空的话,copy的随机指针指向这个的next
			}
			p=p.next.next;
		}
	}
	
	public CNode splitNode(CNode head){
		CNode p = head;
		CNode nHead = null;
		CNode copy=null;
		if(p!=null){
			nHead = p.next;
			copy=p.next;
			p.next=copy.next;//重新连接p原来的next
			p=p.next;//p后移
		}
		while(p!=null){
			copy.next=p.next;;//copy的节点连接
			copy=copy.next;//后移
			p.next = copy.next;//找到原先的
			p=p.next;
		}
		return nHead;
	}
	
	//重复函数
	public CNode splitBack(CNode head){
		CNode p = head;//旧链表的头
		CNode nHead =null;//copy链表头
		CNode copy = null;
		if(p!=null){
			nHead = p.next;
			copy = p.next;
			//上面两行设置了新链表头，下面把原先的找回来
			p.next = copy.next;
			p=p.next;
		}
		//找回来后
		while(p!=null){
			copy.next = p.next;
			copy = copy.next;
			//上面链接了新的，下面把旧的链接好
			p.next=copy.next;
			p=p.next;
		}
		
		return nHead;
	}
	
	
	
}

/**
 * 复杂链表 为简单而言全部设置为public
 * @author checkermu
 *
 */
class CNode{
	public int data;
	public CNode next;
	public CNode psNext;
	
	public CNode(){}
	
	public CNode(int d){
		this.data = d;
	}
	
	public CNode(int d, CNode n, CNode p){
		this.data =d;
		this.next = n;
		this.psNext=p;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(" data :"+data);
		sb.append(" next = "+(null==next?"null":next.data));
		sb.append(" psNext= "+(null==psNext?"null":psNext.data));
		
		return sb.toString();
	}
}
