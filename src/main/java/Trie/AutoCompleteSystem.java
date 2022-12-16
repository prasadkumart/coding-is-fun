package Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//642 - Design Search Autocomplete System.java
//https://www.youtube.com/watch?v=Zm3-cGKKFLg&ab_channel=happygirlzt
//https://leetcode.com/problems/design-search-autocomplete-system/
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
        curr = root;
        for (int i=0; i<sentences.length; i++) {
            add(sentences[i],times[i]);
        }
    }

    //O(M) TS - M IS the length of the word
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

    //O(M) - T : M IS the length of the word
    // O(1) S
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        //add sentence to
        if (c == '#') {
            add(sb.toString(),1);

            //re-pointing curr node to root from a leaf of newly created word
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

        result = obj.input('#');
        System.out.println("Input '#' : " + result);

        result = obj.input('i');
        System.out.println("Input 'i' : " + result);

        //["AutocompleteSystem","input","input","input","input","input","input","input","input","input","input","input","input"]
        //[[["i love you","island","iroman","i love leetcode"],[5,3,2,2]],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"]]

        //[null,["i love you","island","i love leetcode"],["i love you","i love leetcode"],[],[],["i love you","island","i love leetcode"],["i love you","i love leetcode","i a"],["i a"],[],["i love you","island","i a"],["i love you","i a","i love leetcode"],["i a"],[]]
    }
}
