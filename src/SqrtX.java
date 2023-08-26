public class SqrtX {
    public static void main(String[] args) {
        Solution sol = new SqrtX().new Solution();
        System.out.println(sol.mySqrt(49));
    }

    class Solution {
        public int mySqrt(int x) {
            int left = 0;
            int right = Integer.max(x / 2, 1);
            while (left < right - 1) {
                int middle = (left + right) / 2;
                if ((long)middle * middle == x) {
                    return middle;
                } else if ((long)middle * middle < x){
                    left = middle;
                } else {
                    right = middle - 1;
                }
            }
            if ((long)right * right <= x) {
                return right;
            } else {
                return left;
            }
        }
    }
}
