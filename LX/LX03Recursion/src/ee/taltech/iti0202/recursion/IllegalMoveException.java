package ee.taltech.iti0202.recursion;

/**
 * The type Illegal move exception.
 */
public class IllegalMoveException extends RuntimeException {
    /**
     * Instantiates a new Illegal move exception.
     *
     * @param x the x
     * @param y the y
     */
    public IllegalMoveException(int x, int y) {
        super("There is already a button at (" + x + "; " + y + ")");
    }
}
