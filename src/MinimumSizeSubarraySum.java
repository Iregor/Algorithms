/*
LeetCode 209. Minimum Size Subarray Sum
Medium

Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
*/

public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        Solution sol = new MinimumSizeSubarraySum().new Solution();
        System.out.println(sol.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int minLength = 0;
            if (nums == null) {
                return minLength;
            }
            if (target == 0) {
                return 1;
            }
            int left = 0;
            int right = 0;
            int sum = 0;
            while (right < nums.length) {
                sum += nums[right++];
                if (sum >= target) {
                    while (sum >= target) {
                        sum -= nums[left++];
                    }
                    if (minLength == 0 || minLength > right - 1 - (left - 2)) {
                        minLength = right - 1 - (left - 2);
                    }
                }
            }
            return minLength;
        }
    }
}
