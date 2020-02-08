package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * 
 * @author 涛宝宝
 *
 */
public class _114_二叉树展开为链表 {

	public void flatten(TreeNode root) {
		
		if(root == null){
            return;
        }
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode pop = stack.pop();
//			temp.right = new TreeNode(pop.val);
//			temp = temp.right;
//			System.out.println(pop.val);
			list.add(pop.val);
			if (pop.right != null) {
				stack.push(pop.right);
			}
			if (pop.left != null) {
				stack.push(pop.left);
			}
		}
		TreeNode newRoot = new TreeNode(0);
		TreeNode temp = newRoot;
		for(int i = 0 ; i < list.size() ; i++) {
			temp.right = new TreeNode(list.get(i));
			temp.left = null;
			temp = temp.right;
		}
		root = newRoot.right;
	}

}
