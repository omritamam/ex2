public class CleverPlayer extends HumanPlayer {
    @Override
    public void playTurn(Board board, Mark mark) {
        if(Util.markCriticalCell(board, mark)) return;
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
