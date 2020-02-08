package com.panzh.circle;
/**
单向循环链表
 */
import com.panzh.AbstractList;

public class SingleCircleLinkedList<E> extends AbstractList<E>{

private Node<E> first;
	
	//存放节点的静态内部类
	private static class Node<E>{
		E element;
		Node<E> next;
		//构造函数；
		public Node(E element, Node<E> next) {
			super();
			this.element = element;
			this.next = next;
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return node(index).element;
	}


	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E oldElement = node.element;
		node.element = element;
		return oldElement;
	}

	@Override
	public void add(int index, E element) {
		
		/*
		 * 最好：O(1)
		 * 最坏：O(n)
		 * 平均：O(n)
		 */
		rangeCheckForAdd(index);
		
		if (index == 0) {
			Node<E> newFirst = new Node<>(element, first);
			// 拿到最后一个节点
			Node<E> last = (size == 0) ? newFirst : node(size - 1);
			last.next = newFirst;
			first = newFirst;
		} else {
			//这一步写的出神入化
			Node<E> prev = node(index - 1);
			prev.next = new Node<>(element, prev.next);
		}
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		Node<E> node = first;
		if (index == 0) {
			if (size == 1) {
				first = null;
			}else {
				Node<E> last = node(size - 1);
				first = first.next;
				last.next = first;
			}
		} else {
			Node<E> prev = node(index - 1);
			node = prev.next;
			prev.next = node.next;
		}
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		if (first != null) {
			Node<E> currentNode = first;
			if (element == null) {
				currentNode = first;
				for(int i = 0 ; i < size ; i++) {
					if (currentNode.element == null) {
						return i;
					}
					currentNode = currentNode.next;
				}
			}else {
				currentNode = first;
				for(int i = 0 ; i < size ; i++) {
					if (element.equals(currentNode.element)) {
						return i;
					}
					currentNode = currentNode.next;
				}
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	private Node<E> node(int index) {
		rangeCheck(index);
		Node<E> indexNode = first;
		for(int i = 0 ; i < index ; i++) {
			indexNode = indexNode.next;
		}
		return indexNode;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(node.element);
			
			node = node.next;
		}
		string.append("]");
		
//		Node<E> node1 = first;
//		while (node1 != null) {
//			
//			
//			node1 = node1.next;
//		}
		return string.toString();
	}

}
