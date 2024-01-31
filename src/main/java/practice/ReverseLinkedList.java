package practice;

//https://leetcode.com/problems/reverse-linked-list/description/

//TC: O(n)
//SC: O(1)
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        //O(n)
        while(curr != null) {
            ListNode nextNode = curr.next; //2; 3
            curr.next = prev; //1(null); 2(1)
            prev = curr;//1; 2
            curr = nextNode; //2; 3
        }

        return prev;
    }
}