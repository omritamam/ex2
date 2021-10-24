import java.util.function.Function;

public class Board{

    public static final int SIZE = 5;
    public static final int WIN_STREAK = 5;

    private Mark[][] _data = new Mark[SIZE][SIZE];
    private int markedCellsCounter = 0;

    public Board(){
        initBoard();
    }
    public boolean putMark(Mark mark, int row, int col) {
        if((row < 0 || row >= SIZE) && (col < 0 || col >= SIZE)){
            return false;
        }
        if(mark != Mark.BLANK && _data[row][col]==Mark.BLANK){
            markedCellsCounter ++;
        }
        if(mark == Mark.BLANK && _data[row][col]!=Mark.BLANK){
            markedCellsCounter --;
        }
        _data[row][col] = mark;
        return true;
    }

    public Mark getMark(int row, int col) {
        //TODO: check if needed
        if ((row < 0 || row >= SIZE) && (col < 0 || col >= SIZE)) {
            return Mark.BLANK;
        }
        return _data[row][col];
    }

    private void initBoard(){
        for(int row = 0 ; row < SIZE ; row++) {
            for (int col = 0; col < SIZE; col++) {
                _data[row][col] = Mark.BLANK;
            }
        }
    }

    private GameStatus checkRowOrCol(int rowOrCol, boolean checkRow){
        Mark firstCellMark;
        int row = 0 , col = 0;
        if(checkRow) {
            firstCellMark = _data[rowOrCol][0];
            row = rowOrCol;
        }
        else {
            firstCellMark = _data[0][rowOrCol];
            col = rowOrCol;
        }

        for (int counter = 0 ; counter < SIZE; counter++){
            if(checkRow){
                col++;
            }
            else {
                row++;
            }
            if(_data[row][col]!=firstCellMark){
                return GameStatus.IN_PROGRESS;
            }
        }
        return matchMarkToGameStatus(firstCellMark);
    }


    private GameStatus checkStatus(){
        // check all rows and cols
        for (int rowOrCol = 0 ; rowOrCol < SIZE; rowOrCol++){
                GameStatus rowStatus = checkRowOrCol(rowOrCol, true);
                GameStatus colStatus = checkRowOrCol(rowOrCol, false);
                if(rowStatus != GameStatus.IN_PROGRESS){
                    return  rowStatus;
                }
                if(colStatus != GameStatus.IN_PROGRESS){
                    return  colStatus;
                }
        }
        //check diagonals
        GameStatus diagonalsStatus = checkDiagonals();
        if(diagonalsStatus==GameStatus.IN_PROGRESS){
            if(markedCellsCounter == SIZE*SIZE){
                return GameStatus.DRAW;
            }
            return GameStatus.IN_PROGRESS;
        }
        return diagonalsStatus;

    }

    private GameStatus checkDiagonals() {
        GameStatus firstDiagonal = checkDiagonal(true);
        GameStatus secondDiagonal = checkDiagonal(false);
        if(firstDiagonal != GameStatus.IN_PROGRESS){
            return firstDiagonal;
        }
        return secondDiagonal;
    }

    private GameStatus checkDiagonal(boolean checkFirstDiagonal) {
        int col;
        Mark firstMark;
        if(checkFirstDiagonal){
            firstMark = _data[0][0];
            col = 0;
        }
        else{
            firstMark = _data[0][SIZE-1];
            col = SIZE-1;
        }

        for (int row = 0 ; row < SIZE; row++){
            if(_data[row][col]!=firstMark){
                return GameStatus.IN_PROGRESS;
            }
            if (checkFirstDiagonal){
                col++;
            }
            else{
                col--;
            }
        }
        return matchMarkToGameStatus(firstMark);
    }

    private GameStatus matchMarkToGameStatus(Mark mark) {
        if (mark == Mark.X) {
            return GameStatus.X_WIN;
        }
        if (mark == Mark.O) {
            return GameStatus.O_WIN;
        }
        return GameStatus.IN_PROGRESS;
    }

}

