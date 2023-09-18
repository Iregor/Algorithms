/*
71. Simplify Path
Medium

Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system,
convert it to the simplified canonical path.

In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level,
and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. For this problem,
any other format of periods such as '...' are treated as file/directory names.

The canonical path should have the following format:

The path starts with a single slash '/'.
Any two directories are separated by a single slash '/'.
The path does not end with a trailing '/'.
The path only contains the directories on the path from the root directory to the target file or directory
(i.e., no period '.' or double period '..')
Return the simplified canonical path.*/

package leetcode;

import java.util.*;

public class SimplifyPath {
    public static void main(String[] args) {
        Solution sol = new SimplifyPath().new Solution();
        System.out.println(sol.simplifyPath("/hello/../world/"));
    }

    class Solution {
        public String simplifyPath(String path) {
            Deque<String> canPath = new ArrayDeque<>();
            StringBuilder word;
            for (int i = 0; i < path.length(); ) {
                char ch = path.charAt(i);
                if (ch == '/') {
                    i++;
                    continue;
                }
                word = new StringBuilder();
                while (i < path.length() && path.charAt(i) != '/') {
                    word.append(path.charAt(i));
                    i++;
                }
                if (word.length() == 1 && word.charAt(0) == '.') {
                    continue;
                }
                if (word.length() == 2 && word.charAt(0) == '.' && word.charAt(1) == '.') {
                    canPath.pollFirst();
                    continue;
                }
                canPath.addFirst(word.toString());
            }
            StringBuilder sb = new StringBuilder();
            if (canPath.isEmpty()) {
                return "/";
            } else {
                while (!canPath.isEmpty()) {
                    sb.append('/');
                    sb.append(canPath.pollLast());
                }
                return sb.toString();
            }
        }
    }
}
