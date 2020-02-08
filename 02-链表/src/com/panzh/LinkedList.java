package com.panzh;


/**
 * LinkedList模拟的jdk中的双向链表,我太难了
 * @author 涛宝宝
 *
 * @param <E>
 */
public class LinkedList <E> extends AbstractList<E>{
	
	//指向第一个元素，双向链表的标配；
	private Node<E> first;
	
	//指向最后一个元素；
	private Node<E> last;
	
	//定义静态的节点；
	/**
	 * @author 涛宝宝
	 * 定义了一个内部类，因为Node节点只会在LinkedList内部使用，而且必须要static；
	 */
	private static class Node<E>{
		//节点中储存的数据，
		E element;
		//指向上一个节点的指针；
		Node<E> prev;
		//指向下一个节点的指针；
		Node<E> next;
		//定义构造函数；
		public Node(E element, Node<E> prev, Node<E> next) {
			super();
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		
		//进行字符串的拼接输出
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			if (prev != null) {
				sb.append(prev.element);
			} else {
				sb.append("null");
			}
			
			sb.append("_").append(element).append("_");

			if (next != null) {
				sb.append(next.element);
			} else {
				sb.append("null");
			}
			
			return sb.toString();
		}
	
	}
	
	//清空双向链表；
	@Override
	public void clear() {
		/*这里肯定存在node之间的相互关联，但是只要断掉first和last的指针，剩下的节点
		都会在gc的作用下进行垃圾的回收，官方的做法是吧每一个节点都断开，是防止万一有
		迭代器Iterator的指向，会造成无法进行垃圾的回收*/
		first = last = null;
		size = 0;
	}

	//获得某一个位置元素的值；
	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		//获得节点，然后进行值的改写不就完了；
		Node<E> oldNode = node(index);
		E oldElement = oldNode.element;
		oldNode.element = element;
		return oldElement;
	}

	//添加的时候注意要分清楚时机，在位置添加，和中间、末尾添加都是不一样的效果；
	@Override
	public void add(int index, E element) {
		//进行边界的检查；
		rangeCheckForAdd(index);
		//进行元素的添加
		if (index == 0 && size == 0) {
			//在开头添加元素的情况，链表中没有一个元素；
			Node<E> node = new Node<>(element, null, null);
			//first和last的指向进行确定；
			first = last = node;
		}else if (index == size) {
			//向最后一个位置添加元素；
			Node<E> newNode = new Node<>(element, last, null);
			last.next = newNode;
			last = newNode;
		}else {
			//在中间添加元素的情况
			//获得需要添加的元素先后的元素；
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			//创建新的节点；
			Node<E> newNode = new Node<>(element, prev, next);
			//进行线的连接,这里必须考虑往下标为0的地方添加元素的情况；
			if (prev != null) {
				prev.next = newNode;
			}else {
				first = newNode;
			}
			next.prev = newNode;
		}
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		Node<E> node = null;

		if (index == 0) {
			node = first;
			if (size == 1) {
				first = last = null;
			}else {
				first = first.next;
				node.next.prev = null;
			}
		}else if (index == size-1) {
			node = last;
			last = last.prev;
			node.prev.next = null;
		}else {
			node = node(index);
			//拿到前后两个节点
			Node<E> prev = node.prev;
			Node<E> next = node.next;
			//进行线的链接，
			prev.next = next;
			next.prev = prev;
		}
		
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		//因为可能为空值，所以必须进行判断；
		if (element == null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				
				node = node.next;
			}
		} else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) return i;
				
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	//获取index的位置的值；
	private Node<E> node(int index){
		//进行索引的检查；
		rangeCheck(index);
		//初始化indexNode；
		Node<E> indexNode = null;
		//判断从那边开始找，为了考虑效率，我们和size的一半进行比较，若是小于，从左边，大于从右边；
		//从last开始找
		if (index > (size >> 1)) {
			indexNode = last;
			for(int i = size - 1 ; i > index ; i--) {
				//这里可以进行实际的操作进行比较；
				indexNode = indexNode.prev;
			}
		}else {
			//从first开始找；
			indexNode = first;
			for(int i = 0 ; i < index ; i++) {
				indexNode = indexNode.next;
			}
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
			
			string.append(node);
			
			node = node.next;
		}
		string.append("]");
		return string.toString();
	}
}
