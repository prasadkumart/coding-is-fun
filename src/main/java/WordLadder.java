import java.util.*;

//https://leetcode.com/problems/word-ladder/
//https://www.youtube.com/watch?v=M9cVl4d0v04&ab_channel=NickWhite
// uses BFS
// O(M^2 N) T - M is the length of the word; N is the no of words
//O(M^2 * N) S

//Time Complexity: O(M ^ 2 ×N), where M is the length of words and N is the total number of words in the input word list. Similar to one directional,
// bidirectional also takes O(M ^ 2 ×N) time for finding out all the transformations. But the search time reduces to half, since the two parallel searches meet somewhere in the middle.

//Space Complexity: O(M ^ 2 ×N), to store all M transformations for each of the N words in the all_combo_dict dictionary, same as one directional.
// But bidirectional reduces the search space. It narrows down because of meeting in the middle.

public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));

        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log"});
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));

        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList(new String[]{"hit","cog"});
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //List has a linear lookup: O(N)
        //Set has a constant lookup: O(1)
        Set<String> dict = new HashSet<>(wordList);

        //No endWord in the dictionary
        if (!dict.contains(endWord)) {
            return 0;
        }

        //initialise Queue
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int level = 0;

        //BFS
        //we may loop thru all the words in the queue: O(N) - N is the no of words
        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i=0; i<size; i++) {
                String currWord = queue.poll();

                // all chars in the word are compared for an equality, O(M)
                // M is the size of each word
                if (currWord.equals(endWord)) {
                    return level;
                }
                List<String> neighbours = getNeighbours(currWord, dict);
                for(String neighbour: neighbours) {
                    queue.add(neighbour);
                }
            }
        }

        return 0;
    }

    public List<String> getNeighbours(String word, Set<String> dict) {
        List<String> neighbours = new ArrayList<>();
        if (null == word || word.length() == 0) {
            return neighbours;
        }

        char[] charArr = word.toCharArray();
        // O(M^2) - nesting makes it total: O(M^2)
        for(int i=0; i<charArr.length; i++) {
            char temp = charArr[i];
            //constant 26 times; ignored from time complexity
            for(char c='a'; c<='z'; c++) {
                charArr[i] = c;
                String newWord = new String(charArr);

                if (dict.contains(newWord)) {
                    neighbours.add(newWord);
                    dict.remove(newWord);
                }
            }

            charArr[i] = temp;
        }

        return neighbours;
    }
}
