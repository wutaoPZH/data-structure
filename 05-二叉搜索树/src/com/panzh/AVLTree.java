package com.panzh;

import java.util.Comparator;


/**
 * AVL树，自平衡的二叉搜索树
 * @author 涛宝宝
 *
 */
public class AVLTree<E> extends BinarySearchTree<E> {
	
	public AVLTree() {
		this(null);
	}
	
	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}

	//写添加之后的旋转的逻辑；
	@Override
	protected void afterAdd(Node<E> node) {
		while((node = node.praent) != null) {
			if (isBlance(node)) {
				updateHeight(node);
			}else {
				//恢复平衡；
				reblance(node);
				break;
			}
		}
	}
	
	@Override
	protected void afterRemove(Node<E> node) {
		while((node = node.praent) != null) {
			if (isBlance(node)) {
				updateHeight(node);
			}else {
				//恢复平衡；
				reblance(node);
			}
		}
	}
	

	//使用了模板方式的设计模式；
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<E>(element, parent);
	}
	
	//进行左旋转；grend就是需要进行旋转的节点
	private void left(Node<E> grend) {
		Node<E> parent = grend.right;
		Node<E> child = parent.left;
		//进行移位;
		grend.right = parent.left;
		parent.left = grend;
		afterRote(grend, parent, child);
		
	}
	
	//进行右旋转；
	private void right(Node<E> grend) {
		Node<E> parent = grend.left;
		Node<E> child = parent.right;
		//更改指向；
		grend.left = parent.right;
		parent.right = grend;
		
		afterRote(grend, parent, child);
	}
	
	private void afterRote(Node<E> grend,Node<E> parent, Node<E> child) {
		//让parent成为根节点；
		if (grend.praent != null) {
			if (grend.isLeft()) {
				grend.praent.left = parent;
			}else {
				grend.praent.right = parent;
			}
		}else {
			//grend是根节点的情况；
			root = parent;
		}
		//维护父节点；
		if (child != null) {
			child.praent = grend;
		}
		parent.praent = grend.praent;
		grend.praent = parent;
		
		//不要忘记了更新高度
		updateHeight(grend);
		updateHeight(parent);
	}
	
	private void reblance(Node<E> grand) {
		Node<E> parent = ((AVLNode)grand).tallerChild();
		Node<E> node = ((AVLNode)parent).tallerChild();
		
		if (parent.isLeft()) {//L
			if (node.isLeft()) {//LL的情况，进行右旋转；
				right(grand);
			}else {
				//LR的情况；
				left(parent);
				right(grand);
			}
		}else {
			if (node.isLeft()) {//RL的情况，进行右旋转；
				right(parent);
				left(grand);
			}else {
				//RR的情况；
				left(grand);
			}
		}
		
	}
	
	private boolean isBlance(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}
	
	
	
	//AVL树专有的Node的节点；
	private static class AVLNode<E> extends Node<E>{
		//维护则一个高度的属性。默认叶子节点的高度为1.
		int height = 1;

		public AVLNode(E element, Node<E> praent) {
			super(element, praent);
		}
	
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			return leftHeight - rightHeight;
		}
		
		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		
		//返回较高的子节点；
		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			if (leftHeight > rightHeight) return left;
			if (leftHeight < rightHeight) return right;
			return isLeft() ? left : right;
		}
	}
	
}
