//https://leetcode.com/problems/same-tree/solution/
public class SameTree {
    public static void main(String[] args) {



    }

    //O(P+Q) -> O(N)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        } else if ((null== p || null==q) || (p.val != q.val)) {
            return false;
        } else {
            return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        }
    }
}


