package practice;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/implement-stack-using-queues/
public class StackUsingQueues {

}
class MyStack {
    Queue<Integer> q1, q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        //move element from Q1 to Q2
        while(!q1.isEmpty()) {
            q2.add(q1.poll());
        }
        //add current to Q
        q1.add(x);

        while(!q2.isEmpty()) {
            q1.add(q2.poll());
        }
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}