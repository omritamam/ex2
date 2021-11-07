public class Game {
    private final Player[] players;
    private final Mark[] marks;
    private final Renderer renderer;

    /***
     *
     * @param playerX - Player
     * @param playerO - Player
     * @param renderer - Renderer
     */
    public Game(Player playerX, Player playerO, Renderer renderer){
        this.renderer = renderer;
        players = new Player[] { playerX, playerO };
        marks = new Mark[] { Mark.X, Mark.O };
    }

    /**
     * main method of the game, runs a game for 2 players with 2 marks from instance's props.
     * @return - Mark of the winner, Mark.BLANK for tie
     */
    public Mark run(){
        Board board = new Board();
        int turnsPlayed = 0;
        boolean gameEnded = board.gameEnded();
        renderer.renderBoard(board);

        while (!gameEnded && turnsPlayed < Board.SIZE*Board.SIZE){
            players[turnsPlayed % players.length].playTurn(board, marks[turnsPlayed % players.length]);
            renderer.renderBoard(board);
            turnsPlayed++;
            gameEnded = board.gameEnded();
        }
        return board.getWinner();
    }



}
