package 栈;

import java.util.LinkedList;

/**
 * 224. Basic Calculator https://leetcode-cn.com/problems/basic-calculator/
 * 想办法把这个简单计算器的式子转化成前缀或者后缀表达式；
 * 前缀表达式，就是运算符在数字的前面，后缀表达式，就是运算符的后面，只要掌握了一种表达式，对于我们求解都睡得方便了；
 * 我们传统上的表达式，运算符在两个数字的中间，称之为前缀表达式，那么问题来了，我们现在的目标就是把中缀表达式转换成前缀或者后缀表达式。
 * 
 * @author 涛宝宝
 *
 */
public class _224_BasicCalculator {
	public static int calculate(String s) {
		s = getAfterExpression(s);
		System.out.println(s);
		int result = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ('+' == c || '-' == c) {
				int right = list.pop();
				int left = list.pop();
				result = getReult(c, left, right);
				list.push(result);
			} else {
				if (',' == c) {
					StringBuffer intBuffer = new StringBuffer();
					while (s.charAt(i + 1) != ',') {
						intBuffer.append(s.charAt(i++ + 1));
					}
					i++;
					list.push(Integer.parseInt(intBuffer.toString()));
				} else {
					list.push(Integer.parseInt(c + ""));
				}
			}
		}

		// 注意一定要判断栈里面没有只有一个元素的情况，太狠了；
		if (result == 0) {
			StringBuffer buffer = new StringBuffer();
			while (!list.isEmpty()) {
				buffer.append(list.pollLast());
			}
			result = Integer.parseInt(buffer.toString());
		}

		return result;
	}

	// 获得后缀表达式，s是中缀表达式，转换成后缀表达式;
	public static String getAfterExpression(String s) {
		// 转换后的后缀表达式；遇到符号，入栈，遇到数字，加入表达式；
		StringBuffer AfterExpression = new StringBuffer();
		;
		LinkedList<Character> list = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// 判断哪个恶心的空格；防止空格恶心我们；
			if (' ' == c) {
				continue;
			}
			// 因为这里只需要加减，所以，就不需要乘除了；
			if (c == '(') {
				// push查看源码可以知道，在头部添加元素。
				list.push(c);
			} else if (c == '+' || c == '-') {
				// peekLast表示看一下最后一个元素，pop和poll都是弹出first元素；
				// 要是栈顶元素的优先级低于或者等于c的时候，进行弹出(弹出栈顶元素在进行比较)，这时候'('的优先级是最低的。
				// 若是栈顶元素的优先级高于c的时候，就进行压栈，因为这里是简单的进行一个加减操作，所以就不存在这种情况，直接输出；
				while (!list.isEmpty() && list.peek() != '(') {
					AfterExpression.append(list.pop());
				}
				list.push(c);
			} else if (c == ')') {
				// peekLast表示看一下最后一个元素，pop和poll都是弹出first元素；
				while (list.peek() != '(') {
					AfterExpression.append(list.pop());
				}
				// 清除栈顶的'(';
				list.pop();
			} else {
				// 为了多位数的加减法， 我们只能加入','做判断条件
				if ((i + 1) < s.length() && (s.charAt(i + 1) <= '9' && s.charAt(i + 1) >= '0')) {
					AfterExpression.append(',');
					AfterExpression.append(c);
					while ((i + 1) < s.length() && (s.charAt(i + 1) <= '9' && s.charAt(i + 1) >= '0')) {
						AfterExpression.append(s.charAt(i + 1));
						i++;
					}
					AfterExpression.append(',');
				} else {
					AfterExpression.append(c);
				}
			}
		}
		while (!list.isEmpty()) {
			AfterExpression.append(list.pop());
		}
		return AfterExpression.toString();
	}

	public static int getReult(char symbol, int left, int right) {
		if ('+' == symbol) {
			return left + right;
		}
		if ('-' == symbol) {
			return left - right;
		}
//		if ('*'== symbol) {
//			return left * right;
//		}
//		if ('/'== symbol) {
//			return left / right;
//		}
		return 0;
	}

	public static void main(String[] args) {
		String s = "4564545454";
		System.out.println(calculate(s));
	}
}
