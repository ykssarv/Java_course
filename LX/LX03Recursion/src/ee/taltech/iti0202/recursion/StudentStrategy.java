package ee.taltech.iti0202.recursion;

/**
 * The type Student strategy.
 */
public class StudentStrategy implements Strategy {

    /**
     * The constant SIZE.
     */
    public static final int SIZE = 3;
    /**
     * The constant INITIAL.
     */
    public static final int INITIAL = 5;
    public static final int MINUS_ONE = -1;
    private int number;
    private Board board;

    /**
     * Instantiates a new Student strategy.
     */
    public StudentStrategy() {
        this.board = new Board();
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void moveOpponent(int x, int y) {
        this.board.move(x, y);

    }

    @Override
    public int getMove() {
        Board copiedBoard = new Board(board);
        int move = getBestMove(copiedBoard);
        this.board.move(move / SIZE, move % SIZE);
        return move;
    }

    /**
     * Gets best move.
     *
     * @param board the board
     * @return the best move
     */
    public int getBestMove(Board board) {
        int bestMove = 0;
        int bestValue = -INITIAL;
        for (int i = 0; i < SIZE * SIZE; i++) {
            // System.out.println("Checking " + i);
            if (!board.isLegal(i)) {
                continue;
            }
            int x = i / SIZE;
            int y = i % SIZE;
            Board thisBoard = new Board(board);
            thisBoard.move(x, y);
            int thisValue = getMinValue(thisBoard);
            if (thisValue > bestValue) {
                bestValue = thisValue;
                bestMove = i;
            }
        }
        return bestMove;
    }

    /**
     * Gets min value.
     *
     * @param board the board
     * @return the min value
     */
    public int getMinValue(Board board) {
        if (board.isFull()) {
            if (board.getWinner() == number) {
                return 1;
            }
            if (board.getWinner() == 0) {
                return 0;
            }
            return MINUS_ONE;
        }
        int worstValue = INITIAL;
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (!board.isLegal(i)) {
                continue;
            }
            int x = i / SIZE;
            int y = i % SIZE;
            Board thisBoard = new Board(board);
            thisBoard.move(x, y);
            int thisValue = getMaxValue(thisBoard);
            if (thisValue < worstValue) {
                worstValue = thisValue;
            }
            // board.unMove(x, y);
        }
        return worstValue;
    }

    /**
     * Gets max value.
     *
     * @param board the board
     * @return the max value
     */
    public int getMaxValue(Board board) {
        if (board.isFull()) {
            if (board.getWinner() == number) {
                return 1;
            }
            if (board.getWinner() == 0) {
                return 0;
            }
            return MINUS_ONE;
        }
        int bestValue = -INITIAL;
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (!board.isLegal(i)) {
                continue;
            }
            int x = i / SIZE;
            int y = i % SIZE;
            Board thisBoard = new Board(board);
            thisBoard.move(x, y);
            int thisValue = getMinValue(thisBoard);
            if (thisValue > bestValue) {
                bestValue = thisValue;
            }
            // board.unMove(x, y);
        }
        return bestValue;
    }
}
