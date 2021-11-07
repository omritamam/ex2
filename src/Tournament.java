import static java.lang.Integer.parseInt;

public class Tournament {

    private final int rounds;
    private final Renderer renderer;
    private final Player[] players;

    public Tournament(int rounds, Renderer renderer, Player[] players){
        this.rounds = rounds;
        this.renderer = renderer;
        this.players = players;
    }
    public void playTournament(){
        int player1Wins =0, player2Wins = 0, ties = 0;
        for(int i=0; i< rounds; i++){
            Game game;
            game = new Game(players[i%2], players[i%2==0? 1: 0], renderer);
            Mark winner = game.run();
            switch (winner){
                case BLANK:
                    ties++;
                    break;
                case X:
                    if (i%2==0) player1Wins++;
                    else player2Wins++;
                    break;
                case O:
                    if (i%2==0) player2Wins++;
                    else player1Wins++;
            }
            System.out.println(player1Wins + " " + player2Wins + " " + ties);
        }
    }
    public static void main(String[] args){
        PlayerFactory playerFactory = new PlayerFactory();
        RendererFactory rendererFactory = new RendererFactory();
        Tournament tournament = new Tournament(parseInt(args[0]),
                                                rendererFactory.buildRenderer(args[1]),
                                                new Player[]{
                                                        playerFactory.buildPlayer(args[2]),
                                                        playerFactory.buildPlayer(args[3])});
        tournament.playTournament();
    }

}
