package sortnsearchrecusion;

public class Power {
    static long MOD = 1000000007L;
    //TC: O(B) - B is the depth of the recursion tree
    //SC: O(B) - B is the depth of the recursion tree
    static Integer calculate_power_recursion(Long a, Long b) {
        Long power = 0L;

        if (a == 0) {
            return 0;
        }
        if (a == 1) {
            return 1;
        }
        if (b==0) {
            return 1;
        }
        //mod with 1000000007;
        a = a % 1000000007;
        power = a * calculate_power(a, b-1);
        //if (power > 1000000007) {
            power %= 1000000007;
        //}
        return power.intValue();
    }

    static Integer calculate_power(Long a, Long b) {
        return power(a, b).intValue();
    }

    //TC: O(logB) - reduces the exponent by half
    //SC: O(lobB) - reduces the exponent by half
    static Long power(Long a, Long b) {
        Long temp = 0L;

        if (a == 0) {
            return 0L;
        }
        if (a == 1 || b == 0) {
            return 1L;
        }

        temp = power(a, b/2);
        //a^4 = a^2 X a^2;
        temp = temp * temp % MOD;

        //when b is odd number
        //a^5 = a^2 X a^2 * a;
        if (b%2 == 1) {
            temp = temp * a % MOD;
        }

        return temp;
    }

    //TC: O(logB) - reduces the exponent by half
    //SC: O(1) - constant space
    static Integer calculate_power_bitwise(Long a, Long b) {
        if (a == 0) {
            return 0;
        }
        if (a == 1 || b == 0) {
            return 1;
        }

        Long result = 1L;

        // stores a^(power of two)
        // initialized with a^(2^0)
        Long powerOfTwo = a;
        int power = 1;
        long bin = 0;
        //iterate all over bits of b
        while (b != 0) {
            long q = b % 2;
            bin += q * power;
            power *= 10;

            //if there is a bit value at the current position
            if (b%2 == 1) {
                result = result * powerOfTwo % MOD;
            }

            // double the power of two
            // a^i * a^i = a^(2*i)
            powerOfTwo = powerOfTwo * powerOfTwo % MOD;

            b /= 2;
        }
        System.out.println("Binary value for a power b: "+ bin);
        return result.intValue();
    }

    public static void main(String[] args) {
        System.out.println(calculate_power_bitwise(1000000008L, 1L));
        //System.out.println(calculate_power(10000L, 10000000L));
    }
}
