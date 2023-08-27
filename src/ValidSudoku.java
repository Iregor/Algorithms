import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        Solution sol = new ValidSudoku().new Solution();
        System.out.println(sol.isValidSudoku(board));
    }

    class Solution {
        public boolean isValidSudoku(char[][] board) {
            Set<String> digits = new HashSet<>();
            for (int line = 0; line < 9; line++) {
                for (int column = 0; column < 9; column++) {
                    char ch = board[line][column];
                    if (ch != '.' && !(digits.add("L" + line + ch)
                            && digits.add("C" + column + ch)
                            && digits.add("" + (line / 3) + (column / 3) + ch))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
