package com.gt.chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月11日下午7:52:46
 * 二叉树中和为某一值的路径。
 */
public class PathSumBTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PathSumBTree psb = new PathSumBTree();
		BNode root = null;
//		root = psb.createTree(root);
		
		/*Stack<Integer> stack = new Stack<Integer>();
		int num=22;
		int sum=0;
		psb.printPath(root, sum, stack, num);*/
		
//		psb.findPath(root, 22);
		
		root = psb.createTree();
		psb.pathSum(root, 22);
		
	}
	
	/**
	 * 二叉树中和为某一值的路径
	 * @param root
	 * @param sum
	 * @param stack
	 * @param num
	 */
	public void printPath(BNode root, int sum, Stack<Integer> stack, int num){
		if(root!=null){
			sum+=root.data;
			stack.push(root.data);
			
			printPath(root.left, sum, stack, num);
			printPath(root.right, sum, stack, num);
			
			if(sum==num && root.left==null && root.right==null){
				for(int i:stack){
					System.out.print(" "+i);
				}
				System.out.println();
			}
			stack.pop();
		}
	}
	
	private Scanner scanner = new Scanner(System.in);  
    //先序建立二叉树,测试数据10 5 4 # # 7 # # 12 # #  
    public BNode createTree(BNode root){  
        String data = scanner.next();  
        if(data.equals("#"))  
            return null;  
        root = new BNode();  
        root.data = Integer.valueOf(data);  
        root.left = createTree(root.left);  
        root.right = createTree(root.right);  
        return root;  
    }  
	
    /**
     * 构造一棵固定树
     * @return
     */
    public BNode createTree(){
    	BNode ll = new BNode(4);
    	BNode lr = new BNode(7);
    	BNode l = new BNode(5, ll, lr);
    	BNode r = new BNode(12);
    	BNode root = new BNode(10, l, r);
    	return root;
    }
    
    
    public void findPath(BNode root, int num){
    	if(root==null)
    		return;
    	Stack<Integer> stack = new Stack<Integer>();
    	findPath(root, num,stack);
    }
    /**
     * 每次递归进去的k都会减去已经加的node.data
     * @param root
     * @param k
     * @param path
     */
    public void findPath(BNode root, int k, Stack<Integer> path){
    	if(root==null)
    		return;
    	if(root.left==null && root.right==null){
    		if(root.data==k){
    			System.out.println("路径开始:");
    			for(int i:path)
    				System.out.print(i+",");
    			System.out.println(root.data);
    		}
    	}else{
    		path.push(root.data);
    		findPath(root.left, k-root.data, path);
    		findPath(root.right, k-root.data, path);
    		path.pop();
    	}
    }
    
    /**
     * 试试leetcode
     * 
     */
    public List<List<Integer>> pathSum(BNode root, int sum) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> sumlist = new ArrayList<Integer>();
        	
        findPath(root, sum, sumlist, list);
        return list;
    }
    
    public void findPath(BNode root, int sum, List<Integer> sumlist, List<List<Integer>> list){
    	if(root==null)
    		return;
    	sumlist.add(root.data);
    	sum = sum-root.data;
    	if(root.left==null && root.right==null){
    		if(0==sum){
    			list.add(new ArrayList<Integer>(sumlist));
    		}
    	}else{
    		if(root.left!=null)
    			findPath(root.left, sum, sumlist, list);
    		if(root.right!=null)
    			findPath(root.right, sum, sumlist, list);
    	}
    	sumlist.remove(sumlist.size()-1);
    }
    
}
