package org.cis120.othello;

import java.util.LinkedList;

public class OthelloLogic {
    private int[][] board;
    private int playerTurn;
    private boolean gameOver;
    private static final int EMPTY = 0;
    private static final int BLACK = 1;
    private static final int WHITE = 2;
    private LinkedList<int[][]> history = new LinkedList<>();

    public OthelloLogic() {
        reset();
    }

    // Resets game state to start a new game;
    public void reset() {
        board = new int[8][8];
        int[][] historyEntry = new int[8][8];
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                board[row][col] = EMPTY;
                historyEntry[row][col] = EMPTY;
            }
        }
        board[3][3] = WHITE;
        board[3][4] = BLACK;
        board[4][3] = BLACK;
        board[4][4] = WHITE;
        playerTurn = 1;
        gameOver = false;
        historyEntry[3][3] = WHITE;
        historyEntry[3][4] = BLACK;
        historyEntry[4][3] = BLACK;
        historyEntry[4][4] = WHITE;
        history.clear();
        history.add(historyEntry);
    }

    private boolean inBounds(int y, int x) {
        return (y < 8 && y >= 0 && x < 8 && x >= 0);
    }

    // isValidMove()
    public boolean isValidMove(int y, int x, int whoseTurn) {
        if (inBounds(y, x)) {
            return (listOfCoordinatesToModify(y, x, whoseTurn).size() != 0);
        } else {
            return false;
        }
    }

    public boolean hasValidMoves(int whoseTurn) {
        boolean hasValid = false;
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (isValidMove(row, col, whoseTurn)) {
                    hasValid = true;
                }
            }
        }
        return hasValid;
    }

    // executeMove()
    public void executeMove(int y, int x) {
        if (isValidMove(y, x, playerTurn)) {
            LinkedList<int[]> moves = listOfCoordinatesToModify(y, x, playerTurn);
            for (int[] coordinate : moves) {
                System.out.println("reached");
                int yCoord = coordinate[0];
                int xCoord = coordinate[1];
                board[yCoord][xCoord] = playerTurn;
            }
            board[y][x] = playerTurn;
            changePlayerTurn();
            int[][] historyArray = new int[board.length][];
            for (int row = 0; row <= 7; row++) {
                historyArray[row] = board[row].clone();
            }
            history.add(historyArray);
        }
    }

    private LinkedList<int[]> listOfCoordinatesToModify(int y, int x, int whoseTurn) {
        LinkedList<int[]> list = new LinkedList<>();
        if (board[y][x] == EMPTY) {
            int color = whoseTurn;
            for (int verChange = -1; verChange <= 1; verChange++) {
                for (int horChange = -1; horChange <= 1; horChange++) {
                    if (verChange == 0 && horChange == 0) {
                        continue;
                    }
                    boolean hasSameColor = false;
                    int newY = y + verChange;
                    int newX = x + horChange;
                    LinkedList<int[]> temp = new LinkedList<>();
                    while (inBounds(newY, newX) && !(hasSameColor)
                            && (board[newY][newX] == otherColor(whoseTurn))) {
                        temp.add(new int[] { newY, newX });
                        newY += verChange;
                        newX += horChange;
                        if (inBounds(newY, newX)) {
                            if (board[newY][newX] == color) {
                                hasSameColor = true;
                            }
                        }
                    }
                    if (!hasSameColor) {
                        temp = null;
                    } else {
                        list.addAll(temp);
                    }
                }
            }
            return list;
        }
        return list;
    }

    public int[] getColorCount() {
        int black = 0;
        int white = 0;
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (board[row][col] == 1) {
                    black += 1;
                } else if (board[row][col] == 2) {
                    white += 1;
                }
            }
        }
        return new int[] { black, white };
    }

    public void changeGameOverStatus() {
        gameOver = !gameOver;
    }

    public boolean isGameOver() {
        if (hasValidMoves(playerTurn)) {
            gameOver = false;
        } else {
            gameOver = true;
        }
        return gameOver;
    }

    public void changePlayerTurn() {
        if (playerTurn == 1) {
            playerTurn = 2;
        } else {
            playerTurn = 1;
        }
    }

    public int getColor(int y, int x) {
        return board[y][x];
    }

    public int otherColor(int y, int x) {
        if (board[y][x] == BLACK) {
            return WHITE;
        } else {
            return BLACK;
        }
    }

    public int otherColor(int color) {
        if (color == BLACK) {
            return WHITE;
        } else {
            return BLACK;
        }
    }

    public int getPlayerColor() {
        if (playerTurn == 1) {
            return BLACK;
        } else {
            return WHITE;
        }
    }

    private boolean player1Turn() {
        return (playerTurn == 1);
    }

    public void setSquareColor(int y, int x, int color) {
        board[y][x] = color;
    }

    public void printGameState() {
        System.out.println("\n\n Turn" + playerTurn + ": \n");
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                System.out.print(board[row][col]);
                if (col < 7) {
                    System.out.print(" | ");
                }
            }
            if (row < 7) {
                System.out.print("\n");
            }
        }
    }

    public LinkedList<int[][]> getHistory() {
        LinkedList<int[][]> hist = new LinkedList<>();
        hist.addAll(history);
        return hist;
    }

    public void addHistory(int[][] arr) {
        history.add(arr);
    }

    public void resetHistory() {
        history.clear();
    }

    public boolean undoMove() {
        if (history.size() > 1) {
            history.removeLast();
            changePlayerTurn();
            System.out.println("undo Successful");
            for (int row = 0; row <= 7; row++) {
                for (int col = 0; col <= 7; col++) {
                    board[row][col] = history.getLast()[row][col];
                }
            }
            if (isGameOver()) {
                gameOver = !gameOver;
            }
            return true;
        } else {
            return false;
        }
    }

    public void changeGameBoard(int[][] arr) {
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                board[row][col] = arr[row][col];
            }
        }
    }

}
