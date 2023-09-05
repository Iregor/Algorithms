/*
LeetCode 50. Pow(x, n)
Medium

Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
*/

package leetcode;

public class PowProblem {
    public static void main(String[] args) {
        Solution sol = new PowProblem().new Solution();
        System.out.println(sol.myPow(10.0, 10));
    }

    class Solution {
        public double myPow(double x, int n) {
            return myPowRec(x, (long) n);
        }

        private double myPowRec(double x, long n) {
            if (n == 0) {
                return 1;
            }
            if (n < 0) {
                return 1.0 / myPowRec(x, -n);
            }
            if (n % 2 == 1) {
                return x * myPowRec(x * x, (n - 1) / 2);
            } else {
                return myPowRec(x * x, n / 2);
            }
        }
    }
}
