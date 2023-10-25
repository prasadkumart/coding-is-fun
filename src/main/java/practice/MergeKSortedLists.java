package practice;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

    //to push or pop element from PQ/Heap is - log N
    //here heap size is K - log K
    //K -#of lists; N - # of elements from all lists
    //TC: O(N log K)
    //SCL O(N) - size of the PriorityQueue
    public ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        for(ListNode listNode : lists) {
            if (null != listNode) {
                queue.add(listNode); //log K
            }
        }
        ListNode head = queue.poll();
        if (head != null && head.next != null) {
            queue.add(head.next);
        }
        ListNode tail = head;
        while(!queue.isEmpty()) {
            ListNode currNode = queue.poll();
            tail.next = currNode;
            tail = tail.next;
            if (currNode.next != null) {
                queue.add(currNode.next);
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