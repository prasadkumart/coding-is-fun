package practice;

//https://leetcode.com/problems/rotate-string/description/
public class RotateStrings {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s+s).contains(goal);
    }

    public static void main(String[] args) {
        System.out.println(new RotateStrings().rotateString("aa","a"));//false
        System.out.println(new RotateStrings().rotateString("abcde","bcdea"));//false
    }
}
