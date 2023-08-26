/*
LeetCode 205. Isomorphic Strings
Easy

Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character, but a character may map to itself.
*/

import java.util.HashMap;

public class IsomorphicString {
    public static void main(String[] args) {
        Solution sol = new IsomorphicString().new Solution();
        System.out.println(sol.isIsomorphic("egg", "add"));
    }

    class Solution {
        public boolean isIsomorphic(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            HashMap<Character, Character> replacedCharsS = new HashMap<>();
            HashMap<Character, Character> replacedCharsT = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (replacedCharsT.containsKey(t.charAt(i))) {
                    if (replacedCharsT.get(t.charAt(i)) != s.charAt(i)) {
                        return false;
                    }
                } else {
                    replacedCharsT.put(t.charAt(i), s.charAt(i));
                }
                if (replacedCharsS.containsKey(s.charAt(i))) {
                    if (replacedCharsS.get(s.charAt(i)) != t.charAt(i)) {
                        return false;
                    }
                } else {
                    replacedCharsS.put(s.charAt(i), t.charAt(i));
                }
            }
            return true;
        }
    }
}
