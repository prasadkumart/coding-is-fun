package sortnsearchrecusion;

import java.util.*;

//https://leetcode.com/problems/top-k-frequent-elements/
//https://www.youtube.com/watch?v=a3vb_SQVQBo&ab_channel=EricProgramming
// O(N) TS
public class TopKMostFrequentWords {

    static ArrayList<String> k_most_frequent(Integer k, ArrayList<String> words) {
        HashMap<String, Integer> fMap = new HashMap<String, Integer>();
        for(String word : words) { //O(n)
            fMap.put(word, fMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue p = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return fMap.get(i1)-fMap.get(i2);
            }
        });


        //priorityQueue with a custom comparator
        //PQ used min/maxHeap under the hood
        //worst case insertion is O(log n) best case is O(1)
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int frequency1 = fMap.get(o1);
                int frequency2 = fMap.get(o2);

                if (frequency1 == frequency2) {
                    return o1.compareTo(o2);
                }
                return frequency2-frequency1;
            }
        });

        // will try to keep only k elements in the PQ to make a TC of log K
        for (Map.Entry<String, Integer> entry : fMap.entrySet()) {
            pq.add(entry.getKey());
        }

        ArrayList<String> result = new ArrayList<>();
        for (int i=0; i<k; i++) {
            result.add(pq.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(k_most_frequent(4, new ArrayList<String>(Arrays.asList("car", "bus", "taxi", "car", "driver", "candy", "race", "car", "driver", "fare", "taxi"))));
    }
}
