package practice;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/spiral-matrix/
//TC: O(m*n)
//SC: O(1)
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        //no of elements
        int ROWS = matrix.length;
        int COLS = matrix[0].length;
        int counter = ROWS * COLS;
        int left = 0;
        int top = 0;
        int right = COLS - 1;
        int bottom = ROWS - 1;

        while (counter>0) {
            //top row (right -> left)
            if (counter>0) {
                for(int i=left; i<=right; i++) {
                    result.add(matrix[top][i]);
                    counter--;
                }
                top++;
            }

            //right most column (top -> bottom)
            if (counter>0) {
                for(int i=top; i<=bottom; i++) {
                    result.add(matrix[i][right]);
                    counter--;
                }
                right--;
            }

            //bottom row (right -> left)
            if (counter>0) {
                for(int i=right; i>=left; i--) {
                    result.add(matrix[bottom][i]);
                    counter--;
                }
                bottom--;
            }

            //left column (bottom -> top)
            if (counter>0) {
                for(int i=bottom; i>=top; i--) {
                    result.add(matrix[i][left]);
                    counter--;
                }
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SpiralMatrix().spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }
    
}
