package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pear extends Entity{

	GamePanel gp;
	public static final String objName = "Pear";

	public OBJ_Pear(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "a pear";
		type = type_consumable;
		down1 = setup("/objects/pear", gp.tileSize, gp.tileSize);
		description = "["+name+"]\na fruit.";
		price = 50;
		stackable = true;
		
		
		
		setDialogue();
	}
	public void setDialogue() {
			dialogues[0][0] = "You got a rare fruit!";
			dialogues[1][0] = "You healed up!";
	}
	public boolean use(Entity entity) {
		
		entity.life = entity.maxLife;
		startDialogue(this, 1);
		
		return true;
	}
	public void playS() {
		gp.playSE(2);
	}
	
}
