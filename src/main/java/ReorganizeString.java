import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/reorganize-string/
public class ReorganizeString {

    public static void main(String[] args) {
        System.out.println(new ReorganizeString().reorganizeString("aab"));
        System.out.println(new ReorganizeString().reorganizeString("aaab"));
        System.out.println(new ReorganizeString().reorganizeString("abcd"));
    }

    //O(N lon N)
    public String reorganizeString(String s) {
        StringBuilder result = new StringBuilder();

        //count each char in the string
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) { //O(N)
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> countMap.get(b) - countMap.get(a)); //O(N)
        maxHeap.addAll(countMap.keySet());

        while (maxHeap.size() >= 2) { //O(N Log N)
            //poll top two chars from heap
            char current = maxHeap.poll();
            char next = maxHeap.poll();

            //append top two chars to result
            result.append(current);
            result.append(next);

            //update count in map (reduce by one)
            countMap.put(current, countMap.get(current) - 1);
            countMap.put(next, countMap.get(next) - 1);

            //update heap with an updated counts
            if (countMap.get(current) > 0) {
                maxHeap.add(current);
            }
            if (countMap.get(next) > 0) {
                maxHeap.add(next);
            }
        }

        if (!maxHeap.isEmpty()) {
            char last = maxHeap.poll();
            if (countMap.get(last) > 1) {
                return "";
            } else {
                result.append(last);
            }
        }

        return result.toString();
    }
}
