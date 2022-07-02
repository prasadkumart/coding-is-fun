//https://leetcode.com/problems/roman-to-integer/submissions/
public class RomanToInteger {
    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && getValueForChar(s.charAt(i)) < getValueForChar(s.charAt(i + 1)))
                sum = sum - getValueForChar(s.charAt(i));
            else
                sum = sum + getValueForChar(s.charAt(i));
        }
        return sum;
    }

    public int getValueForChar(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new RomanToInteger().romanToInt("MCMXLIV"));
        System.out.println(new RomanToInteger().romanToInt("LXXV"));
    }
}
