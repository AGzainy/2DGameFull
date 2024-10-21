package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Paint_Blue extends Entity{

	GamePanel gp;
	public static final String objName = "Paint Blue";

	public OBJ_Paint_Blue(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "flower";
		type = type_consumable;
		down1 = setup("/tiles_interactive/flowerB", gp.tileSize, gp.tileSize);
		description = "[Flower]\nBlue flower.";
		price = 25;
		stackable = false;
		value = 1;
		value2 = 1;
		
		
		
		setDialogue();
	}
	public void getImage() {	
			down1 = setup("/objects/paintB", gp.tileSize, gp.tileSize);
			gotName = "paint";
			description = "[Paint]\nBlue paint.";
			price = 50;
	}
	public boolean use(Entity entity) {
		
			if(gp.player.paintr > 0) {

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
		dialogues[1][0] = "I don't have any colors to mix..";
		dialogues[2][0] = "I wonder what I can do with \nthis flower??";
	}
	public void playS() {
		gp.playSE(1);
	}
	
}
