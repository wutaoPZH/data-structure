package com.panzh.circle;
/**
 * 用数组实现循环队列。
 * @author 涛宝宝
 *
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class CircleQueue<E> {
	private int front;
	private int size;
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	public CircleQueue() {
		//创建默认的长度；
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		for(int i = 0 ; i < size ; i++) {
			elements[index(i)] = null;
		}
		front = 0; 
		size = 0;
	}

	public void enQueue(E element) {
		//检查容量，至少需要size+1个容量；
		ensureCapacity(size+1);
		elements[index(size)] = element;
		size++;
	}

	public E deQueue() {
		E element = elements[front];
		elements[front] = null;
		front = index(1);
		size--;
		return element;
	}

	public E front() {
		E element = elements[front];
		return element;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("capcacity=").append(elements.length)
		.append(" size=").append(size)
		.append(" front=").append(front)
		.append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}
	
	private int index(int index) {
		index += front;
		return index - (index >= elements.length ? elements.length : 0);
	}
	
	/**
	 * 保证要有capacity的容量
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		if (capacity < elements.length) {
			return;
		}
		
		//这里就进行扩容了；
		int newCapacity = elements.length + elements.length >> 1;
		E [] newElements = (E[]) new Object [newCapacity];
		
		for(int i = 0 ;  i < size ; i++) {
			newElements[i] = elements[index(i)];
		}
		
		elements = newElements;
		front = 0;
	}
}
