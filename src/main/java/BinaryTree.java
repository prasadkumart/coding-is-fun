import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/same-tree/solution/
public class BinaryTree {
    public static int longestPath = 0;

    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    //O(n) T
    //O(1) S
    public static TreeNode connect(TreeNode root) {
        if (null == root) {
            return null;
        }

        TreeNode leftNode = root;
        //DFS
        while (leftNode.left != null) {
            TreeNode head = leftNode;
            //BFS
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
    //O(N) TS
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        TreeNode root = constructBSTRecursive(nums, 0, nums.length-1);

        return root;
    }

    private static TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //int mid = left + (right - left) / 2; // to avoid integer overflow
        int mid = (left + right)/ 2;
        TreeNode current = new TreeNode(nums[mid]);
        current.left = constructBSTRecursive(nums, left, mid - 1);
        current.right = constructBSTRecursive(nums, mid + 1, right);
        return current;
    }

    //https://leetcode.com/problems/binary-tree-level-order-traversal/
    //https://www.youtube.com/watch?v=XZnWETlZZ14&ab_channel=KevinNaughtonJr.
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
    //O(N) TS
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
    //O(N) TS
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
    //https://www.youtube.com/watch?v=Z_-h_mpDmeg&t=410s&ab_channel=KevinNaughtonJr.
    //https://www.youtube.com/watch?v=kR5AxWHa9nc&ab_channel=FisherCoder
    //O(N) TS as visiting every node
    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return validDFS(root, null, null);
        //had to use Double.MAX_VALUE to accommodate (-2147483648 : Integer.MIN_VALUE)
        /*double leftNodeVal = -Double.MAX_VALUE;
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
         */
    }

    private static boolean validDFS(TreeNode root, Integer min, Integer max) {
        //we are paste the validation of all nodes
        if (root == null) {
            return true;
        } else {
            if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
                return false;
            }
        }

        return validDFS(root.left, min, root.val) && validDFS(root.right, root.val, max);
    }

    //https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    //https://www.youtube.com/watch?v=mvQj-L0wEx0&t=3s&ab_channel=KnowledgeCenter
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();

        if (null == root) return results;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while(!queue.isEmpty()) {
            List<Integer> currLevalVals = new ArrayList<>();
            int size = queue.size();
            for (int i= 0; i<size; i++) {
                TreeNode currNode = queue.remove();
                currLevalVals.add(currNode.val);
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            if (!flag) {
                Collections.reverse(currLevalVals);
            }

            results.add(currLevalVals);
        }

        return results;
    }

    public List<List<Integer>> zigzagLevelOrderDQ(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerFirst(root);

        boolean reverse = false;

        while(!deque.isEmpty()) {
            List<Integer> currLevelVals = new ArrayList<>();
            int size = deque.size();

            for (int i =0; i<size; i++) {
                TreeNode currNode = reverse ? deque.pollLast() : deque.pollFirst();
                currLevelVals.add(currNode.val);

                if (reverse) {
                    if (currNode.right != null) {
                        deque.offerFirst(currNode.right);
                    }
                    if (currNode.left != null) {
                        deque.offerFirst(currNode.left);
                    }
                } else {
                    if (currNode.left != null) {
                        deque.offerLast(currNode.left);
                    }
                    if (currNode.right != null) {
                        deque.offerLast(currNode.right);
                    }
                }
            }

            result.add(currLevelVals);
            reverse = !reverse;
        }

        return result;
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



