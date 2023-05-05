package ee.taltech.iti0202.gomoku.app.player.strategy;

import ee.taltech.iti0202.gomoku.app.board.IBoard;
import ee.taltech.iti0202.gomoku.app.board.Location;
import ee.taltech.iti0202.gomoku.app.board.MyBoard;
import ee.taltech.iti0202.gomoku.app.board.Stone;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static ee.taltech.iti0202.gomoku.app.player.strategy.CasinoStrategy.simulateRandomMoves;

public class State {

    private static final double C = Math.sqrt(2);
    private static final int EXPAND_AT = 26;

    private MyBoard board;
    private boolean whiteToMove;
    private int winAmount;
    private int totalAmount;
    private boolean canExpand;
    private Map<Integer, State> nextStates = null;

    private static int expansions = 0;

    public State(MyBoard board, boolean whiteToMove) {
        this.board = board;
        this.whiteToMove = whiteToMove;
        this.winAmount = 0;
        this.totalAmount = 1;
        this.canExpand = true;
    }

    public void expand(MyBoard board) {
        expansions += 1;
        nextStates = new HashMap<>();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                Location location = new Location(y, x);
                if (!board.isFree(location)) {
                    continue;
                }
                Integer locationKey = location.getY() * board.getWidth() + location.getX();
                // nextBoard.makeMove(location, whiteToMove ? Stone.WHITE : Stone.BLACK);
                State nextState = new State(board, !whiteToMove);
                nextStates.put(locationKey, nextState);
            }
        }
        if (nextStates.size() == 0) {
            nextStates = null;
            canExpand = false;
        }
    }

    public boolean doMove(MyBoard board) {
        totalAmount += 1;
        boolean won;
        if (nextStates == null) {
            won = simulateRandomMoves(board, whiteToMove, whiteToMove);
            if (totalAmount >= EXPAND_AT - board.getWidth() && canExpand) {
                expand(board);
            }
            return won;
        } else {
            Integer move = chooseRandomMove();
            board.makeMove(new Location(move / board.getWidth(), move % board.getWidth()), whiteToMove ? Stone.WHITE : Stone.BLACK);
            won = nextStates.get(move).doMove(board);
        }
        winAmount += (won ? 1 : 0);
        return !won;
    }

    public Integer chooseRandomMove() {
        Integer bestKey = 0;
        double bestGoodness = - Double.MAX_VALUE;
        for (Map.Entry<Integer, State> entry : nextStates.entrySet()) {
            State state = entry.getValue();
            double goodness = state.getWinAmount() / (double) state.getTotalAmount() + C * Math.sqrt(Math.log(totalAmount) / (double) state.getTotalAmount());
            if (Double.isNaN(goodness)) {
                System.out.println("Is none again");
            }
            if (goodness > bestGoodness) {
                bestGoodness = goodness;
                bestKey = entry.getKey();
            }
        }
        return bestKey;
    }

    public State getNextState(Integer location) {
        return nextStates.get(location);
    }

    public MyBoard getBoard() {
        return board;
    }

    public MyBoard getCopy() {
        return new MyBoard(board.getWidth(), board.getHeight(), board.getMatrix());
    }

    public int getWinAmount() {
        return winAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Integer getBestMove() {
        if (nextStates.size() == 0) {
            System.out.println("No possible move");
        }
        return nextStates.entrySet().stream()
            .max(Comparator.comparing(x -> x.getValue().getTotalAmount()))
            .get().getKey();
    }
}
