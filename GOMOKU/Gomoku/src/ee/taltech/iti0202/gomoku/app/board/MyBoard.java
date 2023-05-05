package ee.taltech.iti0202.gomoku.app.board;

import ee.taltech.iti0202.gomoku.app.GameSession;
import ee.taltech.iti0202.gomoku.app.exceptions.LocationOccupiedException;

import java.util.Objects;

public class MyBoard implements IBoard {

    private static final int SQUARES_IN_LINE_FOR_WIN = 5;

    private int width;
    private int height;
    private int goodness;

    private Stone[][] matrix;

    private static final int[] inRowGoodness = { 0, 1, 10, 200, 3000, 25000 };

    private int[] whiteRows = { 0, 0, 0, 0, 0, 0 };
    private int[] blackRows = { 0, 0, 0, 0, 0, 0 };

    private int turnAdvantage;

    private ILocation lastMove;

    public MyBoard(int width, int height, Stone[][] matrix) {
        this.width = width;
        this.height = height;

        this.matrix = matrix;
        lastMove = null;
        goodness = calcGoodness();
        turnAdvantage = matrix.length < 15 ? 3 : 3;
    }

    public void makeMove(ILocation location, Stone stone) {
        matrix[location.getY()][location.getX()] = stone;
        lastMove = location;
        updateRowAmounts(location.getY(), location.getX(), true);
    }

    public void updateRowAmounts(int y, int x, boolean moveMade) {
        rDiagonalMoveGoodness(y, x, moveMade);
        lDiagonalMoveGoodness(y, x, moveMade);
        rightwardMoveGoodness(y, x, moveMade);
        upwardMoveGoodness(y, x, moveMade);
    }

    public void directionMoveAmountChange(int y, int x, int dy, int dx, boolean madeMove) {
        int soFar = 0;
        int whites = 0;
        int blacks = 0;
        for (int i = -4; i < 5; i++) {
            int thisY = y + i * dy;
            int thisX = x + i * dx;
            if (thisX < 0 || thisY < 0 || thisX >= width || thisY >= height) {
                continue;
            }
            if (matrix[thisY][thisX] == Stone.WHITE) {
                whites++;
            }
            if (matrix[thisY][thisX] == Stone.BLACK) {
                blacks++;
            }

            soFar++;
            if (soFar > 5) {
                if (matrix[thisY - 5 * dy][thisX - 5 * dx] == Stone.WHITE) {
                    whites--;
                }
                if (matrix[thisY - 5 * dy][thisX - 5 * dx] == Stone.BLACK) {
                    blacks--;
                }
                soFar--;
            }
            if (soFar == 5) {
                int prevWhites = whites - (matrix[y][x] == Stone.WHITE ? 1 : 0);
                int prevBlacks = whites - (matrix[y][x] == Stone.BLACK ? 1 : 0);

                changeRowAmounts(prevWhites, prevBlacks, !madeMove);
                changeRowAmounts(whites, blacks, madeMove);
            }
        }
    }

    public void changeRowAmounts(int whites, int blacks, boolean add) {
        if (whites > 0 && blacks > 0) {
            return;
        }
        if (blacks > 0) {
            blackRows[blacks] += add ? 1 : -1;
            return;
        }
        whiteRows[whites] += add ? 1 : -1;
    }

    public void rDiagonalMoveGoodness(int y, int x, boolean moveMade) {
        int dx = 1;
        int dy = 1;
        directionMoveAmountChange(y, x, dy, dx, moveMade);
    }

    public void lDiagonalMoveGoodness(int y, int x, boolean moveMade) {
        int dx = -1;
        int dy = 1;
        directionMoveAmountChange(y, x, dy, dx, moveMade);
    }

    public void rightwardMoveGoodness(int y, int x, boolean moveMade) {
        int dx = 1;
        int dy = 0;
        directionMoveAmountChange(y, x, dy, dx, moveMade);
    }

    public void upwardMoveGoodness(int y, int x, boolean moveMade) {
        int dx = 0;
        int dy = 1;
        directionMoveAmountChange(y, x, dy, dx, moveMade);
    }

    public void reverseMove(ILocation location) {
        if (!isFree(location) && location.getY() >= 0 && location.getX() >= 0) {
            updateRowAmounts(location.getY(), location.getX(), false);
            matrix[location.getY()][location.getX()] = null;
        }
    }

    public GameSession.GameState oldGetGameState() {
        boolean hasFree = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != null) {
                    if (downward(i, j) || rightward(i, j) || lDiagonal(i, j) || rDiagonal(i, j)) {
                        return matrix[i][j] == Stone.WHITE ? GameSession.GameState.WHITE_WON :
                            GameSession.GameState.BLACK_WON;
                    }
                } else {
                    hasFree = true;
                }
            }
        }

        return hasFree ? GameSession.GameState.PLAYING : GameSession.GameState.DRAW;
    }

    public int getGoodness(boolean whiteToMove) {
        int total = 0;
        for (int i = 0; i < 6; i++) {
            total += (whiteToMove ? turnAdvantage : 1) * inRowGoodness[i] * whiteRows[i];
            total -= (whiteToMove ? 1 : turnAdvantage) * inRowGoodness[i] * blackRows[i];
        }
        return total;
    }

    public int calcGoodness() {
        int goodness = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                goodness += downwardGoodness(i, j)
                    + rightwardGoodness(i, j)
                    + lDiagonalGoodness(i, j)
                    + rDiagonalGoodness(i, j);
            }
        }
        return goodness;
    }

    public GameSession.GameState getGameState() {
        return oldGetGameState();
    }

    public GameSession.GameState badGetGameState() {
        if (lastMove == null) {
            return oldGetGameState();
        }
        Stone moveStone = matrix[lastMove.getY()][lastMove.getX()];

        int upRight = -1;
        for (int x = lastMove.getX(), y = lastMove.getY(); x < width && y < height; x++, y++) {
            if (moveStone == matrix[y][x]) {
                upRight++;
            } else {
                break;
            }
        }
        for (int x = lastMove.getX(), y = lastMove.getY(); x >= 0 && y >= 0; x--, y--) {
            if (moveStone == matrix[y][x]) {
                upRight++;
            } else {
                break;
            }
        }
        if (upRight >= SQUARES_IN_LINE_FOR_WIN) {
            return moveStone == Stone.WHITE ? GameSession.GameState.WHITE_WON :
                GameSession.GameState.BLACK_WON;
        }

        int downRight = -1;
        for (int x = lastMove.getX(), y = lastMove.getY(); x < width && y >= 0; x++, y--) {
            if (moveStone == matrix[y][x]) {
                downRight++;
            } else {
                break;
            }
        }
        for (int x = lastMove.getX(), y = lastMove.getY(); x >= 0 && y < height; x--, y++) {
            if (moveStone == matrix[y][x]) {
                downRight++;
            } else {
                break;
            }
        }
        if (downRight >= SQUARES_IN_LINE_FOR_WIN) {
            return moveStone == Stone.WHITE ? GameSession.GameState.WHITE_WON :
                GameSession.GameState.BLACK_WON;
        }

        int right = -1;
        for (int x = lastMove.getX(), y = lastMove.getY(); x < width; x++) {
            if (moveStone == matrix[y][x]) {
                right++;
            } else {
                break;
            }
        }
        for (int x = lastMove.getX(), y = lastMove.getY(); x >= 0; x--) {
            if (moveStone == matrix[y][x]) {
                right++;
            } else {
                break;
            }
        }
        if (right >= SQUARES_IN_LINE_FOR_WIN) {
            return moveStone == Stone.WHITE ? GameSession.GameState.WHITE_WON :
                GameSession.GameState.BLACK_WON;
        }

        int up = -1;
        for (int x = lastMove.getX(), y = lastMove.getY(); y < height; y++) {
            if (moveStone == matrix[y][x]) {
                up++;
            } else {
                break;
            }
        }
        for (int x = lastMove.getX(), y = lastMove.getY(); y >= 0; y--) {
            if (moveStone == matrix[y][x]) {
                up++;
            } else {
                break;
            }
        }
        if (up >= SQUARES_IN_LINE_FOR_WIN) {
            return moveStone == Stone.WHITE ? GameSession.GameState.WHITE_WON :
                GameSession.GameState.BLACK_WON;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != null) {
                    return GameSession.GameState.PLAYING;
                }
            }
        }
        return GameSession.GameState.DRAW;
    }

    public boolean isFree(ILocation location) {
        return location.getY() >= 0 && location.getX() >= 0 && matrix[location.getY()][location.getX()] == null;
    }

    public Stone[][] getMatrix() {
        return java.util.Arrays.stream(matrix)
            .map(Stone[]::clone)
            .toArray($ -> matrix.clone());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int rDiagonalValue(int r, int c) {
        Stone sq = matrix[r][c];
        int w = 0;

        for (int x = c, y = r; x < width && y < height; x++, y++) {
            if (sq == matrix[y][x]) {
                w++;
            } else {
                break;
            }
        }

        return w;
    }

    private int lDiagonalValue(int r, int c) {
        Stone sq = matrix[r][c];
        int w = 0;

        for (int x = c, y = r; x >= 0 && y < height; x--, y++) {
            if (sq == matrix[y][x]) {
                w++;
            } else {
                break;
            }
        }

        return w;
    }

    private int rightwardValue(int r, int c) {
        Stone sq = matrix[r][c];
        int w = 0;

        for (int i = c; i < width; i++) {
            if (sq == matrix[r][i]) {
                w++;
            } else {
                break;
            }
        }

        return w;
    }

    private int downwardValue(int r, int c) {
        Stone sq = matrix[r][c];
        int w = 0;

        for (int i = r; i < height; i++) {
            if (sq == matrix[i][c]) {
                w++;
            } else {
                break;
            }
        }

        return w;
    }

    private int rDiagonalGoodness(int r, int c) {
        int whites = 0;
        int blacks = 0;

        for (int x = c, y = r, i = 0; x < width && y < height && i < 5; x++, y++, i++) {
            if (matrix[y][x] == Stone.WHITE) {
                whites++;
            } else if (matrix[y][x] == Stone.BLACK) {
                blacks += 1;
            }
        }
        changeRowAmounts(whites, blacks, true);
        if (blacks > 0 && whites > 0) {
            return 0;
        }
        if (blacks > 0) {
            return - inRowGoodness[blacks];
        }
        return inRowGoodness[whites];
    }

    private int lDiagonalGoodness(int r, int c) {
        int whites = 0;
        int blacks = 0;

        for (int x = c, y = r, i = 0; x >= 0 && y < height && i < 5; x--, y++, i++) {
            if (matrix[y][x] == Stone.WHITE) {
                whites++;
            } else if (matrix[y][x] == Stone.BLACK) {
                blacks += 1;
            }
        }
        changeRowAmounts(whites, blacks, true);
        if (blacks > 0 && whites > 0) {
            return 0;
        }
        if (blacks > 0) {
            return - inRowGoodness[blacks];
        }
        return inRowGoodness[whites];
    }

    private int rightwardGoodness(int r, int c) {
        int whites = 0;
        int blacks = 0;

        int y = r;
        for (int x = c, i = 0; x < width && i < 5; x++, i++) {
            if (matrix[y][x] == Stone.WHITE) {
                whites++;
            } else if (matrix[y][x] == Stone.BLACK) {
                blacks += 1;
            }
        }
        changeRowAmounts(whites, blacks, true);
        if (blacks > 0 && whites > 0) {
            return 0;
        }
        if (blacks > 0) {
            return - inRowGoodness[blacks];
        }
        return inRowGoodness[whites];
    }

    private int downwardGoodness(int r, int c) {
        int whites = 0;
        int blacks = 0;

        int x = c;
        for (int y = r, i = 0; y < height && i < 5; y++, i++) {
            if (matrix[y][x] == Stone.WHITE) {
                whites++;
            } else if (matrix[y][x] == Stone.BLACK) {
                blacks++;
            }
        }
        changeRowAmounts(whites, blacks, true);
        if (blacks > 0 && whites > 0) {
            return 0;
        }
        if (blacks > 0) {
            return - inRowGoodness[blacks];
        }
        return inRowGoodness[whites];
    }

    private boolean rDiagonal(int r, int c) {
        return rDiagonalValue(r, c) >= SQUARES_IN_LINE_FOR_WIN;
    }

    private boolean lDiagonal(int r, int c) {
        return lDiagonalValue(r, c) >= SQUARES_IN_LINE_FOR_WIN;
    }

    private boolean rightward(int r, int c) {
        return rightwardValue(r, c) >= SQUARES_IN_LINE_FOR_WIN;
    }

    private boolean downward(int r, int c) {
        return downwardValue(r, c) >= SQUARES_IN_LINE_FOR_WIN;
    }

}
