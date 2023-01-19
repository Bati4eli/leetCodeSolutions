package _037_Sudoku;

// https://leetcode.com/problems/sudoku-solver/

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayDeque;
import java.util.Objects;

class Solution {

    public void solveSudoku(char[][] board) {
        ArrayDeque<Point> points = new ArrayDeque<>();// Comparator.comparingInt(o -> o.amountBusy)

        fillQueue(board, points);

        Point lastPoint = null;
        int amountFails = 0;

        while (!points.isEmpty()) {
            Point point = points.poll();
            //substitution method
            if (amountFails >= points.size()) { // если не удается найти решение, придется идти методом подбора
                System.out.println("substitution method");
            } else if (board[point.rowNum][point.columnNum] != '.') { // если точка уже имеет значение, выкидываем из очереди
                continue;
            } else if (Objects.equals(lastPoint, point)) { // если только что проверяли эту точку, вставляем в конец очереди
                points.addLast(point);
            } else {
                boolean found = calculatePoint(board, points, point);
                if (found) {
                    amountFails = 0;
                } else {
                    ++amountFails;
                }
            }
            lastPoint = point;
        }
    }

    private void fillQueue(char[][] board, ArrayDeque<Point> points) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char ch = board[i][j];
                if (ch == '.') {
                    Point point = new Point(i, j);
                    calculatePoint(board, points, point);
                }
            }
        }
    }

    /**
     * @return true - если найдено и вставлено число
     */
    private boolean calculatePoint(char[][] board, ArrayDeque<Point> points, Point point) {
        point.reset();

        boolean found = checkrow(board, point) ||
                checkcolumn(board, point) ||
                checkBox(board, point);

        // если получилось найти методом исключения из соседних строк и столбцов одного квадрата
        // то выходим из калькулятора, в очередь не добавляем задание
        if (!found && checkBoxRowsAndLines(board, point)) {
            return true;
        }

        if (point.amountBusy == 8) {
            saveNumberOnTheBoard(point.rowNum, point.columnNum, board, findNumber(point));
            return true;
        } else {
            // точки, у которых мало возможных вариантов чисел, кладем в верх очереди
            if (point.amountBusy > 6) {
                points.addFirst(point);
            } else {
                points.addLast(point);
            }
            return false;
        }
    }

    private char findNumber(Point point) {
        //point.amountBusy = 0;
        for (char num = '1'; num <= '9'; num++) {
            int intNum = num - '0' - 1;
            //point.amountBusy += point.busyNums[intNum] ? 1 : 0;
            if (!point.busyNums[intNum]) {
                point.symbol = num;
                return num;
            }
        }
        return '.';
    }

    private boolean checkBoxRowsAndLines(char[][] board, Point point) {
        int rowStart = point.rowNum < 3 ? 0 : point.rowNum < 6 ? 3 : 6;
        int columnStart = point.columnNum < 3 ? 0 : point.columnNum < 6 ? 3 : 6;

        for (char num = '1'; num <= '9'; num++) {

            int numberToSearch = num - '0' - 1;
            // если число свободно, то смотрим на соседние строки и столбцы одного квадрата
            if (!point.busyNums[numberToSearch]) {
                boolean otherRowsHasNumber = true;
                boolean[][] filled = new boolean[3][3];

                for (int r = rowStart; r <= rowStart + 2; r++) {
                    boolean rowHasNumber = rowHasNumber(num, r, board) || r == point.rowNum;
                    otherRowsHasNumber = otherRowsHasNumber && rowHasNumber;
                    if (r != point.rowNum && rowHasNumber) {    // если в этой строке в другом квадрате есть уже это число, то вычеркиваем из нашего квадрата эту строку
                        fillWithFoundNumberInOtherRow(rowStart, columnStart, filled, r, (row, column) -> rowHasNumber);
                    } else {                                    // иначе вычеркиваем только заполненные ячейки квадрата
                        fillWithFoundNumberInOtherRow(rowStart, columnStart, filled, r, (row, column) -> board[row][column] != '.');
                    }
                }
                for (int c = columnStart; c <= columnStart + 2; c++) {
                    boolean columnHasNumber = columnHasNumber(num, c, board) || c == point.columnNum;
                    otherRowsHasNumber = otherRowsHasNumber && columnHasNumber;
                    if (c != point.columnNum && columnHasNumber) {  // если в этом столбце в другом квадрате есть уже это число, то вычеркиваем из нашего квадрата этот столбец
                        fillWithFoundNumberInOtherColumn(rowStart, columnStart, filled, c, (row, column) -> columnHasNumber);
                    } else {                                        // иначе вычеркиваем только заполненные ячейки квадрата
                        fillWithFoundNumberInOtherColumn(rowStart, columnStart, filled, c, (row, column) -> board[row][column] != '.');
                    }
                }
                if (otherRowsHasNumber) { // если число есть в остальных 2х строках и 2х столбцах, то вставляем его
                    saveNumberOnTheBoard(point.rowNum, point.columnNum, board, num);
                    return true;
                } else { // иначе считаем количество занятых ячеек из массива filled
                    int amountFilledCells = 0;
                    int rr = 0;
                    int cc = 0;
                    for (int r = 0; r < 3; r++) {
                        for (int c = 0; c < 3; c++) {
                            if (!filled[r][c]) { // если ячейка свободна, запоминаем ее координаты
                                rr = r + rowStart;
                                cc = c + columnStart;
                            } else {            // подсчитываем количество занятых
                                ++amountFilledCells;
                            }
                        }
                    }
                    if (amountFilledCells == 8) { // если число больше некуда вставлять, вставляем
                        saveNumberOnTheBoard(rr, cc, board, num);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void saveNumberOnTheBoard(int rr, int cc, char[][] board, char num) {
        board[rr][cc] = num;
        System.out.println("row: " + rr + " column: " + cc + " char: " + num);
    }

    /**
     * заполняем булиан массив квадратика, если нашли число в соседних линиях в других квадратах
     */
    private void fillWithFoundNumberInOtherRow(int rowStart, int columnStart, boolean[][] filled, int currRow, Condition condition) {
        for (int j = columnStart; j <= columnStart + 2; j++) {
            boolean b = condition.count(currRow, j);
            filled[currRow - rowStart][j - columnStart] = filled[currRow - rowStart][j - columnStart] || b;
        }
    }

    /**
     * заполняем булиан массив квадратика, если нашли число в соседних столбцах в других квадратах
     */
    private void fillWithFoundNumberInOtherColumn(int rowStart, int columnStart, boolean[][] filled, int currColumn, Condition condition) {
        for (int i = rowStart; i <= rowStart + 2; i++) {
            boolean b = condition.count(i, currColumn);
            filled[i - rowStart][currColumn - columnStart] = filled[i - rowStart][currColumn - columnStart] || b;
        }
    }

    private boolean rowHasNumber(char num, int rowNum, char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[rowNum][i] == num)
                return true;
        }
        return false;
    }

    private boolean columnHasNumber(char num, int columnNum, char[][] board) {
        for (int j = 0; j < board.length; j++) {
            if (board[j][columnNum] == num)
                return true;
        }
        return false;
    }

    private boolean checkBox(char[][] board, Point point) {
        int columnStart = point.rowNum < 3 ? 0 : point.rowNum < 6 ? 3 : 6;
        int rowStart = point.columnNum < 3 ? 0 : point.columnNum < 6 ? 3 : 6;

        for (int i = columnStart; i <= columnStart + 2; i++) {
            for (int j = rowStart; j <= rowStart + 2; j++) {
                char number = board[i][j];
                writeBusyNum(number, point);
                if (point.amountBusy == 8)
                    return true;
            }
        }
        return point.amountBusy == 8;
    }

    private boolean checkcolumn(char[][] board, Point point) {
        for (int i = 0; i < board.length; i++) {
            char number = board[i][point.columnNum];
            writeBusyNum(number, point);
            if (point.amountBusy == 8)
                return true;
        }
        return point.amountBusy == 8;
    }

    private boolean checkrow(char[][] board, Point point) {
        for (int i = 0; i < board.length; i++) {
            char number = board[point.rowNum][i];
            writeBusyNum(number, point);
        }
        return point.amountBusy == 8;
    }

    private void writeBusyNum(char number, Point point) {
        if (number != '.') {
            int ind = number - '0' - 1;
            if (!point.busyNums[ind]) {
                ++point.amountBusy;
                point.busyNums[ind] = true;
            }
        }
    }

    @FunctionalInterface
    interface Condition {
        boolean count(int row, int column);
    }

    class Point {
        int rowNum;
        int columnNum;
        char symbol = '.';
        int amountBusy = 0;
        boolean[] busyNums = new boolean[9];

        public Point(int rowNum, int columnNum) {
            this.rowNum = rowNum;
            this.columnNum = columnNum;
        }

        public void reset() {
            amountBusy = 0;
            busyNums = new boolean[9];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return rowNum == point.rowNum && columnNum == point.columnNum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowNum, columnNum);
        }
    }

    public static void main(String[] args) {
        new Solution().solveSudoku(
                new char[][]{
                        new char[]{'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                        new char[]{'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                        new char[]{'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                        new char[]{'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                        new char[]{'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                        new char[]{'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                        new char[]{'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                        new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                        new char[]{'.', '.', '.', '2', '7', '5', '9', '.', '.'}
                }
        );
//        new Solution().solveSudoku(
//                new char[][]{
//                        new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                        new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                        new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                        new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                        new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                        new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                        new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                        new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                        new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
//                }
//        );
    }
}

//0 = {char[9]@899} [ ., ., 9,  7, 4, 8,  ., ., 2 ]
//1 = {char[9]@900} [ 7, ., .,  6, ., 2,  ., ., 9 ]
//2 = {char[9]@901} [ ., 2, .,  1, ., 9,  ., ., . ]
//3 = {char[9]@902} [ ., ., 7,  9, 8, 6,  2, 4, 1 ]
//4 = {char[9]@903} [ 2, 6, 4,  3, 1, 7,  5, 9, 8 ]
//5 = {char[9]@904} [ 1, 9, 8,  5, 2, 4,  3, 6, 7 ]
//6 = {char[9]@905} [ 9, ., .,  8, 6, 3,  ., 2, . ]
//7 = {char[9]@906} [ ., ., 2,  4, 9, 1,  ., ., 6 ]
//8 = {char[9]@907} [ ., ., .,  2, 7, 5,  9, ., . ]