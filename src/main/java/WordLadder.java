import java.util.*;

//https://leetcode.com/problems/word-ladder/
// uses BFS
// O(M^2 N) T - M is the length of the word; N is the no of words
//O(M * N) S
public class WordLadder {
    //List has a linear lookup: O(N)
    //Set has a constant lookup: O(1)
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //
        Set<String> wordSet = new HashSet<>(wordList);

        //No endWord in the dictionary
        if (!wordList.contains(endWord)) {
            return 0;
        }

        //initialise Queue
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        //initialise visited set
        Set<String> visitedSet = new HashSet<>();
        visitedSet.add(beginWord);

        int changes = 1;
        //we may loop thru all the words in the queue: O(N) - N is the no of words
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size; i++) {
                String word = queue.poll();
                if (word.equalsIgnoreCase(endWord)) { // all chars in the word are compared for an equality, O(M)
                    return changes;                   // M is the size of each word
                }

                //BFS
                for(int j=0;j<word.length();j++) { // O(M)
                    for (int k = 'a'; k <= 'z'; k++) {
                        char[] arr = word.toCharArray();
                        //change char from ('a' to 'z')
                        arr[j] = (char) k;

                        String str = new String(arr); // O(M) - nesting makes it total: O(M^2)
                        if (wordSet.contains(str) && !visitedSet.contains(str)) {
                            queue.add(str);
                        }
                    }
                }
            }
            changes++;
        }

        return 0;
    }
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));

        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log"});
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));
    }
}
