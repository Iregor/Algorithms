/*
LeetCode 70. Climbing Stairs
Easy

You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/

public class ClimbingStairs {
    public static void main(String[] args) {
        Solution sol = new ClimbingStairs().new Solution();
        System.out.println(sol.climbStairs(44));
    }

        class Solution {
            public int climbStairs(int n) {
                int[] distinctWays = new int[n + 1];
                distinctWays[1] = 1;
                if (n > 1) {
                    distinctWays[2] = 2;
                }
                for (int i = 3; i <= n; i++) {
                    distinctWays[i] = distinctWays[i - 1] + distinctWays[i - 2];
                }
                return distinctWays[n];
            }
        }
}
