import java.util.*;

//https://leetcode.com/problems/course-schedule-ii/
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //course catalog
        List<Integer>[] catalog = new ArrayList[numCourses];

        //no of PreReqs for an each course
        int[] preReqsArr = new int[numCourses];
        int[] results = new int[numCourses];

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
        int index = 0;
        while(!availableQ.isEmpty()) {
            //pop first course from Q
            int course = availableQ.removeFirst();
            results[index++] = course;

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

        if (numCourses == 0) {
            return results;
        } else {
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        int[] result = new CourseScheduleII().findOrder(2, new int[][]{{1,0}});
        Arrays.stream(result).forEach(x -> System.out.print(x + "->"));

        System.out.println("\n");
        result = new CourseScheduleII().findOrder(6, new int[][]{{2,5},{0,5},{0,4},{1,4},{3,2},{1,3}});
        Arrays.stream(result).forEach(x -> System.out.print(x + "->"));
    }
}
