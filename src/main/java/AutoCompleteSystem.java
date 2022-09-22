import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//642 - Design Search Autocomplete System.java
//https://www.youtube.com/watch?v=Zm3-cGKKFLg&ab_channel=happygirlzt
public class AutoCompleteSystem {

    class TrieNode implements Comparable<TrieNode> {
        TrieNode[] children;
        String sentence;
        int times;
        List<TrieNode> hot;

        public TrieNode() {
            children = new TrieNode[128];
            sentence = null;
            times = 0;
            hot = new ArrayList<>();
        }

        @Override
        public int compareTo(TrieNode o) {
            if (this.times == o.times) {
                return this.sentence.compareTo(o.sentence);
            }
            return o.times - this.times;
        }

        public void updateHotList(TrieNode node) {
            if (!this.hot.contains(node)) {
                this.hot.add(node);
            }
            Collections.sort(this.hot);
            if (this.hot.size() > 3) {
                this.hot.remove(this.hot.size()-1);
            }
        }
    }

    TrieNode root;
    TrieNode curr;
    StringBuilder sb = new StringBuilder();
    public AutoCompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        
        for (int i=0; i<sentences.length; i++) {
            add(sentences[i],times[i]);
        }
    }

    public void add(String sentence, int time) {
        TrieNode curr = root;
        List<TrieNode> visitedNodes = new ArrayList<>();
        for (char c: sentence.toCharArray()) {
            if (curr.children[c] == null) {
                curr.children[c] = new TrieNode();
            }
            curr = curr.children[c];
            visitedNodes.add(curr);
        }

        // only the last char node of the sentence,
        // will have a sentence and times/occurrences
        curr.sentence = sentence;
        curr.times += time;

        //add last node of the sentence as a hot list to each sentence's node
        for(TrieNode visitedNode : visitedNodes) {
            visitedNode.updateHotList(curr);
        }
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        //add sentence to
        if (c == '#') {
            add(sb.toString(),1);
            curr = root;

            //reset string builder
            sb  = new StringBuilder();

            return result;
        }

        sb.append(c);
        if (curr != null) {
            curr = curr.children[c];
        }

        // no match
        if (curr == null) return result;

        for (TrieNode node : curr.hot) {
            result.add(node.sentence);
        }

        return result;
    }

    public static void main(String[] args) {
        /**
         * Your AutocompleteSystem object will be instantiated and called as such:
         * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
         * List<String> param_1 = obj.input(c);
         */

        String[] sentences = {"i love you", "island","ironman", "i love leetcode"};
        int[] times = {5,3,2,2};
        AutoCompleteSystem obj = new AutoCompleteSystem(sentences, times);
        obj.curr = obj.root;
        List<String> result = obj.input('i');
        System.out.println("Input 'i' : " + result); //["i love you", "island","i love leetcode"]

        result = obj.input(' ');
        System.out.println("Input ' ' : " + result); //["i love you","i love leetcode"]

        result = obj.input('a');
        System.out.println("Input 'a' : " + result);
    }
}
