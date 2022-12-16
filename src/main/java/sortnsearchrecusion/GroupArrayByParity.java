package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GroupArrayByParity {
    //Time: O(n)
    //Space: 0(1)
    static ArrayList<Integer> segregate_evens_and_odds(ArrayList<Integer> numbers) {
        Integer[] numArr = new Integer[numbers.size()];
        numbers.toArray(numArr);

        //Lomuto method
        int evenIndex = -1;
        for (int i=0; i<numArr.length; i++) {
            if (numArr[i] % 2 == 0) {
                evenIndex++;
                swap(numArr, evenIndex, i);
            }
        }

        //O(N) T O(1) S

//        int evenIdx = 0;
//        int oddIdx = numArr.length - 1;
//        while(evenIdx < oddIdx) {
//            if (numArr[evenIdx] % 2 != 0) {
//                for (int i = oddIdx; i > evenIdx; i--) {
//                    if (numArr[oddIdx] % 2 == 0) {
//                        //swap numArr[evenIdx], numArr[oddIdx]
//                        swap(numArr, evenIdx, oddIdx);
////                        int temp = numArr[oddIdx];
////                        numArr[oddIdx] = numArr[evenIdx];
////                        numArr[evenIdx] = temp;
//
//                        oddIdx = i;
//                        break;
//                    }
//                    oddIdx--;
//                }
//            }
//            evenIdx++;
//        }

        return new ArrayList<Integer>(Arrays.asList(numArr));
    }

    public static void swap(Integer[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    public static void main(String[] args) {
        //int[] nums = {1,2,3,4}; // [4,2,1,3] or any order; even numbers followed by odds
        int[] nums = {4, 9, 5, 2, 9, 5, 7, 10}; //[4,2,10,9,5,9,5,7]
        ArrayList<Integer> numsList = IntStream.of(nums)
                                            .boxed()
                                            .collect(Collectors.toCollection(ArrayList::new));
        //System.out.println(Arrays.toString(nums));
        System.out.println(segregate_evens_and_odds(numsList));
    }
}
