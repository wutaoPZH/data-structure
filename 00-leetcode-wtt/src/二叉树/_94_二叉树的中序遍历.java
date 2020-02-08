package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 
 * @author 涛宝宝
 *
 */
public class _94_二叉树的中序遍历 {
	public List<Integer> inorderTraversal(TreeNode root) {
		
		/**
		 * 先走左边，如果发现为null，就pop到父节点，进行输入，然后在走右边，依次压栈，厉害呀！
		 */

		List<Integer> list = new ArrayList<Integer>();

		// 利用栈的先进后出的规则可以实现；
		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = root;
		while (cur != null || !stack.isEmpty()) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}else {
				cur = stack.pop();
				list.add(cur.val);
				cur = cur.right;
			}
		}

		return list;
	}

}
