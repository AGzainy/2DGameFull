package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword extends Entity{
	
	public static final String objName = "Normal Sword";

	public OBJ_Sword(GamePanel gp) {
		super(gp);
		
		name = objName;
		gotName = "a sword";
		type = type_sword;
		down1 = setup("/objects/sword", gp.tileSize, gp.tileSize);
		attackValue = 2;
		value = 2;
		attackArea.width = 36;
		attackArea.height = 36;
		description = "["+name+"]\nAn old sword.";
		price = 50;
		knockBackPower = 2;
		motion1_duration = 5;
		motion2_duration = 25;
		
	}

	
	

}
