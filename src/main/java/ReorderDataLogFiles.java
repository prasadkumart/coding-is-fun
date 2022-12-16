import java.util.Arrays;

//https://leetcode.com/problems/reorder-data-in-log-files/
//O(M * NlogN) M - maximum length of a single log ; N - no of logs
// time complexity of the Arrays.sort() is O(N⋅logN)

public class ReorderDataLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.parallelSort(logs, (log1, log2) -> {
            System.out.println(Thread.currentThread().getName());
            String[] arr1 = log1.split(" ", 2);
            String[] arr2 = log2.split(" ", 2);

            boolean isDigit1 = Character.isDigit(arr1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(arr2[1].charAt(0));

            //no change in order - relative order
            if (isDigit1 && isDigit2) return 0;
            //push digit log down to the list
            else if (isDigit1) return 1;
            //no change in order as 2 log is a digit log
            else if (isDigit2) return -1;

            //if content is same
            if (arr1[1].equals(arr2[1])) {
                return arr1[0].compareTo(arr2[0]);
            }

            return arr1[1].compareTo(arr2[1]);
        });

        return logs;
    }

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        logs = new ReorderDataLogFiles().reorderLogFiles(logs);
        System.out.println(Arrays.deepToString(logs));
    }
}
