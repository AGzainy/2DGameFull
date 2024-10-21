package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{
	
	GamePanel gp;
	public static final String objName = "Tent";

	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "a tent!";
		type = type_consumable;
		down1 = setup("/objects/tent", gp.tileSize,gp.tileSize);
		description = "[Tent]\nYou can sleep\nat night.";
		price = 100;
		stackable = false;
		
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You can only sleep at night.";
		dialogues[1][0] = "Let me get some rest.";
	}
	public boolean use(Entity entity) {
		
		if(gp.eManager.lighting.dayState == gp.eManager.lighting.night) {
			//startDialogue(this, 1);
		gp.gameState = gp.sleepState;
		gp.player.life = gp.player.maxLife;
		gp.player.getSleepingImage(down1);
		gp.aSetter.setInterActiveTile();
		gp.aSetter.setMonster();
		//true for one time use
		return true;
		}else {
			startDialogue(this, 0);
			return false;
		}
		
		
	}
	public void playS() {
		gp.playSE(15);
	}

}
