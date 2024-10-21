package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Paint_Red;

public class IT_Flower_Red extends InteractiveTile{

	GamePanel gp;	

	public IT_Flower_Red(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles_interactive/flowerR", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 1;
		solidArea.x = 18;
		solidArea.y = 18;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 1;
		solidArea.height = 1;
	}
	public boolean isCorrectItem(Entity entity) {
		
		return true;
	}
	public void playSE() {
		gp.playSE(21);
	}
	public InteractiveTile getDestroyedForm() {
		//InteractiveTile tile = new IT_DirtDug(gp, worldX/gp.tileSize, worldY/gp.tileSize);
	
		dropItem(new OBJ_Paint_Red(gp));
		
		return null;
	}
	
	public Color getParticleColor() {
		Color color = new Color(19, 151, 24);
		return color;
	}
	public int getParticleSize() {
		int size = 6; // pixels
		return size;
	}
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
