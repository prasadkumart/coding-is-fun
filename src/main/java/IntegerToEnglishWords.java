//https://leetcode.com/problems/longest-palindromic-substring/solution/
public class IntegerToEnglishWords {
    public String numberToWords(int num) {
        if (num < 0) return "";
        if (num == 0) return "Zero";
        StringBuilder result = new StringBuilder();

        while (num > 0) {
            //greater than a billion
            int b = num / 1000000000;
            if (b > 0) {
                result.append(helper(b)).append(" Billion ");
            }
            num = num % 1000000000;

            //greater than a million
            int m = num / 1000000;
            if (m > 0) {
                result.append(helper(m)).append(" Million ");
            }
            num = num % 1000000;

            //greater than a million
            int t = num / 1000;
            if (t > 0) {
                result.append(helper(t)).append(" Thousand ");
            }
            num = num % 1000;

            result.append(helper(num));
            num = 0;
        }
        return result.toString().trim();
    }

    public String helper(int num) {
        String[] LESS_THAN_TWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            //greater than one thousand
            int hundred = num / 100;
            if (hundred > 0) {
                result.append(LESS_THAN_TWENTY[hundred]).append(" Hundred ");
            }
            num = num % 100;

            if (num < 20) {
                result.append(LESS_THAN_TWENTY[num]);
            } else {
                int tens = num / 10;
                if (tens <= 9) {
                    result.append(TENS[tens]);
                    num = num % 10;
                    result.append(" ").append(LESS_THAN_TWENTY[num]);
                }
            }
            num = 0;
        }
        return result.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(new IntegerToEnglishWords().numberToWords(123));
        System.out.println(new IntegerToEnglishWords().numberToWords(919));
        System.out.println(new IntegerToEnglishWords().numberToWords(17));
        System.out.println(new IntegerToEnglishWords().numberToWords(4));
        System.out.println(new IntegerToEnglishWords().numberToWords(1234567));
        //System.out.println(new LongestPalindromeSubstr().longestPalindrome("cbbd"));
    }
}
