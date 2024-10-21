package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Paint_Purple extends Entity{

	GamePanel gp;
	public static final String objName = "Paint Purple";

	public OBJ_Paint_Purple(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "paint";
		type = type_consumable;
		down1 = setup("/objects/paintPurple", gp.tileSize, gp.tileSize);
		description = "["+name+"]\npurple paint.";
		price = 25;
		stackable = false;
//		value = 1;
//		value2 = 1;
		
		
		setDialogue();
	}
	
	public void setDialogue() {
		dialogues[0][0] = "Thank you so much!!";
		dialogues[0][1] = "I have been looking for this for\na long time..\nWhat can I do for you?";
		dialogues[0][2] = "A boat so I can keep looking\nfor my brother would be of\ngreat help!!";
		dialogues[0][3] = "sure!!";
	}

	public boolean use(Entity entity) {

		int objIndex = getDetected(entity, gp.npc, "Smith");

		if (objIndex != 999) {

			for (int i = 0; i < gp.npc[1].length; i++) {
				if (gp.npc[gp.currentMap][i].name.equals("Smith")) {
					startDialogue(this,0);
					gp.player.inventory.add(new OBJ_Boat(gp));
					break;
				}
			}
			gp.playSE(2);
			return true;
		} else {
			return false;
		}
	}
		
}
