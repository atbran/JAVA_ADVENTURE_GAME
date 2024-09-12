package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    main.GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(main.GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
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

            file = new File("res/floor/earth.png");
            fis = new FileInputStream(file);
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(fis);

            file = new File("res/floor/tree.png");
            fis = new FileInputStream(file);
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(fis);

            file = new File("res/floor/sand.png");
            fis = new FileInputStream(file);
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(fis);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(){
        try {
//            File file = new File("C:\\Users\\ayden\\IdeaProjects\\2D_Game\\res\\maps\\map_01.txt");
//            InputStream is = new FileInputStream(file);

            //intelliJ's input system is genuinely fucking ass. WHY DO I HAVE TO USE '.GETCLASSLOADER()!?!?!?!??!?!'
              InputStream is = getClass().getClassLoader().getResourceAsStream("maps/world01.txt");

            if (is == null) {
                System.out.println("Map file not found!");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col< gp.maxWorldCol&&row<gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;


        while(col < gp.maxWorldRow && row < gp.maxWorldCol){
            int tileNum = mapTileNum[col][row];

            int worldX = col * gp.TileSize;
            int worldY = row * gp.TileSize;
            int ScreenX = worldX - gp.player.worldX + gp.player.screenX;
            int ScreenY = worldY - gp.player.worldY + gp.player.screenY;
            if(worldX + gp.TileSize> gp.player.worldX - gp.player.screenX && worldX - gp.TileSize < gp.player.worldX + gp.player.screenX
            && worldY + gp.TileSize > gp.player.worldY - gp.player.screenY && worldY - gp.TileSize< gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, ScreenX, ScreenY, gp.TileSize, gp.TileSize, null);
            }
            col++;

            if(col==gp.maxWorldCol){
                col = 0;
                row++;
            }

        }
    }

}
