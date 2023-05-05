package ee.taltech.iti0202.recursion;
import java.util.Random;

/**
 * The type Random strategy.
 */
public class RandomStrategy implements Strategy {

    /**
     * The constant SIZE.
     */
    public static final int SIZE = 3;
    private int number;
    private Board board;
    private Random random;

    /**
     * Instantiates a new Random strategy.
     */
    public RandomStrategy() {
        board = new Board();
        random = new Random();
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void moveOpponent(int x, int y) {
        board.move(x, y);
    }

    @Override
    public int getMove() {
        while (true) {
            int move = random.nextInt(SIZE * SIZE);
            if (board.getBoard()[move / SIZE][move % SIZE] == 0) {
                board.move(move / SIZE, move % SIZE);
                return move;
            }
        }
    }
}
