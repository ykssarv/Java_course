package ee.taltech.iti0202.gomoku.app.player.strategy;

import ee.taltech.iti0202.gomoku.app.GameSession;
import ee.taltech.iti0202.gomoku.app.board.IBoard;
import ee.taltech.iti0202.gomoku.app.board.ILocation;
import ee.taltech.iti0202.gomoku.app.board.Location;
import ee.taltech.iti0202.gomoku.app.board.MyBoard;
import ee.taltech.iti0202.gomoku.app.board.Stone;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Random;

public class CasinoStrategy extends Strategy {

    private static final Random random = new Random();
    private State state;
    private State initialState;
    private int turns;

    public CasinoStrategy() {
        state = null;
        initialState = null;
        turns = 0;
    }

    public void initialize(IBoard board) {
        MyBoard emptyBoard = this.getEmptyBoard(board.getWidth(), board.getHeight());
        initialState = new State(emptyBoard, true);
        //initialState.expand();
    }

    public MyBoard getEmptyBoard(int width, int height) {
        return new MyBoard(width, height, new Stone[height][width]);
    }

    public ILocation getMove(IBoard board, boolean isWhite) {
        MyBoard myBoard = getCopy(board);
        int times = board.getHeight() < 15 ? 4 : 3;
        Map.Entry<Integer, Integer> bestMove;
        if (isWhite) {
            bestMove = getMax(myBoard, times, Integer.MIN_VALUE, Integer.MAX_VALUE);
        } else {
            bestMove = getMin(myBoard, times, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Integer moveLocation = bestMove.getKey();
        System.out.println(moveLocation);
        return new Location(moveLocation / board.getWidth(), moveLocation % board.getWidth());
    }

    public Map.Entry<Integer, Integer> getMax(MyBoard board, int timesLeft, int alpha, int beta) {
        Integer bestMove = 0;
        Integer goodness = Integer.MIN_VALUE;
        int thisAlpha = alpha;
        int thisBeta = beta;
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (!board.isFree(new Location(y, x))) {
                    continue;
                }

                Location move = new Location(y, x);
                board.makeMove(move, Stone.WHITE);

                Integer thisGoodness;
                if (timesLeft == 1) {
                    thisGoodness = board.getGoodness(false);
                } else {
                    Map.Entry<Integer, Integer> worstMove = getMin(board, timesLeft - 1, thisAlpha, thisBeta);
                    thisGoodness = worstMove.getValue();
                }

                board.reverseMove(move);
                if (thisGoodness > goodness) {
                    goodness = thisGoodness;
                    bestMove = y * board.getWidth() + x;
                    if (goodness > thisAlpha) {
                        thisAlpha = goodness;
                    }
                }
                if (thisBeta <= thisAlpha) {
                    break;
                }
            }
        }
        return new AbstractMap.SimpleEntry<>(bestMove, goodness);
    }

    public Map.Entry<Integer, Integer> getMin(MyBoard board, int timesLeft, int alpha, int beta) {
        Integer bestMove = 0;
        Integer goodness = Integer.MAX_VALUE;
        int thisAlpha = alpha;
        int thisBeta = beta;
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (!board.isFree(new Location(y, x))) {
                    continue;
                }

                Location move = new Location(y, x);
                board.makeMove(move, Stone.BLACK);

                Integer thisGoodness;
                if (timesLeft == 1) {
                    thisGoodness = board.getGoodness(true);
                } else {
                    Map.Entry<Integer, Integer> worstMove = getMax(board, timesLeft - 1, thisAlpha, thisBeta);
                    thisGoodness = worstMove.getValue();
                }

                board.reverseMove(move);
                if (thisGoodness < goodness) {
                    goodness = thisGoodness;
                    bestMove = y * board.getWidth() + x;
                    if (goodness < thisBeta) {
                        thisBeta = goodness;
                    }
                }
                if (thisBeta <= thisAlpha) {
                    break;
                }
            }
        }
        return new AbstractMap.SimpleEntry<>(bestMove, goodness);
    }

    public ILocation badGetMove(IBoard board, boolean isWhite) {
        state = new State(getCopy(board), isWhite);
        state.expand(state.getBoard());

        long start = System.currentTimeMillis();
        int simCount = 0;
        turns++;
        while (true) {
            simCount++;
            state.doMove(getCopy(state.getBoard()));
            if (System.currentTimeMillis() - start > 1000) {
                break;
            }
        }
        System.out.println(simCount + " simulations made");
        Integer location = state.getBestMove();
        state = state.getNextState(location);
        return new Location(location / board.getWidth(), location % board.getWidth());
    }

    public MyBoard getCopy(IBoard board) {
        return new MyBoard(board.getWidth(), board.getHeight(), (Stone[][]) board.getMatrix());
    }

    public Integer findMove(IBoard current, MyBoard last) {
        MyBoard currentMyBoard = getCopy(current);
        for (int y = 0; y < current.getHeight(); y++) {
            for (int x = 0; x < current.getWidth(); x++) {
                Location location = new Location(y, x);
                if (last.isFree(location) && !currentMyBoard.isFree(location)) {
                    return y * current.getWidth() + x;
                }
            }
        }
        System.out.println("Serious problems");
        return 0;
    }

    public static boolean simulateRandomMoves(MyBoard board, boolean isWhite, boolean whiteMoves) {
        GameSession.GameState gameState = board.getGameState();
        if (gameState == GameSession.GameState.PLAYING) {
            ILocation location = getRandomLocation(board);
            board.makeMove(location, whiteMoves ? Stone.WHITE : Stone.BLACK);
            return simulateRandomMoves(board, isWhite,!whiteMoves);
        } else if (gameState == GameSession.GameState.WHITE_WON) {
            return isWhite;
        } else if (gameState == GameSession.GameState.BLACK_WON) {
            return !isWhite;
        }
        return false;
    }

    public static ILocation getRandomLocation(MyBoard board) {
        while (true) {
            Location location = new Location(random.nextInt(board.getHeight()), random.nextInt(board.getWidth()));
            if (board.isFree(location)) {
                return location;
            }
        }
    }

    @Override
    public void onGameOver() {
        state = null;
        turns = 0;
    }

    @Override
    public String getName() {
        return "Helena";
    }
}
