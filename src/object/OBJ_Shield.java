package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield extends Entity{
	
	public static final String objName = "Shield";

	public OBJ_Shield(GamePanel gp) {
		super(gp);
		
		name = objName;
		gotName = "a shield";
		type = type_shield;
		down1 = setup("/objects/shield", gp.tileSize, gp.tileSize);
		defenseValue = 3;
		description = "["+name+"]\nA Wooden shield.";
		life = 10;
		price = 50;
		value = 1;
		
	}

}
