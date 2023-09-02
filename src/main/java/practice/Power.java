package practice;

//https://leetcode.com/problems/powx-n/
public class Power {
    public double myPow(double x, int n) {
        //return linearExpo(x, n);
        /*if (n == 0) {
            return 1;
        } else if (n < 0) {
            n = -n;
            x =  1.0 / x;
            return 1.0 / binExpo(x, n);
        } else {
            return binExpo(x, n);
        }*/

        return binExpoIterative(x,n);
    }
    //brute force
    //TC: O(n)
    //SC: O(n)
    private double linearExpo(double base, int power) {
        if (power == 0) {
            return 1;
        } else if (power < 0) {
            return 1 / linearExpo(base, -power); //return reciprocal
        }

        return base * linearExpo(base, power-1);
    }

    //Fast Power: Binary exponentiation - RECURSIVE
    //Binary exponentiation, also known as exponentiation by squaring, is a technique for efficiently computing the power of a number.
    //By repeatedly squaring x and halving n, we can quickly compute x^n using a logarithmic number of multiplications.
    //TC: O(log n)
    //SC: O(log n) - stack frames
    private double binExpo(double base, int power) {
        //power is odd
        if (power%2 == 1) {
            return base * binExpo(base, power-1);
        } else {
            return binExpo(base*base, power/2);
        }
    }

    //Fast Power: Binary exponentiation - ITERATIVE
    //Binary exponentiation, also known as exponentiation by squaring, is a technique for efficiently computing the power of a number.
    //By repeatedly squaring x and halving n, we can quickly compute x^n using a logarithmic number of multiplications.
    //TC: O(log n)
    //SC: O(1) - No stack frames
    //changing power to long to accommodate +ve of INT_MIN
    private double binExpoIterative(double base, long power) {
        if (power == 0) {
            return 1;
        } else if (power < 0) {
            power = -1 * power;
            base =  1.0 / base; //reciprocate the base
        }

        double result = 1;
        while(power > 0) {
            //power is odd
            if (power%2 == 1) {
                result *= base;
                power--;
            }
            //square base and reduce power by half
            base *= base;
            power = power / 2;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Power().myPow(2,10)); //1024
        System.out.println(new Power().myPow(2,-2)); //0.25
        System.out.println(new Power().myPow(2,-2147483648)); //0.0
    }
}
