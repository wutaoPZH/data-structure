package com.panzh;

import com.panzh.list.LinkedList;
import com.panzh.list.List;

/**
 * 用LinkedList来模拟双端队列的操作；
 * 
 * @author 涛宝宝
 *
 */
public class Deque<E> {
	
	private List<E> list = new LinkedList<>();
	
	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}

	public void clear() {
		list.clear();
	}

	//后方添加
	public void enQueueRear(E element) {
		list.add(element);
	}

	//前方出队
	public E deQueueFront() {
		return list.remove(0);
	}

	//前方添加；
	public void enQueueFront(E element) {
		list.add(0,element);
	}

	//后方出队
	public E deQueueRear() {
		return list.remove(list.size()-1);
	}

	public E front() {
		return list.get(0);
	}

	public E rear() {
		return list.get(list.size()-1);
	}
}
