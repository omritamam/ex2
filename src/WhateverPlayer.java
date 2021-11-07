import java.util.Random;

public class WhateverPlayer extends HumanPlayer{
    private Random Rand = new Random();
    @Override
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
