/*
14. Longest Common Prefix. Easy
    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".

Example 1:
    Input: strs = ["flower","flow","flight"]
    Output: "fl"

Example 2:
    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.

Constraints:
    1 <= strs.length <= 200
    0 <= strs[i].length <= 200
    strs[i] consists of only lowercase English letters.*/

import java.util.Arrays;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] s = {"flower","flow","flight"};
        Solution14 sol = new Solution14();
        System.out.println(sol.longestCommonPrefix(s));
    }
}

class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        int maxPrefixLength = 0;
        for (int i = 0; i < Integer.min(strs[0].length(), strs[strs.length - 1].length()); i++) {
            if (strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
                maxPrefixLength++;
            } else {
                break;
            }
        }
        return strs[0].substring(0, maxPrefixLength);
    }
}