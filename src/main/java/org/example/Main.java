package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int rowCount = 21, colCount = 19;
            int tileSize = 32;
            int boardWidth = colCount * tileSize;
            int boardHeight = rowCount * tileSize;

            JFrame frame = new JFrame("PACMAN");
            frame.setSize(boardWidth, boardHeight);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(boardWidth, boardHeight));

            pacMan pacmanGame = new pacMan();
            pacmanGame.setBounds(0, 0, boardWidth, boardHeight);
            layeredPane.add(pacmanGame, JLayeredPane.DEFAULT_LAYER);

            int welcomeWidth = 9 * tileSize;   // 288
            int welcomeHeight = 10 * tileSize; // 320

            welcomePage welcome = new welcomePage(() -> {
                layeredPane.remove(0); // remove welcome panel (it's on top, index 0)
                layeredPane.revalidate();
                layeredPane.repaint();
                pacmanGame.requestFocusInWindow();
                pacmanGame.startGame();
            });

            int x = (boardWidth - welcomeWidth) / 2;
            int y = (boardHeight - welcomeHeight) / 2;
            welcome.setBounds(x, y, welcomeWidth, welcomeHeight);
            layeredPane.add(welcome, JLayeredPane.PALETTE_LAYER);

            frame.add(layeredPane);
            frame.pack();
            frame.setVisible(true);
        });
    }


}