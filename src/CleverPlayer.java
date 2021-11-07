public class CleverPlayer extends SmartPlayerEngine {
    @Override
    /**
     * play 1 turn on given board and mark by using markCriticalCell of SmartPlayerEngine first, if no critical cell
     * was found, mark by minimum available cell.
     * @param board - Board, the playing board
     * @param mark - Mark, a mark of current player
     */
    public void playTurn(Board board, Mark mark) {
        //check critical cell and mark
        if(markCriticalCell(board, mark)) return;
        //No critical cells
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                if(board.putMark(mark, row, col)){
                    return;
                }
            }
        }
    }


}
