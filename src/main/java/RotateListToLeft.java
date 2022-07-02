//https://leetcode.com/problems/lru-cache/
public class RotateListToLeft {
    public ListNode rotateRight(ListNode head, int k) {
        if (null == head || null == head.next || k == 0) {
            return head;
        }

        //int count = getCount(head);
        ListNode nextNode = head.next;
        ListNode tailNode = head.next;
        int count = 0;
        while (nextNode != null) {
            count++;
            tailNode = nextNode;
            nextNode = nextNode.next;
        }

//        ListNode firstNode = head.next;
//        nextNode = firstNode.next;
//        tailNode.next = firstNode;
//        tailNode = tailNode.next;
//        head.next = nextNode;

        k = (k <= count) ? k : k % count;
        ListNode firstNode = head.next;
        while (k > 0) {
            tailNode.next = firstNode;
            tailNode = firstNode;
            firstNode = firstNode.next;
            k--;
        }
        head.next = firstNode;
        tailNode.next = null;

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
        ListNode head = new ListNode();
        ListNode tailNode = new ListNode();
        for (int i : arr) {
            ListNode node = new ListNode(i);
            if (null == head.next) {
                head.next = node;
            } else {
                tailNode.next = node;
            }
            tailNode = node;
        }

        return head;
    }

    public void traverse(ListNode head) {
        ListNode node = head.next;
        int count = 0;
        while(node != null) {
            System.out.print(node.val + "->");
            count++;
            node = node.next;
        }
        System.out.println("\ncount " + count);
    }

    public static void main(String[] args) {
        ListNode head = new RotateListToLeft().createList(new int[]{1, 2, 3, 4, 5});
        new RotateListToLeft().traverse(head);
        //head = new RotateList().rotateRight(head,1);
        //new RotateList().traverse(head);

        head = new RotateListToLeft().rotateRight(head, 2);
        new RotateListToLeft().traverse(head);
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
