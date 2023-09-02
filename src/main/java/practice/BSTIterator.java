package practice;

import com.sun.source.tree.Tree;

import java.util.Stack;

//TC:
public class BSTIterator {
    TreeNode root;
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.root = root;
        this.stack = new Stack<>();

        findLeftMost(this.root);
    }

    public void findLeftMost(TreeNode node) {
        while(null != node) {
            stack.add(node);
            node = node.left;
        }
    }

    //TC: O(n)
    //SC: O(n) - stack size
    public int next() {
        //pop topmost node the stack
        TreeNode topMostNode = stack.pop(); //O(1)

        //only in left/right skewed tree, worst TC is O(n), otherwise mostly O(lonN)
        //traverse the right child of the node
        if (null != topMostNode.right) {
            findLeftMost(topMostNode.right);
        }

        return topMostNode.val;
    }

    //TC: O(1)
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public String toString() {
        return String.valueOf(root.val);
    }

    public static void main(String[] args) {


    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
