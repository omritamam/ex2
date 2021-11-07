public class SnartypamtsPlayer extends SmartPlayerEngine {
    @Override
    /**
     * play 1 turn on given board and mark using markCriticalCell of SmartPlayerEngine,
     * id no crirical cell was found, mark from the midle of the board to outside.
     * @param board - Board, the playing board
     * @param mark - Mark, a mark of current player
     */
    public void playTurn(Board board, Mark mark) {
        //check critical cells, if marked - return
        if(markCriticalCell(board, mark)) return;

        // no critical cell, mark for the center outside
        int layerNum = 0;
        while(!markAtLayerNumber(board, mark,layerNum)){
            layerNum++;
        }

    }


    private boolean markAtLayerNumber(Board board, Mark mark, int layerNum) {
        if(markAtRowOrCol(board,  mark,  Board.SIZE/2-layerNum,  Board.SIZE/2-layerNum,  2*layerNum+1, true)) return true;
        if(markAtRowOrCol(board,  mark,  Board.SIZE/2+layerNum,  Board.SIZE/2-layerNum,  2*layerNum+1, true)) return true;
        if(markAtRowOrCol(board,  mark,  Board.SIZE/2-layerNum,  Board.SIZE/2-layerNum,  2*layerNum+1, false)) return true;
        return markAtRowOrCol(board, mark, Board.SIZE / 2 - layerNum, Board.SIZE / 2 + layerNum, 2 * layerNum + 1, false);
    }

    private boolean markAtRowOrCol(Board board, Mark mark, int row, int col, int distance, boolean rowOrCol) {
        boolean isPlayed;
        for (int i = 0; i < distance; i++) {
            {
                isPlayed = board.putMark(mark, col, row);
                if (isPlayed) {
                    return true;
                }
                if(rowOrCol) {
                    col++;
                }
                else {
                    row++;
                }
            }
        }
        return false;
    }



}
