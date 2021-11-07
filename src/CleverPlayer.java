public class CleverPlayer extends SmartPlayerEngine {
    @Override
    public void playTurn(Board board, Mark mark) {

        //check critical cells
        if(markCriticalCell(board, mark)) return;

        // no critical cell, mark for the center outside
        int layerNum = 0;
        while(!playLayer(board, mark,layerNum)){
            layerNum++;
        }

    }


    private boolean playLayer(Board board, Mark mark,int layerNum) {
        if(playRowOrCol(board,  mark,  Board.SIZE/2-layerNum,  Board.SIZE/2-layerNum,  2*layerNum+1, true)) return true;
        if(playRowOrCol(board,  mark,  Board.SIZE/2+layerNum,  Board.SIZE/2-layerNum,  2*layerNum+1, true)) return true;
        if(playRowOrCol(board,  mark,  Board.SIZE/2-layerNum,  Board.SIZE/2-layerNum,  2*layerNum+1, false)) return true;
        return playRowOrCol(board, mark, Board.SIZE / 2 - layerNum, Board.SIZE / 2 + layerNum, 2 * layerNum + 1, false);
    }

    private boolean playRowOrCol(Board board, Mark mark,  int row, int col, int distance, boolean rowOrCol) {
        boolean isPlayed;
        for (int i = 0; i < distance; i++) {
            {
                isPlayed = board.putMark(mark, col, row);
            }
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
        return false;
    }



}
