package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountTriplets {
    static Integer count_triplets(Integer target, ArrayList<Integer> numbers) {
        Integer[] nums = new Integer[numbers.size()];
        numbers.toArray(nums);

        Arrays.sort(nums);

        int count = 0;
        for(int i = 0; i<nums.length; i++) {
            int left = i+1, right = nums.length - 1;
            while(left < right) {
                int sum = nums[i] + nums [left] + nums[right];
                System.out.println(i+","+left+","+right+"=>"+sum);
                if (sum<target) {
                    count += right-left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        //int[] nums = {5, 0, -1, 3, 2}; //2
        //int target = 4;
//        int[] nums = {2, 2, 2, 1}; //3
//        int target = 7;
        int[] nums = {-100, -5, 0, 1, 4, 6}; //15
        int target = 5;
        ArrayList<Integer> numbers = IntStream.of(nums).boxed().collect(Collectors.toCollection(ArrayList::new));

        System.out.println(count_triplets(target, numbers));
    }
}
