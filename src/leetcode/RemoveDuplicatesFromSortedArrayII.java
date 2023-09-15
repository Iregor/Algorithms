//80. Remove Duplicates from Sorted Array II
//Medium

/*
Given an integer array nums sorted in non-decreasing order,
remove some duplicates in-place such that each unique element appears at most twice.
The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages,
you must instead have the result be placed in the first part of the array nums.
More formally, if there are k elements after removing the duplicates,
then the first k elements of nums should hold the final result.
It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
*/

package leetcode;

public class RemoveDuplicatesFromSortedArrayII {
    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicates(new int[]{1,1,1,2,2}));
    }

    static class Solution {
        public int removeDuplicates(int[] nums) {
            int occ = 1;
            int diff = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i-1]) {
                    occ++;
                }
                if (nums[i] != nums[i-1] || i == nums.length - 1) {
                    if (occ > 2) {
                        diff += occ - 2;
                    }
                    occ = 1;
                }
                nums[i - diff] = nums[i];
            }
            return nums.length - diff;
        }
    }
}
