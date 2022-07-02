import java.util.Stack;

//https://leetcode.com/problems/roman-to-integer/submissions/
public class ValidParanthesis {
    public static void main(String[] args) {
        System.out.println(new ValidParanthesis().isValid("()"));
        System.out.println(new ValidParanthesis().isValid("{{}[][[[]]]}"));
    }

    public boolean isValid(String s) {
        if (s == null || s.length() % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case ')' :
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case '}' :
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                default:
                    stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
