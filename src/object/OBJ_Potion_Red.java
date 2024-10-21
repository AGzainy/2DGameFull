package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{
	
	GamePanel gp;
	public static final String objName = "Red Potion";

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		name = objName;
		value = 5;
		gotName = "a potion";
		type = type_consumable;
		down1 = setup("/objects/potion_red",gp.tileSize, gp.tileSize);
		description = "["+ name +"]\nDrink when low on health.\nHeals life by "+value+".";
		price = 25;
		stackable = true;
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = name+" has recovered your life by "+value+".";
	}
	public boolean use(Entity entity) {
		
		startDialogue(this, 0);
		entity.life += value;
		
		
		return true;
	}
	public void playS() {
		gp.playSE(2);
	}

}
