public class Game {

    Player playerX;
    Player playerO;
    Player[] players;
    Renderer renderer;


    public Game(Player playerX, Player playerO, Renderer renderer){
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        players = new Player[]{playerX, playerO};
    }

    public GameStatus run(){


        return GameStatus.IN_PROGRESS;
    }

    public static void main(String[] args){
        System.out.println("before");
        Board board = new Board();
        Game game = new Game(new Player(), new Player(), new Renderer());
        game.run();
    }



}
