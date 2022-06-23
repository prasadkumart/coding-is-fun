import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/problems/lru-cache/
public class LRUCache {

    Node head = new Node();
    Node tail = new Node();
    Map<Integer, Node> nodeMap;
    int capacity;

    public static void main(String[] args) {
        //System.out.println(new LRUCache().isValid("()"));
        //System.out.println(new LRUCache().isValid("{{}[][[[]]]}"));
    }

    public LRUCache(int capacity) {
        nodeMap = new HashMap<>(capacity);
        this.capacity = capacity;
        head.next = tail;
        tail.next = head;
    }

    public int get(int key) {
        int result = -1;
        Node node = nodeMap.get(key);
        if (null != node) {
            result = node.val;
            remove(node);
            add(node);
        }

        return result;
    }

    public void put(int key, int value) {
        Node node = nodeMap.get(key);
        if (null != node) {
            node.val = value;
            remove(node);
            add(node);
        } else {
            // check capacity
            if (nodeMap.size() == capacity) {
                nodeMap.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;

            nodeMap.put(key, newNode);
            add(newNode);
        }
    }

    public void add(Node node) {
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;
    }

    public void remove(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;

        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }
}
