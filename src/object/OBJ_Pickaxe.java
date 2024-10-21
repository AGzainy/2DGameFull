package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{

public static final String objName = "pickaxe"; 
	
	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		
		name = objName;
		gotName = "a pickaxe";
		type = type_pickaxe;
		down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
		attackValue = 3;
		value = 1;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name+"]\nAn Iron pickaxe.\nCan break stones.";
	    price = 75;
	    knockBackPower = 10;
	    motion1_duration = 10;
		motion2_duration = 20;
	}
}
