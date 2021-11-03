
public class Board{

    public static final int SIZE = 4;
    public static final int WIN_STREAK = 3;

    private final Mark[][] _data = new Mark[SIZE][SIZE];

    public Board(){
        initBoard();
    }
    public boolean putMark(Mark mark, int row, int col) {
        if(row < 0 || row >= SIZE || col < 0 || col >= SIZE || _data[row][col]!= Mark.BLANK){
            return false;
        }
        _data[row][col] = mark;
        return true;
    }

    private boolean isValidCoordinates(int row, int col){
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public Mark getMark(int row, int col) {
        //TODO: check if needed
        if (!isValidCoordinates(row, col)) {
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

    public boolean gameEnded(){
        return getWinner() != Mark.BLANK;
    }

    public Mark getWinner(){
        if (Mark.X == checkWinForMark(Mark.X)) {
            return Mark.X;
        }
        if (Mark.O == checkWinForMark(Mark.O)) {
            return Mark.O;
        }
        return Mark.BLANK;
    }




    private Mark checkRowOrCol( int rowOrCol, boolean checkRow, Mark mark){
        int row = 0 , col = 0, counter =0;
        if(checkRow) {
            row = rowOrCol;
        }
        else {
            col = rowOrCol;
        }

        for (int iteration = 0 ; iteration < SIZE; iteration++){
            if(getMark(row,col) != mark){
                counter = 0;
            } else{
                counter++;
            }
            if(counter == WIN_STREAK){
                return mark;
            }
            if(checkRow){
                col++;
            } else {
                row++;
            }
        }
        return Mark.BLANK;
    }


    private Mark checkWinForMark(Mark mark){
        // check all rows and cols
        for (int rowOrCol = 0 ; rowOrCol < SIZE; rowOrCol++){
            Mark rowStatus = checkRowOrCol(rowOrCol, true, mark);
            Mark colStatus = checkRowOrCol(rowOrCol, false, mark);
            if(rowStatus != Mark.BLANK){
                return  rowStatus;
            }
            if(colStatus != Mark.BLANK){
                return  colStatus;
            }
        }
        return checkDiagonals(mark);
    }

    private Mark checkDiagonals(Mark mark) {
        for(int row=0; row<SIZE; row++){
            if(checkDiagonalStartAt(row,0, true, mark) == mark||
                    checkDiagonalStartAt(row,0, false, mark) == mark){
                return mark;
            }
        }
        for(int col=0; col<SIZE; col++){
            if((checkDiagonalStartAt(0,col, false, mark) == mark)||
                    checkDiagonalStartAt(SIZE-1,col, true, mark) == mark){
                return mark;
            }
        }
        return Mark.BLANK;
    }


    private Mark checkDiagonalStartAt(int row, int col, boolean checkAscendDiagonal, Mark mark) {
        int counter = 0;
        while(isValidCoordinates(row,col)){
            if(getMark(row,col) != mark){
                counter = 0;
            } else {
                counter++;
            }
            if(checkAscendDiagonal){
                row--;
                col++;
            }
            else{
                row++;
                col++;
            }
            if(counter == Board.WIN_STREAK){
                return mark;
            }
        }
        return Mark.BLANK;
    }



}

