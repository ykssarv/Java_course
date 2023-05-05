package ee.taltech.iti0202.recursion;

/**
 * The type Game.
 */
public class Game {

    /**
     * The constant TWO.
     */
    public static final int TWO = 2;
    /**
     * The constant SIZE.
     */
    public static final int SIZE = 3;
    private Strategy[] players;
    private Board board;

    /**
     * Instantiates a new Game.
     *
     * @param playerA the player a
     * @param playerB the player b
     */
    public Game(Strategy playerA, Strategy playerB) {
        this.players = new Strategy[TWO];
        this.players[0] = playerA;
        this.players[0].setNumber(1);
        this.players[1] = playerB;
        this.players[1].setNumber(TWO);
        board = new Board();
    }

    /**
     * Play int.
     *
     * @return the int
     */
    public int play() {
        for (int i = 0; i < SIZE * SIZE; i++) {
            int move = this.players[i % TWO].getMove();
            System.out.println(move);
            board.move(move / SIZE, move % SIZE);
            System.out.println(board.toStr());
            this.players[(i + 1) % TWO].moveOpponent(move / SIZE, move % SIZE);
            if (board.getWinner() != 0) {
                return board.getWinner();
            }
        }
        return 0;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Strategy b = new RandomStrategy();
        Strategy a = new StudentStrategy();
        Game game = new Game(a, b);
        System.out.println(game.play());
    }
}
