import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/generate-parentheses/
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        genAll(new char[n*2], 0, n, result);
        //generateAll(new char[n*2], 0, result);
        //genParanthesis(0, 0, n, new StringBuilder(), result);
        return result;
    }

    //TC: O(Branching Factor ^ Height of the tree) X validate 2n results = O(2^2n) X 2n
    // tree height is 2n (each n will have open and closed parentheses)
    // each time slate is validated to match the resultant rules

    //Aux: O(2n) for Array
    //SC: an array will be used for all nodes = O(2^2n) X O(2n)
    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }

    // This analysis is outside the scope of this article, but it turns out this is the n-th Catalan number 1/n+1 * C(2n,n),
    // which is bounded asymptotically by {4^n}{n\sqrt{n}}
    //Time Complexity : O({4^n}{\sqrt{n}})
    // Each valid sequence has at most n steps during the backtracking procedure.

    //Space Complexity : O({4^n}{\sqrt{n}}), as described above, and using O(n)O(n)O(n) space to store the sequence.
    void genParanthesis(int open, int close, int n, StringBuilder current, List<String> result) {
        if (current.length() == n*2) {
            result.add(current.toString());
            return;
        }

        if (open<n) {
            current.append("(");
            genParanthesis(open+1, close, n, current, result);
            current.deleteCharAt(current.length()-1);
        }

        if (close<open) {
            current.append(")");
            genParanthesis(open, close+1, n, current, result);
            current.deleteCharAt(current.length()-1);
        }
    }

    void genAll(char[] slate, int index, int n, List<String> result) {
        if (index == 2*n) {
            result.add(new String(slate));
            return;
        }
        slate[index] = '(';
        genAll(slate, index+1, n, result);

        slate[index] = ')';
        genAll(slate, index+1, n, result);
    }

    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis(2));
    }
}
