package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 困难题；
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * @author 涛宝宝
 *后续遍历，先遍历完当前节点，然后压入栈中，在开始的位置添加数据，这样就可以达到先遍历左子树，在遍历右子树，最后遍历根节点的效果
 */
public class _145_二叉树的后序遍历 {
	public List<Integer> postorderTraversal(TreeNode root) {
	    List<Integer> res = new ArrayList<Integer>();
	    if(root == null)
	        return res;
	    Stack<TreeNode> stack = new Stack<TreeNode>();
	    stack.push(root);
	    while(!stack.isEmpty()){
	        TreeNode node = stack.pop();
	        if(node.left != null) stack.push(node.left);//和传统先序遍历不一样，先将左结点入栈
	        if(node.right != null) stack.push(node.right);//后将右结点入栈
	        res.add(0,node.val);                        //逆序添加结点值
	    }     
	    return res;
	}
}
