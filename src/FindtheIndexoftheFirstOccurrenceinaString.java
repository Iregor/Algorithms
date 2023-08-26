import java.util.HashMap;
import java.util.Map;

public class FindtheIndexoftheFirstOccurrenceinaString {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.strStr("sadbutsad","sad"));
    }

    static class Solution {
        public int strStr(String haystack, String needle) {
            if (needle.length() > haystack.length() || needle.isEmpty()) {
                return -1;
            }
            Map<Character, Integer> chars = new HashMap<Character, Integer>();
            for (int i = 0; i < needle.length(); i++) {
                chars.compute(needle.charAt(i), (k, v) -> v == null ? -1 : v - 1);
            }
            int head = 0;
            int tail = 0;
            while (tail < needle.length()) {
                chars.compute(haystack.charAt(tail), (k, v) -> v == null ? 1 : v + 1);
                if (chars.get(haystack.charAt(tail)) == 0) {
                    chars.remove(haystack.charAt(tail));
                }
                tail++;
            }
            if (chars.isEmpty() && needle.equals(haystack.substring(head, tail))) {
                return head;
            }
            while (tail < haystack.length()) {
                chars.compute(haystack.charAt(tail), (k, v) -> v == null ? 1 : v + 1);
                if (chars.get(haystack.charAt(tail)) == 0) {
                    chars.remove(haystack.charAt(tail));
                }
                chars.compute(haystack.charAt(head), (k, v) -> v == null ? -1 : v - 1);
                if (chars.get(haystack.charAt(head)) == 0) {
                    chars.remove(haystack.charAt(head));
                }
                if (chars.isEmpty() && needle.equals(haystack.substring(head + 1, tail + 1))) {
                    return head + 1;
                }
                tail++;
                head++;
            }
            return -1;
        }
    }
}

