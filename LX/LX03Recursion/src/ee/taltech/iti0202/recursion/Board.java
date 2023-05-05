package ee.taltech.iti0202.recursion;

/**
 * The type Board.
 */
public class Board {
    /**
     * The constant SIZE.
     */
    public static final int SIZE = 3;
    /**
     * The constant TWO.
     */
    public static final int TWO = 2;
    private int[][] board;
    private int nextMove;
    private int moveCounter;

    /**
     * Instantiates a new Board.
     */
    public Board() {
        board = new int[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                board[x][y] = 0;
            }
        }
        nextMove = 1;
        moveCounter = 0;
    }

    /**
     * Instantiates a new Board.
     *
     * @param board the board
     */
    public Board(Board board) {
        this.board = new int[SIZE][SIZE];
        int[][] otherBoard = board.getBoard();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                this.board[x][y] = otherBoard[x][y];
            }
        }
        nextMove = board.getNextMove();
        moveCounter = board.getMoveCounter();
    }

    /**
     * Count int.
     *
     * @return the int
     */
    public int count() {
        int total = 0;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (board[x][y] != 0) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Gets move counter.
     *
     * @return the move counter
     */
    public int getMoveCounter() {
        return moveCounter;
    }

    /**
     * Is legal boolean.
     *
     * @param move the move
     * @return the boolean
     */
    public boolean isLegal(int move) {
        return this.board[move / SIZE][move % SIZE] == 0;
    }

    /**
     * Is legal boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isLegal(int x, int y) {
        return this.board[x][y] == 0;
    }

    /**
     * Get board int [ ] [ ].
     *
     * @return the int [ ] [ ]
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Gets next move.
     *
     * @return the next move
     */
    public int getNextMove() {
        return nextMove;
    }

    /**
     * To str string.
     *
     * @return the string
     */
    public String toStr() {
        StringBuilder string = new StringBuilder();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                string.append(board[x][y] == 1 ? "X" : (board[x][y] == TWO ? "O" : "_"));
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Move.
     *
     * @param x the x
     * @param y the y
     */
    public void move(int x, int y) {
        if (board[x][y] != 0) {
            throw new IllegalMoveException(x, y);
        }
        board[x][y] = nextMove;
        nextMove = nextMove == 1 ? TWO : 1;
        moveCounter += 1;
    }

    /**
     * Un move.
     *
     * @param x the x
     * @param y the y
     */
    public void unMove(int x, int y) {
        board[x][y] = 0;
        nextMove = nextMove == 1 ? TWO : 1;
        moveCounter -= 1;
    }

    /**
     * Is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        return moveCounter >= SIZE * SIZE;
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public int getWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][TWO]) {
                return board[i][0];
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[TWO][i]) {
                return board[0][i];
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[TWO][TWO]) {
            return board[1][1];
        }
        if (board[TWO][0] != 0 && board[TWO][0] == board[1][1] && board[1][1] == board[0][TWO]) {
            return board[1][1];
        }
        return 0;
    }
}
