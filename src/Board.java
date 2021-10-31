
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

    public Mark getMark(int row, int col) {
        //TODO: check if needed
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
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

        for (int iteration = 0 ; iteration < Board.SIZE; iteration++){
            if(getMark(row,col) != mark){
                counter = 0;
            } else{
                counter++;
            }
            if(counter==Board.WIN_STREAK){
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
        for (int rowOrCol = 0 ; rowOrCol < Board.SIZE; rowOrCol++){
            Mark rowStatus = checkRowOrCol(rowOrCol, true, mark);
            Mark colStatus = checkRowOrCol(rowOrCol, false, mark);
            if(rowStatus != Mark.BLANK){
                return  rowStatus;
            }
            if(colStatus != Mark.BLANK){
                return  colStatus;
            }
        }
        //check diagonals
        return checkDiagonals(mark);
    }

    private Mark checkDiagonals( Mark mark) {
        Mark firstDiagonal = checkDiagonal(true, mark);
        Mark secondDiagonal = checkDiagonal(false, mark);
        if(firstDiagonal != Mark.BLANK){
            return firstDiagonal;
        }
        return secondDiagonal;
    }

    private Mark checkDiagonal(boolean checkAscendDiagonal, Mark mark) {
        for(int i =0; i<Board.SIZE; i++){
            int col = i, counter = 0;
            for (int row = 0 ; col < Board.SIZE && col>-1; row++){
               // System.out.println(row+ " " +col);
                if(getMark(row,col) != mark){
                    counter = 0;
                } else {
                    counter++;
                }
                if(counter == Board.WIN_STREAK){
                    return mark;
                }
                if (checkAscendDiagonal){
                    col--;
                } else{
                    col++;
                }
            }
        }

        return Mark.BLANK;
    }

}

