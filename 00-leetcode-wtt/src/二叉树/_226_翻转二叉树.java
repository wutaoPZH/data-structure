package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 可以使用两种方法，递归或者迭代都可哟；
 * @author 吴涛涛
 *
 */
public class _226_翻转二叉树 {

//   public TreeNode invertTree(TreeNode root) {
//	   if (root == null) return root;
//	   
//	   TreeNode tmp = root.left;
//	   root.left = root.right;
//	   root.right = tmp;
//	   
//       invertTree(root.left);
//       invertTree(root.right);
//       
//       return root;
//   }

//	public TreeNode invertTree(TreeNode root) {
//	   if (root == null) return root;
//	   
//       invertTree(root.left);
//       invertTree(root.right);
//	   
//	   TreeNode tmp = root.left;
//	   root.left = root.right;
//	   root.right = tmp;
//       
//       return root;
//    }

//	public TreeNode invertTree(TreeNode root) {
//	   if (root == null) return root;
//	   
//       invertTree(root.left);
//
//	   TreeNode tmp = root.left;
//	   root.left = root.right;
//	   root.right = tmp;
//
//       invertTree(root.left);
//       
//       return root;
//    }

	// 可以通过层序遍历交换两个节点；
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode poll = queue.poll();
			TreeNode temp = poll.left;
			poll.left = poll.right;
			poll.right = temp;
			if (poll.left != null) {
				queue.offer(poll.left);
			}
			if (poll.right != null) {
				queue.offer(poll.right);
			}
		}
		return root;

	}
}
