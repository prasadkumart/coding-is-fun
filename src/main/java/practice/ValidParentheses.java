package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;


//https://leetcode.com/problems/valid-parentheses/
//TC: O(n) linear - single pass
//SC: O(n) - auxiliary memory
public class ValidParentheses {
    public boolean isValid(String s) {
        //Map<Character, Character> map = Arrays.stream(new char[][] {{'{','}'}}).collect(Collectors.toMap(data-> data[0], data -> data[1]));
        //reverse lookup
        Map<Character, Character> map = new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');

        Arrays.stream(new char[][]{{'}','{'}}).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        Stack<Character> stack = new Stack<>(); // SC: O(n)

        for(char c : s.toCharArray()) { //O(n)
            if (!map.containsKey(c)) { //O(1)
                stack.add(c); // O(1)F=
            } else {
                if (!stack.isEmpty() && stack.peek() == map.get(c)) { //stack.peek() - O(1); map.get - O(1)
                    stack.pop(); //O(1)
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("}")); //false
        System.out.println(new ValidParentheses().isValid("{()}")); //true
        System.out.println(new ValidParentheses().isValid("{}{}()")); //true
        System.out.println(new ValidParentheses().isValid("{aa}")); //false
    }
}
