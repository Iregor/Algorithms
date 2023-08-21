/*
11. Container With Most Water. Medium
    You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
    Find two lines that together with the x-axis form a container, such that the container contains the most water.
    Return the maximum amount of water a container can store.
    Notice that you may not slant the container.

Example 1:
    Input: height = [1,8,6,2,5,4,8,3,7]
    Output: 49
    Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
    In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
    Input: height = [1,1]
    Output: 1

Constraints:
    n == height.length
    2 <= n <= 105
    0 <= height[i] <= 104*/

public class ContainerWithMostWater {
    public static void main(String[] args) {
        Solution11 sol = new Solution11();
        int[] height = {1,2,4,3};
        System.out.println(sol.maxArea(height));
    }
}

class Solution11 {
    public int maxArea(int[] height) {
        int head = 0;
        int tail = height.length - 1;
        int maxVolume = Integer.min(height[head], height[tail]) * (tail - head);
        while (head != tail) {
            int currentVolume = 0;
            if (height[head] <= height[tail]) {
                while (height[head] <= height[tail] && head != tail) {
                    head++;
                    if ((currentVolume = Integer.min(height[head], height[tail]) * (tail - head)) > maxVolume) {
                        maxVolume = currentVolume;
                    }
                }
            } else {
                while (height[head] > height[tail]) {
                    tail--;
                    if ((currentVolume = Integer.min(height[head], height[tail]) * (tail - head)) > maxVolume) {
                        maxVolume = currentVolume;
                    }
                }
            }
        }
        return maxVolume;
    }
}
