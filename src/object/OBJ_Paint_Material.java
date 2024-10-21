package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Paint_Material extends Entity{

	GamePanel gp;	
	public static final String objName = "Paint Material";

	public OBJ_Paint_Material(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		gotName = "paint material";
		type = type_consumable;
		down1 = setup("/objects/paint_material", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nUsed to make paint.";
		price = 25;
		stackable = true;
		
		
		setDialogue();
	}
	public void setDialogue() {}
}
