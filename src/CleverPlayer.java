import java.util.Random;

public class CleverPlayer extends SmartPlayerEngine {
    private final Random Rand = new Random();

    @Override
    /*
     * play 1 turn on given board and mark by using markCriticalCell of SmartPlayerEngine first, if no critical cell
     * was found, mark by minimum available cell.
     * @param board - Board, the playing board
     * @param mark - Mark, a mark of current player
     */
    public void playTurn(Board board, Mark mark) {
        //check critical cell and mark
        if(markCriticalCell(board, mark)) return;
        //No critical cells - choose randomly
        int row, col;
        boolean playedOnBoard = false;
        while(!playedOnBoard){
            row = Rand.nextInt(Board.SIZE);
            col = Rand.nextInt(Board.SIZE);
            playedOnBoard = board.putMark(mark, row, col);
        }
    }
}
