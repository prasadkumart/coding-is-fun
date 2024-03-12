package sortnsearchrecusion;

import java.util.*;

//https://leetcode.com/problems/meeting-rooms/

//https://leetcode.com/explore/interview/card/amazon/79/sorting-and-searching/497/
//https://www.youtube.com/watch?v=PWgFnSygweI&ab_channel=KevinNaughtonJr.

//Time Complexity: O(NlogN).
//
//        There are two major portions that take up time here. One is sorting of the array that takes O(NlogN)
//        considering that the array consists of NN elements.
//        Then we have the min-heap. In the worst case, all N meetings will collide with each other.
//        In any case we have N add operations on the heap. In the worst case we will have N extract-min operations as well.
//        Overall complexity being (NlogN) since extract-min operation on a heap takes O(logN).
//Space Complexity: O(N) because we construct the min-heap and that can contain NN elements in the worst case
// as described above in the time complexity section. Hence, the space complexity is O(N).
public class MeetingRooms {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0],b[0]));
        //Min Heap
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        minHeap.add(intervals[0]);

        for(int i = 1; i<intervals.length; i++) {
            int[] current = intervals[i];
            int[] earliest = minHeap.remove();

            if (current[0] < earliest[1]) {
                minHeap.add(current);
            } else {
                earliest[1] = current[1];
                minHeap.add(earliest);
            }
        }

        return minHeap.size();
    }

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2)->Integer.compare(i1[0],i2[0]));

        for(int i=1; i< intervals.length; i++) {
            //current meeting starts before last meeting
            if (intervals[i][0]<intervals[i-1][1]) {
                return false;
            }
        }
        return true;
    }

    public boolean canAttendMeetingsUsingTreeMap(int[][] intervals) {
        if (null == intervals || intervals.length == 0 || intervals.length == 1) {
            return true;
        }
        TreeMap<Pair, Integer> pairTreeMap = new TreeMap<>();
        //TC:O(N logN)
        for(int[] interval : intervals) {
            Pair pair = new Pair(interval[0], 'S');
            pairTreeMap.put(pair, pairTreeMap.getOrDefault(pair,0)+1);
            pair = new Pair(interval[1], 'E');
            pairTreeMap.put(pair, pairTreeMap.getOrDefault(pair,0)+1);
        }

        int counter=0;
        int maxMeetings=0;
        for(Map.Entry<Pair, Integer> entry : pairTreeMap.entrySet()) {
            if (entry.getKey().c=='S') {
                counter += entry.getValue();
            } else if(entry.getKey().c=='E') {
                counter -= entry.getValue();
            }
            maxMeetings = Math.max(maxMeetings, counter);
        }

        return (maxMeetings==1) ? true : false;
    }

    class Pair implements Comparable<Pair>{
        int time;
        char c;

        public Pair(int time, char c) {
            this.time = time;
            this.c = c;
        }

        @Override
        public int compareTo(Pair p) {
            if (this.time == p.time)
                return  this.c-p.c;

            return this.time-p.time;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MeetingRooms().minMeetingRooms(new int[][]{{0,30},{5,10},{15, 20}}));
        System.out.println(new MeetingRooms().minMeetingRooms(new int[][]{{9,10},{4,9},{4,17}}));

        System.out.println(new MeetingRooms().canAttendMeetings(new int[][]{{5,10},{0,30},{15, 20}}));//false
        System.out.println(new MeetingRooms().canAttendMeetings(new int[][]{{9,10},{4,9},{4,17}}));//false
        System.out.println(new MeetingRooms().canAttendMeetings(new int[][]{{9,10},{4,9}}));//true
        System.out.println(new MeetingRooms().canAttendMeetings(new int[][]{}));//true
        System.out.println(new MeetingRooms().canAttendMeetingsUsingTreeMap(new int[][]{{5,10},{0,30},{15, 20}}));//false
        System.out.println(new MeetingRooms().canAttendMeetingsUsingTreeMap(new int[][]{{9,10},{4,9},{4,17}}));//false
        System.out.println(new MeetingRooms().canAttendMeetingsUsingTreeMap(new int[][]{{9,10},{4,9}}));//true
        System.out.println(new MeetingRooms().canAttendMeetingsUsingTreeMap(new int[][]{}));//true
    }
}