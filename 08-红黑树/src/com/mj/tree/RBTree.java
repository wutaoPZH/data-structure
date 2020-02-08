package com.mj.tree;

import java.util.Comparator;

import javax.swing.text.StyledEditorKit.BoldAction;

import com.mj.tree.BinaryTree.Node;

/**
 * 红黑树的代码 ，最难的树了，大家加油
 * @author 涛宝宝
 *
 */
public class RBTree<E> extends BBST<E> {
	
	//分别代表两种颜色
	public static final boolean RED = true;
	
	public static final boolean BLACK = false;
	
	public RBTree() {
		// 进行构造函数的赋值；
		this(null);
	}

	public RBTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	//添加一些辅助的函数,把目标节点染红,正好完成一次向下转型；
	private RBNode<E> red(Node<E> node){
		return color(node, RED);
	}
	
	private RBNode<E> black(Node<E> node){
		return color(node, BLACK);
	}
	
	private RBNode<E> color(Node<E> node, boolean color){
		if (node != null) {
			((RBNode<E>)node).color = color;
		}
		return (RBNode<E>)node;
	}
	
	//判断是红色或者不是红色；
	private boolean isBlack(Node<E> node){
		return ((RBNode<E>)node).color == BLACK; 
	}
	
	private boolean isRed(Node<E> node){
		return ((RBNode<E>)node).color == RED; 
	}
	
	private boolean colorOf(Node<E> node){
		return ((RBNode<E>)node).color == RED ? RED : BLACK; 
	}
	
	
	//添加之后的回复情况；
	@Override
	protected void afterAdd(Node<E> node) {
		Node<E> parent = node.parent;
		//当我们添加根节点的时候，直接染黑就行；
		if (parent == null) {
			black(parent);
			return;
		}
		
		//第一种情况，当我们添加节点的父节点是黑色的时候，我们就可以不做任何的处理；
		if (isBlack(parent)) {
			return;
		}
		
		Node<E> sibling = parent.sibling();
		Node<E> grand = red(parent.parent);
		//LL/RR的旋转操作；
		if (isBlack(sibling)) {//叔父节点是黑色（不是红色）
			black(parent);
			if (parent.isLeftChild()) {
				if (node.isLeftChild()) {
					//LL
					rotateRight(grand);
				}else {
					//LR
					rotateLeft(parent);
					rotateRight(grand);
				}
				
			}else { 
				if (node.isLeftChild()) {
					//RL
					rotateRight(parent);
					rotateLeft(grand);
				}else {
					//RR
					rotateLeft(grand);
				}
			}
		}else {//叔父节点是红色
			black(parent);
			black(sibling);
			afterAdd(grand);
			return;
		}
		
	}
	
	protected void afterRemove(Node<E> node, Node<E> replacement) {
	// 如果删除的节点是红色
	if (isRed(node)) return;
	
	// 用以取代node的子节点是红色
	if (isRed(replacement)) {
		black(replacement);
		return;
	}
	
	Node<E> parent = node.parent;
	// 删除的是根节点
	if (parent == null) return;
	
	// 删除的是黑色叶子节点【下溢】
	// 判断被删除的node是左还是右
	boolean left = parent.left == null || node.isLeftChild();
	Node<E> sibling = left ? parent.right : parent.left;
	if (left) { // 被删除的节点在左边，兄弟节点在右边
		if (isRed(sibling)) { // 兄弟节点是红色
			black(sibling);
			red(parent);
			rotateLeft(parent);
			// 更换兄弟
			sibling = parent.right;
		}
		
		// 兄弟节点必然是黑色
		if (isBlack(sibling.left) && isBlack(sibling.right)) {
			// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
			boolean parentBlack = isBlack(parent);
			black(parent);
			red(sibling);
			if (parentBlack) {
				afterRemove(parent, null);
			}
		} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
			// 兄弟节点的左边是黑色，兄弟要先旋转
			if (isBlack(sibling.right)) {
				rotateRight(sibling);
				sibling = parent.right;
			}
			
			color(sibling, colorOf(parent));
			black(sibling.right);
			black(parent);
			rotateLeft(parent);
		}
	} else { // 被删除的节点在右边，兄弟节点在左边
		if (isRed(sibling)) { // 兄弟节点是红色
			black(sibling);
			red(parent);
			rotateRight(parent);
			// 更换兄弟
			sibling = parent.left;
		}
		
		// 兄弟节点必然是黑色
		if (isBlack(sibling.left) && isBlack(sibling.right)) {
			// 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
			boolean parentBlack = isBlack(parent);
			black(parent);
			red(sibling);
			if (parentBlack) {
				afterRemove(parent, null);
			}
		} else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
			// 兄弟节点的左边是黑色，兄弟要先旋转
			if (isBlack(sibling.left)) {
				rotateLeft(sibling);
				sibling = parent.left;
			}
			
			color(sibling, colorOf(parent));
			black(sibling.left);
			black(parent);
			rotateRight(parent);
		}
	}
}
	
	private class RBNode<E> extends Node<E>{
		
		//增加特有的属性，是一个颜色；我们默认是添加的是一个红色的颜色；
		boolean color = RED;

		public RBNode(E element, Node<E> parent) {
			super(element, parent);
			// TODO Auto-generated constructor stub
		}
		
	}

}
