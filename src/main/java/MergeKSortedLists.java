import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/merge-k-sorted-lists/
//https://www.youtube.com/watch?v=L-8LVBPmIpc&ab_channel=EricProgramming
//O(Nlogk) T
//O(N+K) S
public class MergeKSortedLists {
    public LinkedListNode init(Integer val) {
        return new LinkedListNode(val);
    }

    static LinkedListNode merge_k_lists(ArrayList<LinkedListNode> lists) {
        PriorityQueue<LinkedListNode> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.value, b.value));

        for(int i=0; i<lists.size(); i++) {
            LinkedListNode listNode = lists.get(i);
            if (listNode != null) {
                minHeap.add(listNode); //O(logN)
            }
        }

        LinkedListNode head = new LinkedListNode(null);
        LinkedListNode current = head;
        while (!minHeap.isEmpty()) {
            LinkedListNode topNode = minHeap.poll(); //O(1)
            current.next = topNode;
            current = topNode;

            if (topNode.next != null) {
                minHeap.add(topNode.next); //O(lon n)
            }
        }

        // Write your code here.
        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));

        //only first node is added from all lists - O(1)
        for(ListNode node: lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        // new sorted list
        ListNode head = new ListNode(); //O(n) Creating a new linked list costs O(n) space.
        ListNode temp = head;

        while(!minHeap.isEmpty()) { //O(n)
            ListNode top = minHeap.poll(); //O(1)
            temp.next = top;
            temp = temp.next;
            //add next element from the list to minHeap
            if (top.next != null) {
                minHeap.add(top.next); //O(logK) - minHelp has to sort the elements after adding a new one
            }
        }

        return head.next;
    }

    public ListNode createList(int[] arr) {
        ListNode head = null;
        ListNode tailNode = head;
        for (int i : arr) {
            ListNode node = new ListNode(i);
            if (null == head) {
                head = node;
            } else {
                tailNode.next = node;
            }
            tailNode = node;
        }

        return head;
    }

    public void traverse(ListNode head) {
        ListNode node = head;
        int count = 0;
        while(node != null) {
            System.out.print(node.val + "->");
            count++;
            node = node.next;
        }
        System.out.println("\ncount " + count);
    }

    public static void main(String[] args) {
        ListNode list1 = new MergeKSortedLists().createList(new int[]{1, 4, 5});
        ListNode list2 = new MergeKSortedLists().createList(new int[]{1, 3, 4});
        ListNode list3 = new MergeKSortedLists().createList(new int[]{2, 6});

        ListNode head = new MergeKSortedLists().mergeKLists(new ListNode[]{list1, list2, list3});
        new MergeKSortedLists().traverse(head);
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
}

class LinkedListNode {
    Integer value;
    LinkedListNode next;

    LinkedListNode(Integer value) {
        this.value = value;
        this.next = null;
    }
}

// Time complexity : O(Nlogk) where k is the number of linked lists.
//
//        The comparison cost will be reduced to O(logk) (instead O(NLogN)) for every pop and insertion to
//        priority queue.
//        But finding the node with the smallest value just costs O(1) time.
//        There are N nodes in the final linked list.
// Space complexity :
//
//        O(n) Creating a new linked list costs O(n) space.
//        O(k) The code above present applies in-place method which cost O(1) space.
//        And the priority queue (often implemented with heaps) costs O(k) space
//        (it's far less than NN in most situations).