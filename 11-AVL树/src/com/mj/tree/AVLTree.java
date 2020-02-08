package com.mj.tree;

import java.util.Comparator;

import com.mj.tree.BinaryTree.Node;


public class AVLTree<E> extends BST<E> {

	public AVLTree() {
		// 进行构造函数的赋值；
		this(null);
	}

	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}

	// 处理增加节点之后的逻辑；
	@Override
	protected void afterAdd(Node<E> grand) {
		//明确一点，增加可能导致很多的节点都失衡，但是只要找到最近的一个节点使他回复平衡，就可以整棵树恢复平衡
		while ((grand = grand.parent) != null) {
			if (isBalanced(grand)) {
				//是平衡的，进行更新高度即可；
				updateHeight(grand);
			}else {
				//已经失衡，需要平衡高度；
				rebalance(grand);
				break;
			}
		}

	}
	
	//删除节点之后的逻辑，这里的grand就是删除之后的节点，删除之后只会导致父节点或者祖父节点
	//之中有一个失衡，但是对他恢复平衡之后，就有可能再次失衡，所以对以上所有失衡的节点都进行恢复的操作；
	@Override
	protected void afterRemove(Node<E> grand) {
		while ((grand = grand.parent) != null) {
			if (isBalanced(grand)) {
				//是平衡的，进行更新高度即可；
				updateHeight(grand);
			}else {
				//已经失衡，需要进行平衡；
				rebalance(grand);
			}
		}
	}

	// 设计模式之模板的设计方法；
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode(element, parent);
	}

	/**
	 * 恢复平衡
	 * 
	 * @param grand 高度最低的那个不平衡节点
	 */
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>) grand).tallerChild();
		Node<E> node = ((AVLNode<E>) parent).tallerChild();
		if (parent.isLeftChild()) {
			if (node.isLeftChild()) { // L的情况；
				rotateRight(grand);
			} else { // LR的情况；
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else {
			if (node.isLeftChild()) { // RL的情况；
				rotateRight(parent);
				rotateLeft(grand);
			} else { // R的情况；
				rotateLeft(grand);
			}
		}
	}
	
	//统一的旋转操作,这里涉及到一个更新高度的问题，所以不能进行位置的更改；
	private void rotate(
			Node<E> r, // 子树的根节点
			Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f) {
		
		Node<E> grend = r.parent;
		if (grend != null) {
			d.parent = grend;
			if (r.isLeftChild()) {
				grend.left = d;
			}else {
				grend.right = d;
			}
		}else {
			d = root;
		}
		
		
		b.right = c;
		if (c != null) {
			c.parent = b;
		}
		updateHeight(b);
		
		f.left = e;
		if (e != null) {
			e.parent = f;
		}
		updateHeight(f);

		
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
		updateHeight(d);
		
	}


	//进行左旋；
	private void rotateLeft(Node<E> grand) {
		//获得需要的三个节点；
		Node<E> parent = grand.left;
		Node<E> node = parent.right;
		//更改左右子节点的指向；
		
		parent.left = grand;
		grand.right = node;
		
		afterRotate(grand, parent, node);
	}

	//LL进行右旋转；
	private void rotateRight(Node<E> grand) {
		//获得需要的三个节点；
		Node<E> parent = grand.left;
		Node<E> node = parent.right;
		//更改左右子节点的指向；
		parent.right = grand;
		grand.left = node;
		
		afterRotate(grand, parent, node);
	}

	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> node) {
		if (grand.parent != null) {
			if (grand.isLeftChild()) {
				parent = grand.parent.left;
			}else {
				parent = grand.parent.left;
			}
		}else {
			parent = root;
		}
		
		if (node != null) {
			node.parent = grand;
		}
		
		parent.right = grand;
		
		//更新高度；
		// 更新高度
		updateHeight(grand);
		updateHeight(parent);
	}

	private boolean isBalanced(Node<E> node) {
		return ((AVLNode<E>) node).balanceFactor() <= 1;
	}

	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();
	}

	private static class AVLNode<E> extends Node<E> {
		int height = 1;

		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}

		// 能来到这里的代码。就证明一定是AVL树，所以可以直接进行强制转换；
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			return leftHeight - rightHeight;
		}

		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}

		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			if (leftHeight > rightHeight)
				return left;
			if (leftHeight < rightHeight)
				return right;
			return isLeftChild() ? left : right;
		}

		@Override
		public String toString() {
			String parentString = "null";
			if (parent != null) {
				parentString = parent.element.toString();
			}
			return element + "_p(" + parentString + ")_h(" + height + ")";
		}
	}

}
