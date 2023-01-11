package org.cis120;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.TreeSet;
import java.util.Collection;

public class Game {
    /**
     * Main method run to start and run the game. Initializes the runnable game
     * class of your choosing and runs it. IMPORTANT: Do NOT delete! You MUST
     * include a main method in your final submission.
     */
    public static void main(String[] args) {
//
//        int [] temp = new int[5];
//        try {
//            System.out.println("Try 1");
//            temp[10] = 1;
//        }
//        catch(IndexOutOfBoundsException e ) {
//            System.out.println("Catch 1");
//            throw new IndexOutOfBoundsException();
//        }
//        System.out.println("Code after reached");

        Runnable oth = new org.cis120.othello.RunOthello();
        SwingUtilities.invokeLater(oth);
    }
}
