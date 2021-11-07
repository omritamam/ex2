import java.util.Random;

public class WhateverPlayer extends HumanPlayer{
    private Random Rand = new Random();
    @Override
    /**
     * play 1 turn on given board and mark by user input
     * @param board - Board, the playing board
     * @param mark - Mark, a mark of current player
     */
    public void playTurn(Board board, Mark mark) {
        int row, col;
        boolean playedOnBoard = false;
        while(!playedOnBoard){
            row = Rand.nextInt(Board.SIZE);
            col = Rand.nextInt(Board.SIZE);
            playedOnBoard = board.putMark(mark, row, col);
        }
    }
}
