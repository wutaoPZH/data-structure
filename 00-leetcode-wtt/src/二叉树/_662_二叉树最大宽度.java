package 二叉树;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 * 中等题，所谓宽度，就是每一层的size的长度；记录每一个节点的索引，到时候直接相减就可了
 * 
 * @author 涛宝宝
 *
 */
public class _662_二叉树最大宽度 {

	private int maxW = 1;
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;
		
		Queue<TreeNode> queue = new LinkedList<>();
		LinkedList<Integer> indexList = new LinkedList<>();
		queue.add(root);
		indexList.add(1);
		int size = 1;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			size--;
			int index = indexList.removeFirst();
			if (node.left != null) {
				queue.add(node.left);
				indexList.add(2 * index);
			}
			if (node.right != null) {
				queue.add(node.right);
				indexList.add(2 * index + 1);
			}
			if (size == 0) {
				if (indexList.size() >= 2) {
					maxW = Math.max(maxW, indexList.getLast() - indexList.getFirst() + 1);
				}
				size = queue.size();
			}
		}

		return maxW;
	}

}
