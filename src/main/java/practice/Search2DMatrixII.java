package practice;

//https://leetcode.com/problems/search-a-2d-matrix-ii/description/
//Integers in each row are sorted in ascending from left to right.
//Integers in each column are sorted in ascending from top to bottom.
//[ 1, 4, 7,11,15]
//[ 2, 5, 8,12,19]
//[ 3, 6, 9,16,22]
//[10,13,14,17,24]
//[18,21,23,26,30]
public class Search2DMatrixII {

    //first element matrix[0][0] is the smallest element - elements are higher than [0][0] in both directions, right and bottom column/rows
    //last element matrix[m][n] is the largest element - elements are lesser than [m-1][n-1] in both directions, left and higher column/rows
    //from diagonal start looking from either top right or bottom left

    //TC: O(m+n) m-array width; n-array height
    //SC: O(1) - no auxiliary mem used
    private boolean searchMatrixSearchSpaceReduction(int[][] matrix, int target) {
        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        //start from left bottom cell
        int row = ROWS - 1;
        int col = 0;

        while(row >= 0 && col <= COLS-1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target > matrix[row][col]) {
                col++;
            } else {
                row--;
            }
        }

        return false;
    }

    //TC: O(m*n) m-array width; n-array height
    public boolean searchMatrixBruteForce(int[][] matrix, int target) {
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j< matrix[0].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    //TC: O(m log n) m-array width; n-array height
    public boolean searchMatrixBS(int[][] matrix, int target) {
        if (null == matrix || matrix.length==0 || matrix[0].length==0) {
            return false;
        }

        //O(m log N)
        for(int i=0; i< matrix.length; i++) { //O(m)
            if (binarySearch(target, matrix[i])) return true;
        }

        return false;
    }

    private boolean binarySearch(int target, int[] arr) { //O(lon N)
        int start = 0;
        int end = arr.length-1;

        while(start <= end) {
            int mid = start + (end-start)/2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Search2DMatrixII().searchMatrixBruteForce(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));
        System.out.println(new Search2DMatrixII().searchMatrixBruteForce(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 20));
        System.out.println(new Search2DMatrixII().searchMatrixBS(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));
        System.out.println(new Search2DMatrixII().searchMatrixBS(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 20));
        System.out.println(new Search2DMatrixII().searchMatrixSearchSpaceReduction(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 5));
        System.out.println(new Search2DMatrixII().searchMatrixSearchSpaceReduction(new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}}, 20));
    }
}
