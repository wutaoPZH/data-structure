package com.mj.set;

import java.util.LinkedList;

/**
 * 使用一个链表来模拟一个集合
 * @author 涛宝宝
 *
 * @param <E>
 */
public class ListSet<E> implements Set<E>{

	//这里我们使用jdk自带的LinkedList集合
	private LinkedList<E> list = new LinkedList<>();
	
	public static final int ELEMENT_NOT_FOND = -1;
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(E element) {
		return list.contains(element);
	}

	@Override
	public void add(E element) {
		//这里需要进行一个判断，因为list集合本身是存在重复元素的；
		//但是set集合是不存在重复的元素的，所以必须要进行一次比较；
		int index = -1;
		if ((index = list.indexOf(element)) != ELEMENT_NOT_FOND) {
			list.set(index, element);
		}else {
			list.add(element);
		}
		
	}

	@Override
	public void remove(E element) {
		int index = list.indexOf(element);
		if (index == ELEMENT_NOT_FOND) {
			return;
		}
		list.remove(index);
		
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		for (E e : list) {
			if (visitor.visit(e)) {
				return;
			}
		}
	}

}
