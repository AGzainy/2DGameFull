package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Paint_Red extends Entity{

	GamePanel gp;
	public static final String objName = "Paint Red";

	public OBJ_Paint_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "flower";
		type = type_consumable;
		down1 = setup("/tiles_interactive/flowerR", gp.tileSize, gp.tileSize);
		description = "[Flower]\na red flower.";
		price = 25;
		stackable = false;
		value = 1;
		value2 = 1;
		
		
		setDialogue();
	}
	public void getImage() {	
		down1 = setup("/objects/paintR", gp.tileSize, gp.tileSize);
		gotName = "paint";
		description = "[Paint]\nRed paint.";
		price = 50;
		
}
	public boolean use(Entity entity) {

		if(gp.player.paintb > 0) {	

			gp.player.makePaint(this);
			startDialogue(this, 0);
			return true;
		}
		else {
			if(price > 25) {
				startDialogue(this, 1);}
				else {
					startDialogue(this, 2);
				}
			return false;
		}
	}
	public void setDialogue() {
		dialogues[0][0] = "You uesed the "+name+".";		
		dialogues[1][0] = "I Don't have any other colors \nto mix..";
		dialogues[2][0] = "I wonder what I can do with \nthis flower??";
	}
	public void playS() {
		gp.playSE(1);
	}
}
