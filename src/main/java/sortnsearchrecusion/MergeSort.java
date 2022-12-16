package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSort {
    static ArrayList<Integer> mergeSort(ArrayList<Integer> arr) {
        Integer[] numArr = new Integer[arr.size()];
        arr.toArray(numArr);

        numArr = mergeSort(numArr, 0, numArr.length-1);

        return new ArrayList<Integer>(Arrays.asList(numArr));
    }

    static Integer[] mergeSort(Integer[] arr, int start, int end) {
        //leaf worker
        if (start == end) {
            return new Integer[]{arr[start]};
        }

        //internal node worker
        int mid = start + (end - start) / 2;
        Integer[] leftArr = mergeSort(arr, start, mid);
        Integer[] rightArr = mergeSort(arr, mid + 1, end);

        //merge two sorted halves
        Integer[] auxArr = new Integer[leftArr.length + rightArr.length];
        int leftIdx = 0;
        int rightIdx = 0;
        int auxIdx = 0;
        while (leftIdx < leftArr.length && rightIdx < rightArr.length ) {
            if (leftArr[leftIdx] < rightArr[rightIdx]) {
                auxArr[auxIdx++] = leftArr[leftIdx++];
            } else {
                auxArr[auxIdx++] = rightArr[rightIdx++];
            }
        }

        //gather phase (only one of the below loops are executed)
        while (leftIdx < leftArr.length) {
            auxArr[auxIdx++] = leftArr[leftIdx++];
        }
        while (rightIdx < rightArr.length) {
            auxArr[auxIdx++] = rightArr[rightIdx++];
        }

        return auxArr;
    }

    public static void main(String[] args) {
        int[] nums = {8,5,3,9,4,1,7};
        ArrayList<Integer> numsList = IntStream.of(nums)
                                            .boxed()
                                            .collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(Arrays.toString(nums));
        System.out.println(mergeSort(numsList));
    }
}
