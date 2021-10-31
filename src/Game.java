public class Game {
    private final Player[] players;
    private final Mark[] marks;
    private final Renderer renderer;

    public Game(Player playerX, Player playerO, Renderer renderer){
        this.renderer = renderer;
        players = new Player[] { playerX, playerO };
        marks = new Mark[] { Mark.X, Mark.O };
    }

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



    public static void main(String[] args){
        Game game = new Game(new Player(), new Player(), new Renderer());
        game.run();
    }



}
