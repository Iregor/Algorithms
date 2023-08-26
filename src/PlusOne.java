/*
LeetCode 66. Plus One
Easy

Share
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
The digits are ordered from most significant to least significant in left-to-right order.
The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

Example 1:

Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].

Example 2:

Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].

Example 3:

Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
 */

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
        Solution sol = new PlusOne().new Solution();
        System.out.println(Arrays.toString(sol.plusOne(new int[]{9,9,9})));
    }

    class Solution {
        public int[] plusOne(int[] digits) {
            return plusOneRec(digits, digits.length - 1);
        }

        public int[] plusOneRec(int[] digits, int index) {
            if (digits[index] < 9) {
                digits[index]++;
                return digits;
            } else if (index > 0) {
                digits[index] = 0;
                return plusOneRec(digits, --index);
            } else {
                digits[index] = 0;
                int[] res = new int[digits.length + 1];
                res[0] = 1;
                for (int i = 1; i < res.length; i++) {
                    res[i] = digits[i - 1];
                }
                return res;
            }
        }
    }
}
