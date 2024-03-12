package practice;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
public class BinTreeFromPreOrderInOrder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = null;
        Map<Integer, Integer> inorderIndMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            inorderIndMap.put(inorder[i], i);
        }

        return buildTreeBT(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, inorderIndMap);
    }

    private TreeNode buildTreeBT(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inorderIndMap) {
        //base case
        if (preStart>preEnd || inStart>inEnd) {
            return null;
        }

        //rootNode with the values of preStart in preorder //3
        int rootVal = preorder[preStart];

        //locate rootVal in inorder (midPosition), left of it will go to left branch; right of will go to right branch
        //preorder left partition = [preStart+1 to inorder midPosition] -> left branch
        //preorder right partition = [inorder midPosition+1 to preEnd] -> right branch

        //no of elements in left sub array in inorder from midPosition, 
        int midPosition = inorderIndMap.get(rootVal);
        TreeNode rootNode = new TreeNode(rootVal);

        //all array element left of '3' in inorder, will go to left branch of rootnode
        //all array element right of '3' in inorder, will go to right branch of rootnode
        rootNode.left = buildTreeBT(preorder, preStart+1, midPosition, inorder, 0, midPosition-1, inorderIndMap);
        rootNode.right = buildTreeBT(preorder, midPosition+1, preEnd, inorder, midPosition+1, inEnd, inorderIndMap);

        return rootNode;
    }

    public static void main(String[] args) {
        TreeNode root = new BinTreeFromPreOrderInOrder().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        System.out.println(root);
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
