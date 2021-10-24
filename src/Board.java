import java.util.function.Function;

public class Board{

    public static final int SIZE = 3;
    public static final int WIN_STREAK = 2;

    private Mark[][] _data = new Mark[SIZE][SIZE];

    public Board(){
        initBoard();
    }
    public boolean putMark(Mark mark, int row, int col) {
        if(row < 0 || row >= SIZE || col < 0 || col >= SIZE){
            return false;
        }
        _data[row][col] = mark;
        return true;
    }

    public Mark getMark(int row, int col) {
        //TODO: check if needed
        if ((row < 0 || row >= SIZE) && (col < 0 || col >= SIZE)) {
            return Mark.X;
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


}

