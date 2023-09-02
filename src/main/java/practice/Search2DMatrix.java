package practice;

//https://leetcode.com/problems/search-a-2d-matrix/description/
//TC: O(log M*N) M- rows, N- cols - standard binary search
//SC: O(1) no auxiliary memory used
//Each row is sorted in non-decreasing order.
//The first integer of each row is greater than the last integer of the previous row.
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        boolean flag = false;
        int start = 0;
        int ROWS = matrix.length;
        int COLS = matrix[0].length;
        //rows * cols - 1
        int end = ROWS*COLS - 1; //4*3-1=11

        //binary search
        while (start<=end) {
            int pivotPiont = start + (end-start)/2; //5

            //is target at pivot point cell?? //5/4 = 1; 5%4=1
            int pivotPointElement = matrix[pivotPiont / COLS][pivotPiont % COLS];
            if (target == pivotPointElement) {
                return true;
            } else if (target > pivotPointElement) {
                start = pivotPiont + 1;
            } else {
                end = pivotPiont - 1;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        System.out.println(new Search2DMatrix().searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3)); //true
        System.out.println(new Search2DMatrix().searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13)); //false
    }
}
