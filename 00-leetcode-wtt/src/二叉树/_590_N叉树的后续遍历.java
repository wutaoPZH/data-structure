package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *   和二叉树的后序遍历是一样的，只是两个节点变成了多个节点；
 * @author 涛宝宝
 *
 */
public class _590_N叉树的后续遍历 {

	public List<Integer> postorder(Node root) {
		List<Integer> res = new ArrayList<Integer>();
	    if(root == null) {
	    	return res;
	    }
	    Stack<Node> stack = new Stack<Node>();
	    stack.push(root);
	    while(!stack.isEmpty()){
	    	Node node = stack.pop();
	    	List<Node> childen = node.children;
	    	for(int i = 0 ; i < childen.size() ; i++) {
	    		stack.push(childen.get(i));
	    	}
	        res.add(0,node.val);                        //逆序添加结点值
	    }
	    return res;
	}

}
