package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;

public class SubSetSum {
    static Boolean result = false;
    static Boolean check_if_sum_possible(ArrayList<Long> arr, Long k) {
        if (null == arr || arr.size() == 0) {
            return false;
        }

//        if (arr.size() == 1) {
//            if (arr.get(0) == k) {
//                return true;
//            } else {
//                return false;
//            }
//        }

        return helper(arr, k, 0, 0, new ArrayList<>());

        //return result;
    }

    static Boolean helper(ArrayList<Long> arr, Long target, int index, int count, ArrayList<Long> slate) {
        System.out.println("Start: " + index + " Slate: "+ slate + " Count: "+ count + " target : " + target);
        if (target == 0 && count != 0) {
            System.out.println("Slate: "+ slate + " Count: "+ count + " target : " + target);
            //result = true;
            return true;
        }

        //reached leaf, with no valid result
        if (index == arr.size()) {
            //System.out.println(slate + " target : " + target);
            return false;
        }

        slate.add(arr.get(index));
        Boolean b1 = helper(arr, target-arr.get(index), index+1, count+1, slate);
        slate.remove(slate.size()-1);
        if (b1) {
            return true;
        }

        Boolean b2 = helper(arr, target, index+1, count+1, slate);
        if (b2) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(check_if_sum_possible(new ArrayList<>(Arrays.asList(2L, 4L, 6L)), 6L));
        //System.out.println(check_if_sum_possible(new ArrayList<>(Arrays.asList(50L)), 50L));
        //System.out.println(check_if_sum_possible(new ArrayList<>(Arrays.asList(1L)), 0L));
        //System.out.println(check_if_sum_possible(new ArrayList<>(Arrays.asList(0L)), 0L));
        //System.out.println(check_if_sum_possible(new ArrayList<>(Arrays.asList(-2, 2, 1, 2, 3)), 0L));
    }
}
