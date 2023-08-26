/*
LeetCode 69. Sqrt(x)
Easy

Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.*/

public class SqrtX {
    public static void main(String[] args) {
        Solution sol = new SqrtX().new Solution();
        System.out.println(sol.mySqrt(49));
    }

    class Solution {
        public int mySqrt(int x) {
            int left = 0;
            int right = Integer.max(x / 2, 1);
            while (left < right - 1) {
                int middle = (left + right) / 2;
                if ((long)middle * middle == x) {
                    return middle;
                } else if ((long)middle * middle < x){
                    left = middle;
                } else {
                    right = middle - 1;
                }
            }
            if ((long)right * right <= x) {
                return right;
            } else {
                return left;
            }
        }
    }
}
