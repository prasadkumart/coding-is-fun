import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a.val, b.val));

        for(ListNode node: lists) {
            if (null == node) {
                continue;
            }
            minHeap.add(node);
        }

        ListNode head = new ListNode();
        ListNode temp = head;

        while(!minHeap.isEmpty()) {
            ListNode top = minHeap.poll();
            temp.next = top;
            temp = temp.next;
            //add next element from the list to minHeap
            if (top.next != null) {
                minHeap.add(top.next);
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

//    Time complexity : O(Nlogk) where \text{k}k is the number of linked lists.
//
//        The comparison cost will be reduced to O(logk) (instead O(NLogN)) for every pop and insertion to
//        priority queue.
//        But finding the node with the smallest value just costs O(1)O(1) time.
//        There are NN nodes in the final linked list.
//        Space complexity :
//
//        O(n)O(n) Creating a new linked list costs O(n)O(n) space.
//        O(k)O(k) The code above present applies in-place method which cost O(1)O(1) space.
//        And the priority queue (often implemented with heaps) costs O(k)O(k) space
//        (it's far less than NN in most situations).