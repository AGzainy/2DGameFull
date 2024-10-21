package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shovel extends Entity{
	
	public static final String objName = "Shovel";

	public OBJ_Shovel(GamePanel gp) {
		super(gp);
		
		name = objName;
		gotName = "a shovel";
		type = type_shovel;
		down1 = setup("/objects/shovel", gp.tileSize, gp.tileSize);
		attackValue = 1;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name+"]\nAn Iron shovel.\nUse it do dig dirt.";
	    price = 75;
	    knockBackPower = 5;
	    motion1_duration = 20;
		motion2_duration = 40;
		value = 3;
	}

}
