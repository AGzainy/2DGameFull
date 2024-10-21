package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	
	GamePanel gp;	
	public static final String objName = "Key";

	public OBJ_Key(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "a key";
		type = type_consumable;
		down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nA door key.";
		price = 25;
		stackable = false;
	
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You uesed the "+name+".";
		
		dialogues[1][0] = "Use it to open a door..";
	}
	public boolean use(Entity entity) {
		
		int objIndex = getDetected(entity, gp.obj, "Chain");
		
	    if(objIndex != 999) {
	    	startDialogue(this, 0);
	    	
	    	gp.obj[gp.currentMap][objIndex] = null;
	    	return true;
	    }
	    else {
	    	startDialogue(this, 1);
	    	return false;
	    }
	}
	public void playS() {
		gp.playSE(3);
	}
}
