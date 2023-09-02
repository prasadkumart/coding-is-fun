package practice;

import java.util.List;

//https://leetcode.com/problems/add-two-numbers/
public class AddTwoNumbers {
    //N - length of l1, M - length of l2
    //TC
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryOver = 0;
        ListNode head = null;
        ListNode curr = head;
        while(null != l1 || null != l2 || carryOver == 1) { //O(max(N, M))
            int val1 = 0;
            if (null != l1) {
                val1= l1.val;
                l1 = l1.next;
            }
            int val2 = 0;
            if (null != l2) {
                val2= l2.val;
                l2 = l2.next;
            }
            int val3 = val1 + val2 + carryOver;
            ListNode newNode = new ListNode(val3 % 10);
            if (null == curr) {
                head = curr = newNode;
            } else {
                curr.next = newNode;
            }
            curr = newNode;
            carryOver = val3 / 10;
        }

//        if (carryOver > 0) {
//            ListNode newNode = new ListNode(carryOver);
//            curr.next = newNode;
//        }
        return head;
    }

    public static void main(String[] args) {
        //2 -> 4 -> 3
        ListNode l13= new ListNode(3);
        ListNode l12= new ListNode(4, l13);
        ListNode l11= new ListNode(2, l12);

        //5-> 6 -> 4
        ListNode l23= new ListNode(4);
        ListNode l22= new ListNode(6, l23);
        ListNode l21= new ListNode(5, l22);

        ListNode result = new AddTwoNumbers().addTwoNumbers(l11, l21);

        //9->9->9
        ListNode l33= new ListNode(9);
        ListNode l32= new ListNode(9, l33);
        ListNode l31= new ListNode(9, l32);

        //9-> 9
        ListNode l42= new ListNode(9);
        ListNode l41= new ListNode(9, l42);

        result = new AddTwoNumbers().addTwoNumbers(l31, l41);
        System.out.println("Complete");
    }

}
