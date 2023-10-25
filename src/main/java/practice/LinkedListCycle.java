package practice;

import java.util.HashMap;
import java.util.HashSet;

//https://leetcode.com/problems/linked-list-cycle/description/
public class LinkedListCycle {

    //TC: O(n) visits all nodes
    //SC: O(n) auxiliary memory for set
    public boolean hasCycle(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        HashSet<ListNode> visitedSet = new HashSet<>();
        while (head != null) {
            if (visitedSet.contains(head)) {
                return true;
            } else {
                visitedSet.add(head);
                head = head.next;
            }
        }
        return false;
    }

    //Floyd's also - tortoise & hare algo, slow/fast pointer algo
    //TC: O(n) visits all nodes
    //SC: O(1) - no extra memory
    public boolean hasCycleSlowFastPointer(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode slow = head, fast = head;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
