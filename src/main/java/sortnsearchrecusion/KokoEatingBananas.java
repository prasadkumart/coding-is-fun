package sortnsearchrecusion;

//https://leetcode.com/problems/koko-eating-bananas/
public class KokoEatingBananas {

    //TC: O(mn) - n: #of array elements; m iterations to find a workable solution
    //SC: O(1)
    public int minEatingSpeed(int[] piles, int h) {
        //initial speed
        int speed = 1;
        while(true) { // m iterations to find a workable solution
            int totalHoursNeeded = 0;
            for (int pile: piles) { //O(n) - n: #of array elements
                totalHoursNeeded += Math.ceil(pile/(float)speed);
            }

            if (totalHoursNeeded <= h) {
                return speed;
            } else {
                speed++;
            }
        }
    }

    //TC: O(n. log m)  n: #of array elements; m: max# in the pile
    //SC: O(1)
    public int minEatingSpeedBinSearch(int[] piles, int h) {
        //initial speed
        int low = 1;
        int high = 1;
        //find max value
        for (int pile: piles) { //O(n)
            high = Math.max(high, pile);
        }

        while(low<high) { // O(log m) m: max# in the pile
            int mid = low + (high-low)/2;
            int totalHoursNeeded = 0;
            for (int pile: piles) { //O(n) - n: #of array elements
                totalHoursNeeded += Math.ceil(pile/(float)mid);
            }

            if (totalHoursNeeded <= h) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.
        return low;
    }

    public boolean isOptimal(int[] piles, int h, int k) {
        int totalHoursNeeded = 0;
        for (int pile: piles) {
            totalHoursNeeded += Math.ceil((double) pile/k);
        }

        if (totalHoursNeeded <= h) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int i = 11;
        int k = 3;
        System.out.println((double) i/k);
        //System.out.println(Math.ceil((double) i/k));
        //System.out.println(Math.ceil(1000000001/(float)2));
        System.out.println(new KokoEatingBananas().minEatingSpeedBinSearch(new int[] {3,6,7,11}, 8)); //4
        System.out.println(new KokoEatingBananas().minEatingSpeedBinSearch(new int[] {30,11,23,4,20}, 5)); //30
        System.out.println(new KokoEatingBananas().minEatingSpeedBinSearch(new int[] {30,11,23,4,20}, 6)); //23
        System.out.println(new KokoEatingBananas().minEatingSpeedBinSearch(new int[] {1000,000,000}, 2)); //23
        System.out.println(new KokoEatingBananas().minEatingSpeed(new int[] {5,5,5}, 15)); //23
    }
}
