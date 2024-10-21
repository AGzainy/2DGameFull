package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Shield;
import object.OBJ_Shovel;
import object.OBJ_Sword;

public class NPC_Smith extends Entity{

public NPC_Smith(GamePanel gp) {
		
		super(gp);
		
		name = "Smith";
		direction = "down";
		speed = 0;
		solidArea.x =8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX =  solidArea.x;
		solidAreaDefaultY =  solidArea.y;
		type = type_maker;
		
		getImage();
		setDialogue();
		setItem();
		
		
	}
	public void getImage() {
		
		up1 = setup("/npc/smith", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/smith_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/smith", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/smith_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/smith", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/smith_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/smith", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/smith_2", gp.tileSize, gp.tileSize);
}
	public void setDialogue() {
		
		dialogues[0][0] = "Hello kid.\nCan I help you with anything?";
		dialogues[1][0] = "Come again..";
		dialogues[2][0] = "You don't have enough material.";
		dialogues[3][0] = "You must bring me wood..";
		dialogues[4][0] = "Your inventory is full!";
		
		
	}
	public void setItem() {
		
		inventory.add(new OBJ_Sword(gp));
		inventory.add(new OBJ_Shield(gp));
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Shovel(gp));
		
	}
	public void speak() {
		
		facePlayer();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
