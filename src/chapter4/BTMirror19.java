package com.gt.chapter4;

import java.util.Stack;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月11日下午4:03:50
 * 
 * 二叉树的镜像，也就是从根节点开始，每个节点左右子树互换，当然要前序遍历才可以
 * 
 */
public class BTMirror19 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BTMirror19 bm = new BTMirror19();
		BNode  root =bm.buildTree();
		bm.inOrderRe(root);
		System.out.println("中序非递归");
		bm.inOrder2(root);
		
		System.out.println("镜像");
		bm.mirrorBT(root);
		bm.inOrder2(root);
		
	}
	
	/**
	 * 中序递归访问二叉树
	 * @param root
	 */
	public void inOrderRe(BNode root){
		if(root!=null){
			inOrderRe(root.left);
			visit(root);
			inOrderRe(root.right);
		}
	}
	
	/**
	 * 中序非递归访问二叉树
	 * 左根右， 
	 * 根左入栈，根后访问右，把右当做一棵子树传入
	 * @param root
	 */
	public void inOrder2(BNode root){
		if(root==null)
			return;
		BNode p= root;
		Stack<BNode> stack = new Stack<BNode>();
		while(p!=null||stack.size()>0){
			while(p!=null){
				stack.push(p);
				p=p.left;
			}
			if(stack.size()>0){
				p=stack.pop();
				visit(p);
				p=p.right;
			}
		}
	}
	
	
	public void visit(BNode root){
		System.out.print(" "+root.data);
	}
	
	
	/**
	 * 二叉树弄成镜像
	 * @param root
	 */
	public void mirrorBT(BNode root){
		if(root ==null || root.left==null || root.right==null)
			return;
		BNode tmp = root.left;
		root.left=root.right;
		root.right=tmp;
		//上面是根，下面是左右
		if(root.left!=null)
			mirrorBT(root.left);
		if(root.right!=null)
			mirrorBT(root.right);
	}

	public BNode buildTree(){
		BNode r = new BNode(10);
		BNode ll = new BNode(5);
		BNode lr = new BNode(7);
		BNode l = new BNode(6, ll, lr);
		BNode root = new BNode(8, l, r);
		
		return root;
	}
	
}
//已经在其他文件定义了
class BNode{
	public int data;
	public BNode left;
	public BNode right;
	
	public BNode(){}
	public BNode(int d){
		this.data = d;
	}
	
	public BNode(int d, BNode l, BNode r){
		this.data=d;
		this.left=l;
		this.right=r;
	}
	
}