package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Wood extends Entity{
	
	GamePanel gp;	
	public static final String objName = "Wood";

	public OBJ_Wood(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "wood";
		type = type_consumable;
		down1 = setup("/objects/wood", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nTree Logs.";
		price = 10;
		stackable = true;
		
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You uesed the "+name+".";
		
		dialogues[1][0] = "Use it to open a door..";
	}
//	public boolean use(Entity entity) {
		
//		int objIndex = getDetected(entity, gp.obj, "Door");
//		
//	    if(objIndex != 999) {
//	    	startDialogue(this, 0);
//	    	gp.playSE(3);
//	    	gp.obj[gp.currentMap][objIndex] = null;
//	    	return true;
//	    }
//	    else {
//	    	startDialogue(this, 1);
//	    	return false;
//	    }
//	}

}
