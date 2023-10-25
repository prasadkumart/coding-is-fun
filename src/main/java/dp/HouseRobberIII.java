package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/house-robber-iii/
public class HouseRobberIII {

    Map<TreeNode, Integer> memoMap = new HashMap<>();

    //Recursion
    public int rob(TreeNode root) {
        //base condition
        if (null == root) {
            return 0;
        }

        if (memoMap.containsKey(root)) {
            memoMap.get(root);
        }

        //include root value
        int withRoot = root.val;

        if (null != root.left) {
            withRoot = withRoot + rob(root.left.left) + rob(root.left.right);
        }

        if (null != root.right) {
            withRoot = withRoot + rob(root.right.left) + rob(root.right.right);
        }

        //without root value
        int withoutRoot = rob(root.left) + rob(root.right);

        int maxVal = Math.max(withRoot, withoutRoot);
        memoMap.put(root, maxVal);
        return maxVal;
    }

    public static void main(String[] args) {

    }
}
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }