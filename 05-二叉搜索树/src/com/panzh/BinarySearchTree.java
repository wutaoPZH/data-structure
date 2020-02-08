package com.panzh;

import java.util.Comparator;

import com.panzh.printer.BinaryTreeInfo;

/**
 * 二叉搜索树，会自动排序，涉及到比较，所以就自然需要compatarable和Comparator;
 * 
 * @author 涛宝宝
 *
 * @param <E>
 */
@SuppressWarnings({ "unchecked", "unused" })
public class BinarySearchTree<E> extends BST<E> implements BinaryTreeInfo {

	// 具备比较的的特性；
	private Comparator<E> comparator;

	public BinarySearchTree() {
		this(null);
	}

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	public int size() {
		// 元素的数量
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		// 清空所有元素
		root = null;
		size = 0;
	}

	public void add(E element) {
		// 添加元素。
		// 如果添加的是根节点；
		if (root == null) {
			root = createNode(element, null);
			size++;
			return;
		}
		// 不是根节点的情况，就要进行判断了
		Node<E> node = root;
		Node<E> parent = null;
		int cmp = 0;
		while (node != null) {
			cmp = compare(node.element, element);
			if (cmp == 0) {// 相等的情况；进行值的覆盖
				node.element = element;
				return;
			}
			parent = node;
			if (cmp > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}

		node = createNode(element, parent);
		if (cmp > 0) {
			parent.left = node;
		} else {
			parent.right = node;
		}
		size++;
		afterAdd(node);

	}



	public void remove(E element) {

		Node<E> node = node(element);
		if (root == null || node == null)
			return;

		
		// 度为2的情况；
		if (node.havaTweChild()) {
			Node<E> temp = successor(node);
			node.element = temp.element;
			node = temp;
		}
		
		// 度为1的情况；
		if (node.left != null && node.right == null) {
			if (node.isLeft()) {
				node.praent.left = node.left;
			} else {
				node.praent.right = node.left;
			}
			node.left.praent = node.praent;
			afterRemove(node);
			size--;
			return;
		}
		if (node.right != null && node.left == null) {
			if (node.isLeft()) {
				node.praent.left = node.right;
			} else {
				node.praent.right = node.right;
			}
			node.right.praent = node.praent;
			afterRemove(node);
			size--;
			return;
		}


		// 是叶子节点的情况；
		if (node.isLeft()) {
			node.praent.left = null;
		} else if (node.isRight()) {
			node.praent.right = null;
		} else {
			// 这里就是删除根节点的情况；
			root = null;
		}
		afterRemove(node);
		size--;
		return;

	}



	public boolean contains(E element) {
		return node(element) == null ? false : true;
	}
	
	protected void afterRemove(Node<E> node) {}
	
	//交给子类进行实现；
	protected void afterAdd(Node<E> node) {}

	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}

	// 根据element元素值，还获取对应的节点；
	private Node<E> node(E element) {
		elementNotNullCheck(element);
		Node<E> node = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(node.element, element);
			if (cmp == 0) {// 相等的情况；进行值的覆盖
				return node;
			}
			if (cmp > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return null;
	}

	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must be not null");
		}
	}

	private int compare(E e1, E e2) {
		// 如果传入有comparator，就按照compatator进行比较，但是如果没有传入，就直接使用comparable进行比较；
		return comparator != null ? comparator.compare(e1, e2) : ((Comparable) e1).compareTo(e2);
	}

	@Override
	public Object root() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public Object left(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).element;
	}

}
