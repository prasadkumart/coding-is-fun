package sortnsearchrecusion;

import java.util.ArrayList;
import java.util.Arrays;

public class GroupElementsByColor {
    static ArrayList<Character> dutch_flag_sort(ArrayList<Character> balls) {
        Character[] ballArr = new Character[balls.size()];
        balls.toArray(ballArr);

        int redPointer = -1;
        int greenPointer = -1;
        for (int i=0; i< ballArr.length; i++) {
          if (ballArr[i] == 'G') {
              greenPointer++;
              swap(ballArr, greenPointer, i);
          }
          if (ballArr[i] == 'R') {
              greenPointer++;
              swap(ballArr, greenPointer, i);

              redPointer++;
              swap(ballArr, redPointer, greenPointer);
          }
        }

        return new ArrayList<Character>(Arrays.asList(ballArr));
    }
    static void swap(Character[] arr, int pos1, int pos2) {
        Character temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    public static void main(String[] args) {
        Character[] chars = {'G', 'B', 'G', 'G', 'R', 'B', 'R', 'G'}; //[R, R, G, G, G, G, B, B]
        ArrayList<Character> numsList = new ArrayList<Character>(Arrays.asList(chars));
        System.out.println(dutch_flag_sort(numsList));
    }
}
