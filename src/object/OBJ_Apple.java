package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Apple extends Entity{

	GamePanel gp;
	public static final String objName = "Apple";

	public OBJ_Apple(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "an apple";
		type = type_consumable;
		down1 = setup("/objects/apple", gp.tileSize, gp.tileSize);
		description = "["+name+"]\na fruit.";
		price = 25;
		stackable = true;
		
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You got an apple!";
		dialogues[1][0] = "You healed up!";
		
}
public boolean use(Entity entity) {
		
		int objIndex = getDetected(entity, gp.npc, "Witch");
		
	    if(objIndex != 999) {
	    	
	    	for(int i = 0; i< gp.npc[1].length; i++) {
	    		if(gp.npc[gp.currentMap][i].name.equals("Witch")) {
	    			gp.npc[gp.currentMap][i].dialogueSet = 1;
	    			gp.npc[gp.currentMap][i].speak();
	    			break;
	    		}
	    	}
	    	//gp.npc
	    	return true;
	    }
	    else {
	    
	    	gp.player.life ++;
	    	startDialogue(this, 1);
	    	return true;
	    	}
	    }
public void playS() {
	gp.playSE(2);
}
	}

