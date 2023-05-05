package ee.taltech.iti0202.recursion;

/**
 * The interface Strategy.
 */
public interface Strategy {

    /**
     * Sets number.
     *
     * @param number the number
     */
    void setNumber(int number);

    /**
     * Move opponent.
     *
     * @param x the x
     * @param y the y
     */
    void moveOpponent(int x, int y);

    /**
     * Gets move.
     *
     * @return the move
     */
    int getMove();
}
