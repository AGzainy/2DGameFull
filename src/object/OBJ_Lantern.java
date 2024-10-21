package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{
	
	public static final String objName = "Lantern";

	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		
		name = objName;
		gotName = "a lantern";
		type = type_light;
		down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
		description = "[Lantern]\nIlluminates your\nsurroundings.";
		price = 150;
		lightRadius = 350;
		
	}

}
