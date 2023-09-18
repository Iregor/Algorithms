/*
129. Sum Root to Leaf Numbers
Medium

You are given the root of a binary tree containing digits from 0 to 9 only.

Each root-to-leaf path in the tree represents a number.

For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

A leaf node is a node with no children.
*/

package leetcode;

public class SumRootToLeafNumbers {
    public static void main(String[] args) {
        Solution sol = new SumRootToLeafNumbers().new Solution();
        System.out.println(sol.sumNumbers(null));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int sumNumbers(TreeNode root) {
            return sumRootsRec(root, 0);
        }

        private int sumRootsRec(TreeNode root, int prevSum) {
            if (root == null) {
                return 0;
            }
            int curSum = prevSum * 10 + root.val;
            if (root.left == null && root.right == null) {
                return curSum;
            }
            int leftSum = sumRootsRec(root.left, curSum);
            int rightSum = sumRootsRec(root.right, curSum);
            return leftSum + rightSum;
        }
    }
}
