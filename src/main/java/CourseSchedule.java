import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/course-schedule/
//https://www.youtube.com/watch?v=2l22FRtU45M&t=847s&ab_channel=FisherCoder
// https://www.youtube.com/watch?v=Akt3glAwyfY&t=186s&ab_channel=NeetCode
//O(V+E)
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //course adjList
        //build adjacency list for prerequisites
        //a course is a prerequisite for others
        List<Integer>[] adjList = new ArrayList[numCourses];

        //no of PreReqs for each course
        int[] preReqsArr = new int[numCourses];

        //[a,b] - b is prereq for a
        for (int[] preReq: prerequisites) {
            int a = preReq[0];
            int b = preReq[1];
            if (adjList[b] == null) {
                adjList[b] = new ArrayList<>();
            }
            adjList[b].add(a);
            preReqsArr[a]++;
        }

        //available course
        ArrayDeque<Integer> availableQ = new ArrayDeque<>();
        for (int i=0;i<preReqsArr.length;i++) {
            if (preReqsArr[i] == 0) {
                availableQ.addLast(i);
            }
        }

        //simulate course taking
        while(!availableQ.isEmpty()) {
            //pop first course from Q
            int course = availableQ.removeFirst();

            //reduce no of courses by 1
            numCourses--;

            //preReqs that the current course has
            if (adjList[course] != null) {
                for(int currCourse: adjList[course]) {
                    if (--preReqsArr[currCourse] == 0) {
                        availableQ.addLast(currCourse);
                    }
                }
            }

        }

        return (numCourses == 0);
    }

    public static void main(String[] args) {
        //System.out.println(new CourseSchedule().canFinish(2, new int[][]{{1,0}}));
        System.out.println(new CourseSchedule().canFinish(6, new int[][]{{2,5},{0,5},{0,4},{1,4},{3,2},{1,3}}));
    }
}
