package 二叉树;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * 我觉得可以使用队列先进先出；
 * 
 * @author 涛宝宝
 *
 */
public class _589_N叉树的前序遍历 {

	List<Integer> res = new ArrayList<Integer>();

	public List<Integer> preorder(Node root) {

//		if (root == null)
//			return res;
//		res.add(root.val);
//		for (Node child : root.children) {
//			preorder(child);
//		}
//
//		return res;

		Stack<Node> stack = new Stack<Node>();
		List<Integer> result = new ArrayList<Integer>();
		if (root == null) {
			return result;
		}
		stack.push(root);
		
		//使用栈先进后出的规则进行实现;
		while(!stack.isEmpty()) {
			Node poll = stack.pop();
			result.add(poll.val);
			List<Node> list = poll.children;
			for(int i = list.size() - 1 ; i >= 0 ; i--) {
				stack.push(list.get(i));
			}
		}
		
		return result;
	}

}
