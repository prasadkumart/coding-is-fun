package practice;

import java.util.Stack;

public class QueueUsingStack {
    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(4);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();
    }
}

class MyQueue {

    Stack<Integer> s1,s2;
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    //TC: 4 * O(N)
    //SC: O(N) -- stacks
    public void push(int x) {
        //empty s1
        while(!s1.isEmpty()) {
            s2.add(s1.pop()); //2*O(N) //pop from one stack and push to another
        }
        //add current to bottom
        s1.add(x);

        //reverse the order
        while(!s2.isEmpty()) {
            s1.add(s2.pop()); //2*O(N) //pop from one stack and push to another
        }
    }

    //TC: O(1)
    public int pop() {
        return s1.pop();
    }
    //TC: O(1)
    public int peek() {
        return s1.peek();
    }

    //TC: O(1)
    public boolean empty() {
        return s1.isEmpty();
    }
}
