package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 * 在层次遍历的基础之上逆序遍历。
 * 
 * @author 涛宝宝
 *
 */
public class _107_二叉树的层次遍历2 {

	public List<List<Integer>> levelOrderBottom(TreeNode root) {

		List<List<Integer>> list = new ArrayList<List<Integer>>();

		if (root == null) {
			return list;
		}
		
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		int levelSize = 1;
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		while(!queue.isEmpty()) {
			
			TreeNode poll = queue.poll();
			arrayList.add(poll.val);
			levelSize--;
			if (poll.left != null) {
				queue.offer(poll.left);
			}
			if (poll.right != null) {
				queue.offer(poll.right);
			}
			if (levelSize == 0) {
				levelSize = queue.size();
				list.add(arrayList);
				arrayList = new ArrayList<>();
			}
		}
		
		return list;
	}

}
