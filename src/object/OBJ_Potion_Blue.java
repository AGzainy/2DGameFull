package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Blue extends Entity{

	GamePanel gp;
	public static final String objName = "Blue Potion";

	public OBJ_Potion_Blue(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		name = objName;
		value = 3;
		gotName = "a potion";
		type = type_consumable;
		down1 = setup("/objects/potion_blue",gp.tileSize, gp.tileSize);
		description = "["+ name +"]\nDrink when low on defense.\nRecovers body's defens by "+value+".";
		price = 50;
		stackable = true;
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = name+" has recovered your defense \nby "+value+".";
	}
	public boolean use(Entity entity) {
	
		startDialogue(this, 0);
		entity.defense += value;
		if(entity.defense > entity.maxDefense) {
			entity.defense = entity.maxDefense;
		}
		
		
		
		return true;
	}
	public void playS() {
		gp.playSE(2);
	}
}
