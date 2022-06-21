//https://leetcode.com/problems/same-tree/solution/
public class BinaryTree {
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
        p.root = new TreeNode(1);
        p.root.left = new TreeNode(2);
        p.root.right = new TreeNode(3);

        BinaryTree q = new BinaryTree();
        q.root = new TreeNode(1);
        q.root.left = new TreeNode(2);
        //q.root.right = new TreeNode(3);

        System.out.println(isSameTree(p.root, q.root));
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
        } else if ((null== p || null==q) || (p.val != q.val)) {
            return false;
        } else {
            return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        }
    }
}


//Definition for a binary tree node.
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
