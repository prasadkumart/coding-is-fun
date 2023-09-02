package sortnsearchrecusion;

import java.util.HashMap;
import java.util.Map;

public class MinStack {
    Node head;

    public MinStack() {
        head = null;
    }

    public void push(int val) {
        if (null == head) {
            head = new Node(val, val, null);
        } else {
            head = new Node(val, Math.min(val, head.minVal), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.minVal;
    }


    class Node {
        private int val;
        private int minVal;
        private Node next;

        public Node(int val, int minVal, Node next) {
            this.val = val;
            this.minVal = minVal;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }
}
