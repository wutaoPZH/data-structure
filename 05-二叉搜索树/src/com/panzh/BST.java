package com.panzh;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


/**
 * 二叉树的类。表示一课基本的二叉树，应该定义成抽象类；
 * @author 涛宝宝
 *
 */
@SuppressWarnings({"unchecked","rawtypes","null"})
public abstract class BST<E> {
	
	protected int size;
	
	protected Node<E> root;
	
	//前序遍历,我们四个遍历都使用非递归的形式进行实现;前序遍历，Iterator增强遍历的接口；
	public void preorder(Iterator iterator) {
		
		if (root == null || iterator == null) {
			return;
		}
		
		//前序遍历使用栈进行完成
		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = root;
		while(true) {
			if (node != null) {
				if (iterator.show(node.element)) return;
				if (node.right != null) {
					stack.push(node.right);
				}
				node = node.left;
			}else if (stack.isEmpty()) {
				return;
			}else {
				node = stack.pop();
			}
		}
	}
	
	
	//中序遍历
	public void Inorder (Iterator iterator) {
		
		if (root == null || iterator == null) {
			return;
		}
		
		//前序遍历使用栈进行完成
		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = root;
		while(true) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			}else {
				if (stack.isEmpty()) {
					return;
				}
				node = stack.pop();
				if (iterator.show(node)) return;
				node = node.right;
			}
		}
	}
	
	//后续遍历
	public void postorder(Iterator iterator) {
		
		if (root == null || iterator == null) {
			return;
		}
		
		List<E> res = new ArrayList<>();
	    Stack<Node<E>> stack = new Stack<>();
	    stack.push(root);
	    while(!stack.isEmpty()){
	        Node<E> node = stack.pop();
	        if(node.left != null) stack.push(node.left);//和传统先序遍历不一样，先将左结点入栈
	        if(node.right != null) stack.push(node.right);//后将右结点入栈
	        res.add(0,node.element);                        //逆序添加结点值
	    }
	    for (E element : res) {
	    	if (iterator.show(element)) return;
		}
	}
	
	//层序遍历,层序遍历就比较简单，直接使用队列就可以实现；
	public void levelOrder (Iterator iterator) {
		
		if (root == null || iterator == null) {
			return;
		}
		Node<E> node = root;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		while(!queue.isEmpty()) {
			node = queue.poll();
			if (iterator.show(node.element)) return;
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		
	}
	
	//判断是非为完全二叉树；
	public boolean isCompleteTree () {
		
		if (root == null) {
			return false;
		}
		Node<E> node = root;
		boolean flag = false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		while(!queue.isEmpty()) {
			node = queue.poll();
			if (flag && !node.isLeaf()) return false;
			if (node.left != null) {
				queue.add(node.left);
			}else if (node.right != null) {//left等于空;
				return false;
			}
			
			if (node.right != null) {
				queue.add(node.right);
			}else {
				flag = true;//以后遍历到的所有的节点都是叶子节点
			}
			
		}
		return true;
	}
	
	//寻找Node的前驱节点；
	public Node predecessor(Node<E> node) {
		
		if (node == null) {
			return null;
		}
		Node<E> tem = null;
		if ((tem = node.left) != null) {
			while((tem = tem.right) != null);
			return tem.praent;
		}else if ((tem = node.praent) != null) {
			while (tem.isRight()) {
				tem = tem.praent;
			}
			return tem;
		}else {
			return null;
		}
		
	}
	
	//寻找Node的后继节点；
	public Node successor(Node<E> node) {
		
		if (node == null) {
			return null;
		}
		Node<E> tem = null;
//		Node<E> parent = null;
		if ((tem = node.right) != null) {
			Node<E> parent = tem;
			while((tem = tem.left) != null) {
				parent = tem;
			}
			return parent;
		}else if ((tem = node.praent) != null) {
			while (tem.isLeft()) {
				tem = tem.praent;
			}
			return tem;
		}else {
			return null;
		}
		
	}
	
	 
	//增强遍历的接口；
	public static abstract class Iterator<E>{
		boolean stop;
		//进行接口的遍历访问,返回True就停止访问；
		public abstract boolean show(E e);
	}
	
	//Node节点
	protected static class Node<E>{
		E element;
		Node<E> praent;
		Node<E> left;
		Node<E> right;
		public Node(E element, Node<E> praent) {
			this.element = element;
			this.praent = praent;
		}
		//是右子节点；
		public boolean isRight() {
			return this == praent.right;
		}
		
		//是左子节点；
		public boolean isLeft() {
			return this == praent.left;
		}

		public boolean isLeaf() {
			return (left == null) && (right == null);
		}
		
		public boolean havaTweChild() {
			return (left != null) && (right != null);
		}
		
	}
	

}
