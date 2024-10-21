package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Diamond extends Entity{
	
	public static final String objName = "Diamond Shield";

	public OBJ_Shield_Diamond(GamePanel gp) {
		super(gp);
		
		name = objName;
		gotName = "a diamond shield!";
		type = type_shield;
		down1 = setup("/objects/shield_diamond", gp.tileSize, gp.tileSize);
		defenseValue = 10;
		life = 50;
		description = "["+ name + "]\nMade of diamonds.\nStronger protection!";
		price = 100;
		
			}

}
