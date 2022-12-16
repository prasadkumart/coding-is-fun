package sortnsearchrecusion;

import java.util.ArrayList;

//https://leetcode.com/problems/combinations/
public class Combinations {

    static ArrayList<ArrayList<Integer>> find_combinations(Integer n, Integer k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        findCombinations(n,k, 1, new ArrayList<>(), result);

        return result;
    }

    static void findCombinations(Integer n, Integer k, int start, ArrayList<Integer> slate, ArrayList<ArrayList<Integer>> result) {
        //base case
        if (slate.size() == k) {
            result.add(new ArrayList<>(slate)); //deep copy
            return;
        }

        // other base case to move to next set of numbers
        if (start == n+1) {
            return;
        }

        findCombinations(n, k, start+1, slate, result); //exclude case
        slate.add(start);
        findCombinations(n, k, start+1, slate, result);
        slate.remove(slate.size()-1);
    }

    public static void main(String[] args) {
        System.out.println(find_combinations(5,2));
    }
}
