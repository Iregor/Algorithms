import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TreeAVLBalanced {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            int[] intSeq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            BinaryTree tree = new BinaryTree(new Node(intSeq[0]));
            for (int i = 1; i < intSeq.length - 1; i++) {
                tree.add(intSeq[i]);
            }
            bw.write(tree.isBalanced(tree.root) ? "YES" : "NO");
            bw.write(tree.checkBalance());
        }
    }

    private static class BinaryTree {
        private Node root;

        public BinaryTree(Node root) {
            this.root = root;
        }

        public void add(int key) {
            Node newNode = new Node(key);
            insertIntoTree(root, newNode);
        }

        public int depth() {
            return findMaxDepth(root);
        }

        public List<Integer> displayTree() {
            List<Integer> list = new ArrayList<>();
            return traverse(root, list);
        }

        public String checkBalance() {
            String[] ans = new String[]{"YES"};
            determineDepth(root, ans);
            return ans[0];
        }

        public boolean isBalanced(Node node) {
            if (node.left == null && node.right == null) {
                return true;
            }
            int leftDepth = 0;
            int rightDepth = 0;
            if (node.left != null) {
                leftDepth = findMaxDepth(node.left);
            }
            if (node.right != null) {
                rightDepth = findMaxDepth(node.right);
            }
            if (Math.abs(leftDepth - rightDepth) > 1) {
                return false;
            }
            return (node.left == null || isBalanced(node.left)) && (node.right == null || isBalanced(node.right));
        }

        private int determineDepth(Node node, String[] ans) {
            int leftDepth = 0;
            int rightDepth = 0;
            if (node.left != null) {
                leftDepth = determineDepth(node.left, ans);
            }
            if (node.right != null) {
                rightDepth = determineDepth(node.right, ans);
            }
            if (Math.abs(leftDepth - rightDepth) > 1) {
                ans[0] = "NO";
            }
            return Integer.max(leftDepth, rightDepth) + 1;
        }

        private List<Integer> traverse(Node currentNode, List<Integer> list) {
            if (currentNode.left != null) {
                traverse(currentNode.left, list);
            }
            list.add(currentNode.key);
            if (currentNode.right != null) {
                traverse(currentNode.right, list);
            }
            return list;
        }

        public List<Integer> displayLeafs() {
            List<Integer> leafs = new ArrayList<>();
            return displayLeafs(root, leafs);
        }

        private List<Integer> displayLeafs(Node currentNode, List<Integer> leafs) {
            if (currentNode.left != null) {
                displayLeafs(currentNode.left, leafs);
            }
            if (currentNode.right != null) {
                displayLeafs(currentNode.right, leafs);
            }
            if (currentNode.left == null && currentNode.right == null) {
                leafs.add(currentNode.key);
            }
            return leafs;
        }

        public int findSecondMax(Node parentNode, Node currentNode) {
            if (currentNode.right != null) {
                return findSecondMax(currentNode, currentNode.right);
            } else if (currentNode.left != null) {
                return findMax(currentNode.left);
            } else {
                return parentNode.key;
            }
        }

        private int findMax(Node currentNode) {
            if (currentNode.right != null) {
                return findMax(currentNode.right);
            } else {
                return currentNode.key;
            }
        }

        private int findMaxDepth(Node node) {
            int leftMax = 0;
            int rightMax = 0;
            if (node.left != null) {
                leftMax = findMaxDepth(node.left);
            }
            if (node.right != null) {
                rightMax = findMaxDepth(node.right);
            }
            return Integer.max(leftMax, rightMax) + 1;
        }

        private void insertIntoTree(Node currentNode, Node newNode) {
            if (newNode.key > currentNode.key) {
                if (currentNode.right != null) {
                    insertIntoTree(currentNode.right, newNode);
                } else {
                    currentNode.right = newNode;
                }
            } else if (newNode.key < currentNode.key) {
                if (currentNode.left != null) {
                    insertIntoTree(currentNode.left, newNode);
                } else {
                    currentNode.left = newNode;
                }
            }
        }
    }

    private static class Node {

        public Node(int key) {
            this.key = key;
        }

        private int key;
        private Node left;
        private Node right;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            return ((Node) o).key == this.key;
        }
    }
}