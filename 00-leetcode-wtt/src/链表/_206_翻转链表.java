package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * 
 * @author 涛宝宝
 *
 */
public class _206_翻转链表 {
	public ListNode reverseList(ListNode head) {
//		if (head == null || head.next == null) {
//			return head;
//		}
//		ListNode newHead = reverseList(head.next);
//		//运用递归的方式进行判断；
//		head.next.next = head;
//		head.next = null;
//		return newHead;
		
		//使用头插法;
		ListNode newHead = null;

		while(head != null) {
			ListNode tmp = head.next;
			head.next = newHead;
			newHead = head;
			head = tmp;
		}
		return newHead;
	}
}
