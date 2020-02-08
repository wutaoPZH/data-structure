package 链表;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 该问题直接使用快慢指针即可解决；
 * @author 涛宝宝
 *
 */
public class _141_判断链表是否有环 {

	public boolean hasCycle(ListNode head) {
		ListNode slow = head;
		//可以不从头开始！！
		ListNode fast = head;
		
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				return true;
			}
		}
		return false;
	}
}
