public abstract class SmartPlayerEngine extends HumanPlayer{
    /***
     * mark a
     * @param board - a board
     * @param mark - a maek
     * @return true if a cell was marked, otherwise, false
     */
    protected boolean markCriticalCell(Board board, Mark mark) {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                // check a winning cell
                if (checkCriticalCell(board, row, col, mark)) {
                    board.putMark(mark, row, col);
                    return true;
                }
                //check to block a winning cell for component
                if (checkCriticalCell(board, row, col, mark == Mark.O ? Mark.X : Mark.O)) {
                    board.putMark(mark, row, col);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  check if a cell is critical for mark
     * @param board - a board
     * @param row - a row index
     * @param col - a col index
     * @param mark - a mak
     * @return - true if marking the cell will win the game
     */
    private boolean checkCriticalCell(Board board, int row, int col, Mark mark) {
        if(board.getMark(row,col)!=Mark.BLANK) return false;
        int[] rowDirections = {1, 1, 0, -1};
        int[] colDirections = {0,1, 1, 1};
        int[] rowStart = {row - Board.WIN_STREAK, row - Board.WIN_STREAK, row ,row + Board.WIN_STREAK};
        int[] colStart = {col, col - Board.WIN_STREAK, col -Board.WIN_STREAK, col - Board.WIN_STREAK};
        for(int i = 0; i < 4 ; i++)
            if(checkPossibleStreakForDirection(board, row, col, rowDirections[i], colDirections[i],
                    mark, rowStart[i], colStart[i]))
                return true;
        return false;
    }

    /**
     *
     * @param board - a board
     * @param row - a row index of a cell that supposed to be marked
     * @param col - a col index of a cell that supposed to be marked
     * @param rowChange - row direction of the streak
     * @param colChange - col direction of the streak
     * @param mark -  a mark
     * @param curRow - a row index of a cell that can be the start of the streak
     * @param curCol -  a col index of a cell that can be the start of the streak
     * @return - true if marking the cell will win the game with the given direction
     */
    private boolean checkPossibleStreakForDirection(Board board, int row, int col, int rowChange, int colChange,
                                                           Mark mark, int curRow, int curCol) {
        int counter = 0;
        for (int i = 0; i < 2* Board.WIN_STREAK; i++) {
            if(curRow == row && curCol == col){
                curRow += rowChange;
                curCol += colChange;
                counter++;
                if(counter == Board.WIN_STREAK) return true;

                continue;
            }
            if (board.getMark(curRow, curCol) != mark){
                counter = 0;
            } else {
                counter++;
            }
            if(counter == Board.WIN_STREAK) return true;
            curRow += rowChange;
            curCol += colChange;
        }
        return false;
    }
}
