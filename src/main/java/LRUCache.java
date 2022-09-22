import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://leetcode.com/problems/lru-cache/
//https://www.youtube.com/watch?v=NDpwj0VWz1U&ab_channel=NickWhite
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
        head.next = tail; //dummy head
        tail.next = head; //dummy tail
    }

    public int get(int key) {
        int result = -1;
        Node node = nodeMap.get(key);
        if (null != node) {
            result = node.val;
            //to get the node to front of the list
            remove(node);
            add(node);
        }

        return result;
    }

    public void put(int key, int value) {
        Node node = nodeMap.get(key);
        //if node already exists,
        // remove and add next to the head to get it front of the cache
        if (null != node) {
            remove(node);
            node.val = value;
            add(node);
        } else {
            // check capacity
            if (nodeMap.size() == capacity) {
                //re-assign new value for the key
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

    //add node right before head
    public void add(Node node) {
        /*Node headNext = head.next;
        head.next = node;
        node.prev = head;
        node.next = headNext;
        headNext.prev = node;*/

        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    //move tail to previous node of current tail
    public void remove(Node node) {
        /*
        Node nextNode = node.next;
        Node prevNode = node.prev;

        nextNode.prev = prevNode;
        prevNode.next = nextNode;*/

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
    }
}
