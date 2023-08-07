import java.io.*;
import java.util.Arrays;


public class TreeHeight {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            int[] intSeq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            BinaryTree tree = new BinaryTree(new Node(intSeq[0]));
            for (int i = 1; i < intSeq.length - 1; i++) {
                tree.add(intSeq[i]);
            }
            bw.write(String.valueOf(tree.depth()));
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