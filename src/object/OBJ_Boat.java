package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boat extends Entity{

	GamePanel gp;
	public static final String objName = "Boat";

	public OBJ_Boat(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		name = objName;
		gotName = "a boat";
		type = type_consumable;
		down1 = setup("/objects/boat",gp.tileSize, gp.tileSize);
		description = "["+ name +"]\na small baot.";
		//price = 50;
		stackable = false;
		
		setDialogue();
	}
	public void setDialogue() {}

	public boolean use(Entity entity) {return false;}

	public void playS() {
		//gp.playSE(2);
	}
}
