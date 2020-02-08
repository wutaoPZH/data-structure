package com.panzh;

import java.util.Arrays;

/**
 * 自己重写的ArrayList；
 * 2019.12.21 
 * @author 涛宝宝
 *
 */

public class ArrayList {
	
	//目前集合保存元素的数量
	private int size = 0;
	
	//用来存放数据元素
	private int element[];
	
	private static final int DEFAULT_SIZE = 10;
	
	private static final int ELEMENT_NOT_FOND = -1;
	
	private static final int INCREMENT_EIZE_FACTOR = 2;
	
	public ArrayList() {
		element = new int [DEFAULT_SIZE];
	}
	
	//若是传入的数值大于default_size,就按照传入的进行赋值，若是小于，就按照default_size进行赋值；
	public ArrayList(int capacity) {
		element = new int [capacity > DEFAULT_SIZE ? capacity:DEFAULT_SIZE];
	}
	


	//获取元素的大小
	public int size() {
		return size;
	}
	
	
	//若是空，就返回true，若是不空则返回false；
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean constraints(int element) {
		for (int i : this.element) {
			if (element == i) {
				return true;
			}
		}
		return false;
	}
	
	public void add(int element) {
		//进行动态的扩容；
		if (size >= this.element.length) {
			int [] copyOf = new int [size * INCREMENT_EIZE_FACTOR];
			copyOf = Arrays.copyOf(this.element, this.element.length);
			this.element = copyOf;
		}
		this.element[size++] = element;
	}
	
	public int get(int index) {
		if (index >= size || index <0) {
			throw new RuntimeException("数组下标越界");
		}
		return element[index];
	}
	
	public int set(int index, int element) {
		if (index >= size || index <0) {
			throw new RuntimeException("数组下标越界");
		}
		int original = this.element[index];
		this.element[index] = element;
		return original;
	}
	
	public void add(int index, int element) {
		//进行动态的扩容；
		if (size >= this.element.length) {
			//旧容量的1.5倍；右移1相当与除以二，左移相当与乘以2；
			int [] copyOf = new int [this.element.length + (this.element.length >> 1)];
			copyOf = Arrays.copyOf(this.element, this.element.length);
			this.element = copyOf;
		}
		for (int i = size; i > index; i--) {
			this.element[i] = this.element[i-1];
		}
		size++;
		this.element[index] = element;
	}
	
	//删除一个数并返回下标；注意数组移位的问题；
	public int remove(int element) {
		for (int i = 0; i < size; i++) {
			if (this.element[i] == element) {
				for(int j = i+1 ; j < size ; j++) {
					this.element[j-1] = this.element[j];
				}
				size--;
				return i;
			}
		}
		return ELEMENT_NOT_FOND;
	}
	
	public int indexOf(int element) {
		for (int i = 0; i < this.element.length; i++) {
			if (element == this.element[i]) {
				return i;
			}
		}
		return ELEMENT_NOT_FOND;
	}
	
	//这里尤为重要，记住，是覆盖，而不是删除；
	public void clear(){
		size = 0;
	}
	

}
