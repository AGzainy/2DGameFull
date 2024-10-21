package tile_interactive;

import java.awt.Color;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Key;

public class IT_Dirt extends InteractiveTile{

	GamePanel gp;
	

	public IT_Dirt(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles_interactive/dirt", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 3;
		solidArea.x = 18;
		solidArea.y = 18;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 3;
		solidArea.height = 3;
		
	}
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_shovel) {
			
			isCorrectItem = true;
		}

		return isCorrectItem;
	}
	public void playSE() {
		gp.playSE(20);
	}
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_DirtDug(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		
        int i = new Random().nextInt(100)+1;
	
		if(i < 40 && gp.keyLife > 0) {
			dropItem(new OBJ_Key(gp));
			gp.keyLife --;
		}		
		
		return tile;
	}
	
	public Color getParticleColor() {
		Color color = new Color(91, 56, 4);
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
