/*
LeetCode 530. Minimum Absolute Difference in BST
Easy

Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two
different nodes in the tree.

Example 1:

Input: root = [4,2,6,1,3]
Output: 1
*/

import java.util.ArrayList;
import java.util.List;

public class MinimumAbsoluteDifferenceinBST {
    public static void main(String[] args) {
        Solution sol = new MinimumAbsoluteDifferenceinBST().new Solution();
        System.out.println(sol.getMinimumDifference(null));
    }

    public class TreeNode {
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
        public int getMinimumDifference(TreeNode root) {
            List<TreeNode> nodes = findAllNodes(root, new ArrayList<>());
            int min = -1;
            for (int i = 1; i < nodes.size(); i++) {
                if (min == -1 || min > Math.abs(nodes.get(i).val - nodes.get(i-1).val)) {
                    min = Math.abs(nodes.get(i).val - nodes.get(i-1).val);
                }
            }
            return min;
        }

        private List<TreeNode> findAllNodes(TreeNode root, List<TreeNode> nodes) {
            if (root == null) {
                return nodes;
            }
            if (root.left != null) {
                findAllNodes(root.left, nodes);
            }
            nodes.add(root);
            if (root.right != null) {
                findAllNodes(root.right, nodes);
            }
            return nodes;
        }
    }
}
