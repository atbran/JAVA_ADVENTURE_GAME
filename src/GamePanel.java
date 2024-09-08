package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen Default Settings
    final int originalTileSize = 16; //16*16 tile, [16 by 16 pixels]
    //scales 16*16 by three so it is legible
    final int scale = 3;

    final int TileSize = originalTileSize * scale; // 48*48 size tile

    //4*3 aspect ratio!
    final int maxScreenCol = 16; //16 columns of tiles
    final int maxScreenRow = 12; //12 rows of tiles
    final int screenWidth = TileSize * maxScreenCol; //768 pixels
    final int screenHeight = TileSize * maxScreenRow; // 576 pixels

    //starts the game's clock
    Thread gameThread;

    public GamePanel() {

        //sets size of this jpanel class
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        //improves performance of the rendering
        this.setDoubleBuffered(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run() {

        while(gameThread != null) {

//            System.out.println("DEBUG: Game is running!");

            // Update info
            // Draw screen

        }

    }
}


