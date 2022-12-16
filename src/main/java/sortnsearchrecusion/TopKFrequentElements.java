package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//https://leetcode.com/problems/top-k-frequent-elements/
//https://www.youtube.com/watch?v=a3vb_SQVQBo&ab_channel=EricProgramming
// O(N) TS
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];

        //each elements apprearance in the table
        HashMap<Integer, Integer> bucket1 = new HashMap<>(); //O(n)
        for (int num : nums) {
            bucket1.put(num, bucket1.getOrDefault(num, 0) + 1);
        }

        //each element's frequency as a key
        HashMap<Integer, List<Integer>> bucket2 = new HashMap<>();
        for(Integer key: bucket1.keySet()) {
            Integer keyFrequency = bucket1.get(key);
            if (!bucket2.containsKey(keyFrequency)) {
                bucket2.put(keyFrequency, new ArrayList());
            }
            bucket2.get(keyFrequency).add(key);
        }

        //get top K element
        for(int i= nums.length; i>=1; i--) {
            if (bucket2.containsKey(i)) {
                List<Integer> list = bucket2.get(i);
                for (int num: list) {
                    result[--k] = num;
                    if (k==0) {
                        return result;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new TopKFrequentElements().topKFrequent(new int[]{1,1,1,2,2,3}, 2));
    }
}
