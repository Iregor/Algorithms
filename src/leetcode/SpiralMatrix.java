/*
54. Spiral Matrix
Medium

Given an m x n matrix, return all elements of the matrix in spiral order.
*/

package leetcode;

import java.util.*;

public class SpiralMatrix {
    public static void main(String[] args) {
        Solution sol = new SpiralMatrix().new Solution();
        List<Integer> list = sol.spiralOrder(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[] {7,8,9}});
        for (int numb : list) {
            System.out.println(numb);
        }
    }

    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> elements = new ArrayList<>();
            int rightBorder = matrix[0].length - 1;
            int leftBorder = 0;
            int upBorder = 0;
            int downBorder = matrix.length - 1;
            int posX = 0;
            int posY = 0;
            int[] step = new int[]{1,0};

            while (posX <= rightBorder && posX >= leftBorder && posY >= upBorder && posY <= downBorder) {
                elements.add(matrix[posY][posX]);
                int nextX = posX + step[0];
                int nextY = posY + step[1];
                if (nextX > rightBorder) {
                    upBorder = posY + 1;
                    step[0] = 0;
                    step[1] = 1;
                    nextX = posX + step[0];
                    nextY = posY + step[1];
                }
                if (nextY > downBorder) {
                    rightBorder = posX - 1;
                    step[0] = -1;
                    step[1] = 0;
                    nextX = posX + step[0];
                    nextY = posY + step[1];
                }
                if (nextX < leftBorder) {
                    downBorder = posY - 1;
                    step[0] = 0;
                    step[1] = -1;
                    nextX = posX + step[0];
                    nextY = posY + step[1];
                }
                if (nextY < upBorder) {
                    leftBorder = posX + 1;
                    step[0] = 1;
                    step[1] = 0;
                }
                posX += step[0];
                posY += step[1];
            }
            return elements;
        }
    }
}
