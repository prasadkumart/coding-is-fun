package practice;

import java.util.ArrayList;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int[]{-1,0,1,2,-1,-4})); //[[-1,-1,2],[-1,0,1]]
        System.out.println(new ThreeSum().threeSum(new int[]{0,1,1})); //[]
    }
}
