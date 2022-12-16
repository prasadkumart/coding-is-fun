package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectionSort {
    static ArrayList<Integer> selection_sort(ArrayList<Integer> arr) {
        Integer[] numsArr = new Integer[arr.size()];
        arr.toArray(numsArr);

        for (int i=0; i<numsArr.length; i++) {
            int minIndex = i;
            for (int j=i+1; j<numsArr.length; j++) {
                if (numsArr[j] < numsArr[minIndex]) {
                    minIndex = j;
                }
            }

            if (i!=minIndex) {
                int temp = numsArr[i];
                numsArr[i] = numsArr[minIndex];
                numsArr[minIndex] = temp;
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
        System.out.println(selection_sort(numsList));
    }
}
