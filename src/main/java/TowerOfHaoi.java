import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://www.lintcode.com/problem/169/
//O(2^n) T
// The recurrence relation is: T(n) = 2 * T(n - 1) + O(1)

//Auxiliary Space Used O(n).
//   Space used by the call stack will be O(n), because of the recursive calls.
//Space Complexity O(2^n).
//
//        Space used by input: O(1).
//        Auxiliary space used: O(n).
//        Space used by output: O(2^n), as there are O(2^n) steps in the output.
//        Hence, total space complexity: O(1) + O(n) + O(2^n) = O(2^n).

public class TowerOfHaoi {

    static ArrayList<ArrayList<Integer>> tower_of_hanoi(Integer n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        towerOfHanoi(n, 1, 3, 2, result);
        return result;
    }

    static void towerOfHanoi(Integer n, int source, int dest, int aux, ArrayList<ArrayList<Integer>> result) {
        if (n == 0) {
            //result.add(new ArrayList<>(Arrays.asList(source, dest)));
            return;
        }
        //transfer n-1 disks from Source to Aux/Temp using Dest
        towerOfHanoi(n-1, source, aux, dest, result);
        result.add(new ArrayList<>(Arrays.asList(source, dest)));
        //transfer n-1 disks from Aux/Temp to Dest using Source
        towerOfHanoi(n-1, aux, dest, source, result);

    }

    public static List<String> towerOfHanoi(int n) {
        List<String> result = new ArrayList<>();

        towerOfHanoi(n, 'A','C','B', result);

        return result;
    }

    static void towerOfHanoi(int n, char s, char d, char t, List<String> result) {
        if (n==0) {
            return;
        }

        //transfer n-1 disks from Source to Aux/Temp using Dest
        towerOfHanoi(n-1, s, t, d, result);
        result.add("Move " + s + " to " + d);
        //transfer n-1 disks from Aux/Temp to Dest using Source
        towerOfHanoi(n-1, t, d, s, result);
    }

    public static void main(String[] args) {
        System.out.println(tower_of_hanoi(2));
    }

}
