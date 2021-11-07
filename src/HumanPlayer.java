import  java.util.Scanner;

public class HumanPlayer implements Player{
    public HumanPlayer(){}

    /**
     *
     * @param board - Board, the playing board
     * @param mark - Mark, a mark of current player
     */
    public void playTurn(Board board, Mark mark){
        int row, col;
        boolean playedOnBoard = false;
        while(!playedOnBoard){
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            row = num / 10 - 1;
            col = num % 10 - 1;
            playedOnBoard = board.putMark(mark, row, col);
        }
    }


}
