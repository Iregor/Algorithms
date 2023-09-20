/*
124. Binary Tree Maximum Path Sum
Hard

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.
*/

package leetcode;

public class BinaryTreeMaximumSumPath {
    public static void main(String[] args) {
        Solution sol = new BinaryTreeMaximumSumPath().new Solution();
        System.out.println(sol.maxPathSum(null));
    }

    private static class TreeNode {
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
        public int maxPathSum(TreeNode root) {
            int[] maxGlob = new int[]{root.val};
            maxPathSumRec(root, maxGlob);
            return maxGlob[0];
        }

        private int maxPathSumRec(TreeNode node, int[] maxGlob) {
            if (node == null) {
                return 0;
            }

            int leftSum = Integer.max(0, maxPathSumRec(node.left, maxGlob));
            int rightSum = Integer.max(0, maxPathSumRec(node.right, maxGlob));
            maxGlob[0] = Integer.max(maxGlob[0], node.val + leftSum + rightSum);


            return node.val + Integer.max(leftSum, rightSum);
        }
    }
}
