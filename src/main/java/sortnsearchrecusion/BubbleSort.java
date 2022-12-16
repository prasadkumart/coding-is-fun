package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BubbleSort {
    static ArrayList<Integer> bubbleSort(ArrayList<Integer> arr) {
        Integer[] numsArr = new Integer[arr.size()];
        arr.toArray(numsArr);

        for (int i=0; i<numsArr.length; i++) {
            for (int j=numsArr.length-1; j>=i+1; j--) {
                if (numsArr[j] < numsArr[j-1]) {
                    int temp = numsArr[j - 1];
                    numsArr[j - 1] = numsArr[j];
                    numsArr[j] = temp;
                }
            }
        }

        return new ArrayList<Integer>(Arrays.asList(numsArr));
    }

    public static void main(String[] args) {
        int[] nums = {5,8,3,9,4,1,7};
        ArrayList<Integer> numsList = IntStream.of(nums)
                                            .boxed()
                                            .collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(Arrays.toString(nums));
        System.out.println(bubbleSort(numsList));
    }
}
