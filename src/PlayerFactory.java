public class PlayerFactory {
    public Player buildPlayer(String PlayerType){
        switch (PlayerType){
            case "human":
                return new HumanPlayer();
            case "clever":
                return new CleverPlayer();
            case "whatever":
                return new WhateverPlayer();
        }
        return new HumanPlayer();
    }
}
