/*
LeetCode 392. Is Subsequence
Easy

Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
8of the characters without disturbing the relative positions of the remaining characters.
(i.e., "ace" is a subsequence of "abcde" while "aec" is not). d/kf*/

public class IsSubsequence {
    public static void main(String[] args) {

    }

    class Solution {
        public boolean isSubsequence(String s, String t) {
            if (s.length() > t.length()) {
                return false;
            }
            if (s.isEmpty()) {
                return true;
            }
            int sp = 0;
            int tp = 0;
            while (sp < s.length() && tp < t.length()) {
                while (s.charAt(sp) != t.charAt(tp)) {
                    tp++;
                    if (tp >= t.length()) {
                        return false;
                    }
                }
                sp++;
                tp++;
            }
            return sp == s.length();
        }
    }
}
