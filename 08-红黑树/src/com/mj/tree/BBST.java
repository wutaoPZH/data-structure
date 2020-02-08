package com.mj.tree;

import java.util.Comparator;


/**
 * 此类是为了兼容，兼容红黑树和AVL树的公共代码
 * @author 涛宝宝
 *
 * @param <E>
 */
public class BBST<E> extends BST<E> {
	
	public BBST() {
		this(null);
	}
	
	public BBST(Comparator<E> comparator) {
		super(comparator);
	}
	
	//统一的旋转操作,这里涉及到一个更新高度的问题，所以不能进行位置的更改；
	protected void rotate(
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
		
		f.left = e;
		if (e != null) {
			e.parent = f;
		}

		
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
		
	}


	//进行左旋；
	protected void rotateLeft(Node<E> grand) {
		//获得需要的三个节点；
		Node<E> parent = grand.left;
		Node<E> node = parent.right;
		//更改左右子节点的指向；
		
		parent.left = grand;
		grand.right = node;
		
		afterRotate(grand, parent, node);
	}

	//LL进行右旋转；
	protected void rotateRight(Node<E> grand) {
		//获得需要的三个节点；
		Node<E> parent = grand.left;
		Node<E> node = parent.right;
		//更改左右子节点的指向；
		parent.right = grand;
		grand.left = node;
		
		afterRotate(grand, parent, node);
	}

	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> node) {
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
		
	}

}
