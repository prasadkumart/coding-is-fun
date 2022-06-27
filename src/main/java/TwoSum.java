import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {
    public static int[] twoNumberSum(int[] array, int targetSum) {
        if (null == array || array.length == 0) return new int[]{};

        if (array.length == 1) return new int[]{};


    //O(N^2)
//        for(int i=0; i<array.length; i++) {
//            for(int j=i+1; j<array.length;j++)
//                if (targetSum - array[i] == array[j]) return new int[]{array[i], array[j]};
//        }
//        return new int[]{};

        //O(N)
        Set<Integer> visitedSet = new HashSet<>();
        for(int num: array) {
            if (visitedSet.contains(targetSum-num)) {
                return new int[] {targetSum-num, num};
            } else {
                visitedSet.add(num);
            }
        }
        return new int[]{0};
    }

    public static void main(String[] args) {
        int[] result = twoNumberSum(new int[]{2, 7, 11, 15}, 9);
        Arrays.stream(result).forEach(System.out::print);
        System.out.println("\n");

        result = twoNumberSum(new int[]{3, 2, 3}, 6);
        Arrays.stream(result).forEach(System.out::print);
        System.out.println("\n");

        result = twoNumberSum(new int[]{3, 5, -4, 8, 11, 1, -1, 6}, 10);
        Arrays.stream(result).forEach(System.out::print);
        System.out.println("\n");

        result = twoNumberSum(new int[]{15}, 15);
        Arrays.stream(result).forEach(System.out::print);
    }
}
