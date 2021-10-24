public class Game {
    private final Player[] players;
    private final Mark[] marks;
    private final Renderer renderer;

    public Game(Player playerX, Player playerO, Renderer renderer){
        this.renderer = renderer;
        players = new Player[] { playerX, playerO };
        marks = new Mark[] { Mark.X, Mark.O };
    }

    public GameStatus run(){
        Board board = new Board();
        int turnsPlayed = 0;
        GameStatus gameStatus = checkGameStatus(turnsPlayed, board);
        renderer.renderBoard(board);

        while (gameStatus == GameStatus.IN_PROGRESS){
            //renderer.renderBoard(board);
            players[turnsPlayed % players.length].playTurn(board, marks[turnsPlayed % players.length]);
            renderer.renderBoard(board);
            turnsPlayed++;
            gameStatus = checkGameStatus(turnsPlayed, board);
        }
        System.out.println(gameStatus);
        return GameStatus.IN_PROGRESS;
    }



    private GameStatus checkRowOrCol(Board board, int rowOrCol, boolean checkRow, Mark mark){
        int row = 0 , col = 0, counter =0;
        if(checkRow) {
            row = rowOrCol;
        }
        else {
            col = rowOrCol;
        }

        for (int iteration = 0 ; iteration < Board.SIZE; iteration++){
            if(board.getMark(row,col) != mark){
                counter = 0;
            } else{
                counter++;
            }
            if(counter==Board.WIN_STREAK){
                return matchMarkToGameStatus(mark);
            }
            if(checkRow){
                col++;
            } else {
                row++;
            }
        }
        return GameStatus.IN_PROGRESS;
    }

    private GameStatus checkGameStatus(int turnsPlayed,Board board) {
        if (GameStatus.X_WIN == checkWinForMark(board,Mark.X)) {
            return GameStatus.X_WIN;
        }
        if (GameStatus.O_WIN == checkWinForMark(board,Mark.O)) {
            return GameStatus.O_WIN;
        }
        if(turnsPlayed == Board.SIZE*Board.SIZE){
            return GameStatus.DRAW;
        }
        return GameStatus.IN_PROGRESS;
    }


    private GameStatus checkWinForMark(Board board, Mark mark){
        // check all rows and cols
        for (int rowOrCol = 0 ; rowOrCol < Board.SIZE; rowOrCol++){
            GameStatus rowStatus = checkRowOrCol(board, rowOrCol, true, mark);
            GameStatus colStatus = checkRowOrCol(board, rowOrCol, false, mark);
            if(rowStatus != GameStatus.IN_PROGRESS){
                return  rowStatus;
            }
            if(colStatus != GameStatus.IN_PROGRESS){
                return  colStatus;
            }
        }
        //check diagonals
        return checkDiagonals(board, mark);
    }

    private GameStatus checkDiagonals(Board board, Mark mark) {
        GameStatus firstDiagonal = checkDiagonal(board, true, mark);
        GameStatus secondDiagonal = checkDiagonal(board, false, mark);
        if(firstDiagonal != GameStatus.IN_PROGRESS){
            return firstDiagonal;
        }
        return secondDiagonal;
    }

    private GameStatus checkDiagonal(Board board, boolean checkFirstDiagonal, Mark mark) {
        int col, counter =0;
        if(checkFirstDiagonal){
            col = 0;
        }
        else{
            col = Board.SIZE-1;
        }

        for (int row = 0 ; row < Board.SIZE; row++){
            if(board.getMark(row,col) != mark){
                counter = 0;
            } else {
                counter++;
            }
            if(counter == Board.WIN_STREAK){
                return matchMarkToGameStatus(mark);
            }
            if (checkFirstDiagonal){
                col++;
            } else{
                col--;
            }
        }
        return GameStatus.IN_PROGRESS;
    }

    private GameStatus matchMarkToGameStatus(Mark mark) {
        switch (mark) {
            case X:
                return GameStatus.X_WIN;
            case O:
                return GameStatus.O_WIN;
        }
        return GameStatus.IN_PROGRESS;
    }


    public static void main(String[] args){
        Game game = new Game(new Player(), new Player(), new Renderer());
        game.run();
    }



}
