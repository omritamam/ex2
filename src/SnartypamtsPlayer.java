public class SnartypamtsPlayer extends SmartPlayerEngine {
    @Override
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
