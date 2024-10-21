package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Paint_Yellow extends Entity{
	
	GamePanel gp;
	public static final String objName = "Paint Yellow";

	public OBJ_Paint_Yellow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "flower";
		type = type_consumable;
		down1 = setup("/tiles_interactive/flowerY", gp.tileSize, gp.tileSize);
		description = "[flower]\nYellow flower.";
		price = 25;
		stackable = true;
		value = 1;
		value2 = 1;
		
		
		setDialogue();
	}
	public boolean use(Entity entity) {
		
		if(price > 25) {
			startDialogue(this, 0);}
			else {
				startDialogue(this, 1);
			}
		return false;
	}
	public void getImage() {	
		down1 = setup("/objects/paintY", gp.tileSize, gp.tileSize);
		gotName = "paint";
		description = "[Paint]\nYellow paint.";
		price = 50;
}
	public void setDialogue() {
		dialogues[0][0] = "I can't mix this color..";
		dialogues[1][0] = "I wonder what I can do with \nthis flower??";
	}
}
