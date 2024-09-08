package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        //allows us to close the window properly
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("GAME PLACEHOLDER TITLE");

        main.GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        //window default is the center of the screen
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}