/*
LeetCode 49. Group Anagrams
Medium

Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
*/

import java.util.*;

public class GroupAnagram {
    public static void main(String[] args) {
        Solution sol = new GroupAnagram().new Solution();
        System.out.println(sol.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> anagrams = new HashMap<>();
            for (String s : strs) {
                String anagramBase = getAnagramBase(s);
                List<String> words = anagrams.get(anagramBase);
                if (words == null) {
                    anagrams.put(anagramBase, new ArrayList<>(List.of(s)));
                } else {
                    words.add(s);
                    anagrams.put(anagramBase, words);
                }
            }
            return new ArrayList<>(anagrams.values());
        }

        private String getAnagramBase(String s) {
            int[] chars = new int[58];
            for (int i = 0; i < s.length(); i++) {
                chars[s.charAt(i) - 'A']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                for(int j = 1; j <= chars[i]; j++) {
                    sb.append(i + 'A');
                }
            }
            return sb.toString();
        }
    }
}

