package practice;

import java.util.List;

//https://leetcode.com/problems/reverse-nodes-in-k-group/description/
//TC: O(n)
//SC: O(1)
public class ReverseLinkedListKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        ListNode ansHead = null;
        ListNode prevTail = null;
        while(curr != null) {
            int counter = 0;
            ListNode newHead = curr;
            while(curr != null && counter<k) {
                curr=curr.next;
                counter++;
            }

            if (counter==k) {
                ListNode subHead = reverseLinkedList(newHead, k);
                if (ansHead == null) {
                    ansHead = subHead;
                } else {
                    prevTail.next = subHead;
                }
                //to preserve the tail node from previous iteration
                prevTail = newHead;
            } else {
                prevTail.next = newHead;
            }

        }
        return ansHead;
    }

    private ListNode reverseLinkedList(ListNode node, int k) {
        ListNode curr = node;
        ListNode prev = null;
        int counter = 0;
        while(counter<k) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
            counter++;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);

        ListNode newHead = new ReverseLinkedListKGroup().reverseKGroup(one, 2);
    }
}
