import java.io.*;
import java.util.*;


public class TreeElementDepth {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            int[] intSeq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            BinaryTree tree = new BinaryTree(new Node(intSeq[0]));
            bw.write(String.valueOf(1) + " ");
            for (int i = 1; i < intSeq.length - 1; i++) {
                int currentDepth = tree.add(intSeq[i]);
                if (currentDepth != -1) {
                    bw.write(String.valueOf(currentDepth) + " ");
                }
            }
        }
    }

    private static class BinaryTree {
        private Node root;

        public BinaryTree(Node root) {
            this.root = root;
        }

        public int add(int key) {
            Node newNode = new Node(key);
            return insertIntoTree(root, newNode, 2);
        }

        public int depth() {
            return findMaxDepth(root);
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

        private int insertIntoTree(Node currentNode, Node newNode, int depth) {
            if (newNode.key > currentNode.key) {
                if (currentNode.right != null) {
                    return insertIntoTree(currentNode.right, newNode, depth +1);
                } else {
                    currentNode.right = newNode;
                    return depth;
                }
            } else if (newNode.key < currentNode.key) {
                if (currentNode.left != null) {
                    return insertIntoTree(currentNode.left, newNode, depth +1);
                } else {
                    currentNode.left = newNode;
                    return depth;
                }
            }
            return -1;
        }
    }


    private static class Node {

        public Node (int key) {
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
            return ((Node)o).key == this.key;
        }
    }
}