package org.cis120;

import static org.junit.jupiter.api.Assertions.*;

import org.cis120.othello.OthelloLogic;
import org.junit.jupiter.api.*;

public class OthelloTest {

    @Test
    public void testValidFirstMove() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(3, 2);
        o.printGameState();
        assertEquals(1, o.getColor(3, 2));
        assertEquals(1, o.getColor(3, 3));
        assertEquals(1, o.getColor(3, 4));
        assertEquals(1, o.getColor(4, 3));
        assertEquals(2, o.getColor(4, 4));
        assertEquals(2, o.otherColor(3, 2));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testInBoundsInvalidFirstMove() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(7, 7);
        assertEquals(2, o.getColor(3, 3));
        assertEquals(1, o.getColor(3, 4));
        assertEquals(1, o.getColor(4, 3));
        assertEquals(2, o.getColor(4, 4));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testInvalidMoveWherePieceExists() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(3, 3);
        assertEquals(2, o.getColor(3, 3));
        assertEquals(1, o.getColor(3, 4));
        assertEquals(1, o.getColor(4, 3));
        assertEquals(2, o.getColor(4, 4));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testInvalidMoveWhereMoveWasJustMade() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(3, 2);
        o.executeMove(3, 2);
        assertEquals(1, o.getColor(3, 2));
        assertEquals(1, o.getColor(3, 3));
        assertEquals(1, o.getColor(3, 4));
        assertEquals(1, o.getColor(4, 3));
        assertEquals(2, o.getColor(4, 4));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testOutBoundsInvalidFirstMove() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(8, 7);
        assertEquals(2, o.getColor(3, 3));
        assertEquals(1, o.getColor(3, 4));
        assertEquals(1, o.getColor(4, 3));
        assertEquals(2, o.getColor(4, 4));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testTwoValidMoves() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(3, 2);
        o.executeMove(4, 2);
        o.printGameState();
        assertEquals(1, o.getColor(3, 2));
        assertEquals(1, o.getColor(3, 3));
        assertEquals(1, o.getColor(3, 4));
        assertEquals(2, o.getColor(4, 2));
        assertEquals(2, o.getColor(4, 3));
        assertEquals(2, o.getColor(4, 4));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testDiagonalValidMoveMultipleFlips() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(3, 2);
        o.executeMove(4, 2);
        o.executeMove(5, 2);
        assertEquals(1, o.getColor(3, 2));
        assertEquals(1, o.getColor(3, 3));
        assertEquals(1, o.getColor(3, 4));
        assertEquals(1, o.getColor(4, 2));
        assertEquals(1, o.getColor(4, 3));
        assertEquals(2, o.getColor(4, 4));
        assertEquals(1, o.getColor(5, 2));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testGameOverNotOver() {
        OthelloLogic o = new OthelloLogic();
        assertFalse(o.isGameOver());
    }

    @Test
    public void testGameOverIsOver() {
        // OthelloBoard o = new OthelloBoard(new JLabel("Status"));
        OthelloLogic o = new OthelloLogic();
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                o.setSquareColor(row, col, 1);
            }
        }
        assertTrue(o.isGameOver());
    }

    @Test
    public void testInValidMoveMethod() {
        OthelloLogic o = new OthelloLogic();
        assertFalse(o.isValidMove(3, 3, 1));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testValidMoveMethod() {
        OthelloLogic o = new OthelloLogic();
        assertTrue(o.isValidMove(3, 2, 1));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testHasValidMoves() {
        OthelloLogic o = new OthelloLogic();
        assertTrue(o.hasValidMoves(1));
        assertFalse(o.isGameOver());
    }

    @Test
    public void testNoValidMoves() {
        OthelloLogic o = new OthelloLogic();
        o.setSquareColor(3, 4, 2);
        o.setSquareColor(4, 3, 2);
        assertFalse(o.hasValidMoves(1));
        assertTrue(o.isGameOver());
    }

    @Test
    public void testNoValidMovesEndGame() {
        // Shortest Game of 9 Moves
        // Uses isValidMove()
        OthelloLogic o = new OthelloLogic();
        o.executeMove(4, 5);
        o.executeMove(5, 3);
        o.executeMove(4, 2);
        o.executeMove(3, 5);
        o.executeMove(2, 4);
        o.executeMove(5, 5);
        o.executeMove(4, 6);
        o.executeMove(5, 4);
        o.executeMove(6, 4);
        assertFalse(o.hasValidMoves(2));
        assertTrue(o.isGameOver());
    }

    @Test
    public void testColorCountStartBoard() {
        OthelloLogic o = new OthelloLogic();
        int[] arr = { 2, 2 };
        assertArrayEquals(arr, o.getColorCount());
        assertFalse(o.isGameOver());
    }

    @Test
    public void testColorCountAfterInvalidMove() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(8, 8);
        int[] arr = { 2, 2 };
        assertArrayEquals(arr, o.getColorCount());
        assertFalse(o.isGameOver());
    }

    @Test
    public void testColorCountAfterValidMove() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(3, 2);
        int[] arr = { 4, 1 };
        assertArrayEquals(arr, o.getColorCount());
        assertFalse(o.isGameOver());
    }

    @Test
    public void testColorCountEndOfGame() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(4, 5);
        o.executeMove(5, 3);
        o.executeMove(4, 2);
        o.executeMove(3, 5);
        o.executeMove(2, 4);
        o.executeMove(5, 5);
        o.executeMove(4, 6);
        o.executeMove(5, 4);
        o.executeMove(6, 4);
        int[] arr = { 13, 0 };
        assertArrayEquals(arr, o.getColorCount());
        assertTrue(o.isGameOver());
    }

    @Test
    public void testChangePlayerTurn() {
        OthelloLogic o = new OthelloLogic();
        assertEquals(1, o.getPlayerColor());
        o.changePlayerTurn();
        assertEquals(2, o.getPlayerColor());
    }

    @Test
    public void testHistoryStartContainsStartBoard() {
        OthelloLogic o = new OthelloLogic();
        int[][] test = new int[8][8];
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                test[row][col] = 0;
            }
        }
        test[3][3] = 2;
        test[3][4] = 1;
        test[4][3] = 1;
        test[4][4] = 2;
        assertArrayEquals(test, o.getHistory().getFirst());
        assertFalse(o.getHistory().isEmpty());
    }

    @Test
    public void testHistoryAfterInvalidMove() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(7, 7);
        int[][] test = new int[8][8];
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                test[row][col] = 0;
            }
        }
        test[3][3] = 2;
        test[3][4] = 1;
        test[4][3] = 1;
        test[4][4] = 2;
        assertArrayEquals(test, o.getHistory().getFirst());
        assertFalse(o.getHistory().isEmpty());
    }

    @Test
    public void testHistoryAfterMove() {
        OthelloLogic o = new OthelloLogic();
        o.executeMove(3, 2);
        int[][] test = new int[8][8];
        int[][] historyEntry = new int[8][8];
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                test[row][col] = 0;
                historyEntry[row][col] = 0;
            }
        }
        historyEntry[3][3] = 2;
        historyEntry[3][4] = 1;
        historyEntry[4][3] = 1;
        historyEntry[4][4] = 2;
        test[3][2] = 1;
        test[3][3] = 1;
        test[3][4] = 1;
        test[4][3] = 1;
        test[4][4] = 2;
        assertArrayEquals(historyEntry, o.getHistory().getFirst());
        assertArrayEquals(test, o.getHistory().getLast());
        assertFalse(o.getHistory().isEmpty());
    }

    // @Test
    // public void testListOf1CoordinateToModify() {
    // OthelloLogic o = new OthelloLogic();
    // int[] coord = {3,3};
    // assertArrayEquals(coord , o.listOfCoordinatesToModify(3,2,1).getFirst());
    // }
    //
    // @Test
    // public void testListOf2CoordinatesToModify() {
    // OthelloLogic o = new OthelloLogic();
    // o.executeMove(3,2);
    // o.executeMove(4,2);
    // int[] coord = {4,2};
    // int[] coord2 = {4,3};
    // LinkedList<int[]> coordinates = o.listOfCoordinatesToModify(5,2,1);
    // assertArrayEquals(coord, coordinates.getFirst());
    // assertArrayEquals(coord2, coordinates.getLast());
    // assertEquals(2, coordinates.size());
    // }

}
