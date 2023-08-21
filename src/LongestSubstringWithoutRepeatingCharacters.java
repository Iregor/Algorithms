/*
LeetCode
3. Longest Substring Without Repeating Characters
Medium
Task:
    Given a string s, find the length of the longest substring without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Constraints:

0 <= s.length <= 5 * 10^4
s consists of English letters, digits, symbols and spaces.*/

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution3 sol = new Solution3();
        String s = "abcabcbb";
        System.out.println(sol.lengthOfLongestSubstring(s));
    }
}

class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        Set<Character> uniqueCharacters = new HashSet<>();
        int head = 0;
        int tail = 0;
        while (tail < s.length()) {
            char ch = s.charAt(tail);
            if (!uniqueCharacters.contains(ch)) {
                uniqueCharacters.add(s.charAt(tail));
                tail++;
            } else {
                if (maxLength < uniqueCharacters.size()) {
                    maxLength = uniqueCharacters.size();
                }
                while (s.charAt(head) != s.charAt(tail)) {
                    uniqueCharacters.remove(s.charAt(head++));
                }
                head++;
                tail++;
            }
        }
        if (maxLength < uniqueCharacters.size()) {
            maxLength = uniqueCharacters.size();
        }
        return maxLength;
    }
}
