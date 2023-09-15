/*
42. Trapping Rain Water
Hard

Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.
*/

package leetcode;

public class TrappingRainWaterII {
    public static void main(String[] args) {
        Solution sol = new TrappingRainWaterII.Solution();
        System.out.println(sol.trap(new int[]{1,0,3,0,5,0,3,1}));
    }

    static class Solution {
        public int trap(int[] height) {
            //n == height.length
            //1 <= n <= 2 * 104
            //0 <= height[i] <= 105
            int maxHeight = height[0];
            int maxHeightIndex = 0;
            for (int i = 1; i < height.length; i++) {
                if (height[i] >= height[maxHeightIndex]) {
                    maxHeightIndex = i;
                    maxHeight = height[i];
                }
            }
            int water = 0;
            int curMax = height[0];
            for (int i = 1; i < maxHeightIndex; i++) {
                if (height[i] < curMax) {
                    water += curMax - height[i];
                } else {
                    curMax = height[i];
                }
            }
            curMax = height[height.length - 1];
            for (int i = height.length - 2; i > maxHeightIndex; i--) {
                if (height[i] < curMax) {
                    water += curMax - height[i];
                } else {
                    curMax = height[i];
                }
            }
            return water;
        }
    }
}

