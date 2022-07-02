import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/course-schedule/
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //course catalog
        List<Integer>[] catalog = new ArrayList[numCourses];

        //no of PreReqs for an each course
        int[] preReqsArr = new int[numCourses];

        //[a,b] - b is a prereq for a
        for (int[] preReq: prerequisites) {
            int a = preReq[0];
            int b = preReq[1];
            if (catalog[b] == null) {
                catalog[b] = new ArrayList<>();
            }
            catalog[b].add(a);
            preReqsArr[a]++;
        }

        //available course
        ArrayDeque<Integer> availableQ = new ArrayDeque<>();
        for (int i=0;i<numCourses;i++) {
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
            if (catalog[course] != null) {
                for(int currCourse: catalog[course]) {
                    if (--preReqsArr[currCourse] == 0) {
                        availableQ.addLast(currCourse);
                    }
                }
            }

        }

        return (numCourses == 0);
    }

    public static void main(String[] args) {
        System.out.println(new CourseSchedule().canFinish(2, new int[][]{{1,0}}));
        System.out.println(new CourseSchedule().canFinish(6, new int[][]{{2,5},{0,5},{0,4},{1,4},{3,2},{1,3}}));
    }
}
