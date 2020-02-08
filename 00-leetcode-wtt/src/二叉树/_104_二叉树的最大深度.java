package 二叉树;

import java.rmi.MarshalException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度，可以采用递归和非递归的方式实现；
 * 
 * @author 涛宝宝
 *
 */
public class _104_二叉树的最大深度 {

//	public int maxDepth(TreeNode root) {
//		if (root == null) {
//			return 0;
//		}
//		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
//	}
	
	//非递归写法;当然还是需要层序遍历了；
	public int maxDepth(TreeNode root) {
		
		if (root == null) {
			return 0;
		}
		int levelSize = 1;
		int height = 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			TreeNode poll = queue.poll();
			levelSize--;
			if (poll.left != null) {
				queue.offer(poll.left);
			}
			if (poll.right != null) {
				queue.offer(poll.right);
			}
			if (levelSize == 0) {
				levelSize = queue.size();
				height++;
			}
		}
		return height;
	}


}
