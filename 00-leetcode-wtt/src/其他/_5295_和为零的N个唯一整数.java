package 其他;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/contest/weekly-contest-169/problems/find-n-unique-integers-sum-up-to-zero/
 * @author 涛宝宝
 *
 */

public class _5295_和为零的N个唯一整数 {

	public static int[] sumZero(int n) {
		
		LinkedList<Integer> linkedList = new LinkedList<>();
		
		if (n % 2 != 0) {
			linkedList.offer(0);
		}
		for(int i = 0 ; i < (n >> 1) ; i++){
			int index = i+1;
			linkedList.offerFirst(-index);
			linkedList.offerLast(index);
		}
		int[] result = new int [n];
		int i = 0;
	    while (!linkedList.isEmpty()) {
			result[i++] = linkedList.poll();
		}
//		System.out.println(Arrays.toString(result));
		return result;
	}
	
	public static void main(String[] args) {
		sumZero(5);
	}

}
