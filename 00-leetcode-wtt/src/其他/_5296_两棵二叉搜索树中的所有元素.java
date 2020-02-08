package 其他;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * https://leetcode-cn.com/contest/weekly-contest-169/problems/all-elements-in-two-binary-search-trees/
 * 给你 root1 和 root2 这两棵二叉搜索树。
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 * @author 涛宝宝
 *
 */
public class _5296_两棵二叉搜索树中的所有元素 {
	
	public static List<Integer> list = new LinkedList<>();
	
	//必须先把两个二叉树进行遍历出来。
	public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		dispalyTree(root1);
		dispalyTree(root2);
		Collections.sort(list);
		return list;
	}

	private static void dispalyTree(TreeNode root1) {
		
	   if (root1 == null){
            return;
        }
		dispalyTree(root1.left);
		list.add(root1.val);
		dispalyTree(root1.right);
	}
	
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(0);
		TreeNode t2 = new TreeNode(-10);
		TreeNode t3 = new TreeNode(10);
		
		TreeNode t4 = new TreeNode(5);
		TreeNode t5 = new TreeNode(1);
		TreeNode t6 = new TreeNode(7);
		TreeNode t7 = new TreeNode(0);
		TreeNode t8 = new TreeNode(2);
		t4.left = t5;
		t5.left = t7;
		t5.right = t8;
		t4.right = t6;
		t1.left = t2;
		t1.right = t3;
		List<Integer> list = getAllElements(t1, t4);
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.printf(" %d",iterator.next());
			
		}
	}
	
}
