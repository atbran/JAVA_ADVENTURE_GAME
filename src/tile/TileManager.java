package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TileManager {
    main.GamePanel gp;
    Tile[] tile;

    public TileManager(main.GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage(){

        try {
            File file = new File("res/floor/grass.png");
            FileInputStream fis = new FileInputStream(file);
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(fis);

            file = new File("res/floor/wall.png");
            fis = new FileInputStream(file);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(fis);

            file = new File("res/floor/water.png");
            fis = new FileInputStream(file);
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(fis);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            g2.drawImage(tile[0].image, x, y, gp.TileSize, gp.TileSize, null);
            col++;
            x+= gp.TileSize;

            if(col==gp.maxScreenCol){
                col = 0;
                x=0;
                row++;
                y += gp.TileSize;
            }

        }
    }

}
