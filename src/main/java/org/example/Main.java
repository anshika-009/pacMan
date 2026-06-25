package org.example;
import javax.swing.*;

public class Main {
    static void main() {
        int rowCount=21, colCount=19;
        int tileSize=32;
        int boardWidth=colCount*tileSize;
        int boardHeight=rowCount*tileSize;

        JFrame frame= new JFrame("PACMAN");
        //frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcome initial=new welcome();
        frame.add(initial);
        frame.setVisible(true);

        pacMan pacmanGame= new pacMan();
        frame.add(pacmanGame);
        frame.pack();
        pacmanGame.requestFocus();
        frame.setVisible(true);
    }
}
