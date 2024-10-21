package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
	
	public static final String objName = "Axe"; 
	
	public OBJ_Axe(GamePanel gp) {
		super(gp);
		
		name = objName;
		gotName = "an axe";
		type = type_axe;
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 3;
		value = 1;
		attackArea.width = 30;
		attackArea.height = 30;
		description = "[" + name+"]\nA rusty WoodCutter's Axe.\nCan cut trees.";
	    price = 75;
	    knockBackPower = 10;
	    motion1_duration = 20;
		motion2_duration = 40;
	}

}
