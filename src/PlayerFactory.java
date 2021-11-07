public class PlayerFactory {
    /**
     *
     * @param PlayerType - String = human/clever/whatever/snartypamts
     * @return - Player object for valid input, null otherwise
     */
    public Player buildPlayer(String PlayerType){
        switch (PlayerType){
            case "human":
                return new HumanPlayer();
            case "clever":
                return new CleverPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "snartypamts":
                return new SnartypamtsPlayer();
        }
        return null;
    }
}
