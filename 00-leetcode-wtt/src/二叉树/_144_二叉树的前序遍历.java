package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * 
 * @author 涛宝宝
 *
 */
public class _144_二叉树的前序遍历 {
	public List<Integer> preorderTraversal(TreeNode root) {
		
		List<Integer> list = new ArrayList<Integer>();
		
		if (root == null) {
			return list;
		}
		//利用栈的先进后出的规则可以实现；
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode pop = stack.pop();
			list.add(pop.val);
			if (pop.right != null) {
				stack.add(pop.right);
			}
			if (pop.left != null) {
				stack.add(pop.left);
			}
		}
		
		return list;

	}
}
