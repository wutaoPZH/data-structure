package com.panzh;

import com.panzh.list.LinkedList;
import com.panzh.list.List;
/**
 * Linked模拟简单队列，往list后面添加元素，然后从前面弹出，实现了先进先出；
 * @author 涛宝宝
 *
 * @param <E>
 */
public class Queue<E> {
	
	private List<E> list = new LinkedList<>();
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void clear() {
		list.clear();
	}

	public void offer(E element) {
		list.add(element);
	}

	public E poll() {
		return list.remove(0);
	}

	public E peek() {
		return list.get(0);
	}
	
	

}
