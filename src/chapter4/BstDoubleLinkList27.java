package com.gt.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月5日上午9:56:33
 * 二叉搜索树转换为双向链表
 * 	题目：
	输入一棵二叉搜索树，将该树转换成一个排序的双向链表。
	要求不能创建任何新的结点，只调整指针的指向。
	10
	/ \
	6 14
	/ \ / \
	4 8 12 16
	转换成双向链表
	4=6=8=10=12=14=16
 */
public class BstDoubleLinkList27 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BstDoubleLinkList27 bd = new BstDoubleLinkList27();
		int[] a = {10, 6, 14, 4, 8, 12, 16};
		Node root = bd.createTree(a);
		
//		Node head = bd.trans(root);
//		bd.transTree(root);
		bd.transTree2(root);
		//此时root并非是新双向链表的head,需要一直.left
		while(root.left!=null)
			root = root.left;
		System.out.println(root.data);
	}

	/**
	 * 中序遍历，因为中序遍历是按照从小到大顺序而来，
	 * 记录上次访问节点 prev，当前节点为current；
	 * 则prev->right = current; current->left = prev；
	 * 更新prev；
	 * 
	 * 在java中递归调用，一个全局变量()，需要声明为类变量，否则在递归返回上一层的时候当前层内容传不会上一次。
	 */
	
	private Node lastNode;
	
	public void transTree(Node root){
		if(root==null)
			return;
		Node curr = root;
		if(curr.left!=null)
			transTree(curr.left);
		curr.left=lastNode;
		if(lastNode!=null)
			lastNode.right=curr;
		lastNode = curr;
		if(curr.right!=null)
			transTree(curr.right);
	}
	
	
	public void transTree2(Node root){
		if(root!=null){
			transTree2(root.left);
			if(lastNode!=null){
				lastNode.right=root;
				root.left=lastNode;
			}
			lastNode = root;
			transTree2(root.right);
		}
	}
	
	public void transTreeBack(Node root){
		if(root ==null)
			return;
		Node curr = root;
		if(curr.left!=null)
			transTreeBack(curr.left);
		curr.left=lastNode;
		if(lastNode!=null)
			lastNode.right=curr;
		lastNode =curr;
		if(curr.right!=null)
			transTreeBack(curr.right);
	}
	
	/**
	 * 错误的函数，直接这样传递lastNode是传递不成功的！只是当前每次递归中的临时变量，返回到上一层后失效。
	 * @param root
	 * @return
	 */
	public Node trans(Node root){
		Node lastNode = null;
		transNode(root, lastNode);//开始递归
		Node head = lastNode;
		while(head!=null&&head.left!=null)
			head = head.left;
		
		return head;
	}
	
	/**错误！！！
	 * 虽然模仿C语言版本，但是是失效的，因为在lastNode的地方
	 * @param root
	 * @param lastNode
	 */
	public void transNode(Node root, Node lastNode){
		if(root==null)
			return;
		Node curr = root;
		if(curr.left!=null){
			transNode(curr.left, lastNode);
		}
		
		curr.left=lastNode;  //当前的左是prev
		if(lastNode!=null)
			lastNode.right=curr; //prev不为空的前提下设置其right
		//更新prev
		lastNode = curr;
		if(curr.right!=null){
			transNode(curr.right, lastNode);
		}
	}
	
	
	
	/**
	 * 根据数组元素构造一棵二叉搜索树
	 * @param data
	 * @return
	 */
	public Node createTree(int[] data){
		List<Node> listNode = new ArrayList<Node>();
		for(int tmp:data){
			Node node = new Node(tmp);
			listNode.add(node);
		}
		//完全二叉树最后一个根节点的下标
		int lastRootIndex = data.length/2-1;
		for(int i=lastRootIndex; i>=0; i--){
			int left = i*2+1;
			Node root = listNode.get(i);
			Node leftNode = listNode.get(left);
			root.left=leftNode;
			//是否有右孩子
			if(left+1<data.length){
				Node rightNode = listNode.get(left+1);
				root.right = rightNode;
			}
		}
		
		Node head = listNode.get(0);
		return head;
		
	}
	
	
}

/**
 * 二叉搜索树的节点，为简单而言全部设置为public
 * @author checkermu
 *
 */
class Node{
	public int data;
	public Node left;
	public Node right;
	
	public Node(){}
	public Node(int d){
		this.data = d;
	}
	
	public Node(int d, Node l, Node r){
		this.data=d;
		this.left=l;
		this.right=r;
	}
	
}
