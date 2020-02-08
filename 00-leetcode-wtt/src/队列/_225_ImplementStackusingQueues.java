package 队列;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 用队列实现栈功能
 * @author 涛宝宝
 *
 */
public class _225_ImplementStackusingQueues<E> {

	private  LinkedList<E> in = new LinkedList();
	private  LinkedList<E> out = new LinkedList();
	
    /** Initialize your data structure here. */
    public _225_ImplementStackusingQueues() {
        
    }
    
    /** Push element x onto stack. */
    public void push(E x) {
        in.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public E pop() {
        if (out.isEmpty()) {
			while(!in.isEmpty()) {
				out.offerFirst(in.poll());
			}
		}
        return out.poll();
    }
    
    /** Get the top element. */
    public E top() {
    	if (out.isEmpty()) {
			while(!in.isEmpty()) {
				out.offerFirst(in.poll());
			}
		}
        return  out.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }
    
    public static void main(String[] args) {
    	_225_ImplementStackusingQueues stackusingQueues = new _225_ImplementStackusingQueues();
    	stackusingQueues.push(1);
    	stackusingQueues.push(2);
    	stackusingQueues.push(3);
    	System.out.println(stackusingQueues.pop());
    	System.out.println(stackusingQueues.pop());
    	System.out.println(stackusingQueues.top());
//    	String str="明      天     放    假   ";
//    	String []datas=str.split(" +");
//    	System.out.println("数组的元素:"+Arrays.toString(datas));
//    	String str2="我..我....我...我.要...要...要..学...学学...编.编...编.程..程.程.程";
//    	//转换成: 我要学编程
//    	String []s=str2.split("[.]+");
//    	System.out.println("数组的元素为："+Arrays.toString(s));
	}

}
