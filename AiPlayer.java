import java.util.*;

/**
 * This is the AiPlayer class. It simulates a minimax player for the max connect
 * four game. The constructor essentially does nothing.
 * 
 *
 *
 */

public class AiPlayer {

    int depth;

    public AiPlayer(int d) {
        this.depth = d;
    }

    /**
     * This method plays a piece randomly on the board
     * 
     * @param currentGame The GameBoard object that is currently being used to play
     *                    the game.
     * @return an integer indicating which column the AiPlayer would like to play
     *         in.
     */
    public int findBestPlay(GameBoard gameBoard) {
        int playChoice = maxconnect4.playColumn;
        if (gameBoard.getCurrentTurn() == 1) {
            int v = Integer.MAX_VALUE;
            for (int i = 0; i < 7; i++) {
                if (gameBoard.isValidPlay(i)) {
                    GameBoard successor = new GameBoard(gameBoard.getGameBoard());
                    successor.playPiece(i);
                    int maxValue = getMax(successor, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (v > maxValue) {
                        playChoice = i;
                        v = maxValue;
                    }
                }

            }
        } else {
            int v = Integer.MIN_VALUE;
            for (int i = 0; i < 7; i++) {
                if (gameBoard.isValidPlay(i)) {
                    GameBoard successor = new GameBoard(gameBoard.getGameBoard());
                    successor.playPiece(i);
                    int minVal = getMin(successor, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (v < minVal) {
                        playChoice = i;
                        v = minVal;
                    }
                }
            }
        }
        return playChoice;
    }

    // calculate the max and set alpha
    public int getMax(GameBoard game, int depth, int alpha, int beta) {
        if (!game.checkIfBoardFull() && depth > 0) {
            int v = Integer.MIN_VALUE;
            int i = 0;
            while (i < 7) {
                if (game.isValidPlay(i)) {
                    GameBoard successor = new GameBoard(game.getGameBoard());
                    successor.playPiece(i);
                    int minValue = getMin(successor, depth - 1, alpha, beta);
                    if (v < minValue) {
                        v = minValue;
                    }
                    if (v >= beta) {
                        return v;
                    }
                    if (alpha < v) {
                        alpha = v;
                    }
                }
                i++;
            }
            return v;
        } else {
            return evalFun(game);
        }
    }

    // calculate the min value and set beta
    public int getMin(GameBoard game, int depth, int alpha, int beta) {
        if (!game.checkIfBoardFull() && depth > 0) {
            int v = Integer.MAX_VALUE;
            int i = 0;
            while (i < 7) {
                if (game.isValidPlay(i)) {
                    GameBoard successor = new GameBoard(game.getGameBoard());
                    successor.playPiece(i);
                    int minValue = getMax(successor, depth - 1, alpha, beta);
                    if (v > minValue) {
                        v = minValue;
                    }
                    if (v <= minValue) {
                        return v;
                    }
                    if (beta > v) {
                        beta = v;
                    }
                }
                i++;
            }
            return v;
        } else {
            return evalFun(game);
        }
    }

    // eval function if board full than score of 2 - score of 1 otherwise
    // calculate the no of 4 , 3 and 2 in a row times the required weight and add it
    private int evalFun(GameBoard board) {
        int result = 0;
        if (board.checkIfBoardFull()) {
            if ((board.getScore(2) - board.getScore(1)) > 0) {
                return Integer.MAX_VALUE;
            } else if ((board.getScore(2) - board.getScore(1)) == 0) {
                return 0;
            } else {
                return Integer.MIN_VALUE;
            }
        } else {
            result = (((board.getScore(2) * 1000) + (board.getThreeCount(2) * 50) + (board.getTwoCount(2) * 10))
                    - ((board.getScore(1) * 1000) + (board.getThreeCount(1) * 50) + (board.getTwoCount(1) * 10)));
        }
        return result;
    }

}