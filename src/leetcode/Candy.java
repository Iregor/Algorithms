package leetcode;

import java.util.*;
import java.util.stream.IntStream;

public class Candy {
    public static void main(String[] args) {
        Solution sol = new Candy().new Solution();
        System.out.println(sol.candy(new int[]{1,3,2,2,1}));
    }

    class Solution {
        public int candy(int[] ratings) {
            int prevElement = -1;
            int raisingSeries = 0;
            int reducingForward = 0;
            for (int i = 0; i < ratings.length; i++) {
                int curElement = ratings[i];
                ratings[i] = 1;
                if (curElement > prevElement) {
                    ratings[i] = ++raisingSeries;
                }
                if (i < ratings.length - 1 && reducingForward == 0 && ratings[i+1] < curElement) {
                    raisingSeries = 1;
                    reducingForward++;
                    int j = i + 1;
                    while(j + 1 < ratings.length && ratings[j + 1] < ratings[j]) {
                        reducingForward++;
                        j++;
                    }
                }
                if (ratings[i] < reducingForward + 1) {
                    ratings[i] = reducingForward + 1;
                }
                if (curElement == prevElement) {
                    raisingSeries = 1;
                }
                if (reducingForward > 0) {
                    reducingForward--;
                }
                prevElement = curElement;
            }
            System.out.println(Arrays.toString(ratings));
            return IntStream.of(ratings).sum();
        }
    }
}