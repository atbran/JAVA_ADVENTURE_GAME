package main;

import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;


public class GamePanel extends JPanel implements Runnable{

    //Screen Default Settings
    final int originalTileSize = 16; //16*16 tile, [16 by 16 pixels]
    //scales 16*16 by three so it is legible
    final int scale = 3;

    public final int TileSize = originalTileSize * scale; // 48*48 size tile

    //4*3 aspect ratio!
    public final int maxScreenCol = 16; //16 columns of tiles
    public final int maxScreenRow = 12; //12 rows of tiles
    public final int screenWidth = TileSize * maxScreenCol; //768 pixels
    public final int screenHeight = TileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    TileManager tileM = new TileManager(this);

    //starts the game's clock
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);



    public GamePanel() {

        //sets size of this jpanel class
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        //improves performance of the rendering
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run() {
        double drawInterval = 1E9/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {


            while(gameThread != null){
                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);

                lastTime = currentTime;

                if(delta >= 1){
                    update();
                    repaint();
                    delta--;
                }
                if(timer >= 1E9){
                    System.out.println("FPS: " + FPS);
                    drawCount = 0;
                    timer = 0;
                }
            }


//            System.out.println("DEBUG: Game is running!");

//            // Update info
//            update();
//            // Draw screen
//            repaint();
        }

    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}


