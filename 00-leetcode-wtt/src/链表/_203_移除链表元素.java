package 链表;

import java.util.logging.Handler;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 
 * @author 涛宝宝
 *
 */
public class _203_移除链表元素 {
	
	public static ListNode removeElements(ListNode head, int val) {
		
		
		
		ListNode header = new ListNode(-1);
        header.next = head;
        ListNode cur = header;
        while(cur.next != null){
            if(cur.next.val == val ){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return header.next;
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		ListNode listNode = new ListNode(1);
		listNode = removeElements(listNode,1);
		System.out.println(listNode.val);
	}
}
