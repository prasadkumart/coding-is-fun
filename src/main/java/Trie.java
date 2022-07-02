//https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {
    Node root;
    public Trie() {
        root = new Node('\0');
    }

    //O(M) TS - M IS the length of the word
    public void insert(String word) {
        Node curr = root;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (curr.children[c-'a'] == null) {
                curr.children[c-'a'] = new Node(c);
            }

            //new current
            curr = curr.children[c-'a'];
        }
        curr.isWord = true;
    }

    //O(M) - T : M IS the length of the word
    // O(1) S
    public boolean search(String word) {
        //Node node = getNode(word);
        //return node != null && node.isWord;
        return dfs(root, 0, word);
    }

    //O(M) - M IS the length of the word
    //O(1) S
    public boolean startsWith(String prefix) {
        return dfs(root, 0, prefix);
        //Node node = getNode(prefix);
        //return node != null;
    }

    //look for a work in the trie
    private Node getNode(String word) {
        Node curr = root;
        for (int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            //no match found
            if (curr.children[c-'a'] == null) {
                return null;
            } else {
                curr = curr.children[c-'a'];
            }
        }

        return curr;
    }

    private boolean dfs(Node node, int count, String word) {
        if (count == word.length() && node.isWord) {
            return true;
        }

        if (count >= word.length()) {
            return false;
        }

        char c = word.charAt(count);
        if (c == '.') {
            //search can be with-in it's any children
            // look for the char at any children
            boolean subFlag = false;
            for(int j=0;j<26;j++) {
                Node newNode = node.children[j];
                if (newNode != null) {
                    if (dfs(newNode, count + 1, word)) {
                        subFlag = true;
                        break;
                    }
                }
            }

            if (subFlag)
                return subFlag;
            //else
                //ignore;
        } else {
            int index = c - 'a';
            Node newNode = node.children[index];
            if (newNode != null) {
                return dfs(newNode, count+1, word);
            } else {
                return false;
            }
        }

        return false;
    }

    class Node {
        char c;
        boolean isWord;
        public Node[] children;

        public Node(char c) {
            this.c = c;
            isWord = false;
            //word and prefix consist only of lowercase English letters.
            children = new Node[26];
        }
    }
    public static void main(String[] args) {
     Trie obj = new Trie();
     obj.insert("apple");
     System.out.println(obj.search("apple"));
     System.out.println(obj.search("app"));
     //System.out.println(obj.startsWith("app"));
     System.out.println(obj.search("a.p.e"));
    }

}
