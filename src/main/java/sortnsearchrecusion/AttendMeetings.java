package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//is Space Complexity : O(n)??
public class AttendMeetings {
    static Integer can_attend_all_meetings(ArrayList<ArrayList<Integer>> intervals) {
        Collections.sort(intervals, new Comparator<ArrayList<Integer>>() { //O(n lon N) n is no of internals
            @Override
            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
                return Integer.compare(list1.get(0), list2.get(0));
            }
        });

//        PriorityQueue<ArrayList<Integer>> minHeap = new PriorityQueue(new Comparator<ArrayList<Integer>>() {
//            @Override
//            public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
//                return Integer.compare(list1.get(1), list2.get(1));
//            }
//        });
//
//        minHeap.add(intervals.get(0));
//        for(int i=1; i<intervals.size(); i++) {
//            ArrayList<Integer> current = intervals.get(i);
//            ArrayList<Integer> earliest = minHeap.poll();
//
//            //ear [1,3]
//            //curr [3,5]
//            //current starts ealier then previous
//            if (current.get(0) > earliest.get(1)) {
//                minHeap.add(current);
//            } else {
//                earliest.set(1, current.get(1));
//                minHeap.add(earliest);
//            }
//        }
//
//        return minHeap.size() == 0 ? 1 : 0;

        for(int i=0; i<intervals.size()-1; i++) { //O(n)
            ArrayList<Integer> current = intervals.get(i);
            ArrayList<Integer> next = intervals.get(i+1);

            if (current.get(1) > next.get(0)) {
                return 0;
            }
        }

        return 1;
    }
}
