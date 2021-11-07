public abstract class SmartPlayerEngine extends HumanPlayer{
    protected boolean markCriticalCell(Board board, Mark mark) {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++)
                if (checkCriticalCell(board, row, col, mark == Mark.O ? Mark.X : Mark.O)) {
                    board.putMark(mark, row, col);
                    return true;
                }
        }
        return false;
    }
    private boolean checkCriticalCell(Board board, int row, int col, Mark mark) {
        if(board.getMark(row,col)!=Mark.BLANK) return false;
        int[] rowDirections = {1, 1, 0, -1};
        int[] colDirections = {0,1, 1, 1};
        int[] rowStart = {row-Board.WIN_STREAK, row-Board.WIN_STREAK, row,row +Board.WIN_STREAK};
        int[] colStart = {col, col -Board.WIN_STREAK, col -Board.WIN_STREAK, col -Board.WIN_STREAK};
        for(int i=0; i<4;i++){
            if(checkPossibleStreakForDirection(board, row, col, rowDirections[i], colDirections[i],
                    mark, rowStart[i], colStart[i])){
                return true;
            }
        }
        return false;

    }


    private static boolean checkPossibleStreakForDirection(Board board, int row, int col, int rowChange, int colChange,
                                                           Mark mark, int curRow, int curCol) {
        int counter = 0;
        for (int i = 0; i < 2* Board.WIN_STREAK; i++) {
            if(curRow == row && curCol == col){
                counter++;
                continue;
            }
            if (board.getMark(row, col) != mark){
                counter = 0;
            } else {
                counter++;
            }
            if(counter==Board.WIN_STREAK) return true;
            curRow += rowChange;
            curCol += colChange;
        }
        return false;
    }

}
