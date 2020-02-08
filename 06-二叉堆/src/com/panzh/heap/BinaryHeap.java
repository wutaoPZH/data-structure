package com.panzh.heap;

import java.net.Inet4Address;
import java.util.Comparator;

import com.panzh.print.BinaryTreeInfo;


/**
 * 二叉堆，可以解决top K的问题；
 * @author 涛宝宝
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<E> implements Heap<E> , BinaryTreeInfo {

	private int size;
	
	private E [] elements;
	
	private static final int DEAULT_INIT_CAPACITY = 1 << 4;
	
	private Comparator<E> comparator;
	
	
	public BinaryHeap() {
		this(DEAULT_INIT_CAPACITY,null);
	}
	
	public BinaryHeap(int capacity) {
		this(capacity,null);
		elements = (E[]) new Object[capacity > DEAULT_INIT_CAPACITY ? capacity : DEAULT_INIT_CAPACITY];
	}
	
	public BinaryHeap(int capacity,Comparator<E> comparator) {
		elements = (E[]) new Object[capacity > DEAULT_INIT_CAPACITY ? capacity : DEAULT_INIT_CAPACITY];
		this.comparator = comparator;
	}
	
	public BinaryHeap(E [] elements,Comparator<E> comparator) {
		this.comparator = comparator;
		if (elements == null || elements.length == 0) {
			this.elements = (E[]) new Object[DEAULT_INIT_CAPACITY];
		}else {
			int capacity = Math.max(DEAULT_INIT_CAPACITY, elements.length);
			this.elements = (E[]) new Object[capacity];
			for(int i = 0; i < elements.length ; i++) {
				this.elements[i] = elements[i];
			}
			///批量建堆
			heapify();
		}
	}
	
	public BinaryHeap(E [] elements) {
		this(elements, null);
	}
	

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		for (E e : elements) {
			e = null;
		}
		size = 0;
	}

	@Override
	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size + 1);
		elements[size++] = element;
		//然后进行上溢
		siftUp(size - 1);
	}



	@Override
	public E get() {
		emptyCheck();
		return elements[0];
	}

	@Override
	public E remove() {
		//删除的逻辑，就是把最后一个元素覆盖掉第一个元素，然后让第一个元素进行下溢；
		emptyCheck();
		E first = elements[0];
		int lastIndex = --size;
		elements[0] = elements[lastIndex];
		elements[lastIndex] = null;
		
		siftDown(0);
		return first;
	
	}




	@Override
	public E replace(E element) {
		E top = null;
		if (size == 0) {
			elements[size++] = element;
		}else {
			top = elements[0];
			elements[0] = element;
			siftDown(0);
		}
		return top;
	}
	
	private void siftDown(int index) {
		E element = elements[index];
		int half = size >> 1;
		// 第一个叶子节点的索引 == 非叶子节点的数量
		// index < 第一个叶子节点的索引
		// 必须保证index位置是非叶子节点
		while (index < half) { 
			// index的节点有2种情况
			// 1.只有左子节点
			// 2.同时有左右子节点
			
			// 默认为左子节点跟它进行比较
			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];
			
			// 右子节点
			int rightIndex = childIndex + 1;
			
			// 选出左右子节点最大的那个
			if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
				child = elements[childIndex = rightIndex];
			}
			
			if (compare(element, child) >= 0) break;

			// 将子节点存放到index位置
			elements[index] = child;
			// 重新设置index
			index = childIndex;
		}
		elements[index] = element;
	}
	private void heapify() {
		for(int i= (size >> 1) ; i > 0 ; i--) {
			siftDown(i);
		}
	}

	
	private int compare(E e1, E e2) {
		return comparator == null ? ((Comparable)e1).compareTo(e2) : comparator.compare(e1, e2);
	}

	private void siftUp(int index) {
		E element = elements[index];
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			E parent = elements[parentIndex];
			if (compare(parent,element) >= 0) break;
			elements[index] = parent;
			index = parentIndex;
		}
		elements[index] = element;
	}

	private void ensureCapacity(int capacity) {
		if (capacity > elements.length) {
			int newCapacity = elements.length + (elements.length >> 1);
			E [] newElements = (E[]) new Object[newCapacity];
			for(int i = 0 ; i < elements.length ; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
		}
	}

	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element not null");
		}
	}
	
	
	private void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Heap is empty");
		}
	}

	@Override
	public Object root() {
		return 0;
	}

	@Override
	public Object left(Object node) {
		int index = ((int)node << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		int index = ((int)node << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		return elements[(int)node];
	}
}
