package _036_ValidSudoku;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0; i < 9; i++){
            for(int j=0; j < 9; j++){
                char ch = board[i][j];
                if( ch != '.' && !isValid( board, i ,j, ch ) ){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            
            if (board[row][i] == c && col != i) return false;
            if (board[i][col] == c && row != i) return false;

            int i1 = 3 * (row / 3) + i / 3;
            int i2 = 3 * (col / 3) + i % 3;
            if (board[i1][i2] == c && i1 != row && i2 != col) return false;
        }
        return true;
    }
}