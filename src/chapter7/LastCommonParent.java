package com.gt.chapter7;

import java.util.ArrayList;
import java.util.List;



/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月15日上午10:46:19
 * 
 * 二叉树的最低公共祖先
 * 1、是否二叉搜索树
 * 2、普通二叉树是否有父节点
 * 3、普通二叉树没有父节点
 * 
 */
public class LastCommonParent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LastCommonParent lcp = new LastCommonParent();
		Node root = lcp.createTree();
		Node lrl = new Node(0);
    	Node lrr = new Node(1);
		
//		Node common = lcp.lastCommon(root, lrl, lrr);
//    	Node common = lcp.findLastCommon(root, lrl, lrr);
    	Node common = lcp.lastCommon2(root, lrl, lrr);
    	
    	
		/*Node root = lcp.createTreeBST();
		Node p1 = new Node(4);
    	Node p2 = new Node(18);
		Node common = lcp.lastCommonBST(root, p1, p2);*/
		System.out.println(common.data);
	}

	/**
	 * 只遍历一次的算法，
	 * p1, p2,只要有一个与根相等，则最低公共祖先就是根
	 * 否则，在左子树中找p1,p2的公共节点； 在右子树中找p1, p2的公共节点
	 * 如果两个都不为空，那么左右分别有，最低公共祖先还是根
	 * 如果一个为空，说明LCA在另一个树种
	 */
	public Node lastCommon2(Node root, Node p1, Node p2){
		if(root ==null)
			return null;
		if(root.data==p1.data||root.data==p2.data)
			return root;
		Node left = lastCommon2(root.left, p1, p2);
		Node right = lastCommon2(root.right, p1, p2);
		
		if(left!=null && right!=null)
			return root;
		
		return (left!=null)?left:right;
	}
	
	
	/**
	 * 剑指offer的思路：
	 * 每个节点找到从根到他的路径path
	 * 从path中找公共节点
	 */
	
	public Node findLastCommon(Node root, Node p1, Node p2){
		if(root==null)
			return null;
		ArrayList<Node> list1 = new ArrayList<Node>();
		ArrayList<Node> list2 = new ArrayList<Node>();
		boolean find1 = findPath(root, p1, list1);
		boolean find2 = findPath(root, p2, list2);
		
		if(find1 && find2){
			int min = list1.size()<list2.size()?list1.size():list2.size();
			Node common = null;
			for(int i=0; i<min; i++){
				if(list1.get(i).data == list2.get(i).data)
					common = list1.get(i);
				else
					break;
			}
			return common;
		}
		
		return null;
	}
	
	
	/**
	 * 给一个二叉树root根，一个节点，一个list的path，返回路径
	 * @param root
	 * @param p
	 * @param path
	 * @return
	 */
	public boolean findPath(Node root, Node p, ArrayList<Node> path){
		if(root==null)
			return false;
		path.add(root);
		if(root.data == p.data)
			return true;
		//左子树或者右子树是否找到，如果在左或右找到，当前root就在path中不用弹出
		boolean find = (findPath(root.left, p, path)||findPath(root.right, p, path));
		
		if(find)
			return true;
		path.remove(path.size()-1);
		return false;
	}
	
	
	
	/**
	 * 1、自顶向下方法，AB节点如果在根的左子节点，则最低公共祖先在左子树；
	 * 若AB节点在根右子节点，则最低公共祖先在右子树；一个左子树，一个右子树则最低公共祖先就是根。
	 * 
	 * 2、自底向下方法，
	 * 
	 */
	
	/**
	 * 普通二叉树查找最低公共祖先
	 * 自定向下：从根开始往下，每个都查看是否在左右子树中
	 */
	/**
	 * 在以root为根的树种查询 p1和p2两个节点的最低公共祖先
	 * 思路：自顶向下方法，
	 * @param root
	 * @param p1
	 * @param p2
	 * @return
	 */
	public Node lastCommon(Node root, Node p1, Node p2){
		if(root==null)
			return null;
		if(hasNode(root.left,p1)&&hasNode(root.left, p2))
			return lastCommon(root.left, p1, p2);
		if(hasNode(root.right,p1)&&hasNode(root.right, p2))
			return lastCommon(root.right, p1, p2);
		
		return root;
	}
	
	/**
	 * 如果是一棵二叉查找树，要不要考虑不在二叉搜索树中呢！
	 * @param root
	 * @param p1
	 * @param p2
	 * @return
	 */
	public Node lastCommonBST(Node root, Node p1, Node p2){
		if(root==null)
			return null;
		int left =p1.data;
		int right = p2.data;
		if(left>right){
			left = left+right;
			right = left-right;
			left = left-right;
		}
		Node parent = null;
		while(root!=null){
			if(root.data<left){
				parent = root;
				root = root.right;
			}else if(root.data>right){
				parent = root;
				root = root.left;
			}else if(root.data==left||root.data==right){
				return parent;
			} else{
				return root;
			}
		}
		return null;
	}
	
	/**
	 * 判断以root为根的树中是否包含p节点
	 * @param root	树的根
	 * @param p	要查询的节点
	 * @return  Boolean
	 */
	public boolean hasNode(Node root, Node p){
		if(root==null)
			return false;
		if(root.data==p.data)
			return true;
		
		return hasNode(root.left, p)||hasNode(root.right, p);
	}
	
    /**二叉搜索树
     * 构造一棵固定树
     * 		10
     *	   /  \
     *	  5		12
     *	/ \
     *	4  7
     *
     * @return
     */
    public Node createTreeBST(){
    	Node ll = new Node(4);
    	Node lr = new Node(7);
    	Node l = new Node(5, ll, lr);
    	Node r = new Node(12);
    	Node root = new Node(10, l, r);
    	return root;
    }
	
    /**
     * 构造一棵固定树
     * 				3
     * 			/		\
     * 		   5		1
     * 		/	\	   / \
     * 		6	2	  0	  8
     * 			/\
     * 			7 4
     * @return
     */
    public Node createTree(){
    	Node lrl = new Node(4);
    	Node lrr = new Node(7);
    	
    	Node ll = new Node(6);
    	Node lr = new Node(2, lrl, lrr);
    	
    	Node l = new Node(5, ll, lr);
    	//上面是左子树构造了，下面右子树
    	Node rl = new Node(0);
    	Node rr = new Node(8);
    	Node r = new Node(1, rl, rr);
    	
    	Node root = new Node(3, l, r);
    	return root;
    }
	
}

class Node{
	int data;
	Node left;
	Node right;
	public Node(){}
	public Node(int d){
		this.data=d;
	}
	public Node(int d, Node l, Node r){
		this.data=d;
		this.left =l;
		this.right=r;
	}
}