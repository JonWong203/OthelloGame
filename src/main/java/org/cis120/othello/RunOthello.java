package org.cis120.othello;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RunOthello implements Runnable {

    public void run() {

        // Main Window Frame
        final JFrame frame = new JFrame("Jon's Othello Game");
        frame.setLocation(500, 200);

        // Status Panel
        final JPanel statusPanel = new JPanel();
        frame.add(statusPanel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        statusPanel.add(status);

        // Game board
        final OthelloBoard gameBoard = new OthelloBoard(status);
        frame.add(gameBoard, BorderLayout.CENTER);

        // Panel at the top with buttons
        final JPanel saveReadPanel = new JPanel();
        frame.add(saveReadPanel, BorderLayout.NORTH);

        final JFrame instructionsFrame = new JFrame("Game Rules and Instructions");
        instructionsFrame.setPreferredSize(new Dimension(400, 700));
        instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        instructionsFrame.setLocation(100, 150);
        instructionsFrame.setLayout(new BorderLayout());
        JTextArea explanationText = new JTextArea();
        explanationText.setEditable(false);
        explanationText.setLineWrap(true);
        explanationText.setWrapStyleWord(true);
        explanationText.setFont(new Font("Calibri", Font.ITALIC, 18));
        explanationText.setText(
                "Welcome to Othello, also known as Reversi. \n \n" +
                        "The aim of the game is to control the board with any many pieces of " +
                        "your color as possible."
                        +
                        " \n " +
                        "\n" + "Rules \n " +
                        "   - 2 players take turns and make moves against one another. \n" +
                        "   - A move is considered valid if and only if it the color that " +
                        "is placed sandwiches "
                        +
                        "pieces of another color in any direction, causing the sandwiched " +
                        "pieces to flip colors. \n"
                        +
                        "   - The game ends when a player has no more valid moves. \n" +
                        "   - When this happens, the player with more pieces of their color " +
                        "on the board wins. \n \n"
                        +
                        "How to Make a Move: \n" +
                        "   - Click the square where you want to place your piece. \n \n" +
                        "Interesting Features \n" +
                        "   - Save Feature and Load Feature: You can save the current game " +
                        "so if your friend is"
                        +
                        " losing and " +
                        "makes an excuse to leave, you can simply load your old game" +
                        " state and destroy him next "
                        +
                        "time. \n" +
                        "   - Undo Feature: If you made a blunder and your friend is feeling" +
                        " scrumptious, "
                        +
                        "you can undo your last move."
        );
        instructionsFrame.add(explanationText);
        instructionsFrame.pack();

        JButton instructions = new JButton("Game Rules");
        instructions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                instructionsFrame.setVisible(true);
            }
        });
        saveReadPanel.add(instructions);

        JButton saveButton = new JButton("Save Game");
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameBoard.saveGameFile();
            }
        });
        saveReadPanel.add(saveButton);

        JButton readButton = new JButton("Load Game");
        readButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameBoard.changeGameState(gameBoard.readGameFile());
            }
        });
        saveReadPanel.add(readButton);

        JButton undoButton = new JButton("Undo Move");
        undoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameBoard.undoMove();
            }
        });
        saveReadPanel.add(undoButton);

        JButton resetButton = new JButton("Reset Game");
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameBoard.reset();
            }
        });
        saveReadPanel.add(resetButton);

        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
