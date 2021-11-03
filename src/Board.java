
public class Board{

    public static final int SIZE = 4;
    public static final int WIN_STREAK = 3;
    private final Mark[][] _data = new Mark[SIZE][SIZE];
    private int _markedCellsCounter = 0;

    /**
     * Default ctor
     */
    public Board(){
        initBoard();
    }

    /**
     *  paints the board at giveen coordinates with given mark
     * @param mark - Mark number
     * @param row - number, valid between [0, SIZE-1]
     * @param col - number, valid between [0, SIZE-1]
     * @return true if space is free and marked as given mark, false otherwise.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if(row < 0 || row >= SIZE || col < 0 || col >= SIZE || _data[row][col]!= Mark.BLANK){
            return false;
        }
        _data[row][col] = mark;
        _markedCellsCounter++;
        return true;
    }

    /**
     *
     * @param row - number, valid between [0, SIZE-1]
     * @param col - number, valid between [0, SIZE-1]
     * @return true if row and col are valid, false otherwise
     */
    private boolean isValidCoordinates(int row, int col){
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    /**
     *
     * @param row - number, valid between [0, SIZE-1]
     * @param col - number, valid between [0, SIZE-1]
     * @return the mark in the given coordinates on the board, for invalid inputs returns Mark.BLANK
     */
    public Mark getMark(int row, int col) {
        //TODO: check if needed
        if (!isValidCoordinates(row, col)) {
            return Mark.BLANK;
        }
        return _data[row][col];
    }

    /**
     * marks all spaces with Mark.BLANK
     */
    private void initBoard(){
        for(int row = 0 ; row < SIZE ; row++) {
            for (int col = 0; col < SIZE; col++) {
                _data[row][col] = Mark.BLANK;
            }
        }
    }

    /**
     *
     * @return true if gameEnded, false otherwise
     */
    public boolean gameEnded(){
        Mark winner = getWinner();
        return winner != Mark.BLANK || _markedCellsCounter == SIZE * SIZE;
    }

    /**
     *
     * @return - mark of the winner, Mark.BLANK for ongoing game or tie
     */
    public Mark getWinner(){
        if (Mark.X == checkWinForMark(Mark.X)) {
            return Mark.X;
        }
        if (Mark.O == checkWinForMark(Mark.O)) {
            return Mark.O;
        }
        return Mark.BLANK;
    }

    /**
     * checks if there is a winning streak at given row or col
     * @param rowOrCol - number, presents the number of the col or row
     * @param checkRow - boolean, true for checking row, false for checking column
     * @param mark - Mark, which mark to check
     * @return - mark id a winning streak found, Mark.Blank otherwise
     */
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

    /**
      * checks a winning streak o board for a given mark
     * @param mark - a mark to check
     * @return - mark if a winning streak found, Mark.Blank otherwise
     */
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

    /**
     * checks a winning streak o board for all possible diagonals
     * @param mark - a mark to check
     * @return - mark if a winning streak found, Mark.Blank otherwise
     */
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

    /**
     *
     * @param row - number, valid between [0, SIZE-1]
     * @param col - number, valid between [0, SIZE-1]
     * @param checkAscendDiagonal - boolean, true for ascend diagonal, false for descend
     * @param mark - a mark to check
     * @return - mark id a winning streak found, Mark.Blank otherwise
     */
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

