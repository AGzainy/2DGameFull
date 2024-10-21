package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	ArrayList<String> fileNames = new ArrayList<>();
	ArrayList<String> collisionStatus = new ArrayList<>();
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		//read tile data file
		InputStream is = getClass().getResourceAsStream("/maps/tilesData.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		//getting tiles names and collision info from the file
		String line;
		
		try {
			while((line = br.readLine()) != null) {
				fileNames.add(line);
				collisionStatus.add(br.readLine());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//initialize the tile array based on the fileNames size
		tile = new Tile[fileNames.size()];
		getTileImage();

		//get max world columns
		is = getClass().getResourceAsStream("/maps/FFmap.txt");
		br = new BufferedReader(new InputStreamReader(is));
//		
//		try {
//			String line2 = br.readLine();
//			String maxTile[] = line2.split(",");
//			
//			gp.maxWorldCol = maxTile.length;
//			gp.maxWorldRow = maxTile.length;
			mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
			
//			br.close();
//
//			}catch(IOException e) {
//			System.out.println("Exception");
//		}
//		
		loadMap("/maps/FFmap.txt",0);
		loadMap("/maps/dungeon1.txt",1);
		loadMap("/maps/dungeon2.txt",2);
		
	}
	
	public void getTileImage() {
		
		for(int i = 0; i < fileNames.size(); i++) {
			
			String fileName;
			boolean collision;
			
			//get a file name
			fileName = fileNames.get(i);
			
			//get a collision status
			if(collisionStatus.get(i).equals("true")) {
				collision = true;
			}
			else {
				collision = false;
			}
			setup(i, fileName, collision);
		}
		
//
//			setup(0, "table01",false);
//			
//			setup(1, "table01", true);
//		
//			setup(2,"table01",true);
//			
//			setup(3,"table01",false);
//			
//			setup(4,"table01",true);
//			
//			setup(5,"table01",false);
//			
//			setup(6,"table01",false);			
//			setup(7,"table01",false);
//			
//			setup(8,"table01",true);
//			
//			setup(9,"table01",true);
//			
//			setup(10,"grassO",false);//			
//			setup(11,"wall",true);//			
//			setup(12,"water",true);//			
//      		setup(13,"earth",false);//
//			setup(14,"tree0",true);//
//			setup(15,"sand",false);//
//			setup(16,"grassU",false);//			
//			setup(17,"flowers",false);//			
//			setup(18,"water0",true);//			
//			setup(19,"topWater",true);//			
//			setup(20,"leftsideWater",true);
//			
//			setup(21,"bottomRight",true);
//			
//			setup(22,"topRight",true);
//
//			setup(23,"sandB",false);
//
//			setup(24,"sandT",false);
//
//			setup(25,"sandR",false);
//			
//			setup(26,"sandBR",false);
//			
//			setup(27,"sandTR",false);
//			
//			setup(28,"sandTL",false);
//			
//			setup(29,"sandL",false);
//			
//			setup(30,"sandBL",false);
//			//new
//			setup(31,"rightsideWater",true);
//			setup(32,"downsideWater",true);
//			setup(33,"leftTopCorner",true);
//			setup(34,"rightBottomCorner",true);
//			setup(35,"sandH",false);
//			setup(36,"leftBottomCorner",true);
//			setup(37,"sandM",false);
//			setup(38,"toprRightCorner",true);
//			setup(39,"topLeft",true);
//			setup(40,"bottomLeft",true);
//
//			
//			
//			setup(41,"tree0",true);
//			setup(42,"hut",true);
//			setup(43,"floor01",false);
//			setup(44,"table01",true);


}
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/"+imageName ));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String mapPath, int map) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();//reads a line of text
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(",");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;	
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row ++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if( worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(tile[tileNum].image, screenX, screenY,  null);
			} 
			worldCol++;
		   
			
		    if(worldCol == gp.maxWorldCol) {
		    	worldCol =0;
		    	
		    	worldRow ++;
		    	
		    }
			
		}
		
		
		
		
		//test
//		for(int y=0; y<193;y+=48) {
//		for(int x=0;x<193;x+=48) {
//			if(y<48 || x <48 || (x>144 && y != 96)) {
// 	g2.drawImage(tile[1].image, x, y, gp.tileSize, gp.tileSize,null);
//			}else if(x >47 && x < 192 && y >144) {
//				g2.drawImage(tile[2].image, x, y, gp.tileSize, gp.tileSize,null);
//
//			}else{
//				g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize,null);
//	
//			}
//			
//		}
//		}
		
		
	}
}
