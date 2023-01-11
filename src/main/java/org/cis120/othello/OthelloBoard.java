package org.cis120.othello;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.io.IOException;
import javax.swing.*;

public class OthelloBoard extends JPanel {
    private OthelloLogic game; // model for game
    private JLabel status; // current status text

    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;

    // Initializes Othello Board
    public OthelloBoard(JLabel initialStatus) {
        status = initialStatus;
        game = new OthelloLogic();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click Registered");
                Point p = e.getPoint();
                int yIndex = p.y / 100;
                int xIndex = p.x / 100;
                if (game.isValidMove(yIndex, xIndex, game.getPlayerColor())) {
                    game.executeMove(yIndex, xIndex);
                    updateStatus();
                    repaint();
                }
            }
        });

    }

    // Resets OthelloBoard to Start of Game
    public void reset() {
        game.reset();
        game.resetHistory();
        updateStatus();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(
                new Color(
                        0, 150, 0
                )
        );
        g.fillRect(0, 0, 800, 800);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 800, 800);

        g.drawLine(100, 0, 100, 800);
        g.drawLine(200, 0, 200, 800);
        g.drawLine(300, 0, 300, 800);
        g.drawLine(400, 0, 400, 800);
        g.drawLine(500, 0, 500, 800);
        g.drawLine(600, 0, 600, 800);
        g.drawLine(700, 0, 700, 800);

        g.drawLine(0, 100, 800, 100);
        g.drawLine(0, 200, 800, 200);
        g.drawLine(0, 300, 800, 300);
        g.drawLine(0, 400, 800, 400);
        g.drawLine(0, 500, 800, 500);
        g.drawLine(0, 600, 800, 600);
        g.drawLine(0, 700, 800, 700);

        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                int state = game.getColor(row, col);
                if (state == 1) {
                    g.setColor(Color.BLACK);
                    g.fillOval(col * 100, row * 100, 100, 100);
                } else if (state == 2) {
                    g.setColor(Color.WHITE);
                    g.fillOval(col * 100, row * 100, 100, 100);
                }
            }
        }
        game.printGameState();
        System.out.println("");
    }

    private void updateStatus() {
        if (game.getPlayerColor() == 1) {
            status.setText("Player 1's (Black) Turn");
        } else {
            status.setText("Player 2's (White) Turn");
        }
        // If a player has no valid moves
        if (!(game.hasValidMoves(game.getPlayerColor()))) {
            if (game.getPlayerColor() == 1) {
                status.setText("Game Over: Player 1 (Black) has no valid moves");
                game.changeGameOverStatus();
            } else {
                status.setText("Game Over: Player 2 (White) has no valid moves");
                game.changeGameOverStatus();
            }
        }
        if (game.isGameOver()) {
            int numBlack = game.getColorCount()[0];
            int numWhite = game.getColorCount()[1];
            if (numBlack > numWhite) {
                status.setText(
                        "Player 1 (Black) wins!!! Count: Black " + numBlack + " :  White " +
                                numWhite
                );
            } else if (numWhite > numBlack) {
                status.setText(
                        "Player 2 (White) wins!!! Count: Black " + numBlack + " :  White " +
                                numWhite
                );
            } else if (numBlack == numWhite) {
                status.setText(
                        "It's a tie. Count: Black " + numBlack + " :  White " +
                                numWhite
                );
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

    // Writes whose turn it is and the state of the array into a CSV file
    public void saveGameFile() {
        try {
            FileWriter w = new FileWriter("files/savedFile.csv", false);
            BufferedWriter buffW = new BufferedWriter(w);
            buffW.write(Integer.toString(game.getPlayerColor()));
            System.out.print(game.getPlayerColor());
            buffW.newLine();
            System.out.println();
            buffW.newLine();
            System.out.println();
            // for (int row = 0; row <= 7; row++) {
            // for (int col = 0; col <= 7; col++) {
            // buffW.write (Integer.toString(game.getColor(row,col)));
            // if (col != 7) {
            // buffW.write(",");
            // }
            // }
            // buffW.newLine();
            // }
            // buffW.newLine();

            for (int[][] arr : game.getHistory()) {
                for (int row = 0; row <= 7; row++) {
                    for (int col = 0; col <= 7; col++) {
                        System.out.print(arr[row][col]);
                        buffW.write(Integer.toString(arr[row][col]));
                        if (col != 7) {
                            buffW.write(",");
                        }
                    }
                    System.out.println();
                    buffW.newLine();
                }
                if (!(arr.equals(game.getHistory().getLast()))) {
                    buffW.newLine();
                }
            }
            buffW.close();
        } catch (IOException e) {
            System.out.println("IO Exception cannot create file");
        }
    }

    public int[][] readNextEightLines(BufferedReader temp) {
        try {
            int[][] historyArray = new int[8][8];
            String s;
            int newRow = 0;
            while (newRow < 8 && (s = temp.readLine()) != null) {
                String[] rowValues = s.split(",");
                for (int col = 0; col <= 7; col++) {
                    historyArray[newRow][col] = Integer.parseInt(rowValues[col]);
                }
                newRow++;
            }
            return historyArray;
        } catch (IOException e) {
            System.out.println("Invalid History");
            return null;
        }
    }

    public OthelloLogic readGameFile() {
        OthelloLogic o = new OthelloLogic();
        try {
            FileReader r = new FileReader("files/savedFile.csv");
            BufferedReader r1 = new BufferedReader(r);
            int whoseTurn = Integer.parseInt(r1.readLine());
            if (whoseTurn != game.getPlayerColor()) {
                o.changePlayerTurn();
            }
            String line;
            while (r1.readLine() != null) {
                o.addHistory(readNextEightLines(r1));
            }
            o.changeGameBoard(o.getHistory().getLast());
            r1.close();
            return o;
        } catch (IOException e) {
            System.out.println("No Such File");
            return o;
        }
    }

    public OthelloLogic readGameFile(String fileName) {
        OthelloLogic o = new OthelloLogic();
        try {
            FileReader r = new FileReader("files/" + fileName + ".csv");
            BufferedReader r1 = new BufferedReader(r);
            int whoseTurn = Integer.parseInt(r1.readLine());
            if (whoseTurn != game.getPlayerColor()) {
                o.changePlayerTurn();
            }
            String line;
            while (r1.readLine() != null) {
                o.addHistory(readNextEightLines(r1));
            }
            o.changeGameBoard(o.getHistory().getLast());
            r1.close();
            return o;
        } catch (IOException e) {
            System.out.println("No Such File");
            return o;
        }
    }

    public void changeGameState(OthelloLogic o) {
        game = o;
        updateStatus();
        repaint();
    }

    public void undoMove() {
        if (game.undoMove()) {
            updateStatus();
            repaint();
        }
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }

    public int[] getColorCount() {
        return game.getColorCount();
    }

}
