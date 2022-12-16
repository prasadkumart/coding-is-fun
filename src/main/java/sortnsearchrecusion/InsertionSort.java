package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InsertionSort {
    static ArrayList<Integer> insertionSort(ArrayList<Integer> arr) {
        Integer[] numArr = new Integer[arr.size()];
        arr.toArray(numArr);

        for(int i=0; i<numArr.length; i++) {
            int temp = numArr[i];
            int j=i-1;
            for (; j>=0 && numArr[j]>temp; j--) {
                //scud over
                numArr[j+1] = numArr[j];
            }
            numArr[j+1] = temp;
        }

        return new ArrayList<Integer>(Arrays.asList(numArr));
    }

    public static void main(String[] args) {
        int[] nums = {5,8,3,9,4,1,7};
        ArrayList<Integer> numsList = IntStream.of(nums)
                                            .boxed()
                                            .collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(Arrays.toString(nums));
        System.out.println(insertionSort(numsList));
    }
}
