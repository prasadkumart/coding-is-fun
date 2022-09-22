import java.util.*;

//https://leetcode.com/problems/word-ladder/
//https://www.youtube.com/watch?v=M9cVl4d0v04&ab_channel=NickWhite
// uses BFS
// O(M^2 N) T - M is the length of the word; N is the no of words
//O(M * N) S
public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));

        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log"});
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //List has a linear lookup: O(N)
        //Set has a constant lookup: O(1)
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

        int levels = 1; //levels in BFS is the no of changes - solution to the problem
        //we may loop thru all the words in the queue: O(N) - N is the no of words
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size; i++) {
                String word = queue.poll();
                visitedSet.add(word);
                if (word.equalsIgnoreCase(endWord)) { // all chars in the word are compared for an equality, O(M)
                    return levels;                   // M is the size of each word
                }

                //BFS
                for(int j=0;j<word.length();j++) { // O(M)
                    char originalChar = word.charAt(j);
                    char[] arr = word.toCharArray();
                    for (int k = 'a'; k <= 'z'; k++) {
                        //change char from ('a' to 'z')
                        arr[j] = (char) k;

                        String str = new String(arr); // O(M) - nesting makes it total: O(M^2)
                        if (wordSet.contains(str) && !visitedSet.contains(str)) {
                            queue.add(str);
                        }
                    }
                    arr[j] = originalChar;
                }
            }
            levels++;
        }

        return 0;
    }
}
