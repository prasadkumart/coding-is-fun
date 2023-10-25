package practice;


//https://leetcode.com/problems/compare-version-numbers/description/
//TC: O(O(max(N,M)), where
//N and M are lengths of the input strings respectively. It's a one-pass solution.

//SC : O(max(N,M)).
public class CompareVersions {
    public int compareVersion(String version1, String version2) {
        int v1Pointer = 0, v2Pointer = 0;
        while(v1Pointer < version1.length() || v2Pointer < version2.length()) {
            int v1Val = 0, v2Val = 0;
            while(v1Pointer < version1.length() && version1.charAt(v1Pointer) != '.') {
                v1Val = v1Val*10 + version1.charAt(v1Pointer) - '0';
                v1Pointer++;
            }
            while(v2Pointer < version2.length() && version2.charAt(v2Pointer) != '.') {
                v2Val = v2Val*10 + version2.charAt(v2Pointer) - '0';
                v2Pointer++;
            }
            if (v1Val < v2Val) {
                return -1;
            } else if (v1Val > v2Val) {
                return 1;
            }
            v1Pointer++;
            v2Pointer++;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new CompareVersions().compareVersion("1.01","1.001"));
        System.out.println(new CompareVersions().compareVersion("1.01.00","1.001"));
    }
}
