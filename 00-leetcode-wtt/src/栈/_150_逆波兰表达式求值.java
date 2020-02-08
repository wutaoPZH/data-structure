package 栈;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.W3CDomHandler;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * 
 * @author 涛宝宝
 *
 */
public class _150_逆波兰表达式求值 {
	public static int evalRPN(String[] tokens) {
		int result = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < tokens.length; i++) {
			if ("+".equals(tokens[i]) || "-".equals(tokens[i]) || "*".equals(tokens[i]) || "/".equals(tokens[i])) {
				int right = list.pop();
				int left = list.pop();
				result = getReult(tokens[i], left, right);
				list.push(result);
			} else {
				list.push(Integer.parseInt(tokens[i]));
			}
		}
		
		//注意一定要判断栈里面没有只有一个元素的情况，太狠了；
		if (list.size() == 1) {
			return list.pop();
		}

		return result;
	}

	public static int getReult(String symbol, int left, int right) {
		if ("+".equals(symbol)) {
			return left + right;
		}
		if ("-".equals(symbol)) {
			return left - right;
		}
		if ("*".equals(symbol)) {
			return left * right;
		}
		if ("/".equals(symbol)) {
			return left / right;
		}
		return 0;
	}

	public static void main(String[] args) {
		String[] token = {"18"};
		System.out.println(evalRPN(token));
	}
}
