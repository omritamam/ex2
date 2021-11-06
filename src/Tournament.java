public class Tournament {

    private int rounds;
    private final Renderer renderer;
    private final Player[] players;

    public Tournament(int rounds, Renderer renderer, Player[] players){
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = players;
    }
    public void playTournament(){
        for(int i=0; i< rounds; i++){
            Game currnetGame = new Game(players[0], players[1], renderer);
        }
    }
    public static void main(String[] args){
        Game game = new Game(new HumanPlayer(), new HumanPlayer(), new ConsoleRenderer());
        game.run();
    }

}
