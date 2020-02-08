package 二叉树;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.w3c.dom.ls.LSResourceResolver;

/**
 * 使用队列来体现层次遍历；
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * 我们把层次遍历的结果来一个翻转就好了；Collections.reverse(List)//返回值是void
 * @author 涛宝宝
 *
 */
public class _102_二叉树的层次遍历 {

	public List<List<Integer>> levelOrder(TreeNode root) {
		
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
		Collections.reverse(list);
		
		return list;
	}

}
