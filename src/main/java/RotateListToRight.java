//https://leetcode.com/problems/lru-cache/
public class RotateListToRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (null == head || null == head.next || k == 0) {
            return head;
        }

        //find out the depth of the list
        //int count = getCount(head);
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }

        //point tail to head to make a circular reference
        tail.next = head;

        //rotate tail position to new tail
        k = length - k % length;
        while (k > 0) {
            tail = tail.next;
            k--;
        }

        //disconnect previous tail
        head = tail.next;
        tail.next = null; //tail 5 head 1

        return head;
    }

    public int getCount(ListNode head) {
        ListNode node = head.next;
        int count = 0;
        while(node != null) {
            count++;
            node = node.next;
        }

        return count;
    }

    public ListNode rotateList(ListNode head, int k) {
        ListNode node = head.next;
        int count = 0;
        while(node != null) {
            count++;
            node = node.next;
        }

        return null;
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

    public static void main(String[] args) {
        ListNode head = new RotateListToRight().createList(new int[]{1, 2, 3, 4, 5});
        //ListNode head = new RotateListToLeft().createList(new int[]{0,1,2});
        new RotateListToRight().traverse(head);
        //head = new RotateList().rotateRight(head,1);
        //new RotateList().traverse(head);

        //head = new RotateListToLeft().rotateRight(head,1);
        head = new RotateListToRight().rotateRight(head, 8);
        new RotateListToRight().traverse(head);
    }
}
