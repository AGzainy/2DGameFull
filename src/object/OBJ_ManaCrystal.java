package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity{
	
	GamePanel gp;
	public static final String objName = "Mana Crystal";

	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		value = 1;
		type = type_pickup;
		down1 =setup("/objects/mana", gp.tileSize, gp.tileSize);
		image = setup("/objects/mana", gp.tileSize-5, gp.tileSize-5);
		image2 = setup("/objects/mana_empty", gp.tileSize-5, gp.tileSize-5);
	    price = 500;
	    
	}
public boolean use(Entity entity) {
		
		gp.playSE(2);
		gp.ui.addMessage("Ammo +" + value);
		entity.maxMana += value;
		entity.mana = entity.maxMana;
		
		return true;
	}

}
