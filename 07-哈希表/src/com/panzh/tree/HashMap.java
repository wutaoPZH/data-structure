package com.panzh.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import com.panzh.print.BinaryTreeInfo;
import com.panzh.print.BinaryTrees;




/**
 * 使用AVL树来实现一个hash表;
 * @author 涛宝宝
 * @time:2020/2/8
 * @param <K>
 * @param <V>
 */
public class HashMap<K,V> implements Map<K, V> {

	//hash表中元素的多少；
	private int size;
	
	//默认的桶数组的长度,我们默认就取一个16;
	private static final int DEFAULT_CAPACITY = 1 << 4;

	//最大的桶数组的长度；
	private static final int MAXIMUM_CAPACITY = 1 << 30;

	
	//桶数组；
	private Entry<K, V> [] element;
	
	//构造函数；
	public HashMap() {
		this(DEFAULT_CAPACITY);
	}
	
	//进行构造函数的构造；
	public HashMap(int capacity) {
		capacity = tableSizeFor(capacity);
		element = new Entry[capacity > DEFAULT_CAPACITY ? capacity : DEFAULT_CAPACITY];
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
		size = 0;
		for (Entry<K, V> entry : element) {
			entry = null;
		}
	}

	@Override
	public V put(K key, V value) {
		//1.根据hash值算出在桶数组的索引。
		int hash = hash(key);
		Entry<K,V> node = element[hash];
		int cmp = 0;
		//2.若是该位置为null。就直接进行插入；
		if (node == null) {
			element[hash] = new Entry<>(key, value, null);
			size++;
			return null;
		}
	       //3.若是不为null。就进行比较。若是相同的，就进行覆盖，不同的话就进行插入。compare方法是重点；
		Entry<K, V> parent = node;
		boolean flag = false;
		while (node != null) {
			int nodeHash = hash(node.key);
			//进行比较的逻辑；
			if (!flag) {
				//这里是指找到节点，要是找不到节点，也就证明节点不在整颗AVL树中，便可根据内存地址进行插入；
				Entry<K, V> tmp = Entry(node,key);
				if (tmp != null) {
					V oldValue = tmp.value;
					tmp.key = key;
					tmp.value = value;
					return oldValue;
				}else {
					flag = true;
				}
			}else {
				cmp = compare(node.key,key);
			}
			parent = node;
			if (cmp > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}

		node = new Entry<>(key, value, parent);
		if (cmp > 0) {
			parent.left = node;
		} else {
			parent.right = node;
		}
		size++;
		afterAdd(node);
		return null;
	}



	@Override
	public V get(K key) {
		Entry<K, V> result = Entry(element[hash(key)], key);
		return result == null ? null : result.value;
	}

	@Override
	public V remove(K key) {
		return remove(Entry(element[hash(key)], key));
	}



	@Override
	public boolean containsKey(K key) {
		return Entry(element[hash(key)], key) == null ? false : true;
	}

	@Override
	public boolean containsValue(V value) {
		//我们只能进行一次又一次的遍历了;
		for (Entry<K, V> entry : element) {
			if (entry == null) continue;
			//层序遍历二叉树；
			if (sequence(entry,value)) return true;
		}
		return false;
	}



	@Override
	public void traversal(Visitor<K, V> visitor) {
		//我们只能进行一次又一次的遍历了;
		Queue<Entry<K, V>> queue = null;
		for (Entry<K, V> entry : element) {
			if (entry == null) continue;
			queue = new LinkedList<>();
			queue.offer(entry);
			while(!queue.isEmpty()) {
				Entry<K, V> poll = queue.poll();
				if (visitor.visit(poll.key, poll.value)) return;
				if (poll.left != null) {
					queue.offer(poll.left);
				}
				if (poll.right != null) {
					queue.offer(poll.right);
				}
			}
		}
	}
	
	public void print() {
		if (size == 0) return;
		for (int i = 0; i < element.length; i++) {
			final Entry<K, V> root = element[i];
			System.out.println("【index = " + i + "】");
			BinaryTrees.println(new BinaryTreeInfo() {
				@Override
				public Object string(Object node) {
					return node;
				}
				
				@Override
				public Object root() {
					return root;
				}
				
				@Override
				public Object right(Object node) {
					return ((Entry<K, V>)node).right;
				}
				
				@Override
				public Object left(Object node) {
					return ((Entry<K, V>)node).left;
				}
			});
			System.out.println("---------------------------------------------------");
		}
	}
	
	private boolean sequence(Entry<K, V> entry, V value) {
		Queue<Entry<K, V>> queue = new LinkedList<>();
		queue.offer(entry);
		while(!queue.isEmpty()) {
			Entry<K, V> poll = queue.poll();
			if (Objects.equals(value, poll.value)) return true;
			if (poll.left != null) {
				queue.offer(poll.left);
			}
			if (poll.right != null) {
				queue.offer(poll.right);
			}
		}
		return false;
	}
	
	private V remove(Entry<K, V> entry) {
		
		if (entry == null) return null;
		
		V oldValue = entry.value;

		

		// 度为2的情况；
		if (entry.havaTweChild()) {
			Entry<K,V> temp = successor(entry);
			entry.key = temp.key;
			entry.value = temp.value;
			entry = temp;
		}
		
		// 度为1的情况；
		if (entry.left != null && entry.right == null) {
			if (entry.isLeft()) {
				entry.praent.left = entry.left;
			} else {
				entry.praent.right = entry.left;
			}
			entry.left.praent = entry.praent;
			afterRemove(entry);
			size--;
			return oldValue;
		}
		if (entry.right != null && entry.left == null) {
			if (entry.isLeft()) {
				entry.praent.left = entry.right;
			} else {
				entry.praent.right = entry.right;
			}
			entry.right.praent = entry.praent;
			afterRemove(entry);
			size--;
			return oldValue;
		}

		// 是叶子节点的情况；
		if (entry.praent != null) {
			if (entry.isLeft()) {
				entry.praent.left = null;
			}else {
				entry.praent.right = null;
			}
		}else {
			// 这里就是删除根节点的情况；
			element[hash(entry.key)] = null;
		}
		afterRemove(entry);
		size--;
		return oldValue;
		
//		if (entry == null) return null;
//		
//		Entry<K, V> willNode = entry;
//		
//		size--;
//		
//		V oldValue = entry.value;
//		
//		if (entry.havaTweChild()) { // 度为2的节点
//			// 找到后继节点
//			Entry<K, V> s = successor(entry);
//			// 用后继节点的值覆盖度为2的节点的值
//			entry.key = s.key;
//			entry.value = s.value;
//			// 删除后继节点
//			entry = s;
//		}
//		
//		// 删除node节点（node的度必然是1或者0）
//		Entry<K, V> replacement = entry.left != null ? entry.left : entry.right;
//		int index = hash(entry.key);
//		
//		if (replacement != null) { // node是度为1的节点
//			// 更改parent
//			replacement.praent = entry.praent;
//			// 更改parent的left、right的指向
//			if (entry.praent == null) { // node是度为1的节点并且是根节点
//				element[index] = replacement;
//			} else if (entry == entry.praent.left) {
//				entry.praent.left = replacement;
//			} else { // node == node.parent.right
//				entry.praent.right = replacement;
//			}
//			
//			// 删除节点之后的处理
//			afterAdd(replacement);
//		} else if (entry.praent == null) { // node是叶子节点并且是根节点
//			element[index] = null;
//		} else { // node是叶子节点，但不是根节点
//			if (entry == entry.praent.left) {
//				entry.praent.left = null;
//			} else { // node == node.parent.right
//				entry.praent.right = null;
//			}
//			
//			// 删除节点之后的处理
//			afterAdd(replacement);
//		}
//		
//		return oldValue;
	}
	
	//寻找Node的后继节点；
	private Entry successor(Entry<K, V> node) {
		
		if (node == null) {
			return null;
		}
		Entry<K, V> tem = null;
//		Node<E> parent = null;
		if ((tem = node.right) != null) {
			Entry<K, V> parent = tem;
			while((tem = tem.left) != null) {
				parent = tem;
			}
			return parent;
		}else if ((tem = node.praent) != null) {
			while (tem.isLeft()) {
				tem = tem.praent;
			}
			return tem;
		}else {
			return null;
		}
		
	}
	
	
	/**
	 * key > key2:返回1；
	 * key = key2:返回0；
	 * key < key2:返回-1； 
	 */
	//进行比较的逻辑，因为我们必须要分出一个大小，但是没办法，依靠有用的信息进行比较；
//	private int compare(K key1, int h1, K key2, int h2) {
	private int compare(K key1, K key2) {
//		//我们先比较hash值是为了降低命中率;
//		if (h1 > h2) return 1;
//		if (h1 < h2) return -1;
//		
//		//这里就证明hash值不同。其实极大的情况下，hash值都是不同的；
//		//比较equals方法；使用自带的方法就过滤了key1和key2为空的情况：(a == b) || (a != null && a.equals(b));
//		if (Objects.equals(key1, key2)) return 0; 
//		
//		//到这里就证明了equals方法不相同，必须要返回一个大小；可以检查元素是否具有比较性；
//		if (key1 instanceof Comparable) return ((Comparable<K>)key1).compareTo(key2);
//		
//		//到这里就证明以上的所有方法都不能实现；根据内存地址来得到一个值进行大小的判断；
		return System.identityHashCode(key1) - System.identityHashCode(key2);

	}
	
	//节点的扫描的方法；
	private Entry<K, V> Entry(Entry<K, V> Entry, K k1) {
		int h1 = hash(k1);
		// 存储查找结果
		Entry<K, V> result = null;
		int cmp = 0;
		while (Entry != null) {
			K k2 = Entry.key;
			int h2 = hash(Entry.key);
			// 先比较哈希值
			if (h1 > h2) {
				Entry = Entry.right;
			} else if (h1 < h2) {
				Entry = Entry.left;
			} else if (Objects.equals(k1, k2)) {
				return Entry;
			} else if (k1 != null && k2 != null 
					&& k1 instanceof Comparable
					&& k1.getClass() == k2.getClass()
					&& (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
				Entry = cmp > 0 ? Entry.right : Entry.left;
			} else if (Entry.right != null && (result = Entry(Entry.right, k1)) != null) { 
				return result;
			} else { // 只能往左边找
				Entry = Entry.left;
			}
		}
		return null;
	}
	
	//计算出hash值的函数。
	private int hash(K key) {
		if (key == null) return 0;
		//获得原始的hashCode；
		int hashCode = key.hashCode();
		//进行扰动计算；
		hashCode = hashCode ^ (hashCode >> 16);
		//相当与拿到了索引；
		return hashCode & (element.length - 1);
	}
	
	//计算出比cap大的最小的2^n的数；
	private final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
	
	//写添加之后的旋转的逻辑；
	private void afterAdd(Entry<K,V> node) {
		while((node = node.praent) != null) {
			if (isBlance(node)) {
				updateHeight(node);
			}else {
				//恢复平衡；
				reblance(node);
				break;
			}
		}
	}
	
	private void afterRemove(Entry<K,V> node) {
		while((node = node.praent) != null) {
			if (isBlance(node)) {
				updateHeight(node);
			}else {
				//恢复平衡；
				reblance(node);
			}
		}
	}
	

//	//使用了模板方式的设计模式；
//	@Override
//	private Entry<K,V> createNode(E element, Entry<K,V> parent) {
//		return new AVLEntry<K,V>(element, parent);
//	}
	
	//进行左旋转；grend就是需要进行旋转的节点
	private void left(Entry<K,V> grend) {
		Entry<K,V> parent = grend.right;
		Entry<K,V> child = parent.left;
		//进行移位;
		grend.right = parent.left;
		parent.left = grend;
		afterRote(grend, parent, child);
		
	}
	
	//进行右旋转；
	private void right(Entry<K,V> grend) {
		Entry<K,V> parent = grend.left;
		Entry<K,V> child = parent.right;
		//更改指向；
		grend.left = parent.right;
		parent.right = grend;
		
		afterRote(grend, parent, child);
	}
	
	private void afterRote(Entry<K,V> grend,Entry<K,V> parent, Entry<K,V> child) {
		//让parent成为根节点；
		if (grend.praent != null) {
			if (grend.isLeft()) {
				grend.praent.left = parent;
			}else {
				grend.praent.right = parent;
			}
		}else {
			//grend是根节点的情况；
			 element[hash(grend.key)] = parent;
		}
		//维护父节点；
		if (child != null) {
			child.praent = grend;
		}
		parent.praent = grend.praent;
		grend.praent = parent;
		
		//不要忘记了更新高度
		updateHeight(grend);
		updateHeight(parent);
	}
	
	private void reblance(Entry<K,V> grand) {
		Entry<K,V> parent = grand.tallerChild();
		Entry<K,V> node = parent.tallerChild();
		
		if (parent.isLeft()) {//L
			if (node.isLeft()) {//LL的情况，进行右旋转；
				right(grand);
			}else {
				//LR的情况；
				left(parent);
				right(grand);
			}
		}else {
			if (node.isLeft()) {//RL的情况，进行右旋转；
				right(parent);
				left(grand);
			}else {
				//RR的情况；
				left(grand);
			}
		}
		
	}
	
	private boolean isBlance(Entry<K,V> node) {
		return Math.abs(node.balanceFactor()) <= 1;
	}
	
	private void updateHeight(Entry<K,V> node) {
		node.updateHeight();
	}
		
	
	//AVL树专有的Node的节点；
	private static class Entry<K,V> {
		//维护则一个高度的属性。默认叶子节点的高度为1.
		int height = 1;
		K key;
		V value;
		Entry<K,V> praent;
		Entry<K,V> left;
		Entry<K,V> right;
		public Entry(K key, V value, Entry<K, V> praent) {
			this.key = key;
			this.value = value;
			this.praent = praent;
		}
		//是右子节点；
		public boolean isRight() {
			return praent != null && this == praent.right;
		}
		
		//是左子节点；
		public boolean isLeft() {
			return praent != null && this == praent.left;
		}

		public boolean isLeaf() {
			return (left == null) && (right == null);
		}
		
		public boolean havaTweChild() {
			return (left != null) && (right != null);
		}
		

	
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : left.height;
			int rightHeight = right == null ? 0 : right.height;
			return leftHeight - rightHeight;
		}
		
		public void updateHeight() {
			int leftHeight = left == null ? 0 : left.height;
			int rightHeight = right == null ? 0 : right.height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		
		//返回较高的子节点；
		public Entry tallerChild() {
			int leftHeight = left == null ? 0 : left.height;
			int rightHeight = right == null ? 0 : right.height;
			if (leftHeight > rightHeight) return left;
			if (leftHeight < rightHeight) return right;
			return isLeft() ? left : right;
		}
		
		@Override
		public String toString() {
			return key+"_"+value;
		}
	}

}
