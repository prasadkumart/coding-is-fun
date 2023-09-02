package practice;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    //K -#of lists; N - # of elements from all lists
    //TC: O(N log K)
    //SCL O(N) - size of the PriorityQueue
    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));
        for(int i=0; i<lists.length; i++) { //O(K)
            if (null != lists[i]) {
                pq.add(lists[i]); //O(log K) // one node from each list is added to PQ
            }
        }

        ListNode head = null;
        ListNode tail = null;

        while(!pq.isEmpty()) { //O(N) N: total no of elements in all lists
            ListNode curr = pq.poll();

            if (null == head) {
                head = tail = curr;
            } else {
                tail.next = curr;
                tail = curr;
            }

            if (curr.next != null) {
                pq.add(curr.next); //O(log K), repeated for N times, total TC: O(N * log K)
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode list13 = new ListNode(5);
        ListNode list12 = new ListNode(4, list13);
        ListNode list11 = new ListNode(1, list12);

        ListNode list23 = new ListNode(4);
        ListNode list22 = new ListNode(3, list23);
        ListNode list21 = new ListNode(2, list22);

        ListNode list32 = new ListNode(6);
        ListNode list31 = new ListNode(1, list32);

        ListNode[] listNodes = new ListNode[]{list11, list21, list31};

        ListNode mergedList = new MergeKSortedLists().mergeKLists(new ListNode[]{null});
        System.out.println(mergedList);
        mergedList = new MergeKSortedLists().mergeKLists(new ListNode[]{});
        System.out.println(mergedList);
        mergedList = new MergeKSortedLists().mergeKLists(listNodes);
        System.out.println(mergedList);
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}