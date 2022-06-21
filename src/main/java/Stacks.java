import java.util.Stack;

public class Stacks {

    public static void stack_push(Stack<Integer> stack) {
        for(int i=0;i<5;i++) {
            stack.push(i);
        }
    }

    public static void stack_peek(Stack<Integer> stack) {
        System.out.println("peek operation....");
        System.out.println((Integer) stack.peek());
    }

    public static void stack_pop(Stack<Integer> stack) {
        System.out.println("pop operation....");
        int s = stack.size();
        for (int i=0; i<s; i++) {
            System.out.println((Integer) stack.pop());
        }
        System.out.println("Stack size: "+ stack.size());
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack_push(stack);
        System.out.println("Stack size: "+ stack.size());
        stack_peek(stack);
        System.out.println("Stack size: "+ stack.size());
        stack_pop(stack);
        System.out.println("Stack size: "+ stack.size());
        stack_peek(stack);
        System.out.println("Stack size: "+ stack.size());
    }
}
