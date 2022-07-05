import com.sun.source.tree.Tree;

import java.util.*;

//https://leetcode.com/problems/same-tree/solution/
public class BinaryTree {
    public static int longestPath = 0;

    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    public static TreeNode connect(TreeNode root) {
        if (null == root) {
            return null;
        }

        TreeNode leftNode = root;
        while (leftNode.left != null) {
            TreeNode head = leftNode;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftNode = leftNode.left;
        }
        return root;
    }

    //https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return constructBSTRecursive(nums, 0, nums.length-1);
    }

    private static TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //int mid = left + (right - left) / 2;
        int mid = (left + right)/ 2;
        TreeNode current = new TreeNode(nums[mid]);
        current.left = constructBSTRecursive(nums, left, mid - 1);
        current.right = constructBSTRecursive(nums, mid + 1, right);
        return current;
    }

    //https://leetcode.com/problems/binary-tree-level-order-traversal/
    //O(N) TS
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (null == root) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> currLevelValues = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.remove();
                currLevelValues.add(currNode.val);

                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            result.add(currLevelValues);
        }

        return result;
    }


    //https://leetcode.com/problems/symmetric-tree/
    //O(N)
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }

        if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    //https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public static int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }


    //https://leetcode.com/problems/diameter-of-binary-tree/
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);

        return longestPath;
    }

    public static int dfs(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        longestPath = Math.max(longestPath, left + right);
        return Math.max(left, right) + 1;
    }

    //https://leetcode.com/problems/validate-binary-search-tree/
    public static boolean isValidBST(TreeNode root) {
        //had to use Double.MAX_VALUE to accommodate (-2147483648 : Integer.MIN_VALUE)
        double leftNodeVal = -Double.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || null != root) {

            //add all left nodes to a stack
            while (null != root) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (root.val <= leftNodeVal) return false;
            leftNodeVal = root.val;
            root = root.right;
        }

        return true;
    }

    //Left, Root, Right
    void printInorder(TreeNode node) {
        if (null == node) {
            return;
        }
        //recur on left node
        printInorder(node.left);
        System.out.println(node.val);
        //recur on right node
        printInorder(node.right);
    }

    //Root, Left, Right
    void printPreorder(TreeNode node) {
        if (null == node) {
            return;
        }
        System.out.println(node.val);
        //recur on left node
        printPreorder(node.left);
        //recur on right node
        printPreorder(node.right);
    }

    //Left, Right, Root
    void printPostorder(TreeNode node) {
        if (null == node) {
            return;
        }
        //recur on left node
        printPostorder(node.left);
        //recur on right node
        printPostorder(node.right);
        System.out.println(node.val);
    }

    private void printPostorder() { printPostorder(root); }

    private void printInorder() { printInorder(root); }

    private void printPreorder() { printPreorder(root); }

    private void printLevelOrder() { //printLevelOrder(root);
    }

    //root node
    TreeNode root;

    //O(P+Q) -> O(N)
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        } else if ((null == p || null == q) || (p.val != q.val)) {
            return false;
        } else {
            return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        //Depth First Traversals:
        System.out.println("\nInorder traversal of binary tree is ");
        //Left, Root, Right
        tree.printInorder();

        System.out.println("Preorder traversal of binary tree is ");
        //Root, Left, Right
        tree.printPreorder();

        System.out.println("\nPostorder traversal of binary tree is ");
        //Left, Right, Root
        tree.printPostorder();

        //Level Order Binary Tree Traversal (Breadth First Binary Tree)
        System.out.println("Level order traversal of binary tree is ");
        tree.printLevelOrder();


        //tree comparison
        BinaryTree p = new BinaryTree();
        p.root = new TreeNode(2);
        p.root.left = new TreeNode(1);
        p.root.right = new TreeNode(3);

        BinaryTree q = new BinaryTree();
        q.root = new TreeNode(2);
        //q.root.left = new TreeNode(1);
        q.root.right = new TreeNode(3);

        System.out.println("SameTree: " + isSameTree(p.root, q.root));
        System.out.println("diameterOfBinaryTree " + diameterOfBinaryTree(q.root));

        System.out.println("isValidBST " + isValidBST(p.root));

        System.out.println("isValidBST " + isValidBST(new TreeNode(-2147483648)));


        q = new BinaryTree();
        q.root = new TreeNode(3);
        q.root.left = new TreeNode(9);
        q.root.right = new TreeNode(20);
        q.root.right.left = new TreeNode(15);
        q.root.right.right = new TreeNode(7);
        System.out.println("depth of the tree " + maxDepth(q.root));
        System.out.println("diameterOfBinaryTree " + diameterOfBinaryTree(q.root));

        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(2);
        tree.root.left.left = new TreeNode(3);
        tree.root.left.right = new TreeNode(4);
        tree.root.right.left = new TreeNode(4);
        tree.root.right.right = new TreeNode(3);
        System.out.println("isSymmetric: " + isSymmetric(tree.root));
        System.out.println("levelOrder Traversal: " + levelOrder(tree.root));

        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(2);
        tree.root.left.right = new TreeNode(4);
        tree.root.right.right = new TreeNode(4);
        System.out.println("isSymmetric: " + isSymmetric(tree.root));

        System.out.println("levelOrder Traversal: " + levelOrder(tree.root));

        tree.root = sortedArrayToBST(new int[]{-10, -5, -3, 0, 5, 8, 9});
        tree.printInorder();

        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);
        tree.root = connect(tree.root);
        System.out.println("levelOrder Traversal: " + levelOrder(tree.root));
    }
}

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode next;

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



