package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 * 
 * @author 涛宝宝
 *
 */
public class _559_N叉树的最大深度 {
	public int maxDepth(Node root) {
		if (root == null) {
			return 0;
		}
		int levelSize = 1;
		int height = 0;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			Node poll = queue.poll();
			levelSize--;
			List<Node> nodes = poll.children;
			for(int i = 0 ; i < nodes.size() ; i++) {
				if (nodes.get(i) != null) {
					queue.offer(nodes.get(i));
				}
			}
			if (levelSize == 0) {
				levelSize = queue.size();
				height++;
			}
		}
		return height;
	}
}
